package com.github.zzbbc.config;

import com.github.zzbbc.log.Log;
import com.github.zzbbc.utils.ConfigUtils;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class RealtimeConfig {
    private Log log;
    private JsonObject realtimeConfig;
    private String path;
    private InstanceLoadConfig instance;

    public RealtimeConfig(String path, InstanceLoadConfig instance, Log log) {
        this.log = log;
        this.path = path;
    }

    // Return
    public void initRealtimeConfig(ConfigType configType, boolean rawData) {
        ConfigStoreOptions fileStore = ConfigUtils.getConfigStoreOptions(configType, path, rawData);

        ConfigRetrieverOptions options =
                new ConfigRetrieverOptions().setScanPeriod(2000).addStore(fileStore);

        ConfigRetriever retriever = ConfigRetriever.create(Vertx.vertx(), options);
        retriever.configStream().exceptionHandler(t -> {
            logError(t);
        }).handler(config -> {
            // TODO Handle new value;
            log("\n Old config: \n" + realtimeConfig);
            // New configuration
            realtimeConfig = config;
            log("\n New config: \n" + realtimeConfig);
            instance.loadConfig(config);
        });
    }

    public JsonObject getRealtimeConfig() {
        return realtimeConfig;
    }

    private void log(String message) {
        if (log != null) {
            log.log(message);
        }
    }

    private void logError(Throwable t) {
        if (log != null) {
            log.logError(t);
        }
    }
}
