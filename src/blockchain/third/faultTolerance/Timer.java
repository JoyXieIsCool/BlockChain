package blockchain.third.faultTolerance;

public class Timer implements Runnable {
	private double counts; //秒级倒计时

	public Timer(double counts) {
		this.counts = counts;
	}

	@Override
	public void run() {
		while (counts>0) {
			System.out.println("还剩： " + counts + " 秒");
			counts--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
