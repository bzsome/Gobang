package com.ShowUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.Socket.*;
import com.chao.*;
import com.control.*;

/**
 * ������Ŀ��ư�ť���
 */
public class ConPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static ConPanel my;
	private JButton btShowChess, btAgain, btChessAI, btTest, btCoupe, btOnline,
			btRobot;
	int i = 0;

	public ConPanel() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setBackground(new Color(220, 220, 220, 220));
		btAgain = new JButton("������Ϸ");
		btCoupe = new JButton("˫�˶�ս");
		btRobot = new JButton("�˻���ս");
		btOnline = new JButton("���߶�ս");
		btChessAI = new JButton("����AI����");
		btShowChess = new JButton("��ʾ��������");
		btTest = new JButton("���ò��԰�ť");

		this.add(btAgain);
		this.add(btCoupe);
		this.add(btRobot);
		this.add(btOnline);
		this.add(btChessAI);
		this.add(btShowChess);
		this.add(btTest);

		my = this;
		addListener();
	}

	public static void init() {
		my.repaint();
	}

	/**
	 * ��Ӽ����¼�
	 */
	private void addListener() {

		btShowChess.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameCenter.showChess();
			}
		});

		btAgain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameCenter.reStart();
				ChessBroad.my.repaint();
				MySocket.close();
				btCoupe.setEnabled(true);
				btRobot.setEnabled(true);
				btOnline.setEnabled(true);
				btChessAI.setEnabled(true);
				try {
					MySocket.socket.close();
				} catch (Exception e) {
				}
			}
		});

		btChessAI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (GameCenter.isEnd()) {
					JOptionPane.showMessageDialog(null, "��Ϸδ��ʼ���޷�ʹ��AI����",
							"��ʾ��Ϣ", JOptionPane.CANCEL_OPTION);
					return;
				}
				new AutoChess();
			}
		});

		btTest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ChessBroad.my.repaint();
				System.out.println("----------����Ȩֵ-------");
				GameCenter.showWeight(Spot.blackChess);

				System.out.println("----------����Ȩֵ-------");
				GameCenter.showWeight(Spot.whiteChess);
			}
		});

		btCoupe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GameCoupe();
				btCoupe.setEnabled(false);
				btRobot.setEnabled(false);
				btOnline.setEnabled(false);
			}
		});

		btRobot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GameRobot();
				btCoupe.setEnabled(false);
				btRobot.setEnabled(false);
				btOnline.setEnabled(false);
			}
		});

		btOnline.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				// new һ��Ҫ��ǰ�棬�������ݱ����ã�
				new GameOnline();
				MyDialogx.online();

				btCoupe.setEnabled(false);
				btRobot.setEnabled(false);
				btOnline.setEnabled(false);
				btChessAI.setEnabled(false);
			}
		});
	}
}
