package com.ShowUI;

import com.chao.*;
import java.awt.*;
import javax.swing.*;

/**
 * ������ʾ ֮ ��Ϸ״̬���,��ʾ��Ϸģʽ�ͳ������
 */
public class StatePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JLabel colorLabel, modeLabel, timeJLabel, levelLabel;
	public static StatePanel my;

	public StatePanel() {
		modeLabel = new JLabel("��ʾ��Ϸģʽ");
		levelLabel = new JLabel("�Ѷȼ���:Ĭ���м�");
		timeJLabel = new JLabel("����ʱ:��δ����");
		colorLabel = new JLabel("���:��������");
		this.setBackground(new Color(200, 200, 198));
		this.setLayout(new GridLayout(0, 1));
		this.add(modeLabel);
		this.add(levelLabel);
		this.add(timeJLabel);
		this.add(colorLabel);
		my = this;
		// ���ӣ�ע�ʹ˴��룬����UserPanel�����޷���ʾ��ԭ��δ֪(ԭ����UserPanel ��ʾ���ݺ�δ���»��ƣ�
		putInfo();
	}

	/**
	 * ʹ���̶߳�̬����״̬���
	 */
	private static void putInfo() {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						colorLabel.setText("�������ʾ�쳣��");
					}
					int color = TableData.getTheColor();
					if (color == Spot.blackChess) {
						colorLabel.setText("���ְ��壬���º���");
					} else if (color == Spot.whiteChess) {
						colorLabel.setText("���ֺ��壬���°���");
					} else {
						colorLabel.setText("���:��������");
					}

					switch (GameCenter.getMode()) {
					case GameCenter.MODE_END:
						modeLabel.setText("��Ϸģʽ:δ��ʼ��Ϸ");
						break;
					case GameCenter.MODE_COUPE:
						modeLabel.setText("��Ϸģʽ:˫�˶�ս");
						break;
					case GameCenter.MODE_ROBOT:
						modeLabel.setText("��Ϸģʽ:�˻���ս");
						break;
					case GameCenter.MODE_ONLINE:
						modeLabel.setText("��Ϸģʽ:���߶�ս");
						break;
					default:
						break;
					}

					switch (Algorithm.LEVEL) {
					case Algorithm.LEVEL_Low:
						levelLabel.setText("��Ϸ�Ѷ�:�����Ѷ�");
						break;
					case Algorithm.LEVEL_Middle:
						levelLabel.setText("��Ϸ�Ѷ�:�м��Ѷ�");
						break;
					case Algorithm.LEVEL_Hight:
						levelLabel.setText("��Ϸ�Ѷ�:�߼��Ѷ�");
						break;
					default:
						break;
					}

				}
			}
		}.start();
	}

	public static void setTime(int mTime) {
		timeJLabel.setText("ʣ��ʱ��:" + mTime + "��");
	}
}
