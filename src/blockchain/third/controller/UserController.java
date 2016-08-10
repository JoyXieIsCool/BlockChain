package blockchain.third.controller;

import java.util.Scanner;

/**
 * 处理与用户的UI交互
 */
public class UserController {

	public String decideOperation() {
		while(true) {
			Scanner scanner = new Scanner(System.in);
			String str = scanner.next();
			switch (str) {
			case "QUERY":
				// 查询数据库
				System.out.println("query DB");
				break;
			case "ADD":
				// 添加交易记录
				String msg = scanner.next();
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
	
	public static void main(String[] args) {
		new UserController().decideOperation();
	}
}
