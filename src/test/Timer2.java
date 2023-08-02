package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.Thread.State;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import util.StringManager;

public class Timer2 extends JFrame implements Runnable {
	JLabel la_worktap;
	JLabel la_break;
	//JPanel p_tap; //탭 라벨 붙이는 영역
	//String[] title= {"pomodoro", "break"}; 
	//ArrayList<JLabel> tap;
	
	JLabel label; //타이머 출력
	JButton bt_start;
	JButton bt_pause;
	
	public static int sec;
	public static int min;

	Thread tt;

	public Timer2() {
		//setSec();
		String timer=StringManager.getNumString(min)+" : "+StringManager.getNumString(sec);
		la_worktap=new JLabel("pomodoro");
		la_break=new JLabel("break");
		//p_tap=new JPanel();
		//createTap();
		label=new JLabel(timer);
		bt_start=new JButton("▶");
		bt_pause=new JButton("⏸");
		
		setLayout(new FlowLayout());
		//setLayout(new BorderLayout());
		//setBorder(new LineBorder(Color.WHITE, 1));
		
		la_worktap.setPreferredSize(new Dimension(120, 25));
		la_break.setPreferredSize(new Dimension(120, 25));
		la_worktap.setOpaque(true);
		la_worktap.setBackground(Color.RED);
		la_break.setOpaque(true);
		la_break.setBackground(Color.GREEN);
		label.setPreferredSize(new Dimension(250, 150));
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setFont(new Font("digital-7", Font.BOLD, 50));
		label.setHorizontalAlignment(JLabel.CENTER);
		
		add(la_worktap);
		add(la_break);
		add(label);
		add(bt_start);
		add(bt_pause);
		
		setSize(300, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		tt=new Thread(this);
		
		bt_start.addActionListener(new ActionListener() { //타이머 시작
			public void actionPerformed(ActionEvent e) {
				try{
					tt.start();
				}catch(IllegalThreadStateException ex) {
					//ex.printStackTrace();
					JOptionPane.showMessageDialog(getParent(), "이미 실행 중임");
				}
				
				if(tt.getState()==State.WAITING) {
					tt.resume();
				}
				
				System.out.println(tt.getState());
			}
		});
		
		bt_pause.addActionListener(new ActionListener() { //타이머 일시정지
			public void actionPerformed(ActionEvent e) {
				// tt.sus
					tt.suspend();
			}
		});
		
		la_worktap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //클릭 시 25분 타이머
				System.out.println("25분 뽀모도로");
			
			}
		});
		la_break.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //클릭 시 5분 타이머[
				System.out.println("5분 휴식");
			
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
				// setMin();
				// sec++;
				// Integer.toString(min)
				label.setText(StringManager.getNumString(min) + " : " + StringManager.getNumString(sec));

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public int setSec() {
		sec++;
		if (sec >= 59) {
			sec = 0;
			setMin();
		}

		return sec;
	}

	public int setMin() {
		min++;
		if (min == 25) {
			min = 0;
			// 타이머멈춤
		}

		return min;
	}

	public static void main(String[] args) {
		new Timer2();
	}
}
