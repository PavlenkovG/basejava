package com.urise.webapp.storage.serializer;


import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            writeWithException(r.getContacts().entrySet(), dos, (contact -> {
                dos.writeUTF(contact.getKey().name());
                dos.writeUTF(contact.getValue());
            }));
            writeWithException(r.getSections().entrySet(), dos, (section -> {
                SectionType type = section.getKey();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> {
                        TextSection textSection = (TextSection) section.getValue();
                        dos.writeUTF(textSection.getContent());
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        ListSection listSection = (ListSection) section.getValue();
                        writeWithException(listSection.getStrings(), dos, dos::writeUTF);
                    }
                    case EXPERIENCE, EDUCATION -> {
                        CompanySection companySection = (CompanySection) section.getValue();
                        writeWithException(companySection.getCompany(), dos, (company -> {
                            dos.writeUTF(company.getTitle());
                            String website = company.getWebsite();
                            dos.writeUTF(website == null ? "" : website);
                            writeWithException(company.getPeriods(), dos, (period -> {
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                String description = period.getDescription();
                                dos.writeUTF(description == null ? "" : description);
                            }));
                        }));
                    }
                }
            }));
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

    private interface myConsumer<T> {
        void accept(T t) throws IOException;
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, myConsumer<T> consumer) throws
            IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            consumer.accept(element);
        }
    }

}