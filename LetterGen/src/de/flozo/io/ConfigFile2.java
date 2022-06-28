package de.flozo.io;

import de.flozo.data.ConfigGroup;

import java.util.Properties;

public class ConfigFile2 {


    private final ConfigGroup configGroup;
    private final File configFile;
//    private final Path defaultConfigDir;
    private final Properties properties;

    private ConfigFile2(ConfigGroup configGroup, File configFile) {
        this.configGroup = configGroup;
        this.configFile = configFile;
        this.properties = new Properties();
    }




//    public static ConfigFile2 inDefaultDirectory(ConfigGroup configGroup) {
//        String homeDirectory = System.getProperty(HOME_DIR_SYSTEM_PROPERTY_NAME);
////        if (configGroup == ConfigGroup.MASTER) {
////            String fileName = MASTER_CONFIG_FILE_NAME.getPropertyKey();
////        } else {
////            ConfigFile2 masterFile = ConfigFile2.inDefaultDirectory(ConfigGroup.MASTER);
////            masterFile.readPropertiesFromFile();
////
////        }
//        Path defaultConfigDir = Paths.get(homeDirectory, CONFIG_DIR, SUB_DIR, fileName);
//        return new ConfigFile2();
//    }
//
//    public static ConfigFile2 inCustomDirectory(String completePath) {
//        return new ConfigFile2(Paths.get(completePath));
//    }
//
//
//    public void readPropertiesFromFile() {
//        System.out.println("[config] Read from config file \"" + completePath + "\" ...");
//        try (InputStreamReader input = new InputStreamReader(new FileInputStream(completePath.toFile()), StandardCharsets.UTF_8)) {
//            properties.load(input);
//        } catch (IOException e) {
//            System.out.println("[config] [IOException] Failed to open config file!");
//        }
//    }


    public ConfigGroup getConfigGroup() {
        return configGroup;
    }

    public File getConfigFile() {
        return configFile;
    }


    public Properties getProperties() {
        return properties;
    }

}
