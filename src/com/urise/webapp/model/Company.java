package com.urise.webapp.model;

import java.util.List;

public class Company {
    private final String title;
    private final String website;
    private final List<Period> periods;

    public Company(String title, String website, List<Period> periods) {
        this.title = title;
        this.website = website;
        this.periods = periods;
    }

    public String getTitle() {
        return title;
    }

    public String getWebsite() {
        return website;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company that)) return false;

        if (!title.equals(that.title)) return false;
        if (!website.equals(that.website)) return false;
        return periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + website.hashCode();
        result = 31 * result + periods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return title + '\n' + website + '\n' + periods + '\n';
    }
}
