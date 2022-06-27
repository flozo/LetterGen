package de.flozo.data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigDirectory {

    public static final String HOME_DIR_SYSTEM_PROPERTY_NAME = "user.home";
    public static final String CONFIG_DIR = ".config";
    public static final String SUB_DIR = "LetterGen";
//    public static final ConfigGroup MASTER_CONFIG_FILE_NAME = "master.config";//ConfigGroup.MASTER;
    public static final String MASTER_CONFIG_FILE_NAME = "master.config";

    private final Path configDirectory;
    private final Path masterConfigFile;


    private ConfigDirectory(Path configDirectory) {
        this.configDirectory = configDirectory;
        this.masterConfigFile = Paths.get(configDirectory.toString(), MASTER_CONFIG_FILE_NAME);
    }


    public static ConfigDirectory useDefaultDirectory() {
        String homeDirectory = System.getProperty(HOME_DIR_SYSTEM_PROPERTY_NAME);
        Path defaultConfigDir = Paths.get(homeDirectory, CONFIG_DIR, SUB_DIR);
        return new ConfigDirectory(defaultConfigDir);
    }

    public static ConfigDirectory useCustomDirectory(String customDirectory) {
        return new ConfigDirectory(Paths.get(customDirectory));
    }


    public boolean exists() {
        return Files.isDirectory(configDirectory);
    }

    public boolean containsMasterConfigFile() {
        return Files.isRegularFile(masterConfigFile);
    }


    public Path getConfigDirectory() {
        return configDirectory;
    }

    public Path getMasterConfigFile() {
        return masterConfigFile;
    }

    @Override
    public String toString() {
        return "ConfigDirectory{" +
                "configDirectory=" + configDirectory +
                ", masterConfigFile=" + masterConfigFile +
                '}';
    }
}
