package blockchain.third.communication;
//接收口
public class ListenTest {
		
	public static void main(String[] args) {
		
//		FileListener t =new FileListener(5000);
//		t.setPath("E:/get/b.txt");
//		
////		UniTest t=new UniTest(3000);
//		Thread t1 = new Thread(t);
//		t1.start();
		
		
//		UniListener RecordListener = new UniListener(3000); 
//		Thread t1 = new Thread(RecordListener);
//		UniListener BlockListener = new UniListener(3001); 
//		Thread t2 = new Thread(BlockListener);
		BroadListener IPListener = new BroadListener(4000);
		Thread t3 = new Thread(IPListener);
//		BroadListener RecordListener2 = new BroadListener(4001);
//		Thread t4 = new Thread(RecordListener2);
//		BroadListener RealeaseListener = new BroadListener(4002);
//		Thread t5 = new Thread(RealeaseListener);
//		BroadListener StoreListener = new BroadListener(4003);
//		Thread t6 = new Thread(StoreListener);
//		t1.start();
//		t2.start();
		t3.start();
//		t4.start();
//		t5.start();
//		t6.start();

	}
}
