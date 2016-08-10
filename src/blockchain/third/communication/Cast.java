package blockchain.third.communication;

public class Cast {

	public static void main(String[] args) {
		UniCast RecordCast = new UniCast("127.0.0.1", 3000);
		RecordCast.Send("广播记录");
		UniCast BlockCast = new UniCast("127.0.0.1", 3001);
		BlockCast.Send("一个私有块");

		BroadCast IPCast = new BroadCast(4000);
		IPCast.Send("127.0.0.1");
		BroadCast RecordCast2 = new BroadCast(4001);
		RecordCast2.Send("点对点记录");
		BroadCast RealeaseCast = new BroadCast(4002);
		RealeaseCast.Send("你需要释放私有块");
		BroadCast StoreCast = new BroadCast(4003);
		StoreCast.Send("我的公有链");

	}

}
