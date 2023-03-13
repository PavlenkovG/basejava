package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        int index = storage.indexOf(r);
        storage.set(index, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        int index = findIndex(searchKey);
        return storage.get(index);
    }

    @Override
    protected void doDelete(Object searchKey) {
        int index = findIndex(searchKey);
        storage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.contains(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                return r;
            }
        }
        return null;
    }

    private int findIndex(Object searchKey) {
        return storage.indexOf(searchKey);
    }
}