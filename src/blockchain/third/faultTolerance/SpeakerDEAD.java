package blockchain.third.faultTolerance;

import java.net.InetAddress;
import java.util.Calendar;

import blockchain.third.bean.BROADCASTTYPY;
import blockchain.third.bean.GlobalVariable;
import blockchain.third.communication.UniCast;
import blockchain.third.controller.MakeConcensus;
import blockchain.third.utils.JsonUtil;

public class SpeakerDEAD extends Timer {

	public SpeakerDEAD(double counts) {
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
			if (ping(TimerVar.SpeakerIP)) {
				// Speaker并不没有死亡！重发数据包
				System.out.println("Speaker并不没有死亡！重发数据包"+JsonUtil.transBlock2JsonStr(MakeConcensus.m_tmpBlock));
				MakeConcensus.unicast(TimerVar.SpeakerIP, GlobalVariable.sendBlockPort,
						JsonUtil.transBlock2JsonStr(MakeConcensus.m_tmpBlock));
				restart();
			} else {
				System.out.println("Speaker is dead. Find a new Speaker");
				Calendar c = Calendar.getInstance();
				int minute = c.get(Calendar.MINUTE);
				GlobalVariable.ipList.remove(TimerVar.SpeakerID);
				int sum = GlobalVariable.ipList.size() + 1;
				int key = minute % sum;
				String SpeakerID = 'A' + key + "";
				while (!GlobalVariable.ipList.containsKey(SpeakerID)) {
					key++;
					SpeakerID = 'A' + key + "";
					if (key >= sum)
						System.out.println("BOOM!");
				}
				if (SpeakerID.equals(GlobalVariable.ID)) {
					// 我是speaker！！
					System.out.println("Speaker is dead. I am the new Speaker");
					MakeConcensus.broadcast(BROADCASTTYPY.REQUSTBLOCK, "");
				} else {
					// 等着Speaker发言！！
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
