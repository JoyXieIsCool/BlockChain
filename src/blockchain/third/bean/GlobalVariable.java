package blockchain.third.bean;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import blockchain.third.utils.PropertyUtil;

public class GlobalVariable {
	private static Properties properties;
	
	// 当前是否是议长
	public static boolean isSpeaker;
	// 是否是核心节点，负责对新加入的块转发数据和IP表
	public static boolean isRoot;
	// 自己的ID
	public static String ID;
	// ID和IP映射表
	public static Map<String, String> ipList = new ConcurrentHashMap<String, String>();
	
	// 监听加入网络的计算机的广播的端口
	public static int joinListenPort;
	// 普通节点加入网络时与根节点通信的端口，用于接收IP列表
	public static int listenToRootPort;
	// 接受根节点发过来的DB文件
	public static int receiveRootFilePort;
	// DB文件的存储路径
	public static String dbPath;
	//倒计时时间设置
	public static int countDown;
	
	public static int requestResponsePort;
	public static int requestBlockPort;
	public static int sendResponsePort;
	public static int sendBlockPort;
	public static int sendFinalBlockPort;
	public static int receveSpeakerIDPort;
	
	public static int blockMaxRecord;
	public static int maxIpTable;
	
	static {
		properties = PropertyUtil.loadProps("system.properties");
		
		isSpeaker = PropertyUtil.getBoolean(properties, "isSpeaker", false);
		isRoot = PropertyUtil.getBoolean(properties, "isRoot", false);
		ID = PropertyUtil.getString(properties, "ID");
		joinListenPort = PropertyUtil.getInt(properties, "joinListenPort");
		listenToRootPort = PropertyUtil.getInt(properties, "listenToRootPort");
		receiveRootFilePort = PropertyUtil.getInt(properties, "receiveRootFilePort");
		dbPath = PropertyUtil.getString(properties, "dbPath");
		
		requestResponsePort = PropertyUtil.getInt(properties, "requestResponsePort");
		requestBlockPort = PropertyUtil.getInt(properties, "requestBlockPort");
		sendResponsePort = PropertyUtil.getInt(properties, "sendResponsePort");
		sendBlockPort = PropertyUtil.getInt(properties, "sendBlockPort");
		sendFinalBlockPort=PropertyUtil.getInt(properties, "sendFinalBlockPort");
		receveSpeakerIDPort = PropertyUtil.getInt(properties, "receveSpeakerIDPort");
		
		blockMaxRecord = PropertyUtil.getInt(properties, "blockMaxRecord");
		maxIpTable = PropertyUtil.getInt(properties, "maxIpTable");
		
		//容错
		countDown = PropertyUtil.getInt(properties, "countDown");
		
	}
	
	public static void main(String[] args) throws IOException {
		
	}
	
}
