package blockchain.third.utils;

public class CastUtil {

	/**
     * 转为String类型
     */
    public static String castString(Object obj) {
        return CastUtil.castString(obj, "");
    }

    /**
     * 转为String类型（提供默认值）
     */
    public static String castString(Object obj, String defaultValue) {
        return null != obj ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转为int类型(默认值为0)
     */
    public static int castInt(Object obj) {
        return castInt(obj, 0);
    }

    /**
     * 转为int类型（可以指定默认值）
     */
    public static int castInt(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if(null != obj) {
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     * 转为double型
     */
    public static double castDouble(Object obj) {
        return CastUtil.castDouble(obj, 0);
    }

    /**
     * 转为double型（提供默认值）
     */
    public static double castDouble(Object obj, double defaultValue) {
        double doubleValue = defaultValue;
        if(null != obj) {
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转为long型
     */
    public static long castLong(Object obj) {
        return CastUtil.castLong(obj, 0);
    }

    /**
     * 转为long型（提供默认值）
     */
    public static long castLong(Object obj, long defaultValue) {
        long longValue = defaultValue;
        if (null != obj) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }

        }
        return longValue;
    }

    /**
     * 转为boolean类型(提供默认值)
     */
    public static boolean castBoolean(Object obj) {
        return castBoolean(obj, false);
    }

    /**
     * 转为boolean类型(可以指定默认类型)
     */
    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean boolValue = defaultValue;
        if(null != obj) {
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)) {
                boolValue = Boolean.parseBoolean(strValue);
            }
        }
        return boolValue;
    }
}
