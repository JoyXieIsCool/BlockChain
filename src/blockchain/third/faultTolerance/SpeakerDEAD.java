package blockchain.third.faultTolerance;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
				System.out
						.println("Speaker并不没有死亡！重发数据包"
								+ JsonUtil
										.transBlock2JsonStr(MakeConcensus.m_tmpBlock));
				MakeConcensus.unicast(TimerVar.SpeakerIP,
						GlobalVariable.sendBlockPort,
						JsonUtil.transBlock2JsonStr(MakeConcensus.m_tmpBlock));
				restart();
			} else {
				System.out.println("Speaker is dead. Find a new Speaker");
				Calendar c = Calendar.getInstance();
				int minute = c.get(Calendar.MINUTE);
				GlobalVariable.ipList.remove(TimerVar.SpeakerID);
				int sum = GlobalVariable.ipList.size() + 1;
				int key = minute % sum;
				String SpeakerID = "";
				List<String> keySet = new ArrayList<String>(
						GlobalVariable.ipList.keySet());
				keySet.add(GlobalVariable.ID);
				Collections.sort(keySet);
				System.out.println("SP53: " + keySet.get(0) + "key=" + key);
				SpeakerID = keySet.get(key);

				if (SpeakerID.equals(GlobalVariable.ID)) {
					// 我是speaker！！
					System.out.println("Speaker is dead." + SpeakerID
							+ " is the new Speaker");
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
