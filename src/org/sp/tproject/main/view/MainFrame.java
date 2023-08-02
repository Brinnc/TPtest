package org.sp.tproject.main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.sp.tproject.calendar.view.DiaryPage;
import org.sp.tproject.member.view.MyPage;

public class MainFrame extends JFrame{
	JPanel p_north; //네비게이션 영역
	JPanel p_content; //각 페이지 및 컨텐츠들이 배치될 메인 영역
	
	//네비게이션 이미지경로 배열
	String[] naviImg= 
		{"res/img/naviIcon/home_main.png", "res/img/naviIcon/calendar.png", "res/img/naviIcon/git.png", "res/img/naviIcon/logout.png"}; 
	//네비게이션(라벨) 배열
	ArrayList<JLabel> naviIcon;
	
	Page[] pages; //페이지 배열
	public static final int MAIN=0;
	public static final int DIARY=1;
	public static final int MYPAGE=2;
	
	int width=1230;
	int height=800;
	
	public MainFrame() {
		p_north=new JPanel();
		p_content=new JPanel();
		createNavi();
		
		pages=new Page[3];
		pages[MAIN]=new MainPage();
		pages[DIARY]=new DiaryPage();
		pages[MYPAGE]=new MyPage();
			
		//스타일
		p_north.setBackground(Color.DARK_GRAY);
		p_north.setPreferredSize(new Dimension(width, 50));
		p_content.setBackground(Color.WHITE);
		//p_content.setBorder(new LineBorder(Color.WHITE, 1));
		
		//조립
		for (int i = 0; i < pages.length; i++) {
			p_content.add(pages[i]);
		}
		add(p_north, BorderLayout.NORTH);
		add(p_content);
		
		setSize(width, height);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//디폴트 페이지는 메인페이지
		showHide(MAIN);
		
	}
	
	public void login() { //로그인 시 호출하는 메서드
		
	}
	public void logout() { //로그아웃 시 호출하는 메서드
		
	}
	
	public void createNavi() { //네비 생성 메서드
		
	}
	
	public void showHide(int n) { //페이지 전환처리 메서드
		for (int i = 0; i < pages.length; i++) {
			if (i == n) { // 넘겨받은 매개변수와 i가 일치할때만 보이게함
				pages[i].setVisible(true);
			} else {
				pages[i].setVisible(false);
			}
		}
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
