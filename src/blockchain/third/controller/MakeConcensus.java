package blockchain.third.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import blockchain.third.bean.Block;
import blockchain.third.bean.Constants;
import blockchain.third.bean.GlobalVariable;
import blockchain.third.bean.Message;

public class MakeConcensus {
	
	Map<String, Message> msg_map = new HashMap<String, Message>();
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
		
		broadcast(m);
	}

	public void broadcast(Message m) {

		switch (1) {

		case 1:
			;

			break;

		case 2:

			// insert a new record;

			break;
		}
		;
	}

	public void listen() {

		switch (1) {

		case 1:
			;

			break;

		case 2:

			// insert a new record;

			break;
		}

	}

	public void choseNextSpeaker() {
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

	Block m_tmpBlock;
	int roler;
	int state;

}
