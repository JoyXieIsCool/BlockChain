package blockchain.third.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import blockchain.third.bean.Constants;
import blockchain.third.bean.Message;

public class MakeConcensus {
	
	Map<String, Message> msg_map = new HashMap<String, Message>();
	// List<Message> tmp_table = new ArrayList<Message>();
	
	public void insertMeassage(Message m) {
		if (m.operation_code == Constants.REQB || m.operation_code == Constants.REQR)
			msg_map.put(m.timestamp, m);
		broadcast();
	}

	public void broadcast() {

		switch (1) {

		case 1:
			;

			break;

		case 2:

			// insert a new record;

			if (msg_map.size() >= 10) {
				if (roler == 0) {// is a speaker;
					broadcast();

					while (state == 1) {
						select();
					}

				} else {
					broadcast();// dispatch block;
				}

			}

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

			if (msg_map.size() >= 10) {
				if (roler == 0) {// is a speaker;
					broadcast();

					while (state == 1) {
						select();//make a concensus;
						choseNextSpeaker();
					}

				} else {
					broadcast();// dispatch block;
				}

			}

			break;
		}

	}

	public void select() {
		;
	}

	public void choseNextSpeaker() {
		
		broadcast();
		
	}

	int roler;

	int state;

}
