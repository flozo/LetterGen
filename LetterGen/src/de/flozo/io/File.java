package de.flozo.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class File {

    private final Path parentDirectory;
    private final Path fileName;
    private final Path completePath;


    public File(Path path) {
        this.parentDirectory = path.getParent();
        this.fileName = path.getFileName();
        this.completePath = path.toAbsolutePath();
    }


    public static File fromPath(Path path) {
        return new File(path);
    }

    public static File fromString(String path) {
        return new File(Paths.get(path));
    }


    public boolean exists() {
        return Files.isRegularFile(completePath);
    }


    public Path getParentDirectory() {
        return parentDirectory;
    }

    public Path getFileName() {
        return fileName;
    }

    public Path getCompletePath() {
        return completePath;
    }

    @Override
    public String toString() {
        return "File{" +
                "parentDirectory=" + parentDirectory +
                ", fileName=" + fileName +
                ", completePath=" + completePath +
                '}';
    }
}
