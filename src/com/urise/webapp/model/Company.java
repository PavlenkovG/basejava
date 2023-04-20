package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String title;
    private String website;
    private List<Period> periods;

    public Company() {
    }

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
        if (!(o instanceof Company company)) return false;
        return Objects.equals(title, company.title)
                && Objects.equals(website, company.website)
                && Objects.equals(periods, company.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, website, periods);
    }

    @Override
    public String toString() {
        return title + '\n' + website + '\n' + periods + '\n';
    }
}
