package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final void doSave(Resume r, Integer searchKey) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        saveResume(r, searchKey);
        size++;
    }

    @Override
    public void doUpdate(Resume r, Integer searchKey) {
        storage[searchKey] = r;
    }

    @Override
    public Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    public final void doDelete(Integer searchKey) {
        deleteResume(searchKey);
        size--;
    }

    public boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> list = new ArrayList<>();
        for (Resume r : storage) {
            if (r == null) {
                break;
            }
            list.add(r);
        }
        return list;
    }

    protected abstract void saveResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    protected abstract Integer getSearchKey(String uuid);
}
