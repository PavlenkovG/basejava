package com.urise.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<String> strings;

    public ListSection() {
    }

    public ListSection(List<String> strings) {
        Objects.requireNonNull(strings, "strings must not be null");
        this.strings = strings;
    }

    public List<String> getStrings() {
        return strings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection that)) return false;
        return Objects.equals(strings, that.strings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strings);
    }

    @Override
    public String toString() {

        return strings.toString();
    }
}
