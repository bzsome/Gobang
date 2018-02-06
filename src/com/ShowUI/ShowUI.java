package com.ShowUI;

import com.Socket.Record;
import com.chao.*;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

/**
 * ������UI �汾0.3 2017.06.23
 * 
 * @author chaos
 */
public class ShowUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final String VER = "1.1.2  ��ʽ��";
	public static final String TIME = "2017.06.26.23(��ʽ��)";
	public static final String COPR = "������ƿ���  ӵ�б�������а�Ȩ";
	public static final String UPDA = "���߶�ս��������AI��";
	// �����С
	int width = 730;
	int hight = 760;

	// ���������
	public static ShowUI showUI;

	public ShowUI() {
		showUI = this;
		UIManager.put("Label.font", new Font("����", Font.BOLD, 15));
		UIManager.put("Button.font", new java.awt.Font("����", 0, 20));

		// TODO Auto-generated constructor stub
		this.setTitle(" ����������  " + TIME);
		this.setSize(width, hight);
		this.setResizable(false);
		this.setLayout(null);

		int sWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int sHight = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((sWidth - width) / 2, (sHight - hight) / 2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			java.net.URL imgURL = getClass().getResource("/raw/goBang.png");
			ImageIcon imgIcon = new ImageIcon(imgURL);
			this.setIconImage(imgIcon.getImage());
		} catch (Exception e) {
			System.out.println("ͼ������ʧ�ܣ�");
		}

		addWidget();
	}

	/**
	 * ��ӿؼ��Ͳ˵���
	 */
	private void addWidget() {

		int x = 5;
		int y = 2;
		int mWidth = width / 4 * 3;
		int mHight = width / 5;

		this.setJMenuBar(new MyJMenuBar());

		getContentPane().add(new UserPanel());
		UserPanel.my.setBounds(x, y, mWidth, mHight);

		getContentPane().add(new ChessBroad());
		ChessBroad.my.setBounds(x, mHight + 2 * y, mWidth, hight - mHight - 2
				* y);

		getContentPane().add(new StatePanel());
		StatePanel.my.setBounds(mWidth + 2 * x, y, width - mWidth - 2 * x,
				mHight);

		getContentPane().add(new Chatroom());
		Chatroom.my.setBounds(mWidth + 2 * x, mHight + 2 * y, width - mWidth
				- 2 * x, (hight - mHight - 2) / 2 * y);

		getContentPane().add(new ConPanel());
		ConPanel.my.setBounds(mWidth + 2 * x, 340, width - mWidth - 2 * x,
				hight - mHight - 2 * y);
	}

	// ������ʾ���ؼ�������Ϻ�ִ��(��ؼ��������ݵ�)
	public static void init() {
		GameCenter.reStart();
		ChessBroad.init();
		UserPanel.init();
		ConPanel.init();
		Player.init();
		showUI.repaint();
		new Record();
	}

	// ���ڹر��¼�
	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			close();
			return; // ֱ�ӷ��أ���ֹĬ�϶�������ֹ���ڹر�
		}
		super.processWindowEvent(e); // ������ִ�д����¼���Ĭ�϶���(�磺����)
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		ConPanel.init();
	}

	public static void close() {
		int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�˳�ϵͳ��", "�����˳�������...",
				JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}