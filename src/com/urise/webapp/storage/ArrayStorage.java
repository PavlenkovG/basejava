package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insert(Resume r) {
        storage[size] = r;
        size++;
    }

    @Override
    protected void erase(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
