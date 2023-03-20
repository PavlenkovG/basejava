package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    public void save(Resume r) {
        SK searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void update(Resume r) {
        SK searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public Resume get(String uuid) {
        SK searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public void delete(String uuid) {
        SK searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }

    private SK getExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract List<Resume> doCopyAll();
}
