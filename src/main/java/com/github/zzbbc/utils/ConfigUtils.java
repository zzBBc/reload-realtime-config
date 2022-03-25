package com.github.zzbbc.utils;

import com.github.zzbbc.config.ConfigType;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.json.JsonObject;

public class ConfigUtils {
    private static final String FORMAT_PROPERTIES = "properties";


    /**
     * @param configType
     * @param path
     * @param rawData = true, getString() all data
     * @return ConfigStoreOptions
     */
    public static ConfigStoreOptions getConfigStoreOptions(ConfigType configType, String path,
            boolean rawData) {
        ConfigStoreOptions fileStore = null;

        switch (configType) {
            case PROPERTIES:
                fileStore = new ConfigStoreOptions().setType("file").setFormat(FORMAT_PROPERTIES)
                        .setConfig(new JsonObject().put("path", path).put("raw-data", rawData));
                break;
            case HIERARCHICAL:
                fileStore = new ConfigStoreOptions().setFormat(FORMAT_PROPERTIES).setType("file")
                        .setConfig(new JsonObject().put("path", path).put("hierarchical", true)
                                .put("raw-data", rawData));
                break;
            default:
                break;
        }

        return fileStore;
    }
}
