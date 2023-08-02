package org.sp.tproject.main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import util.StringManager;

public class CurrentTime extends JPanel implements Runnable {
	MainFrame mainFrame;
	private JLabel la_date;
	private JLabel la_time;
	
	Calendar cal;
	int currentYear;
	int currentMonth;
	int currentDate;

	public CurrentTime() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.WHITE, 1));
		
		cal=Calendar.getInstance(); //현재 날짜 객체
		currentYear=cal.get(Calendar.YEAR);
		currentMonth=cal.get(Calendar.MONTH);
		currentDate=cal.get(Calendar.DATE);
		System.out.println(currentYear+"년"+(currentMonth+1)+"월"+currentDate+"일");
		String nowDate=StringManager.getNumString(currentYear)+"-"+StringManager.getNumString(currentMonth+1)+"-"+StringManager.getNumString(currentDate);
		
		String time = getCurrentTime(); //현재 시간 객체
		
		la_date=new JLabel(nowDate);
		la_date.setFont(new Font("digital-7", Font.BOLD, 40));
		la_date.setHorizontalAlignment(JLabel.CENTER);
		
		la_time = new JLabel(time);
		la_time.setFont(new Font("digital-7", Font.BOLD, 90));
		la_time.setHorizontalAlignment(JLabel.CENTER);
		
		add(la_date, BorderLayout.NORTH);
		add(la_time, BorderLayout.CENTER);
		
		setPreferredSize(new Dimension(300,200));
		//setSize(300, 200);
		Thread t1 = new Thread(this);
		t1.start();

		setVisible(true);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				String time = getCurrentTime();
				la_time.setText(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public String getCurrentTime() {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);

		String time = StringManager.getNumString(hour) + ":" + StringManager.getNumString(min) + ":" + StringManager.getNumString(sec);
		return time;
	}

}


