package control.init;

import java.util.TimerTask;

import data.bo.ArticleBO;

public class ArticleTimerTask extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArticleBO articleBo = new ArticleBO();
		
		articleBo.getCheckNoticeDate();
		
		System.out.println("Hello World!");
	}
}
