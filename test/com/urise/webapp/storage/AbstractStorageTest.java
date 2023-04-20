package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.urise.webapp.ResumeTestData.createResume;
import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("/home/irondog/java/projects/storage");
    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String NOT_EXIST_UUID = "dummy";
    private static final String FULL_NAME_1 = "fullName1";
    private static final String FULL_NAME_2 = "fullName2";
    private static final String FULL_NAME_3 = "fullName3";
    private static final String FULL_NAME_4 = "fullName4";
    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = createResume(UUID_1, FULL_NAME_1);
        RESUME_2 = createResume(UUID_2, FULL_NAME_2);
        RESUME_3 = createResume(UUID_3, FULL_NAME_3);
        RESUME_4 = createResume(UUID_4, FULL_NAME_4);
    }

    private final List<Resume> EMPTY_ARRAY = new ArrayList<>();

    public AbstractStorageTest(Storage storage) {
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
        assertSize(expected);
    }

    @Test
    public void clear() {
        storage.clear();
        int expected = 0;
        assertSize(expected);
        assertEquals(EMPTY_ARRAY, storage.getAllSorted());
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4, "save failed");
        int expected = 4;
        assertSize(expected);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void update() {
        Resume expected = createResume("uuid1", FULL_NAME_4);
        storage.update(expected);
        assertEquals(expected, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test
    public void get() {
        assertGet(RESUME_1, "get RESUME_1 test. Wrong resume was returned");
        assertGet(RESUME_2, "get RESUME_2 test. Wrong resume was returned");
        assertGet(RESUME_3, "get RESUME_3 test. Wrong resume was returned");
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(NOT_EXIST_UUID);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        int expected = 2;
        assertSize(expected);
        assertGet(RESUME_3, "delete failed");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(NOT_EXIST_UUID);
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }

    private void assertSize(int expected) {
        assertEquals(expected, storage.size());
    }

    private void assertGet(Resume r, String message) {
        assertEquals(message, r, storage.get(r.getUuid()));
    }
}