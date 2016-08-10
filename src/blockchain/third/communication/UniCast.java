package blockchain.third.communication;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class UniCast {
	
	private String IP;
	private int port;
	
	UniCast(String ip,int p){
		this.IP=ip;
		this.port=p;
	}
	
	
    public void Send(String message) {
        try {
            //1.建立客户端socket连接，指定服务器位置及端口
            Socket socket =new Socket(IP,port);
            //2.得到socket读写流
            OutputStream os=socket.getOutputStream();
            PrintWriter pw=new PrintWriter(os);
            //输入流
            InputStream is=socket.getInputStream();
            pw.write(message);
            System.out.println("send "+message);
            pw.flush();
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