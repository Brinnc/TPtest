package org.sp.tproject.main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import util.RoundedButton;
import util.StringManager;

public class Timer extends JPanel implements Runnable {
	JLabel la_worktap;
	JLabel la_break;

	JLabel label;
	RoundedButton bt_start;
	RoundedButton bt_pause;

	public static int sec;
	public static int min;

	Thread tt;

	public boolean flag = false; // 타이머를 제어하는 플래그

	public Timer() {
		// setSec();
		String timer = StringManager.getNumString(min) + " : " + StringManager.getNumString(sec);
		la_worktap = new JLabel(" pomodoro");
		la_break = new JLabel(" break");
		label = new JLabel(timer);
		bt_start = new RoundedButton("▶");
		bt_pause = new RoundedButton("⏸");

		setLayout(new FlowLayout());
		// setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.WHITE, 1));

		la_worktap.setPreferredSize(new Dimension(120, 25));
		la_worktap.setOpaque(true);
		la_worktap.setBackground(new Color(204, 051, 051));
		la_worktap.setFont(new Font("goyang", Font.BOLD, 20));
		
		la_break.setPreferredSize(new Dimension(120, 25));
		la_break.setOpaque(true);
		la_break.setBackground(new Color(051, 204, 102));
		la_break.setFont(new Font("goyang", Font.BOLD, 20));
		
		label.setPreferredSize(new Dimension(250, 150));
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setFont(new Font("digital-7", Font.BOLD, 70));
		label.setHorizontalAlignment(JLabel.CENTER);

		add(la_worktap);
		add(la_break);
		add(label);
		add(bt_start);
		add(bt_pause);

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(300, 250));
		setVisible(true);
		// setSize(300, 300);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 타이머를 동작할 쓰레드
		tt = new Thread(this);
		tt.start();

		// 버튼과 타이머 쓰레드 start 연결
		bt_start.addActionListener(new ActionListener() { // 타이머 시작
			public void actionPerformed(ActionEvent e) {
				flag = true;
			}
		});
		// 쓰레드를 죽이는 것이 아닌 flag를 통해 제어
		bt_pause.addActionListener(new ActionListener() { // 타이머 일시정지
			public void actionPerformed(ActionEvent e) {
				flag = false;
				System.out.println(flag);
			}
		});

		
		//라벨탭과 이벤트 연결
		la_worktap.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				label.setBackground(new Color(204, 051, 051));
			}
		});
		la_break.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				label.setBackground(new Color(051, 204, 102));
			}
		});
		
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				
				if(flag){
					setSec();
					label.setText(StringManager.getNumString(min) + " : " + StringManager.getNumString(sec));
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int setSec() { // 초를 설정하는 메서드
		sec++;
		if (sec >= 59) {
			sec = 0;
			setMin();
		}

		return sec;
	}

	public int setMin() { // 분을 설정하는 메서드
		min++;
		if (min == 25) {
			min = 0;
			// 타이머멈춤
			flag=false;
		}

		return min;
	}

}
