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
			if(ping(MyVar.SpeakerIP)){
				//Speak而并不没有死亡！重发数据包
				UniCast unicast = new UniCast(MyVar.SpeakerIP,MyVar.SpeakerPort);
				unicast.Send(JsonUtil.transBlock2JsonStr(MakeConcensus.m_tmpBlock));
				restart();
			}
			else{
				Calendar c = Calendar.getInstance();
				int second = c.get(Calendar.SECOND);
				GlobalVariable.ipList.remove(MyVar.SpeakerID);
				int sum=GlobalVariable.ipList.size()+1;
				int key=second%sum;
				if(key==0){
					//我是speaker！！
					MakeConcensus.broadcast(BROADCASTTYPY.REQUSTBLOCK, "");
				}else{
					//等着Speaker发言！！
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
