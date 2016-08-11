package blockchain.third.controller;

import java.util.List;
import java.util.Scanner;

import blockchain.third.bean.Block;
import blockchain.third.bean.DB;
import blockchain.third.bean.Message;
import blockchain.third.utils.JsonUtil;

public class TerminalController {
	
	public static void main(String[] args) {
		// 部署节点
		new JoinController();
		MakeConcensus.listen();
		// 
		new TerminalController().decideOperation();
	}

	public String decideOperation() {
		while(true) {
			Scanner scanner = new Scanner(System.in);
			String str = scanner.next();
			switch (str) {
			case "QUERY":
				// 查询数据库
				int currentBlock = scanner.nextInt();
				List<Block> blocks = DB.getDBInstance().queryBlock(currentBlock);
				String blockJsonString = JsonUtil.transBlock2JsonStr(blocks);
				
				System.out.println(blockJsonString);
				break;
			case "ADD":
				// 添加交易记录
				Message msg = new Message(scanner.next());
				MakeConcensus mc = new MakeConcensus();
				mc.insertMeassage(msg);
				System.out.println(msg);
				break;

			default:
				System.out.println(str);
				break;
			}
			if("QUIT".equals(str)) {
				scanner.close();
				break;
			}
		}
		return null;
	}
}
