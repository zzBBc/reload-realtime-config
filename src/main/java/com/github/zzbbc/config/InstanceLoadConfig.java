package com.github.zzbbc.config;

import io.vertx.core.json.JsonObject;

public interface InstanceLoadConfig {

    void loadConfig(JsonObject config);

}
