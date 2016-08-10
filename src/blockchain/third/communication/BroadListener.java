package blockchain.third.communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class BroadListener implements Runnable {
	private int port; // 端口
	private DatagramSocket ds;
	private DatagramPacket dp;
	public BroadListener(int p) {
		this.port = p;// 设置广播端口
	}

	public void closeListener() {
		if (ds != null) {
			ds.close();
		}
	}
	
	public void run() {
		byte[] buf = new byte[1024];// 存储发来的消息
		StringBuffer sbuf = new StringBuffer();
		try {
			ds = new DatagramSocket(port);
			dp = new DatagramPacket(buf, buf.length);
			System.out.println("监听广播端口" + port + "打开：");
			while (!ds.isClosed()) {
				ds.receive(dp);
				for (int i = 0; i < 1024; i++) {
					if (buf[i] == 0) {
						break;
					}
					sbuf.append((char) buf[i]);
				}
				System.out.println("收到广播消息：" + sbuf.toString());
				buf = new byte[1024];
				dp = new DatagramPacket(buf, buf.length);
				sbuf = new StringBuffer();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			ds.close();
		}
	}
}
