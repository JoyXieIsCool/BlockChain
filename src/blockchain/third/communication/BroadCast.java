package blockchain.third.communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class BroadCast{
	private int port;
	private DatagramSocket ds;
	public BroadCast(int p){
		this.port=p;//设置端口
	}
		
	public void Send(String message){ 
		//设置广播IP
		String host = "255.255.255.255";//广播IP
		try {
			InetAddress adds = InetAddress.getByName(host);
			ds = new DatagramSocket();
			DatagramPacket dp = new DatagramPacket(message.getBytes(),
					message.length(), adds, port);
			ds.send(dp);
			System.out.println("send "+message); //发送信息结束
			ds.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
