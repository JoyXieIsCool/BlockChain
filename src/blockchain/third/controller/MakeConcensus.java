package blockchain.third.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import blockchain.third.bean.BROADCASTTYPY;
import blockchain.third.bean.Block;
import blockchain.third.bean.Constants;
import blockchain.third.bean.DB;
import blockchain.third.bean.GlobalVariable;
import blockchain.third.bean.Message;
import blockchain.third.communication.BroadCast;
import blockchain.third.communication.BroadListener;
import blockchain.third.communication.UniCast;
import blockchain.third.utils.JsonUtil;

public class MakeConcensus {
	
	public static Map<String, Message> msg_map = new HashMap<String, Message>();
	public static ArrayList<Block> block_arr = new ArrayList<Block>();
	// List<Message> tmp_table = new ArrayList<Message>();
	
	public static void insertMeassage(Message m) {
		if (m.operation_code == Constants.RESB || m.operation_code == Constants.RESR) {
			if (msg_map.get(m.timestamp) != null) {
				m_tmpBlock.addRecord(m.toString());
				m_tmpBlock.addRecord(msg_map.get(m.timestamp).toString());
				msg_map.remove(m.timestamp);
			}
		}
		else if (m.operation_code == Constants.REQB || m.operation_code == Constants.REQR) {
			msg_map.put(m.timestamp, m);
		}
		
		broadcast(BROADCASTTYPY.REQUESTRESPONSE, m.toString());
	}
	
	
	public static void unicast(String ip, int port,String str){
		UniCast uni = new UniCast(ip,port);
		uni.Send(str);
		System.out.println(GlobalVariable.ID + "_" + "send a block");
		
	}
	
	/**
	 * 响应与自己相关的请求，并把结果广播出去 
	 */
	public static void ackRequest(boolean isAck) {
		Message s_msg = new Message(GlobalVariable.alertMessage);
		Message msg = new Message(GlobalVariable.alertMessage);

		s_msg.operation_code = Constants.RESB;
		s_msg.receiver = msg.sender;
		s_msg.sender = msg.receiver;
		s_msg.timestamp = msg.timestamp;
		// // decide send what response in each time;
		if (isAck) {
			s_msg.value = 1;
		} else {
			s_msg.value = 0;
		}
		MakeConcensus.m_tmpBlock.addRecord(msg.toString());
		MakeConcensus.m_tmpBlock.addRecord(s_msg.toString());
		System.out.println(GlobalVariable.ID + "_" + "get a  request");
		broadcast(BROADCASTTYPY.SENDRESPOSE,
				s_msg.toString());
	}
	
	public static void broadcast(BROADCASTTYPY type, String str) {

		switch (type) {

		case REQUESTRESPONSE:
			
			BroadCast requstResponse = new BroadCast(GlobalVariable.requestResponsePort);
			requstResponse.Send(str);
			System.out.println(GlobalVariable.ID + "_" + "request a response" + "_" + "message: " + str);
			break;

		case REQUSTBLOCK:

			BroadCast requstBlock = new BroadCast(GlobalVariable.requestBlockPort);
			Message msg = new Message();
			msg.operation_code = Constants.RESBLOCK;
			System.out.println(GlobalVariable.ID + "_" + "request a block");
			requstBlock.Send(msg.toString());
			break;
			
			

		case SENDRESPOSE:
			BroadCast sendResponse = new BroadCast(GlobalVariable.sendResponsePort);
			sendResponse.Send(str);
			System.out.println(GlobalVariable.ID + "_" + "send a response" + "_" + "message: " + str);
			break;

		case SENDBLOCK:
			// dispatch block;
			BroadCast sendBlock = new BroadCast(GlobalVariable.sendBlockPort);
			System.out.println(GlobalVariable.ID + "_" + "send a block");
			sendBlock.Send(str);
			
			
			
			
			
			break;
		
		case SENDSPEAKERID:
			// dispatch block;
			BroadCast sendSpeakerID = new BroadCast(GlobalVariable.receveSpeakerIDPort);
			///////////////
			sendSpeakerID.Send(MakeConcensus.nextSpeaker);
			System.out.println(GlobalVariable.ID + "_" + "send next speaker" + "_" + MakeConcensus.nextSpeaker);
			break;

		default:
			;
			break;
		}

		;
	}

	public static void listen() {
		// 监听回应请求
		Listener responseListnener = new Listener(GlobalVariable.requestResponsePort);
		Thread t = new Thread(responseListnener);
		t.start();

		// 监听block请求
		Listener blockResponseListnener = new Listener(GlobalVariable.requestBlockPort);
		Thread tblock = new Thread(blockResponseListnener);
		tblock.start();

		// 监听回应响应
		Listener responseListnener3 = new Listener(GlobalVariable.sendResponsePort);
		Thread t3 = new Thread(responseListnener3);
		t3.start();

		// 监听block响应
		
		//Listener responseListnener4 = new Listener(GlobalVariable.sendBlockPort);
		//Narc
		RequestListener responseListnener4 = new RequestListener(GlobalVariable.sendBlockPort);
		Thread t4 = new Thread(responseListnener4);
		t4.start();
		
		// 监听block响应
		Listener responseListnener5 = new Listener(GlobalVariable.receveSpeakerIDPort);
		Thread t5 = new Thread(responseListnener5);
		t5.start();
	}

	public static void choseNextSpeaker() {
		int num_node = GlobalVariable.ipList.size();
		int speakerIndex = (int)(Math.random()*1000%num_node), index = 0;
		MakeConcensus.nextSpeaker = "";
		for (String key : GlobalVariable.ipList.keySet()) {
			if (index == speakerIndex) {
				if (key == GlobalVariable.ID)
					speakerIndex = (++ speakerIndex) % num_node;
				else
					MakeConcensus.nextSpeaker = key;
			}
			index = (++ index) % num_node;
		}
		GlobalVariable.isSpeaker = false;
		System.out.println(MakeConcensus.nextSpeaker);
		
		MakeConcensus.broadcast(BROADCASTTYPY.SENDSPEAKERID, MakeConcensus.nextSpeaker);
		
		
	}

	public static Block m_tmpBlock = new Block(DB.getDBInstance().getLastBlockHash());
	int roler;
	int state;
	static Block finalBlock;
	Message m_tmpMessage;
	public static String nextSpeaker = "";

}
