package com.fatlamb.fattt.util;

import java.io.*;
import java.util.Properties;

/**
 * Created by 58 on 2017/1/6.
 */
public class SettingUtil {

    public static Properties SETTINGS;

    private static String environment = "online";
    public static String hostName = "";
    private static String BASE = "";

    static {
        init();
        environment = getString("environment", "online");

    }

    private static String getPath(String filename) {
        String path = BASE + File.separator + filename;
        return path;
    }

    private static void init() {
        String filePath = getPath("setting.properties");
        SETTINGS = new Properties();
        InputStream stream = null;
        try {
            File f = new File(filePath);
            System.out.println("setting path is" + f.getAbsolutePath());
            stream = new FileInputStream(filePath);
            SETTINGS.load(stream);

            stream.close();
        } catch (Exception e) {
//            log.error("初始化配置失败！", e);
        }
    }

    public static String getString(String key) {
        return getSetting(key);
    }

    public static String getString(String key, String defaultValue) {
        String value = getString(key);
        return (value != null && value.length() > 0) ? value : defaultValue;
    }

    public static int getInt(String key, int defaultValue) {
        try {
            String value = getSetting(key);
            if (value != null && value.length() > 0) {
                return Integer.parseInt(value);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;

    }

    public static boolean getBoolean(String key) {
        String value = getSetting(key);
        if (value != null && value.length() > 0 && value.equals("true")) {
            return true;
        }
        return false;

    }

    private static String getSetting(String key) {
        String enviResult = SETTINGS.getProperty(environment + "_" + key);
        return enviResult != null ? enviResult : SETTINGS.getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(getString("weixin_appid"));
    }

    public static boolean isOnline() {
        if(environment != null && environment.equals("dev")){
            return false ;
        }
        return true ;
//        return !(environment != null && environment.equals("dev"));
    }

}
