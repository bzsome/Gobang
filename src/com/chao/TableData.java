package com.chao;

/**
 * ���������࣬���洦���������ݣ�19*19����
 * 
 * @author chaos
 * 
 */
public class TableData {
	// ��Ϸģʽ
	public static int gameModel;
	// ��Ϸģʽ����
	public final static int MODE_END = 0;
	public final static int MODE_COUPE = 1;
	public final static int MODE_ROBOT = 2;
	public final static int MODE_ONLINE = 3;
	// ���浱ǰӦ�����������ɫ
	private static int color = Spot.notChess;
	// ��Ϸ�Ƿ����
	private static boolean flagOver = false;
	private static Spot[][] spots = new Spot[19][19];

	// ������Ϸ����ʱ������������ʼλ��
	public static int indexRow = 0;
	public static int indexCol = 0;
	public static int endRow = 0;
	public static int endCol = 0;

	protected TableData() {
		// TODO Auto-generated constructor stub
		// ��ʼ����������
		for (int i = 0; i < spots.length; i++) {
			for (int j = 0; j < spots[0].length; j++) {
				spots[i][j] = new Spot(i, j, 0);
			}
		}
		flagOver = false;
		color = Spot.notChess;
		indexRow = indexCol = endRow = endCol = 0;
		System.out.println("�ѳ�ʼ����������");
	}

	/**
	 * �����������壬��Χ0-18,����������������ݣ�
	 */
	public static void putDownChess(Spot spot) {
		int mColor = spot.getColor();
		int row = spot.getRow();
		int col = spot.getCol();
		if (flagOver) {// ��Ϸ�ѽ�����ֱ�ӷ���
			return;
		}
		if (color != mColor) {
			System.out.println("putChess() �����ڴ���Ҳ���" + mColor);
			return;
		}

		if (isSpot(row, col)) {
			System.out.println("��λ����������" + row + ":" + col);
			return;
		}

		color = Spot.getBackColor(color);
		spots[row][col].setColor(mColor);
	}

	/**
	 * �ж�����Ƿ��ʤ
	 */
	public static boolean isOver() {
		if (flagOver) {// ��Ϸ�ѽ�����ֱ�ӷ��ؽ��
			return true;
		}

		for (int i = 0; i < spots.length; i++) {
			for (int j = 0; j < spots[0].length; j++) {
				int coutRow = 0;
				int coutCol = 0;
				int coutCR = 0;
				int coutCRS = 0;
				indexRow = i;
				indexCol = j;

				Spot spot = spots[i][j];

				// ��ȡ��ǰ���������ɫ
				int color = spot.getColor();

				// �жϴ˵��Ƿ�������
				if (color == Spot.notChess) {
					continue;
				}
				// �ж�ÿ��
				if (j <= 14) {
					for (int m = 1; m <= 4; m++) {
						int colorm = spots[i][j + m].getColor();
						if (colorm == color) {
							coutRow++;
						}
					}
				}

				// �ж�ÿ��
				if (i <= 14)
					for (int m = 1; m <= 4; m++) {
						int colorm = spots[i + m][j].getColor();
						if (colorm == color) {
							coutCol++;
						}
					}

				// �ж����Խ���
				if (i <= 14 && j <= 14)
					for (int m = 1; m <= 4; m++) {
						int colorm = spots[i + m][j + m].getColor();
						if (colorm == color) {
							coutCR++;
						}
					}

				// �ж���Խ���
				if (i <= 14 && j >= 4)
					for (int m = 1; m <= 4; m++) {
						int colorm = spots[i + m][j - m].getColor();
						if (colorm == color) {
							coutCRS++;
						}
					}

				// �õ������������ʼ����ֵ
				if (coutRow == 4) {
					endRow = indexRow;
					endCol = indexCol + 4;
					flagOver = true;
				}
				if (coutCol == 4) {
					endRow = indexRow + 4;
					endCol = indexCol;
					flagOver = true;
				}
				if (coutCR == 4) {
					endRow = indexRow + 4;
					endCol = indexCol + 4;
					flagOver = true;
				}
				if (coutCRS == 4) {
					endRow = indexRow + 4;
					endCol = indexCol - 4;
					flagOver = true;
				}
				if (flagOver) {
					return flagOver;
				}
			}
		}
		return false;
	}

	/**
	 * ��ʼ��Ϸ������ҷ�����ɫ
	 */
	public static int start() {
		if (color == Spot.notChess) {
			color = Spot.blackChess;
			return Spot.blackChess;
		} else {
			return Spot.whiteChess;
		}
	}

	public static Spot getSpot(int row, int col) {
		return spots[row][col];
	}

	public static int getTheColor() {
		return color;
	}

	public static boolean isSpot(int row, int col) {
		Spot spot = spots[row][col];
		int color = spot.getColor();
		return color != Spot.notChess;
	}
}