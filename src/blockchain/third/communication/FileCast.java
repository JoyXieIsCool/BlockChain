package blockchain.third.communication;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/*
 * 文件传输基类
 * 初始化需要设置对应端口//需要与接收端口相同
 * 调用Send()方法即可发送文件
 * 输出为目标IP地址以及文件的路径
 */
public class FileCast {
	private int port;

	public FileCast(int p) {
		this.port = p;
	}

	public void Send(String IP, String filePath) throws IOException {
		int length = 0;
		double sumL = 0;
		byte[] sendBytes = null;
		Socket socket = null;
		DataOutputStream dos = null; // 输出流
		FileInputStream fis = null; // 输入流
		boolean bool = false;
		try {
			File file = new File(filePath);
			long l = file.length();// 记录文件的长度
			socket = new Socket();// 新开一个socket
			socket.connect(new InetSocketAddress(IP, port));
			dos = new DataOutputStream(socket.getOutputStream());
			fis = new FileInputStream(file);
			sendBytes = new byte[1024]; // 缓存数组
			while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
				sumL += length; // 统计传输的情况
				dos.write(sendBytes, 0, length);
				dos.flush();
			}
			if (sumL == l) {
				bool = true;
			}
		} catch (Exception e) {
			bool = false;
			e.printStackTrace();
		} finally {
			if (dos != null)
				dos.close();
			if (fis != null)
				fis.close();
			if (socket != null)
				socket.close();
		}
		System.out.println(bool ? "file transport success"
				: "file transport fail");
	}
}