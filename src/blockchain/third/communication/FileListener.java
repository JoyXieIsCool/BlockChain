package blockchain.third.communication;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 文件监听基类
 * 初始化需要设置端口//与发送端口相同
 * 需要先调用setPath路径进行设置保存路径
 */
public class FileListener implements Runnable {
	private String filePath;// 文件路径
	private int port;// 监听端口

	public FileListener(int p) {
		this.port = p;
	}

	public void setPath(String path) {// 设置保存路径
		this.filePath = path;
	}

	public String getPath() {// 获得文件保存路径
		return this.filePath;
	}

	public void run() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(port); // 设置端口
			System.out.println("文件监听" + port + "端口开始监听");
			while (true) {// 等待触发监听
				Socket socket = server.accept();
				receiveFile(socket); // 调用存文件的方法
				System.out.println(filePath + " 接收完成");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void receiveFile(Socket socket) throws IOException {
		byte[] inputByte = null;
		int length = 0;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		try {
			try {
				dis = new DataInputStream(socket.getInputStream());
//				File f = new File("D:/temp");  
//				if (!f.exists()) {
//					f.mkdir();
//				}
				fos = new FileOutputStream(new File(filePath));
				inputByte = new byte[1024];
				while ((length = dis.read(inputByte, 0, inputByte.length)) > 0) {
					fos.write(inputByte, 0, length);
					fos.flush();
				}
			} finally {
				if (fos != null)
					fos.close();
				if (dis != null)
					dis.close();
				if (socket != null)
					socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
