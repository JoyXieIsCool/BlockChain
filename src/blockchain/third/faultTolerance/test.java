package blockchain.third.faultTolerance;

public class test {

	public static void main(String[] args) {
		Timer t=new Timer(5);
		Thread t1 = new Thread(t);
		t1.start(); 
		try {
			Thread.sleep(3000);
			t.restart();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
