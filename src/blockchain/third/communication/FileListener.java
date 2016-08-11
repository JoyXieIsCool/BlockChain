package blockchain.third.communication;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileListener implements Runnable {
	private String filePath;
	private int port;

	public FileListener(int p) {
		this.port = p;
	}

	public void setPath(String path) {
		this.filePath = path;
	}

	public String getPath() {
		return this.filePath;
	}

	public void run() {
		try {
			ServerSocket server = new ServerSocket(port);
			System.out.println("文件监听开始");
			while (true) {
				Socket socket = server.accept();
				receiveFile(socket);
				System.out.println(filePath+" 接收完成");
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
				File f = new File("D:/temp");
				if (!f.exists()) {
					f.mkdir();
				}
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
