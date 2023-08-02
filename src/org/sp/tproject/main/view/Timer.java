package org.sp.tproject.main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import util.StringManager;

public class Timer extends JPanel implements Runnable {
	JLabel label;
	JButton bt;

	public static int sec;
	public static int min;

	Thread tt;

	public Timer() {
		// setSec();
		String timer = StringManager.getNumString(min) + " : " + StringManager.getNumString(sec);

		label = new JLabel(timer);
		bt=new JButton("START");
		
		setLayout(new FlowLayout());
		//setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.WHITE, 1));

		label.setPreferredSize(new Dimension(250, 150));
		label.setBackground(Color.WHITE);
		label.setFont(new Font("digital-7", Font.BOLD, 50));	
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label);
		add(bt);

		setPreferredSize(new Dimension(300,200));
		setVisible(true);
		//setSize(300, 300);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);

		//타이머를 동작할 쓰레드
		tt = new Thread(this);
		//버튼과 타이머 쓰레드 start 연결
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tt.start();
			}
		});
	}

	@Override
	public void run() {
		while (true) {
			// System.out.println(sec++);

			try {
				Thread.sleep(1000);
				setSec();
				label.setText(StringManager.getNumString(min) + " : " + StringManager.getNumString(sec));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int setSec() { //초를 설정하는 메서드
		sec++;
		if (sec >= 59) {
			sec = 0;
			setMin();
		}

		return sec;
	}

	public int setMin() { //분을 설정하는 메서드
		min++;
		if (min == 25) {
			min = 0;
			// 타이머멈춤
		}

		return min;
	}

}
