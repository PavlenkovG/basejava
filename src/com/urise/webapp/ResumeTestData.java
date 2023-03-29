package com.urise.webapp;

import com.urise.webapp.model.*;
import com.urise.webapp.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.addContact(ContactType.PHONE, "+7 (800) 000-0001");
        resume.addContact(ContactType.SKYPE, "skype");
        resume.addContact(ContactType.EMAIL, "somename@mail.org");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/name");
        resume.addContact(ContactType.GITHUB, "https://github.com/name");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/00001/name");
        resume.addContact(ContactType.HOMEPAGE, "https://homepage.com/");

        resume.addSection(SectionType.OBJECTIVE, new TextSection("some objective content"));
        resume.addSection(SectionType.PERSONAL, new TextSection("some personal content. "));
        List<String> achievements = new ArrayList<>();
        achievements.add("some achievement 1");
        achievements.add("some achievement 2");
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(achievements));
        List<String> qualifications = new ArrayList<>();
        qualifications.add("Java");
        qualifications.add("Git");
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(qualifications));
        List<Period> periods = new ArrayList<>();
        periods.add(new Period("Company 1", DateUtil.of(2013, Month.OCTOBER), LocalDate.now(),
                "description"));
        periods.add(new Period("Company 1", DateUtil.of(2012, Month.OCTOBER),
                DateUtil.of(2013, Month.OCTOBER), "description"));
        List<Company> companiesOfWork = new ArrayList<>();
        companiesOfWork.add(new Company("Company 1", "https://www.company1.com/", periods));
        periods = new ArrayList<>();
        periods.add(new Period("Company 1", DateUtil.of(2013, Month.OCTOBER), LocalDate.now(),
                "description"));
        periods.add(new Period("Company 1", DateUtil.of(2012, Month.OCTOBER),
                DateUtil.of(2013, Month.OCTOBER), "description"));
        companiesOfWork.add(new Company("Company 2", "https://www.company2.com/", periods));
        periods = new ArrayList<>();
        resume.addSection(SectionType.EXPERIENCE, new CompanySection(companiesOfWork));
        periods.add(new Period("'some education1",
                DateUtil.of(2013, Month.MARCH), DateUtil.of(2013, Month.MAY), ""));
        List<Company> companiesOfStudy = new ArrayList<>();
        companiesOfStudy.add(new Company("some university1", "https://www.somepage.ru", periods));
        periods = new ArrayList<>();
        periods.add(new Period("'some education2",
                DateUtil.of(2011, Month.MARCH), DateUtil.of(2011, Month.APRIL), ""));
        companiesOfStudy.add(new Company("some university2", "https://www.somepage2.ru", periods));
        resume.addSection(SectionType.EDUCATION, new CompanySection(companiesOfStudy));

        return resume;
/*for (ContactType type : ContactType.values()) {
            System.out.print("" + type.getTitle() + " : ");
            System.out.println(resume.getContact(type));
        }
        System.out.println();

        for (SectionType type : SectionType.values()) {
            System.out.print("" + type.getTitle() + "\n ");
            System.out.println(resume.getSection(type));
            System.out.println();
        }*/
    }
}
