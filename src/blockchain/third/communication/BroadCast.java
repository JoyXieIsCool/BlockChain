package blockchain.third.communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/*
 * 发送广播基类
 * 声明时需要设置端口号//要与监听的端口号相同
 * 调用Send()即可
 * 输入需要在局域网中广播的message
 */
public class BroadCast {
	private int port; // 广播的端口

	public BroadCast(int p) {
		this.port = p;// 设置端口
	}

	public void Send(String message) {
		// 设置广播IP
		String host = "255.255.255.255";// 广播IP
		DatagramSocket ds = null;// 数据包
		try {
			InetAddress adds = InetAddress.getByName(host);
			ds = new DatagramSocket();
			DatagramPacket dp = new DatagramPacket(message.getBytes(),
					message.length(), adds, port);
			ds.send(dp);// 发送数据包
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ds != null)
				ds.close();
		}
	}
}
