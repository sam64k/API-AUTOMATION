package com.theverygroup.lf.steps;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GlobalContext {
    private static Map<String, String> sharedData = new HashMap<String, String>();
    private static Map<String, String> credentials = new HashMap<String, String>();

    private static Properties environmentURI;
    private static String env;

    public static void addToGlobalDataContext(String key, String value) {
        sharedData.put(key, value);
    }

    public static String getGlobalDataContext(String key) {
        return sharedData.get(key);
    }

    public static String getCredentials(String Key) {
        return credentials.get(Key);
    }

    public static void addCredentials(String key, String value) {
        credentials.put(key, value);
    }

    public static void setEnvironmentURI(Properties prop) {
        environmentURI = prop;
        env = System.getenv("TEST_ENV");
        System.out.println("ENVIRONMENT SET TO:" + env);
    }

    public static String getEnvironmentURI(String key) {
        if (environmentURI != null) {
            return environmentURI.getProperty(key);
        } else {
            throw new NullPointerException("Environment properties is null!!");
        }
    }

    public static String getEnv() {
        if (StringUtils.isNotEmpty(env)) {
            return env;
        } else {
            throw new NullPointerException("Environment not set!!");
        }
    }
}
