package blockchain.third.communication;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Cast {

	public static void main(String[] args) throws UnknownHostException {
		String IP="127.0.0.1";
		String myIP=InetAddress.getLocalHost().toString();
		UniCast RecordCast = new UniCast(IP, 3000);
		RecordCast.Send(myIP+":P2P_RecordCast");
//		UniCast BlockCast = new UniCast(IP, 3001);
//		BlockCast.Send(myIP+":my block");
//
//		BroadCast IPCast = new BroadCast(4000);
//		IPCast.Send(myIP+":my IP");
//		BroadCast RecordCast2 = new BroadCast(4001);
//		RecordCast2.Send(myIP+":BroadCast");
//		BroadCast RealeaseCast = new BroadCast(4002);
//		RealeaseCast.Send(myIP+":RealeaseCast");
//		BroadCast StoreCast = new BroadCast(4003);
//		StoreCast.Send(myIP+":StoreCast");

	}

}
