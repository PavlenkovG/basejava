package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected void insert(Resume r) {
        int insertPoint = Arrays.binarySearch(storage, 0, size, r);
        if (insertPoint < 0) {
            insertPoint = - insertPoint - 1;
        }
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1, size - insertPoint);
        storage[insertPoint] = r;
        size++;
    }

    @Override
    protected void erase(int index) {
        storage[index] = null;
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
    }
    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
