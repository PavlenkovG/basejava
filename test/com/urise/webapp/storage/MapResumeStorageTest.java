package com.urise.webapp.storage;

import org.junit.Test;

public class MapResumeStorageTest extends AbstractStorageTest {
    public MapResumeStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Test
    public void saveOverflow() {
    }
}