package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {
    List<Resume> storage = new ArrayList<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Resume r) {
        int index = storage.indexOf(r);
        if (size() >= 10000) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            storage.add(r);
        }
    }

    @Override
    public void update(Resume r) {
        int index = storage.indexOf(r);
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage.set(index, r);
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = storage.indexOf(getKey(uuid));
        return storage.get(index);
    }

    @Override
    public void delete(String uuid) {
        storage.remove(getKey(uuid));
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[size()]);
    }

    private Resume getKey(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                return r;
            }
        }
        throw new NotExistStorageException(uuid);
    }
}