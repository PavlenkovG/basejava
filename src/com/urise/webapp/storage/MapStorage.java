package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected void doSave(Resume r, String searchKey) {
        map.put(searchKey, r);
    }

    @Override
    protected void doUpdate(Resume r, String searchKey) {
        map.put(searchKey, r);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected void doDelete(String searchKey) {
        map.remove(searchKey);
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected boolean isExist(String searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}
