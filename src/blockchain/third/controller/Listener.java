package blockchain.third.controller;

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
import blockchain.third.utils.JsonUtil;

public class Listener extends BroadListener {

	public static boolean state = false;

	public Listener(int p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public void doIT(String info) {
		System.out.println("*************" + info);

		// 监听响应请求
		if (port == GlobalVariable.requestResponsePort) {
			Message msg = new Message(info);
			if (GlobalVariable.ID.equals(msg.receiver)) {
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
			//这里改成单播！！！！！！！！！
			//////////////////////////////////////////////////////////
			MakeConcensus.m_tmpBlock.generateHash();
			MakeConcensus.broadcast(BROADCASTTYPY.SENDBLOCK,
					JsonUtil.transBlock2JsonStr(MakeConcensus.m_tmpBlock));
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
				System.out.println("now tmpBlock size is: " + MakeConcensus.m_tmpBlock.getBlockSize());

				if (MakeConcensus.m_tmpBlock.getBlockSize() >= GlobalVariable.blockMaxRecord) {
					MakeConcensus.m_tmpBlock.generateHash();
					if (GlobalVariable.isSpeaker) {
						MakeConcensus.broadcast(BROADCASTTYPY.REQUSTBLOCK, "");
					} 
//						else {
//						MakeConcensus.broadcast(BROADCASTTYPY.SENDBLOCK,
//								MakeConcensus.m_tmpBlock.toString());
//					}

				}
			}

		}

		// 接受BLOCK
		else if (port == GlobalVariable.sendBlockPort) {
			System.out.println("--------vote here------");
			System.out.println("info is:" + info);
			Block block = new Block(info, Constants.JSON_FORMAT);
			if (GlobalVariable.isSpeaker == false) {
				// write final block
				DB.getDBInstance().addBlock(block);
				MakeConcensus.m_tmpBlock.clear();
				return;
			}
			
			MakeConcensus.block_arr.add(block);

			System.out.println(GlobalVariable.ID + "_" + "get a  block");
			System.out.println("L108: " + MakeConcensus.block_arr.size());
			if (GlobalVariable.isSpeaker
					&& MakeConcensus.block_arr.size() >= GlobalVariable.maxIpTable-1) {
				Map<String, Integer> blk_map = new HashMap<String, Integer>();
				// put myself block
				MakeConcensus.block_arr.add(MakeConcensus.m_tmpBlock);

				for (Block b : MakeConcensus.block_arr) {
					System.out.println(JsonUtil.transBlock2JsonStr(b));
					if (blk_map.get(b.hash) == null || blk_map.get(b.hash) == 0)
						blk_map.put(b.hash, 1);
					else
						blk_map.put(b.hash, blk_map.get(b.hash) + 1);

				}
				int max = -100;
				String max_hash = "";
				System.out.println("blk_map: " + blk_map.toString());
				for (String keyString : blk_map.keySet()) {
					System.out.println("---blk_map keystring---: " + keyString);
					if (blk_map.get(keyString).intValue() > max) {
						max = blk_map.get(keyString);
						max_hash = keyString;
					}
				}
				Block res_block = null;
				for (Block b : MakeConcensus.block_arr) {
					if (max_hash == null)
						System.out.println("max_hash is null");
					if (b.hash == null)
						System.out.println("b.hash is null");
					if (max_hash.equals(b.hash)) {
						res_block = b;
						break;
					}
				}

				MakeConcensus.choseNextSpeaker();
				MakeConcensus.finalBlock = res_block;
				System.out.println("L147 final_block: " + JsonUtil.transBlock2JsonStr(MakeConcensus.finalBlock));
				MakeConcensus.broadcast(BROADCASTTYPY.SENDBLOCK,
						JsonUtil.transBlock2JsonStr(MakeConcensus.finalBlock));
				System.out.println("-----speaker write block------");
				DB.getDBInstance().addBlock(MakeConcensus.finalBlock);
				MakeConcensus.m_tmpBlock.clear();

			}
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
