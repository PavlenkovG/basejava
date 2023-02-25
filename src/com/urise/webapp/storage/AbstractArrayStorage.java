package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
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
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (index > 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveResume(r);
            size++;
        }
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            deleteResume(index);
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void saveResume(Resume r);

    protected abstract void deleteResume(int index);

    protected abstract int findIndex(String uuid);
}
