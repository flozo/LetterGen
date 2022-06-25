package de.flozo.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigFile2 {

    public static final String HOME_DIR_SYSTEM_PROPERTY_NAME = "user.home";
    public static final String CONFIG_DIR = ".config";
    public static final String SUB_DIR = "LetterGen";

    private final Path parentDirectory;
    private final Path fileName;
    private final Path completePath;
    private final Properties properties;

    private ConfigFile2(Path path) {
        this.fileName = path.getFileName();
        this.parentDirectory = path.getParent();
        this.completePath = path.toAbsolutePath();
        this.properties = new Properties();
    }

    public static ConfigFile2 inDefaultDirectory(String fileName) {
        String homeDirectory = System.getProperty(HOME_DIR_SYSTEM_PROPERTY_NAME);
        Path defaultConfigDir = Paths.get(homeDirectory, CONFIG_DIR, SUB_DIR, fileName);
        return new ConfigFile2(defaultConfigDir);
    }

    public static ConfigFile2 inCustomDirectory(String completePath) {
        return new ConfigFile2(Paths.get(completePath));
    }


    public void readPropertiesFromFile() {
        System.out.println("[config] Read from config file \"" + completePath + "\" ...");
        try (InputStreamReader input = new InputStreamReader(new FileInputStream(completePath.toFile()), StandardCharsets.UTF_8)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("[config] [IOException] Failed to open config file!");
        }
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

    public Properties getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "ConfigFile2{" +
                "parentDirectory=" + parentDirectory +
                ", fileName=" + fileName +
                ", completePath=" + completePath +
                ", properties=" + properties +
                '}';
    }
}
