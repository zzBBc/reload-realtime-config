package com.github.zzbbc.config;

public enum ConfigType {
    HIERARCHICAL("HIERARCHICAL"), PROPERTIES("PROPERTIES");

    private String type;

    ConfigType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
}
