package com.chao;

/**
 * ��Ϸ�����࣬������Ϸ����
 * 
 * @author chaos
 * 
 */
public class GameCenter {

	/** ������Ϸģʽ */
	private static int gameModel;
	/** ��Ϸģʽ ����Ϸ���� */
	public final static int MODE_END = 0;
	/** ��Ϸģʽ ��˫�˶�ս */
	public final static int MODE_COUPE = 1;
	/** ��Ϸģʽ ���˻���ս */
	public final static int MODE_ROBOT = 2;
	/** ��Ϸģʽ �����߶�ս */
	public final static int MODE_ONLINE = 3;

	/**
	 * ������Ϸ
	 */
	public static void reStart() {
		new TableData();// ��ʼ����������
		gameModel = MODE_END;
	}

	/**
	 * �жϴ˴˵��������Ҫ��
	 */
	public static boolean isFit(Spot spot) {
		if (GameCenter.gameModel == GameCenter.MODE_END) {
			return false;
		}
		int color = spot.getColor();
		int row = spot.getRow();
		int col = spot.getCol();
		if (color != TableData.getTheColor()) {
			System.out.println("isFit() �����ڴ���Ҳ���" + color);
			return false;
		}

		// �жϴ˵��Ƿ�������
		Spot s = TableData.getSpot(row, col);
		int xcolor = s.getColor();
		if (xcolor != Spot.notChess) {
			System.out.println("��λ����������" + row + ":" + col);
			return false;
		}
		return true;
	}

	/**
	 * �������ԣ���ʾ��������
	 */
	public static void showChess() {
		System.out.println("��ʾ������ ��Ϸ�Ƿ������ isOver() " + TableData.isOver());
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				int color = TableData.getSpot(i, j).getColor();
				System.out.print(color + ", ");
			}
			System.out.println();
		}
	}

	/**
	 * �������ԣ���ʵ�ʹ��ܣ���ʾ���е��Ȩֵ
	 */
	public static void showWeight(int mColor) {
		if (mColor == 0) {
			System.out.println("showWeight ��ɫ���ô���");
			return;
		}
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				Spot spot = TableData.getSpot(i, j);
				int n = Algorithm.getWeight(spot, mColor);
				System.out.print(n + ",\t");
			}
			System.out.println();
		}
	}

	public static int getMode() {
		return gameModel;
	}

	public static void setMode(int mode) {
		gameModel = mode;
	}

	public static boolean isEnd() {
		return gameModel == MODE_END;
	}
}
