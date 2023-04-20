package com.urise.webapp.model;

import com.google.gson.annotations.JsonAdapter;
import com.urise.webapp.util.JsonLocalDateAdapter;
import com.urise.webapp.util.XmlLocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String title;
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @JsonAdapter(JsonLocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @JsonAdapter(JsonLocalDateAdapter.class)
    private LocalDate endDate;
    private String description;

    public Period() {
    }

    public Period(String title, LocalDate startDate, LocalDate endDate, String description) {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Period period)) return false;

        if (!title.equals(period.title)) return false;
        if (!startDate.equals(period.startDate)) return false;
        if (!endDate.equals(period.endDate)) return false;
        return description.equals(period.description);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return startDate + "  -  " + endDate + ". " + title + '\n' + description + '\n';
    }
}
