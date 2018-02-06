package com.chao;

import java.util.ArrayList;
import java.util.Random;

/**
 * ����AI���
 ** 
 * �޸��������壬��ֹ�ظ�����ͬ��ɫ������
 ** 
 * ֻ�����ݴ����������ų���
 * 
 * @author chaos
 * 
 */
public class PlayerAI extends Player {

	public PlayerAI() {
		this.name = "����AI ";
	}

	/**
	 * ������Ҫ��������Ŷ��󣬲����������,����������
	 ** 
	 * ����������Ķ��󣬼��ϲ���color���Ա���ɫ��������ֵ
	 */
	public Spot bestChess(int mColor) {
		if (mColor == Spot.notChess) {
			System.out.println("playChess ��ɫ���ô���");
			return null;
		}

		// ����ҷ����Ȩֵ��
		Spot max = maxSpot(mColor);

		// ���δ�õ����ֵ���������ֵ��ȨֵΪ�㣬 ���������
		if (max == null) {
			System.out.println("AI�㷨���Ϊ�գ��������aaaa");
			return playRandom(mColor);
		}

		// �˶δ���ʵ�ֽ���
		// ͬʱ������ֵ�Ȩֵ�����Ȩֵ���ĵ�

		int matchColor = Spot.getBackColor(mColor);// �õ����ֵ���ɫ
		Spot matchSpot = maxSpot(matchColor);

		int a = Algorithm.getWeight(max, mColor);
		int b = Algorithm.getWeight(matchSpot, matchColor);
		System.out.println("�ҷ����ֵ:" + a + ",  �Է����ֵ:" + b);
		if (b - a > 550) {
			// ����ֵ�Ȩֵ���Ƿ񳬹�100
			max = matchSpot;
		}
		// ��������Spot���󣬷�ֹ��ɫ����
		int row = max.getRow();
		int col = max.getCol();
		return new Spot(row, col, mColor);
	}

	/**
	 * ������壬ֻ�������󣬲����������
	 */
	public static Spot playRandom(int mColor) {
		Spot spot;
		int col;
		int row;
		while (true) {
			col = new Random().nextInt(19);
			row = new Random().nextInt(19);
			spot = TableData.getSpot(row, col);
			int color = spot.getColor();
			if (color == 0) {
				break;
			}
		}
		return new Spot(row, col, mColor);
	}

	/**
	 * ���������ɫ�����Ȩֵ��
	 * 
	 * @param mColor
	 * @return
	 */
	public static Spot maxSpot(int mColor) {
		if (mColor == Spot.notChess) {
			System.out.println("maxWeight ��ɫ���ô���");
			return null;
		}
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Spot> listSpot = new ArrayList<>();

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				Spot spot = TableData.getSpot(i, j);
				if (spot.getColor() == Spot.notChess) {
					int w = Algorithm.getWeight(spot, mColor);
					list.add(w);
					listSpot.add(spot);
				}
			}
		}

		if (list.size() < 1)
			return null;

		int max = 0;
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(max) < list.get(i + 1)) {
				max = i + 1;
			}
		}

		return listSpot.get(max);
	}
}
