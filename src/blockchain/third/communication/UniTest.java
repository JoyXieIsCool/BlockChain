package blockchain.third.communication;

public class UniTest extends UniListener{

	public UniTest(int p) {
		super(p);
	}
	public void doIT(String info){
		//Modify the following
		System.out.println("dosomethingTO"+info);
	}
	
}