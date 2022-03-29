package com.github.zzbbc.config.log;

public interface LogConfig {
    void log(String message);

    void logError(Exception exception);

    void logError(Throwable cause);
}
