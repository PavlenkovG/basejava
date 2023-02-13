package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Storage is overflow");
        } else if (index > 0){
            System.out.printf("Resume %s already exists", storage[index]);
        } else {
            insert(r);
        }
    }

    protected abstract void insert(Resume r);

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume not found");
        } else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Resume not found");
            return null;
        }
        return storage[index];
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            erase(index);
        } else {
            System.out.println("Resume not found");
        }
    }

    protected abstract void erase(int index);

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int findIndex(String uuid);

}
