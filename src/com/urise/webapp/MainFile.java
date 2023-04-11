package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) throws IOException {
        String filePath = "./.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        printDirAndFile("./", "");
    }

    private static void printDirAndFile(String path, String indent) throws IOException {
        File dir = new File(path);
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                System.out.println(indent + "Dir: " + file.getPath());
                printDirAndFile(file.getCanonicalPath(), indent + "  ");
            } else {
                System.out.println(indent + "File: " + file.getName());
            }
        }
    }
}
