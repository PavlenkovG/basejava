package com.urise.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class TextSection extends AbstractSection implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String content;

    public TextSection(String content) {
        this.content = content;
    }

    public String getContent() {
        Objects.requireNonNull(content, "content must not be null");
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return content;
    }
}
