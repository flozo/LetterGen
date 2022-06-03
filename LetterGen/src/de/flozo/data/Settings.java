package de.flozo.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Settings {

    private final String masterConfigFile;

    public Settings(String masterConfigFile) {
        this.masterConfigFile = masterConfigFile;
    }


    public Properties getConfigGroupProperties(ConfigGroup configGroup) {
        return getAll().get(configGroup);
    }

    public Map<String, String> getRawMap(ConfigGroup configGroup) {
        Map<String, String> propertiesRawMap = new HashMap<>();
        Properties groupProperties = getConfigGroupProperties(configGroup);
        for (Map.Entry<Object, Object> property : groupProperties.entrySet()) {
            propertiesRawMap.put(property.getKey().toString(), property.getValue().toString());
        }
        return propertiesRawMap;
    }

    public Map<ConfigGroup, Properties> getAll() {
        Map<ConfigGroup, Properties> settings = new HashMap<>();
        Properties configFileNames = readConfigFileNames();
        for (ConfigGroup configGroup : ConfigGroup.values()) {
            settings.put(configGroup, new ConfigFile(configFileNames.getProperty(configGroup.getPropertyKey())).getProperties());
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
            if (!properties.containsKey(configGroup.getPropertyKey())) {
                System.out.println("[config] [error] ... required config group \"" + configGroup.getPropertyKey() + "\" not found in master config!");
                return false;
            }
        }
        System.out.println("[config] ... master-config groups are complete!");
        return true;
    }

}
