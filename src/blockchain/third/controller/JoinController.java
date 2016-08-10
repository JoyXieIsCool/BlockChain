package blockchain.third.controller;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.Map;

import blockchain.third.bean.GlobalVariable;
import blockchain.third.communication.BroadCast;
import blockchain.third.communication.BroadListener;
import blockchain.third.communication.UniCast;
import blockchain.third.communication.UniListener;

/**
 * 控制节点接入的逻辑
 */
public class JoinController {
	
	private UpdateIPListThread updateIPListThread;
	private RootNodeMsgListener rootNodeMsgListener;
	private OtherNodeMsgListener otherNodeMsgListener;
	
	public JoinController() {
		broadcastAndUpdateIP();
	}
	
	public void broadcastAndUpdateIP() {
		if (GlobalVariable.isRoot == true) {
			//TODO 如果是根节点则无需广播自己的IP，只要监听新的加入节点即可
			otherNodeMsgListener = new OtherNodeMsgListener(GlobalVariable.joinListenPort);
			Thread t = new Thread(otherNodeMsgListener);
			t.start();
			return;
		} else {
			// 普通节点首先启动监听节点加入的线程
			updateIPListThread = new UpdateIPListThread(GlobalVariable.joinListenPort);
			Thread broadcastIPThread = new Thread(updateIPListThread);
			broadcastIPThread.start();
			
			// 然后启动监听根节点发送消息的线程
			rootNodeMsgListener = new RootNodeMsgListener(GlobalVariable.listenToRootPort);
			Thread listenRootNodeThread = new Thread(rootNodeMsgListener);
			listenRootNodeThread.start();
			
			// 最后广播自己的IP
			String ip = getLocalIP();
			String id = GlobalVariable.ID;
			BroadCast bc = new BroadCast(GlobalVariable.joinListenPort);
			bc.Send(id + "&" + ip);
			
		}
		
	}
	
	private String updateLocalIpList(String info) {
		String localIp = getLocalIP();
		// 解析IP数据，每一行都是ID&IP的格式
		String id = info.split("&")[0];
		String ip = info.split("&")[1];
		System.out.println("New node join: " + ip + "\t" + id);
		// 如果不是自己的IP则添加到IP列表中去
		if (!localIp.equals(ip)) {
			GlobalVariable.ipList.put(id, ip);
			return ip;
		}
		
		return null;
	}

	/**
	 * 监听新加入节点的IP地址并更新自己的IP地址列表
	 */
	class UpdateIPListThread extends BroadListener {

		public UpdateIPListThread(int p) {
			super(p);
		}

		@Override
		public void doIT(String info) {
			updateLocalIpList(info);
		}
		
	}
	
	/**
	 * 监听根节点发来的初始化数据
	 */
	class RootNodeMsgListener extends UniListener {

		public RootNodeMsgListener(int p) {
			super(p);
		}

		@Override
		public void doIT(String info) {
			if (info.startsWith("[IP]")) {
				// 处理根节点发来的IP列表
				String[] tmp = info.substring(4).split("&");
				String id = tmp[0];
				String ip = tmp[1];
				String localIp = getLocalIP();
				// 如果不是自己的IP则添加到IP列表中去
				if (!localIp.equals(ip))
					GlobalVariable.ipList.put(id, ip);
			} else if (info.startsWith("[RECORD]")) {
				// 处理根节点发过来的历史账单
				String[] tmp = info.substring(8).split("&");
				// TODO 解析记录，然后存储到数据库中，一条一条地存
			}
		}
		
	}
	
	/**
	 * 根节点监听普通节点发来的广播，更新自己的IP列表并发送初始化数据给新加入的节点
	 */
	class OtherNodeMsgListener extends BroadListener {

		public OtherNodeMsgListener(int p) {
			super(p);
		}

		@Override
		public void doIT(String info) {
			// 更新自己的IP列表
			String newNodeIp = updateLocalIpList(info);
			if (newNodeIp.equals(getLocalIP()))
				return;
			
			// 发送初始化数据，包括IP列表和历史账单
			Thread sendThread = new Thread(new SendInitialDataThread(newNodeIp));
			sendThread.start();
		}
		
	}
	
	/**
	 * 发送初始化数据，包括IP列表和历史账单
	 */
	class SendInitialDataThread implements Runnable {

		private String host;
		public SendInitialDataThread(String host) {
			this.host = host;
		}
		
		@Override
		public void run() {
			StringBuffer buffer = new StringBuffer();
			UniCast uc = new UniCast(host, GlobalVariable.listenToRootPort);
			Iterator<Map.Entry<String, String>> idIterator = GlobalVariable.ipList.entrySet().iterator();
			while (idIterator.hasNext()) {
				Map.Entry<String, String> entry = idIterator.next();
				String id = entry.getKey();
				String ip = entry.getValue();
				buffer.append("[IP]").append(id).append("&").append(ip).append("\n");
			}
			
			// TODO 查数据库并把记录添加到buffer中
			
			uc.Send(buffer.toString());
		}
	}
	
	private String getLocalIP() {
		String ip; 
        try { 
             /**返回本地主机。*/ 
             InetAddress addr = InetAddress.getLocalHost(); 
             /**返回 IP 地址字符串（以文本表现形式）*/ 
             ip = addr.getHostAddress();  
        } catch(Exception ex) { 
            ip = ""; 
        } 
        
        return ip;
	}
	
	private void end() {
		if (GlobalVariable.isRoot == true) {
			otherNodeMsgListener.closeListener();
		} else {
			updateIPListThread.closeListener();
		}
	}
	
	public static void main(String[] args) {
		
	}
}
