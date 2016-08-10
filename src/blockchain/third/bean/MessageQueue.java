package blockchain.third.bean;

import java.util.HashMap;
import java.util.Map;

public class MessageQueue {
	Map<String, Message> msg_map = null;
	
	public MessageQueue() {
		msg_map = new HashMap<String, Message>();
	}
	
	public void insert(Message msg) {
		msg_map.put(msg.timestamp, msg);
	}
	
}
