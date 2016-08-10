package blockchain.third.controller;

public class MakeConcensus {

	public void insertMeassage(Meassage m) {

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

			if (m_tmp.num >= 10) {
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

			if (m_tmp.num >= 10) {
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
	
	TempTable m_tmp;

	int roler;

	int state;

}
