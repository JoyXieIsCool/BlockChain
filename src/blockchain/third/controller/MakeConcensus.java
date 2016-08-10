package blockchain.third.controller;

import java.util.ArrayList;
import java.util.List;

import blockchain.third.bean.Message;

public class MakeConcensus {
	
	List<Message> msg_list = new ArrayList<Message>();

	public void insertMeassage(Message m) {

		broadcast();

		;
	}

	public void broadcast() {

		switch (1) {

		case 1:
			;

			break;

		case 2:

			// insert a new record;

			if (msg_list.size() >= 10) {
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

			if (msg_list.size() >= 10) {
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
