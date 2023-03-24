package com.urise.webapp.model;

public enum ContactType {
    PHONE("phone"),
    SKYPE("skype"),
    EMAIL("email"),
    LINKEDIN("linkedin"),
    GITHUB("github"),
    STACKOVERFLOW("stackoverflow"),
    HOMEPAGE("homepage");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
