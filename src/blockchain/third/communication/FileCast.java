package blockchain.third.communication;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class FileCast {
	private int port;

	public FileCast(int p) {
		this.port = p;
	}
	
	public void Send(String IP,String filePath) throws IOException {
		int length = 0;
		double sumL = 0;
		byte[] sendBytes = null;
		Socket socket = null;
		DataOutputStream dos = null;
		FileInputStream fis = null;
		boolean bool = false;
		try {
			File file = new File(filePath);  
			long l = file.length();
			socket = new Socket();
			socket.connect(new InetSocketAddress(IP, port));
			dos = new DataOutputStream(socket.getOutputStream());
			fis = new FileInputStream(file);
			sendBytes = new byte[1024];
			while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
				sumL += length;
				System.out.println("已完成" + ((sumL / l) * 100) + "%");
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
		System.out.println(bool ? "success" : "fail");
	}
}