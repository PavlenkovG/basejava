package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private final String NOT_EXIST_UUID = "dummy";
    private final Resume RESUME_1 = new Resume(UUID_1);
    private final Resume RESUME_2 = new Resume(UUID_2);
    private final Resume RESUME_3 = new Resume(UUID_3);
    private final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        int expected = 3;
        assertEquals(expected, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        int expected = 0;
        assertEquals(expected, storage.size());
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        int expected = 4;
        assertEquals(RESUME_4, storage.get(UUID_4));
        assertEquals(expected, storage.getAll().length);
    }

    @Test(expected = StorageException.class)
    public void SaveOverflow() {
        int storageLength = 10000;
        storage.clear();
        try {
            for (int i = 0; i < storageLength; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            fail("Array overflow ahead of time");
        }
        storage.save(new Resume());
    }

    @Test
    public void update() {
        Resume expected = RESUME_1;
        storage.update(expected);
        assertSame(expected, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test
    public void get() {
        assertEquals(RESUME_2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(NOT_EXIST_UUID);
    }

    @Test
    public void delete() {
        storage.delete(UUID_3);
        int expected = 2;
        assertEquals(expected, storage.getAll().length);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(NOT_EXIST_UUID);
    }

    @Test
    public void getAll() {
        int expected = 3;
        assertEquals(expected, storage.getAll().length);
    }
}