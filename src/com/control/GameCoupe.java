package com.control;

import com.ShowUI.UserPanel;
import com.chao.*;

/**
 * ˫�˶�ս
 * 
 * @author chaos
 * 
 */
public class GameCoupe {

	public GameCoupe() {
		GameCenter.reStart();
		GameCenter.setMode(GameCenter.MODE_COUPE);
		Player.my.start();
		Player.pe.start();

		// �û���壬�����û���Ϣ
		UserPanel.setUserInfo(Player.my, UserPanel.left);
		UserPanel.setUserInfo(Player.pe, UserPanel.right);
	}

}
