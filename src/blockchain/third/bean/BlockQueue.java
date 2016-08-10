package blockchain.third.bean;

public class BlockQueue {
	public static int Max_Len = 30;
	
	BlockQueue() {
		first = 0;
		last = 0;
	}
	
	public void insert(Block m) {
		if (last > Max_Len) {	
			////
			//exit(0);
			return;
		}
		queue[last++] = m;
	}

	public Block pop() {
		return null;
	}
	
	public void clear() {
		first = 0;
		last = 0;
	}

	int first;
	int last;
	Block queue[] = new Block[Max_Len];
}
