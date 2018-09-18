package com.nanwulife.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;


public class PropertiesUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private String properiesName = "";

    public PropertiesUtil() {

    }

    /**
     * PropertiesUtil工具类构造函数
     * @param fileName 配置文件名
     */
    public PropertiesUtil(String fileName) {
        this.properiesName = fileName;
    }

    /**
     * 返回配置文件中指定的key对应的value
     * eg. new PropertiesUtil("server.properties").readProperty("report.server.win.basePath");
     * @param key
     * @return
     */
    public String readProperty(String key) {
        String value = "";
        InputStreamReader is = null;
        try {
            is = new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(properiesName), "utf-8");
            Properties p = new Properties();
            p.load(is);
            value = p.getProperty(key);
        } catch (IOException e) {
            logger.error("配置文件读取异常",e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                logger.error("配置文件读取异常",e);
            }
        }
        return value;
    }

    /**
     * 将配置文件读取到Properties中返回
     * eg. Properties prop = new PropertiesUtil("server.properties").getProperties();
     *     String bathPath = prop.getProperty("report.server.win.basePath");
     *     String bathPath = prop.getProperty("report.server.linux.basePath");
     * @return
     */
    public Properties getProperties() {
        Properties p = new Properties();
        InputStreamReader is = null;
        try {
            is = new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(properiesName), "utf-8");
            p.load(is);
        } catch (IOException e) {
            logger.error("Properties读取异常",e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                logger.error("Properties读取异常",e);
            }
        }
        return p;
    }

    /**
     * 写入Properties中，一般情况很少用到
     * @param key
     * @param value
     */
    public void writeProperty(String key, String value) {
        InputStream is = null;
        OutputStream os = null;
        Properties p = new Properties();
        try {
            is = new FileInputStream(properiesName);
            p.load(is);
            os = new FileOutputStream(PropertiesUtil.class.getClassLoader().getResource(properiesName).getFile());

            p.setProperty(key, value);
            p.store(os, key);
            os.flush();
            os.close();
        } catch (Exception e) {
            logger.error("配置文件写入异常",e);
        } finally {
            try {
                if (null != is)
                    is.close();
                if (null != os)
                    os.close();
            } catch (IOException e) {
                logger.error("配置文件写入异常",e);
            }
        }

    }



}