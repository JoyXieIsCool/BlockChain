package blockchain.third.controller;

import java.util.HashMap;
import java.util.Map;

import blockchain.third.bean.BROADCASTTYPY;
import blockchain.third.bean.Block;
import blockchain.third.bean.Constants;
import blockchain.third.bean.DB;
import blockchain.third.bean.GlobalVariable;
import blockchain.third.communication.UniListener;
import blockchain.third.utils.JsonUtil;

public class RequestListener extends UniListener {

	public RequestListener(int p) {
		super(p);
	}

	public void doIT(String info) {
		System.out.println("--------vote here------");
		System.out.println("info is:" + info);
		Block block = new Block(info, Constants.JSON_FORMAT);
		if (GlobalVariable.isSpeaker == false) {
			// write final block
			DB.getDBInstance().addBlock(block);
			return;
		}
		
		MakeConcensus.block_arr.add(block);
		System.out.println(GlobalVariable.ID + "_" + "get a  block");
		if (GlobalVariable.isSpeaker
				&& MakeConcensus.block_arr.size() >= GlobalVariable.maxIpTable-1) {
			Map<String, Integer> blk_map = new HashMap<String, Integer>();

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
			MakeConcensus.broadcast(BROADCASTTYPY.FINALBLOCK,
					JsonUtil.transBlock2JsonStr(MakeConcensus.finalBlock));
			System.out.println("-----speaker write block------");
			DB.getDBInstance().addBlock(MakeConcensus.finalBlock);
			MakeConcensus.m_tmpBlock.clear();
		}
	}

}
