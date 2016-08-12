package blockchain.third.controller;

import blockchain.third.bean.BROADCASTTYPY;
import blockchain.third.bean.Block;
import blockchain.third.bean.Constants;
import blockchain.third.bean.DB;
import blockchain.third.bean.GlobalVariable;
import blockchain.third.bean.Message;
import blockchain.third.communication.BroadListener;
import blockchain.third.faultTolerance.SpeakerDEAD;
import blockchain.third.faultTolerance.TimerVar;
import blockchain.third.utils.JsonUtil;

public class Listener extends BroadListener {

	public static boolean state = false;

	public Listener(int p) {
		super(p);
	}

	public void doIT(String info) {
		System.out.println("*************" + info);

		// 监听响应请求
		if (port == GlobalVariable.requestResponsePort) {
			Message msg = new Message(info);
			if (GlobalVariable.ID.equals(msg.receiver)) {
				GlobalVariable.alertMessage = info;
			} else {
				MakeConcensus.msg_map.put(msg.timestamp, msg);
				Message s_msg = new Message(info);

				s_msg.operation_code = Constants.RESB;
				s_msg.receiver = msg.sender;
				s_msg.sender = msg.receiver;
				s_msg.timestamp = msg.timestamp;
				// // decide send what response in each time;
				if (true) {
					s_msg.value = 1;
				} else {
					s_msg.value = 0;
				}
				MakeConcensus.m_tmpBlock.addRecord(msg.toString());
				MakeConcensus.m_tmpBlock.addRecord(s_msg.toString());
				System.out.println(GlobalVariable.ID + "_" + "get a  request");
				MakeConcensus.broadcast(BROADCASTTYPY.SENDRESPOSE,
						s_msg.toString());
			}
		}

		// 监听BLOCK请求
		else if (port == GlobalVariable.requestBlockPort) {
			// 这里改成单播！！！！！！！！！
			// ////////////////////////////////////////////////////////

			// Narc
			MakeConcensus.m_tmpBlock.generateHash();
			String SpeakerID = info.split("_")[1];
			String SpeakerIP = GlobalVariable.ipList.get(SpeakerID);
			MakeConcensus.unicast(SpeakerIP, GlobalVariable.sendBlockPort,
					JsonUtil.transBlock2JsonStr(MakeConcensus.m_tmpBlock));
			// Narc

			// 容错
			SpeakerDEAD s = new SpeakerDEAD(GlobalVariable.countDown);
			TimerVar.SpeakerID = SpeakerID;
			TimerVar.SpeakerIP = SpeakerIP;
			Thread timer = new Thread(s);
			timer.start();

			// MakeConcensus.broadcast(BROADCASTTYPY.SENDBLOCK,
			// JsonUtil.transBlock2JsonStr(MakeConcensus.m_tmpBlock));

			// Narc

			// MakeConcensus.broadcast(BROADCASTTYPY.SENDBLOCK,
			// JsonUtil.transBlock2JsonStr(MakeConcensus.m_tmpBlock));

			// send block;
			System.out
					.println(GlobalVariable.ID + "_" + "get a  block request");
		}

		// 接受响应
		else if (port == GlobalVariable.sendResponsePort) {
			Message msg = new Message(info);

			// add a new record;
			// if record size over a num, then ........;
			Message t_msg = MakeConcensus.msg_map.get(msg.timestamp);
			if (t_msg != null) {
				System.out.println(GlobalVariable.ID + "_" + "get a  response");
				MakeConcensus.m_tmpBlock.addRecord(MakeConcensus.msg_map.get(
						msg.timestamp).toString());
				MakeConcensus.m_tmpBlock.addRecord(msg.toString());
				MakeConcensus.msg_map.remove(msg.timestamp);
				System.out.println("now tmpBlock size is: "
						+ MakeConcensus.m_tmpBlock.getBlockSize());

				if (MakeConcensus.m_tmpBlock.getBlockSize() >= GlobalVariable.blockMaxRecord) {
					MakeConcensus.m_tmpBlock.generateHash();
					if (GlobalVariable.isSpeaker) {
						MakeConcensus.broadcast(BROADCASTTYPY.REQUSTBLOCK, "");
					}
					// else {
					// MakeConcensus.broadcast(BROADCASTTYPY.SENDBLOCK,
					// MakeConcensus.m_tmpBlock.toString());
					// }

				}
			}

		}

		// 接受FinalBLOCK
		else if (port == GlobalVariable.sendFinalBlockPort) {
			System.out.println("--------Get Final Block------");
			System.out.println("info is:" + info);

			Block block = new Block(info, Constants.JSON_FORMAT);
			// if (GlobalVariable.isSpeaker == false) {
			// write final block
			DB.getDBInstance().addBlock(block);
			MakeConcensus.m_tmpBlock.clear();
			// return;
		}
		else if (port == GlobalVariable.receveSpeakerIDPort) {
			System.out.println(GlobalVariable.ID + "_" + "become a speaker");
			if (GlobalVariable.ID.equals(info)) {
				GlobalVariable.isSpeaker = true;
			}
		}

		// 用来重写的方法
		System.out.println("DoSomething" + info);
	}
}
