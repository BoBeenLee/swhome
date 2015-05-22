package control.init;

import java.util.TimerTask;

import data.bo.LotteryBO;

public class LotteryTimerTask extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		LotteryBO lotteryBo = new LotteryBO();
		
		lotteryBo.getWinLottery();
		
		System.out.println("Hello World!2");
	}
	
}
