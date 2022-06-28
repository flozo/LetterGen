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

public class Settings2 {

    private final MasterConfig masterConfig;
    private final Map<ConfigGroup, File> configFiles;
    private final Map<ConfigGroup, Properties> properties;

    private Settings2(MasterConfig masterConfig, Map<ConfigGroup, File> configFiles) {
        this.masterConfig = masterConfig;
        this.configFiles = configFiles;
        this.properties = new HashMap<>();
    }

    public static Settings2 fromMasterConfigFile(MasterConfig masterConfig) {
        Properties masterProperties = masterConfig.readProperties();
        Map<ConfigGroup, File> configFiles = new HashMap<>();
        for (ConfigGroup configGroup : ConfigGroup.values()) {
            Path path = Paths.get(masterConfig.getParentDirectory().toString(), masterProperties.getProperty(configGroup.getPropertyKey()));
            File file = File.fromPath(path);
            configFiles.put(configGroup, file);
        }
        return new Settings2(masterConfig, configFiles);
    }

    public void readConfigFiles() {
        for (ConfigGroup configGroup : ConfigGroup.values()) {
            properties.put(configGroup, readProperties(configFiles.get(configGroup)));
        }
    }

    private Properties readProperties(File configFile) {
        if (!configFile.exists()) {
            System.out.println("[config] Config file \"" + configFile.getCompletePath() + "\" does not exist!");
            return null;
        }
        System.out.print("[config] Read from config file \"" + configFile.getCompletePath() + "\" ...");
        Properties properties = new Properties();
        try (InputStreamReader input = new InputStreamReader(new FileInputStream(configFile.getCompletePath().toFile()), StandardCharsets.UTF_8)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println();
            System.out.println("[config] [IOException] Failed to open config file!");
        }
        System.out.println(" done!");
        return properties;
    }

    @Override
    public String toString() {
        return "Settings2{" +
                "masterConfig=" + masterConfig +
                ", configFiles=" + configFiles +
                ", properties=" + properties +
                '}';
    }
}
