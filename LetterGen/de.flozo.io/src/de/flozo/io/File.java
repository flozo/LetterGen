package de.flozo.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class File {

    private final Path parentDirectory;
    private final Path fileName;
    private final Path completePath;
    private final boolean overwriteExistingFile;


    public File(Path path) {
        this.parentDirectory = path.getParent();
        this.fileName = path.getFileName();
        this.completePath = path.toAbsolutePath();
        this.overwriteExistingFile = false;
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


    public List<String> getLines() {
        List<String> lines = new ArrayList<>();
        try {
            lines.addAll(Files.readAllLines(Paths.get(completePath.toString())));
        } catch (IOException e) {
            System.out.println("[error] [IOException] Failed to open file \"" + completePath + "\"!");
        }
        return lines;
    }


    public boolean writeLines(List<String> lines, boolean overwriteExistingFile) {
        if (exists()) {
            System.out.println("[output] A file named \"" + completePath + "\" exists already!");
            if (!overwriteExistingFile) {
                System.out.println("[output] Overwriting of existing files is disabled. Stopping process.");
                System.out.println("[output] To write a new file enable overwriting or delete existing file.");
                return false;
            }
            System.out.println("[output] Overwriting of existing files is enabled.");
        }
        System.out.print("[output] Writing to file \"" + completePath + "\" ...");
        try (PrintWriter printWriter = new PrintWriter(completePath.toString())) {
            lines.forEach(printWriter::println);
            printWriter.println();
            System.out.println(" done!");
            return true;
        } catch (IOException e) {
            System.out.println();
            System.out.println("[output] [IOException] ... failed to write to file!");
            return false;
        }
    }

    public boolean writeLines(List<String> lines) {
        return writeLines(lines, false);
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
