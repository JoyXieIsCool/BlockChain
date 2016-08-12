package blockchain.third.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
/*
 * 单播发送基类
 * 初始化需要设置IP和端口
 * 调用Send()即可发送信息
 * 输入为信息的内容
 */
public class UniCast {
	private String IP; // IP地址
	private int port; // 端口号

	public UniCast(String ip, int p) {
		this.IP = ip; // 设置IP
		this.port = p; // 设置端口
	}

	public void Send(String message) {
		Socket socket = null;
		OutputStream os = null;
		PrintWriter pw = null;
		InputStream is = null;
		try {
			socket = new Socket(IP, port);// 新建socket
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			is = socket.getInputStream();
			pw.write(message);
			pw.flush();// 发送信息结束
			// 关闭打开的流
			socket.shutdownOutput();
			is.close();
			pw.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}