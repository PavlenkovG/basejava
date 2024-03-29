package com.urise.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CompanySection extends AbstractSection implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private  List<Company> company;

    public CompanySection() {
    }

    public CompanySection(List<Company> company) {
        Objects.requireNonNull(company, "company must not be null");
        this.company = company;
    }

    public List<Company> getCompany() {
        return company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection that = (CompanySection) o;
        return company.equals(that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company);
    }

    @Override
    public String toString() {
        return company.toString();
    }
}
