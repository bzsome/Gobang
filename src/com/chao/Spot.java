package com.chao;

/**
 * ������
 * 
 * @author chaos
 * 
 */
public class Spot {
	/** ������ɫ��û������ */
	public final static int notChess = 0;
	/** ������ɫ������ */
	public final static int blackChess = 1;
	/** ������ɫ������ */
	public final static int whiteChess = 2;

	private int color;
	private int row;
	private int col;

	public Spot() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ȡֵ��Χ0-18
	 * 
	 * @param row
	 * @param col
	 * @param color
	 */
	public Spot(int row, int col, int color) {
		this.color = color;
		this.row = row;
		this.col = col;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	/**
	 * ����෴��ɫ���ӵ�ֵ
	 * 
	 * @param mColor
	 * @return
	 */
	public static int getBackColor(int mColor) {
		return mColor == Spot.blackChess ? Spot.whiteChess : Spot.blackChess;
	}

	/**
	 * ����������ɫ�ַ���
	 */
	public static String getColorString(int mColor) {
		if (mColor == Spot.notChess) {
			return "δ�õ�����";
		}
		if (mColor == Spot.blackChess) {
			return "����";
		}
		if (mColor == Spot.whiteChess) {
			return "����";
		}
		return "getColorString() �����������" + mColor;
	}
}
