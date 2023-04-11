package com.urise.webapp.storage;

import com.urise.webapp.strategy.ObjectStreamStorage;

public class ObjectFileStorageTest extends AbstractStorageTest{
    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStorage()));
    }
}