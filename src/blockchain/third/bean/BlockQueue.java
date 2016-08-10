package blockchain.third.bean;

public class BlockQueue {
	public static int Max_Len = 50;
	Block queue[] = new Block[Max_Len];
	
	public BlockQueue() {
		first = 0;
		last = 0;
	}
	
	public void insert(Block m) {
		queue[last ++] = m;
		last %= Max_Len;
	}

	public Block pop() {
		Block tmp = queue[first ++];
		first %= Max_Len;
		
		return tmp;
	}
	
	public void clear() {
		first = 0;
		last = 0;
	}

	int first;
	int last;
}
