package blockchain.third.communication;

public class BroadTest extends UniListener{

	public BroadTest(int p) {
		super(p);
	}
	public void doIT(String info){
		//Modify the following
		System.out.println("dosomethingTO"+info);
	}
	
}