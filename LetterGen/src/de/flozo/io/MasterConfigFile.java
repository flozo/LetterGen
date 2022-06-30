package de.flozo.io;

import de.flozo.data.ConfigGroup;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
        System.out.print("[config] Read master config file \"" + getCompletePath() + "\" ...");
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
