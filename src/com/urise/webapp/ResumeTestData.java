package com.urise.webapp;

import com.urise.webapp.model.*;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.addContact(ContactType.PHONE, "+7 (921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и " +
                "корпоративного обучения по Java Web и Enterprise технологиям"));

        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры. "));

        List<String> achievements = new ArrayList<>();
        achievements.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: " +
                "приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов " +
                "на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для " +
                "комплексных DIY смет\n");
        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"," +
                " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов." +
                " Более 3500 выпускников. \n");
        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike." +
                " Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk. ");
        achievements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM." +
                " Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке:" +
                " Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей," +
                " интеграция CIFS/SMB java сервера.\n");
        achievements.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring," +
                " Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.\n");
        achievements.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов" +
                " (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о" +
                " состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования" +
                " и мониторинга системы по JMX (Jython/ Django).\n");
        achievements.add("Реализация протоколов по приему платежей всех основных платежных системы России" +
                " (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(achievements));

        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 \n");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce \n");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL," +
                " SQLite, MS SQL, HSQLDB \n");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy \n");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts \n");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis," +
                " Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice," +
                " GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit," +
                " Selenium (htmlelements). \n");
        qualifications.add("Python: Django. \n");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js \n");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka \n");
        qualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX," +
                " SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2," +
                " LDAP, OAuth1, OAuth2, JWT. \n");
        qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix \n");
        qualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway," +
                " Nagios, iReport, OpenCmis, Bonita, pgBouncer \n");
        qualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования," +
                " архитектурных шаблонов, UML, функционального программирования \n");
        qualifications.add("Родной русский, английский \"upper intermediate\"");
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(qualifications));

        List<Period> periods = new ArrayList<>();
        periods.add(new Period("Автор проекта.", "10/2013", "now",
                "Создание, организация и проведение Java онлайн проектов и стажировок."));
        List<Company> companiesOfWork = new ArrayList<>();
        companiesOfWork.add(new Company("Java Online Projects", "http://javaops.ru/", periods));
        periods = new ArrayList<>();
        periods.add(new Period("Старший разработчик (backend)", "10/2014", "01/2016",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API," +
                        " Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация," +
                        " авторизация по OAuth1, OAuth2, JWT SSO."));
        companiesOfWork.add(new Company("Wrike", "https://www.wrike.com/", periods));
        periods = new ArrayList<>();
        periods.add(new Period("Java архитектор", "04/2012", "10/2014",
                "Организация процесса разработки системы ERP для разных окружений: релизная политика," +
                        " версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway)," +
                        " конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и" +
                        " серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2," +
                        " 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html)." +
                        " Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office." +
                        " Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat," +
                        "WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh " +
                        "tunnels, PL/Python"));
        companiesOfWork.add(new Company("RIT Center", "none", periods));
        periods = new ArrayList<>();
        resume.addSection(SectionType.EXPERIENCE, new CompanySection(companiesOfWork));
        periods.add(new Period("'Functional Programming Principles in Scala' by Martin Odersky",
                "03/2013", "05/2013", ""));
        List<Company> companiesOfStudy = new ArrayList<>();
        companiesOfStudy.add(new Company("Coursera", "https://www.coursera.org/course/progfun", periods));
        periods = new ArrayList<>();
        periods.add(new Period("'Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'",
                "03/2011", "04/2011", ""));
        companiesOfStudy.add(new Company("Luxoft",
                "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", periods));
        periods = new ArrayList<>();
        periods.add(new Period("3 месяца обучения мобильным IN сетям (Берлин)", "01/2005",
                "04/2005", ""));
        companiesOfStudy.add(new Company("Siemens AG", "http://www.siemens.ru/", periods));
        resume.addSection(SectionType.EDUCATION, new CompanySection(companiesOfStudy));

        for (ContactType type : ContactType.values()) {
            System.out.print("" + type.getTitle() + " : ");
            System.out.println(resume.getContact(type));
        }
        System.out.println();

        for (SectionType type : SectionType.values()) {
            System.out.print("" + type.getTitle() + "\n ");
            System.out.println(resume.getSection(type));
            System.out.println();
        }
    }
}
