package blockchain.third.faultTolerance;

public class Timer implements Runnable {
	private double counts; // 秒级倒计时
	private boolean isOK = false;
	private double tmpTime;

	public Timer(double counts) {
		this.counts = counts;
		this.tmpTime = counts;
	}

	public void DisasterRecovery() {
		System.out.println("We Should do Something!");
	}

	public void setOK() {
		this.isOK = true;
		this.counts = this.tmpTime;
	}

	public void restart() {
		this.counts = this.tmpTime;
	}

	@Override
	public void run() {
		isOK=false;
		while (counts > 0 && !isOK) {
			System.out.println("还剩： " + counts + " 秒");
			counts--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (!isOK)
			DisasterRecovery();
	}

}
