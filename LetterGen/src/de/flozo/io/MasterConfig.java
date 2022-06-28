package de.flozo.io;

import de.flozo.data.ConfigGroup;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class MasterConfig extends File {

    //    public static final ConfigGroup MASTER_CONFIG_FILE_NAME = ConfigGroup.MASTER;
    public static final String MASTER_CONFIG_FILE_NAME = "master.config";


    public MasterConfig(Path path) {
        super(path);
    }

    public static MasterConfig withDefaultFileName(ConfigDirectory configDirectory) {
        return withCustomFileName(configDirectory, MASTER_CONFIG_FILE_NAME);
    }

    public static MasterConfig withCustomFileName(ConfigDirectory configDirectory, String customFileName) {
        Path path = Paths.get(configDirectory.getPath().toString(), customFileName);
        return new MasterConfig(path);
    }

    public Properties readProperties() {
        if (!exists()) {
            System.out.println("[config] Master config file \"" + getCompletePath() + "\" does not exist!");
            return null;
        }
        System.out.print("[config] Read from config file \"" + getCompletePath() + "\" ...");
        Properties properties = new Properties();
        try (InputStreamReader input = new InputStreamReader(new FileInputStream(getCompletePath().toFile()), StandardCharsets.UTF_8)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println();
            System.out.println("[config] [IOException] Failed to open config file!");
        }
        System.out.println(" done! Checking integrity ...");
        if (!isComplete(properties)) {
            System.out.println("[config] [warning] ... master config appears to be incomplete!");
        }
        return properties;
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
}
