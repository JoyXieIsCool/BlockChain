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
//            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            //3.利用流按照一定的操作，对socket进行读写操作
            pw.write(message);
            pw.flush();
            socket.shutdownOutput();
            //接收服务器的相应
//            String reply=null;
//            while(!((reply=br.readLine())==null)){
//                System.out.println("接收服务器的信息："+reply);
//            }
            //4.关闭资源
//            br.close();
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