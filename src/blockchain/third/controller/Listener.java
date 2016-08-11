package blockchain.third.controller;

import java.util.HashMap;
import java.util.Map;

import blockchain.third.bean.BROADCASTTYPY;
import blockchain.third.bean.Block;
import blockchain.third.bean.Constants;
import blockchain.third.bean.GlobalVariable;
import blockchain.third.bean.Message;
import blockchain.third.communication.BroadCast;
import blockchain.third.communication.BroadListener;

public class Listener extends BroadListener {
	
	public static boolean state = false;

	public Listener(int p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public void doIT(String info) {

		// 监听响应请求
		if (port == GlobalVariable.requestResponsePort) {
			Message msg = new Message(info);
			if (msg.receiver == GlobalVariable.ID) {
				Message s_msg = new Message(info);

				s_msg.operation_code = Constants.RESB;
				s_msg.receiver = msg.sender;
				s_msg.sender = msg.receiver;
				s_msg.timestamp = msg.timestamp;
				//// decide send what response in each time;
				if (true) {
					s_msg.value = 1;
				} else {
					s_msg.value = 0;
				}
				MakeConcensus.broadcast(BROADCASTTYPY.SENDRESPOSE, msg.toString());
			}
		}

		// 监听BLOCK请求
		else if (port == GlobalVariable.requestBlockPort) {
			MakeConcensus.broadcast(BROADCASTTYPY.SENDBLOCK, MakeConcensus.m_tmpBlock.toString());
			// send block;
		}

		// 接受响应
		else if (port == GlobalVariable.sendResponsePort) {
			Message msg = new Message(info);

			// add a new record;
			// if record size over a num, then ........;
			Message t_msg = null;
			if ((t_msg = MakeConcensus.msg_map.get(msg.timestamp)) != null) {
				MakeConcensus.m_tmpBlock.addRecord(MakeConcensus.msg_map.get(msg.timestamp).toString());
				MakeConcensus.msg_map.remove(msg.timestamp);

				if (MakeConcensus.m_tmpBlock.getBlockSize() >= 10) {
					if (GlobalVariable.isSpeaker) {
						MakeConcensus.broadcast(BROADCASTTYPY.REQUSTBLOCK, "");		
					}
					else {
						MakeConcensus.broadcast(BROADCASTTYPY.SENDBLOCK, MakeConcensus.m_tmpBlock.toString());
					}

				}
			}

		}

		// 接受BLOCK
		else if (port == GlobalVariable.sendBlockPort) {
			Block block = new Block(info);
			MakeConcensus.block_arr.add(block);
			
			
			if (GlobalVariable.isSpeaker && MakeConcensus.block_arr.size() >= 5) {
				Map<String, Integer> blk_map = new HashMap<String, Integer>();
				
				for (Block b : MakeConcensus.block_arr) {
					if (blk_map.get(b.hash) == null || blk_map.get(b.hash) == 0)
						blk_map.put(b.hash, 1);
					else
						blk_map.put(b.hash, blk_map.get(b.hash) + 1);
					
				}
				int max = -100;
				String max_hash = "";
				for (String keyString : blk_map.keySet()) {
					if (blk_map.get(keyString).intValue() > max) {
						max = blk_map.get(keyString);
						max_hash = keyString;
					}
				}
				Block res_block = null;
				for (Block b : MakeConcensus.block_arr) {
					if (b.hash == max_hash) {
						res_block = b;
						break;
					}
				}
				
				MakeConcensus.choseNextSpeaker();
				MakeConcensus.finalBlock = res_block;
				MakeConcensus.broadcast(BROADCASTTYPY.SENDBLOCK, MakeConcensus.finalBlock.toString());
				
			}
		}

		// 用来重写的方法
		System.out.println("DoSomething" + info);
	}

}
