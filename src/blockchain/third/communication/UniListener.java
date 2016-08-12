package blockchain.third.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class UniListener implements Runnable {
	private int port; //端口号

	public UniListener(int p) {
		this.port = p; //设置端口号
	}
	public void doIT(String info){
		//用来重写的方法
		System.out.println("Get UniCast" + info);
	}
	public void run() {
		ServerSocket serverSocket=null;
		try {
			serverSocket = new ServerSocket(port);  //新建Socket
			String IP=InetAddress.getLocalHost().toString(); //获得本机IP
			System.out.println(IP+" PC " + port + "is listening"); //输出状态
			while (true) {
				Socket socket = serverSocket.accept(); 
				InputStream is = socket.getInputStream();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is)); 
				String info = null;
				while (!((info = br.readLine()) == null)) {
					doIT(info);   //进行操作
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}