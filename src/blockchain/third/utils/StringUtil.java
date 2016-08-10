package blockchain.third.utils;

public class StringUtil {

	public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(String str) {
        return null == str || 0 == str.length();
    }
}
