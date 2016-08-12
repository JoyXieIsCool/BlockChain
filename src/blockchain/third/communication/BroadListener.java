package blockchain.third.communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
/*
 * 广播监听基类
 * 初始化需要设置端口号//需要与对应的Cast端口号相同
 * 
 */
public class BroadListener implements Runnable {
	public int port; // 端口号
	private DatagramSocket ds; //数据包socket
	private DatagramPacket dp; //数据包

	public BroadListener(int p) {
		this.port = p;// 设置端口号
	}

	public void closeListener() { //关闭监听
		if (ds != null) {
			ds.close();
		}
	}

	public void doIT(String info) {
		//用来重写的方法
		//你需要重写这个方法来处理对监听的响应
	}

	public void run() {
		
		byte[] buf = new byte[1024];// 暂存数组
		StringBuffer sbuf = new StringBuffer(); //输出的字符串buffer
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String localip=addr.getHostAddress().toString();//获得本机IP
			ds = new DatagramSocket(port);
			dp = new DatagramPacket(buf, buf.length);
			System.out.println("My BoradListener port: " + port + "is opened");
			while (!ds.isClosed()) {
				ds.receive(dp);
				InetAddress getIP=dp.getAddress();
				if(!localip.equals(getIP.toString().substring(1))){//如果不是自己发出的广播，则需要进行处理
					for (int i = 0; i < 1024; i++) {
						if (buf[i] == 0) {
							break;
						}
						sbuf.append((char) buf[i]);
					}
					doIT(sbuf.toString());   //对监听进行响应
				}
				//对缓存进行清空处理
				buf = new byte[1024];
				dp = new DatagramPacket(buf, buf.length);
				sbuf = new StringBuffer();
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ds.close(); //关闭监听
		}
	}
}
