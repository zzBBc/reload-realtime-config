package com.github.zzbbc;

import com.github.zzbbc.config.ConfigType;
import com.github.zzbbc.config.RealtimeConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RealtimeConfig realtimeConfig = new RealtimeConfig("hierarchical.properties", null);
        realtimeConfig.initRealtimeConfig(ConfigType.PROPERTIES, true);
        System.out.println( "Hello World!" );
    }
}
