package com.github.zzbbc.log;

public interface Log {
    void log(String message);

    void logError(Exception exception);

    void logError(Throwable cause);
}
