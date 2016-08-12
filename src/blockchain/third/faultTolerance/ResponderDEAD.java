package blockchain.third.faultTolerance;

import java.net.InetAddress;
import java.util.Calendar;

import blockchain.third.bean.BROADCASTTYPY;
import blockchain.third.bean.GlobalVariable;
import blockchain.third.communication.UniCast;
import blockchain.third.controller.MakeConcensus;
import blockchain.third.utils.JsonUtil;

public class ResponderDEAD extends Timer {

	public ResponderDEAD(double counts) {
		super(counts);
	}
	public static boolean ping(String ipAddress) throws Exception {
		int timeOut = 3000; // 超时应该在3钞以上
		boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);
		// 当返回值是true时，说明host是可用的，false则不可。
		return status;
	}
	public void DisasterRecovery() {
		try {
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
