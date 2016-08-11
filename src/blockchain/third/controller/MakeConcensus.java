package blockchain.third.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import blockchain.third.bean.BROADCASTTYPY;
import blockchain.third.bean.Block;
import blockchain.third.bean.Constants;
import blockchain.third.bean.GlobalVariable;
import blockchain.third.bean.Message;
import blockchain.third.communication.BroadCast;
import blockchain.third.communication.BroadListener;

public class MakeConcensus {
	
	public static Map<String, Message> msg_map = new HashMap<String, Message>();
	public static ArrayList<Block> block_arr = new ArrayList<Block>();
	// List<Message> tmp_table = new ArrayList<Message>();
	
	public void insertMeassage(Message m) {
		if (m.operation_code == Constants.RESB || m.operation_code == Constants.RESR) {
			if (msg_map.get(m.timestamp) != null) {
				m_tmpBlock.addRecord(m.toString());
				m_tmpBlock.addRecord(msg_map.get(m.timestamp).toString());
				msg_map.remove(m.timestamp);
			}
		}
		else if (m.operation_code == Constants.REQB || m.operation_code == Constants.REQR)
			msg_map.put(m.timestamp, m);
		
		broadcast(BROADCASTTYPY.REQUESTRESPONSE, m.toString());
	}

	public static void broadcast(BROADCASTTYPY type, String str) {

		switch (type) {

		case REQUESTRESPONSE:
			BroadCast requstResponse = new BroadCast(10001);
			requstResponse.Send(str);
			break;

		case REQUSTBLOCK:

			BroadCast requstBlock = new BroadCast(10002);
			Message msg = new Message();
			msg.operation_code = Constants.RESBLOCK;
			requstBlock.Send(msg.toString());
			break;
			
			

		case SENDRESPOSE:
			BroadCast sendResponse = new BroadCast(10003);
			sendResponse.Send(":my IP");
			break;

		case SENDBLOCK:
			// dispatch block;
			BroadCast sendBlock = new BroadCast(10004);
			sendBlock.Send(m_tmpBlock.toString());
			break;

		default:
			;
			break;
		}

		;
	}

	public void listen() {
		// ¼àÌý»ØÓ¦ÇëÇó
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Listener responseListnener = new Listener(10003);
				Thread t = new Thread(responseListnener);
				t.start();
			}

		});
		t1.start();

		// ¼àÌýblockÇëÇó
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Listener responseListnener = new Listener(10004);
				Thread t = new Thread(responseListnener);
				t.start();
			}

		});
		t2.start();

		// ¼àÌý»ØÓ¦ÏìÓ¦
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				Listener responseListnener = new Listener(10003);
				Thread t = new Thread(responseListnener);
				t.start();
			}

		});
		t3.start();

		// ¼àÌýblockÏìÓ¦
		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				Listener responseListnener = new Listener(10004);
				Thread t = new Thread(responseListnener);
				t.start();
			}

		});
		t4.start();
	}

	public static void choseNextSpeaker() {
		int num_node = GlobalVariable.ipList.size();
		int speakerIndex = (int)(Math.random()*1000%num_node), index = 0;
		String nextSpeaker = "";
		for (String key : GlobalVariable.ipList.keySet()) {
			if (index == speakerIndex) {
				if (key == GlobalVariable.ID)
					speakerIndex = (++ speakerIndex) % num_node;
				else
					nextSpeaker = key;
			}
			index = (++ index) % num_node;
		}
		GlobalVariable.isSpeaker = false;
		System.out.println(nextSpeaker);
		
		//broadcast result
		
	}

	static Block m_tmpBlock;
	int roler;
	int state;
	static Block finalBlock;
	Message m_tmpMessage;

}
