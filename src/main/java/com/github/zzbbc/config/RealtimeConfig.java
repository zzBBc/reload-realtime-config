package com.github.zzbbc.config;

import com.github.zzbbc.config.log.LogConfig;
import com.github.zzbbc.utils.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class RealtimeConfig {
    Logger LOGGER = LoggerFactory.getLogger(RealtimeConfig.class);

    private LogConfig log;
    private JsonObject realtimeConfig;
    private String path;
    private InstanceLoadConfig instance;
    private boolean startLoad = false;
    private boolean firstTimeLoaded = false;

    public RealtimeConfig(String path, InstanceLoadConfig instance, LogConfig log) {
        this.log = log;
        this.path = path;
        this.instance = instance;

    }

    public RealtimeConfig(String path, InstanceLoadConfig instance) {
        this.path = path;
        this.instance = instance;
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
            startLoad = true;
            log("\n Old config: \n" + realtimeConfig);
            // New configuration
            realtimeConfig = config;
            log("\n New config: \n" + realtimeConfig);
            instance.loadConfig(config);

            firstTimeLoaded = true;
        });
    }

    public boolean firstTimeLoaded() {
        if (startLoad) {
            while (!firstTimeLoaded) {
                LOGGER.info("First time realtime loading...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
            LOGGER.info("Realtime config loaded!");
        }

        return firstTimeLoaded;
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
