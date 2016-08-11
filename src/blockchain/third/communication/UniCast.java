package blockchain.third.communication;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class UniCast {
	private String IP; //IP地址
	private int port;   //端口号
	
	public UniCast(String ip,int p){
		this.IP=ip; //设置IP
		this.port=p; //设置端口
	}
	
    public void Send(String message) {
        try {
            Socket socket =new Socket(IP,port);//新建socket
            OutputStream os=socket.getOutputStream();
            PrintWriter pw=new PrintWriter(os);
            InputStream is=socket.getInputStream();
            pw.write(message);
            System.out.println("[SentTo:" + IP + "] " + message); //发送信息结束
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