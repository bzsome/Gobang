package com.control;

import com.chao.*;
import com.ShowUI.ChessBroad;

/**
 * AI��������
 */
public class AutoChess {

	public AutoChess() {
		// ����ʵ����һ��AI����ֹӰ���˻���ս
		PlayerAI ai = new PlayerAI();
		// AIϵͳ����������λ��,һ��Ҫ����player����ɫ��AIĬ������ɫ��
		int color = TableData.getTheColor();
		Spot spot = ai.bestChess(color);

		int row = spot.getRow();
		int col = spot.getCol();
		// ������壬�������µ�����
		spot = new Spot(row, col, color);
		// �ύ���Ƹ�����
		ChessBroad.submitPaint(spot);

		System.out.println("����AI����λ�ã�" + ",  " + (row + 1) + ":" + (col + 1));
	}
}
