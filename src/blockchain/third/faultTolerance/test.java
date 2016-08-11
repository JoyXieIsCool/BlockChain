package blockchain.third.faultTolerance;

public class test {

	public static void main(String[] args) {
		Timer t=new Timer(5);
		Thread t1 = new Thread(t);
		t1.start(); 

	}

}
