package de.flozo.letter.data;

import de.flozo.io.Directory;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigDirectory extends Directory {

    public static final String HOME_DIR_SYSTEM_PROPERTY_NAME = "user.home";
    public static final String CONFIG_DIR = ".config";
    public static final String SUB_DIR = "LetterGen";
    public static final String MASTER_CONFIG_FILE_NAME = "master.config";


    public ConfigDirectory(Path path) {
        super(path);
    }


    public static ConfigDirectory fromDefaultDirectory() {
        String homeDirectory = System.getProperty(HOME_DIR_SYSTEM_PROPERTY_NAME);
        Path defaultConfigDir = Paths.get(homeDirectory, CONFIG_DIR, SUB_DIR);
        return fromCustomDirectory(defaultConfigDir.toString());
    }

    public static ConfigDirectory fromCustomDirectory(String customDirectory) {
        return new ConfigDirectory(Paths.get(customDirectory));
    }


    public boolean containsMasterConfigFile() {
        return containsFile(Paths.get(MASTER_CONFIG_FILE_NAME));
    }
}
