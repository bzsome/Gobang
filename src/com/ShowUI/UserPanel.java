package com.ShowUI;

import java.awt.*;

import javax.swing.*;
import com.chao.Player;

/**
 * ������ʾ ֮ �����Ϣ��壬��ʾ����ǳƣ���ַ����Ϣ
 */
public class UserPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel myPanel, pePanel;
	private static JLabel myName, myAddress, myColor, myGrade;
	private static JLabel peName, peAddress, peColor, peGrade;
	static UserPanel my;
	/** �û���Ϣ��壬�����(�ҵ���Ϣ) */
	public static final int left = 0;
	/** �û���Ϣ��壬�����(�Է���Ϣ) */
	public static final int right = 1;

	public UserPanel() {
		this.setVisible(true);
		this.setLayout(null);
		this.setBackground(new Color(180, 180, 180));

		myPanel = new JPanel();
		pePanel = new JPanel();
		myPanel.setLayout(new GridLayout(0, 1));
		pePanel.setLayout(new GridLayout(0, 1));

		myName = new JLabel("����ǳ�: ");
		myAddress = new JLabel("��ҵ�ַ: ");
		myColor = new JLabel("�����ɫ: ");
		myGrade = new JLabel("��ҷ���: ");

		peName = new JLabel("����ǳ�: ");
		peAddress = new JLabel("��ҵ�ַ: ");
		peColor = new JLabel("�����ɫ: ");
		peGrade = new JLabel("��ҷ���: ");

		myPanel.add(myName);
		myPanel.add(myAddress);
		myPanel.add(myColor);
		myPanel.add(myGrade);

		pePanel.add(peName);
		pePanel.add(peAddress);
		pePanel.add(peColor);
		pePanel.add(peGrade);

		this.add(myPanel);
		this.add(pePanel);
		my = this;
	}

	/**
	 * ������ʾ���ؼ�������Ϻ�ִ��
	 */
	public static void init() {

		myPanel.setBounds(2, 2, my.getWidth() / 2 - 2, my.getHeight() - 5);
		pePanel.setBounds(my.getWidth() / 2 + 2, 2, my.getWidth() / 2 - 2,
				my.getHeight() - 5);

		setUserInfo(null, left);
		setUserInfo(null, right);
		my.repaint();
	}

	/**
	 * ���û���Ϣ���棬��ʾ�û���Ϣ
	 * 
	 * @param player
	 * @param position
	 */
	public static void setUserInfo(Player player, int position) {
		if (player == null) {
			player = new Player();
		}
		if (position == left) {
			myName.setText("����ǳ�: " + player.getName());
			myAddress.setText("��ҵ�ַ: " + player.getAddress());
			myColor.setText("�����ɫ: " + player.getColorString());
			myGrade.setText("��ҷ���: " + player.getGrade());
		} else {
			peName.setText("����ǳ�: " + player.getName());
			peAddress.setText("��ҵ�ַ: " + player.getAddress());
			peColor.setText("�����ɫ: " + player.getColorString());
			peGrade.setText("��ҷ���: " + player.getGrade());
		}
	}

	/**
	 * ������Ϣ
	 */
	public static void setGrade(int grade, int position) {
		if (position == left) {
			myGrade.setText("��ҷ���: " + grade);
		} else {
			peGrade.setText("��ҷ���: " + grade);
		}
	}
}
