package blockchain.third.communication;

public class Lis {

	public static void main(String[] args) {
		UniListener RecordListener = new UniListener(3000); // µã¶Ôµã¼ÇÂ¼µÄ¼àÌı
		Thread t1 = new Thread(RecordListener);
		UniListener BlockListener = new UniListener(3001); // Ë½ÓĞ¿é¼àÌı
		Thread t2 = new Thread(BlockListener);

		BroadListener IPListener = new BroadListener(4000); // IP¼àÌı
		Thread t3 = new Thread(IPListener);
		BroadListener RecordListener2 = new BroadListener(4001); // ¹ã²¥¼ÇÂ¼¼àÌı
		Thread t4 = new Thread(RecordListener2);
		BroadListener RealeaseListener = new BroadListener(4002); // ÊÍ·ÅË½ÓĞ¿é¼àÌı
		Thread t5 = new Thread(RealeaseListener);
		BroadListener StoreListener = new BroadListener(4003); // ´æ´¢¹«ÓĞÁ´¼àÌı
		Thread t6 = new Thread(StoreListener);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();

	}
}
