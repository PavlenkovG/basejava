package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class AbstractStorage implements Storage {

    public int size() {
        return 0;
    }

    public void clear() {
    }

    public void save(Resume r) {
    }

    public void update(Resume r) {
    }

    public Resume get(String uuid) {
        return null;
    }

    public void delete(String uuid) {
    }

    public Resume[] getAll() {
        return new Resume[0];
    }
}
