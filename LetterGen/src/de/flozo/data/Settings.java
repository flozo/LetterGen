package de.flozo.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Settings {


    private final String masterConfigFile;


    public Settings(String masterConfigFile) {
        this.masterConfigFile = masterConfigFile;
    }

    public String getProperty(ConfigGroup configGroup, String propertyName, String defaultValue) {
        return getAll().get(configGroup).getProperty(propertyName, defaultValue);
    }


    public Properties getConfigGroupProperties(ConfigGroup configGroup) {
        return getAll().get(configGroup);
    }


    public Map<ConfigGroup, Properties> getAll() {
        Map<ConfigGroup, Properties> settings = new HashMap<>();
        Properties configFileNames = readConfigFileNames();
        for (ConfigGroup configGroup : ConfigGroup.values()) {
            settings.put(configGroup, new ConfigFile(configFileNames.getProperty(configGroup.getString())).getProperties());
        }
        return settings;
    }

    private Properties readConfigFileNames() {
        ConfigFile master = new ConfigFile(masterConfigFile);
        System.out.println("[config] Checking master config file \"" + masterConfigFile + "\" ...");
        if (isComplete(master.getProperties())) {
            System.out.println("[config] Successfully read master config!");
        } else {
            System.out.println("[config] Possibly incomplete master config!");
        }
        return master.getProperties();
    }


    private boolean isComplete(Properties properties) {
        for (ConfigGroup configGroup : ConfigGroup.values()) {
            if (!properties.containsKey(configGroup.getString())) {
                System.out.println("[config] [error] ... required config group \"" + configGroup.getString() + "\" not found in master config!");
                return false;
            }
        }
        System.out.println("[config] ... master-config groups are complete!");
        return true;
    }


}
