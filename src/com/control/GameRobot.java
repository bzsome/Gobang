package com.control;

import com.chao.*;
import com.ShowUI.*;

/**
 * �˻���ս
 * 
 * @author chaos
 * 
 */
public class GameRobot {

	public GameRobot() {
		GameCenter.reStart();
		GameCenter.setMode(GameCenter.MODE_ROBOT);
		Player.my.start();
		final PlayerAI playerAI = new PlayerAI();
		playerAI.start();
		playerAI.setGrade(Player.pe.getGrade());

		// �û���壬�����û���Ϣ
		UserPanel.setUserInfo(Player.my, UserPanel.left);
		UserPanel.setUserInfo(playerAI, UserPanel.right);

		// ����һ���̣߳���������
		new Thread() {
			public void run() {
				int color = playerAI.getColor();
				while (!GameCenter.isEnd()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("robotThread ˯���쳣��");
					}
					if (color == TableData.getTheColor()) {
						Spot spot = playerAI.bestChess(color);
						ChessBroad.submitPaint(spot);
					}
				}
				System.out.println("���������߳���ֹͣ");
			};
		}.start();
	}
}
