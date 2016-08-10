package blockchain.third.bean;

import java.awt.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import blockchain.third.utils.PropertyUtil;

public class GlobalVariable {
	private static Properties properties;
	
	// 当前是否是矿工
	public static boolean isMiner;
	// 是否是核心节点，负责对新加入的块转发数据和IP表
	public static boolean isRoot;
	// ID和IP映射表
	public static Map<String, String> ipList = new ConcurrentHashMap<String, String>();
	
	
	static {
		properties = PropertyUtil.loadProps("system.properties");
		
		isMiner = PropertyUtil.getBoolean(properties, "isMiner", false);
		isRoot = PropertyUtil.getBoolean(properties, "isRoot", false);
	}
	
	public static void main(String[] args) {
		
	}
	
}
