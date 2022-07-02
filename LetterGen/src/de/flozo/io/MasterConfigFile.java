package de.flozo.io;

import de.flozo.data.ConfigGroup;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static de.flozo.Main.VERSION_INFO_PDF_META_DATA;

public class MasterConfigFile extends File {

    public static final String MASTER_CONFIG_FILE_NAME = "master.config";

    private final Map<ConfigGroup, File> configGroupFiles;


    public MasterConfigFile(Path path) {
        super(path);
        this.configGroupFiles = new HashMap<>();
    }


    public static MasterConfigFile withDefaultFileName(ConfigDirectory configDirectory) {
        return withCustomFileName(configDirectory, MASTER_CONFIG_FILE_NAME);
    }

    public static MasterConfigFile withCustomFileName(ConfigDirectory configDirectory, String customFileName) {
        Path path = Paths.get(configDirectory.getPath().toString(), customFileName);
        return new MasterConfigFile(path);
    }


    public void readProperties() {
        if (!exists()) {
            System.out.println("[config] Master config file \"" + getCompletePath() + "\" does not exist!");
            return;
        }
        System.out.print("[config] Reading master config file \"" + getCompletePath() + "\" ...");
        Properties properties = new Properties();
        try (InputStreamReader input = new InputStreamReader(new FileInputStream(getCompletePath().toFile()), StandardCharsets.UTF_8)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println();
            System.out.println("[config] [IOException] Failed to open master config file!");
        }
        System.out.println(" done! Checking integrity ...");
        if (isComplete(properties)) {
            assignConfigGroupFiles(properties);
        } else {
            System.out.println("[config] [warning] ... master config appears to be incomplete!");
        }
    }

    public void writeProperties() {
        if (exists()) {
            System.out.println("[config] Master config file \"" + getCompletePath() + "\" already exists!");
            return;
        }
        System.out.println("[config] No master config file found. Creating new master config with default values.");
        System.out.print("[config] Collecting default values ...");
        List<String> masterConfigLines = new ArrayList<>();
        masterConfigLines.add("# Program info: " + VERSION_INFO_PDF_META_DATA);
        masterConfigLines.add("#");
        masterConfigLines.add("# File info:");
        masterConfigLines.add("# This is the master config file.");
        masterConfigLines.add("# It specifies the individual config files for the respective config groups.");
        masterConfigLines.add("#");
        masterConfigLines.add("# Format info:");
        masterConfigLines.add("# Lines starting with \"#\" or \"!\" are comment lines and are ignored by the program. However, values may contain \"#\" and/or \"!\".");
        masterConfigLines.add("# Blank lines are ignored as well.");
        masterConfigLines.add("# Configuration settings are specified as key-value pairs, separated by \"=\", \":\", or whitespace.");
        masterConfigLines.add("# If a line contains multiple non-consecutive whitespace characters, the first one is interpreted as key-value separator.");
        masterConfigLines.add("# Keys cannot contain whitespace.");
        masterConfigLines.add("# Leading whitespace is ignored for both keys and values.");
        masterConfigLines.add("# Trailing whitespace is ignored for keys only.");
        masterConfigLines.add("");
        masterConfigLines.addAll(Arrays.stream(ConfigGroup.values())
                .map(value -> value.getPropertyKey() + " = " + value.getDefaultFileName())
                .collect(Collectors.toList()));
        System.out.println(" done!");
        File masterConfigFile = File.fromPath(getCompletePath());
        if (!masterConfigFile.writeLines(masterConfigLines)) {
            System.out.println("[config] [error] Failed to write master config file!");
        }
    }

    private void assignConfigGroupFiles(Properties properties) {
        for (ConfigGroup configGroup : ConfigGroup.values()) {
            Path path = Paths.get(getParentDirectory().toString(), properties.getProperty(configGroup.getPropertyKey()));
            File file = File.fromPath(path);
            configGroupFiles.put(configGroup, file);
        }
    }

    private boolean isComplete(Properties properties) {
        for (ConfigGroup configGroup : ConfigGroup.values()) {
            if (!properties.containsKey(configGroup.getPropertyKey())) {
                System.out.println("[config] [error] ... required config group \"" + configGroup.getPropertyKey() + "\" not found in master config!");
                return false;
            }
        }
        System.out.println("[config] ... master config file is complete!");
        return true;
    }

    public Map<ConfigGroup, File> getConfigGroupFiles() {
        return configGroupFiles;
    }

    @Override
    public String toString() {
        return "MasterConfigFile{" +
                "configGroupFiles=" + configGroupFiles +
                '}';
    }
}
