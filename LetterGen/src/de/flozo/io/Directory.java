package de.flozo.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Directory {

    private final Path path;


    public Directory(Path path) {
        this.path = path;
    }

    public static Directory fromPath(Path path) {
        return new Directory(path.toAbsolutePath());
    }


    public boolean exists() {
        return Files.isDirectory(path);
    }

    public void createIfNotExists() {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean containsFile(Path fileName) {
        return Files.isRegularFile(fileName);
    }


    public Path getPath() {
        return path;
    }


    public String getString() {
        return path.toString();
    }

    @Override
    public String toString() {
        return "Directory{" +
                "path=" + path +
                '}';
    }
}
