package com.urise.webapp.storage.serializer;


import com.urise.webapp.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            // TODO implements sections
            dos.writeInt(r.getSections().size());
            for (Map.Entry<SectionType, AbstractSection> section : r.getSections().entrySet()) {
                SectionType type = section.getKey();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> {
                        TextSection textSection = (TextSection) section.getValue();
                        dos.writeUTF(textSection.getContent());
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        ListSection listSection = (ListSection) section.getValue();
                        dos.writeInt(listSection.getStrings().size());
                        for (String string : listSection.getStrings()) {
                            dos.writeUTF(string);
                        }
                    }
                    case EXPERIENCE, EDUCATION -> {
                        CompanySection companySection = (CompanySection) section.getValue();
                        dos.writeInt(companySection.getCompany().size());
                        for (Company company : companySection.getCompany()) {
                            dos.writeUTF(company.getTitle());
                            String website = company.getWebsite();
                            dos.writeUTF(website == null ? "" : website);
                            dos.writeInt(company.getPeriods().size());
                            for (Period period : company.getPeriods()) {
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                String description = period.getDescription();
                                dos.writeUTF(description == null ? "" : description);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            // TODO implements sections
            int sectionSize = dis.readInt();
            for (int i = 0; i < sectionSize; i++) {
                SectionType type = SectionType.valueOf(dis.readUTF());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> resume.addSection(type, new TextSection(dis.readUTF()));
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        int stringCount = dis.readInt();
                        List<String> strings = new ArrayList<>();
                        for (int j = 0; j < stringCount; j++) {
                            strings.add(dis.readUTF());
                        }
                        resume.addSection(type, new ListSection(strings));
                    }
                    case EXPERIENCE, EDUCATION -> {
                        int organizationCount = dis.readInt();
                        List<Company> companies = new ArrayList<>();
                        for (int j = 0; j < organizationCount; j++) {
                            String title = dis.readUTF();
                            String website = dis.readUTF();
                            int periodCount = dis.readInt();
                            List<Period> periods = new ArrayList<>();
                            for (int k = 0; k < periodCount; k++) {
                                String titlePeriod = dis.readUTF();
                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                LocalDate endDate = LocalDate.parse(dis.readUTF());
                                String description = dis.readUTF();
                                periods.add(new Period(titlePeriod, startDate, endDate, description));
                            }
                            companies.add(new Company(title, website, periods));
                        }
                        resume.addSection(type, new CompanySection(companies));
                    }
                }
            }
            return resume;
        }
    }
}