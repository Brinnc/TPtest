package test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	public static void main(String[] args) {
		System.out.println("시~작");
		
		//Timer: 실제 타이머의 기능을 수행하는 클래스
		Timer timer=new Timer();
		//TimerTask: 타이머 클래스가 수행되어야 할 내용을 작성하는 클래스
		TimerTask task=new TimerTask() {

			@Override
			public void run() {
				System.out.println("5분지남");
				
			}
			
		};
		
		timer.schedule(task, 300000);
	}
}
