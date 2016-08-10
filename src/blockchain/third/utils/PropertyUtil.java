package blockchain.third.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	
	/**
     * 加载属性文件
     */
    public static Properties loadProps(String fileName) {
        Properties props = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (null == is) {
                throw new FileNotFoundException(fileName + " file is not found.");
            }
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();;
        } finally {
            if(null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return props;
    }
	
	/**
     * 获取字符型属性（默认值为空字符串）
     */
    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }

    /**
     * 获取字符型属性（可指定默认值）
     */
    public static String getString(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if(props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * 获取int类型属性（默认值为0）
     */
    public static int getInt(Properties props, String key) {
        return getInt(props, key, 0);
    }

    /**
     * 获取int类型属性（可以指定默认值）
     */
    public static int getInt(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if(props.containsKey(key)) {
            value = CastUtil.castInt(props.getProperty(key));
        }

        return value;
    }

    /**
     * 获取boolean类型属性（默认值为false）
     */
    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }

    /**
     * 获取boolean类型属性（可以指定默认值）
     */
    public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
        boolean boolValue = defaultValue;
        if (props.containsKey(key)) {
            boolValue = CastUtil.castBoolean(props.getProperty(key));
        }
        return boolValue;
    }
    
    
}
