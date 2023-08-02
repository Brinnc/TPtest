package org.sp.tproject.main.view;

import java.awt.Dimension;

import javax.swing.JPanel;

//모든 페이지의 최상단 클래스
public class Page extends JPanel{
	
	//디폴트값은 메인프레임의 width, height를 따름
	//각 페이지에 맞게 변형해서 사용 권장
	public Page() {
		setPreferredSize(new Dimension(1200, 700));
		setVisible(false);
	}
}
