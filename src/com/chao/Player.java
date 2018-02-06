package com.chao;

/**
 * �����
 * 
 * @author chaos
 * 
 */
public class Player {
	protected String name = "Ĭ�����";
	protected String address = "��IP��ַ(�������)";
	protected int grade = 100;
	protected int color = Spot.notChess;
	protected static int i = 1;
	// �����ս��ң��ҷ����Է�
	public static Player my, pe;

	public Player() {
		this.name = "Ĭ�����" + i;
		i++;
	}

	public Player(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	/** ��ʼ������ң���Ҫ�������߶�ս */
	public static void init() {
		my = new Player();
		pe = new Player();
	}

	/**
	 * �����������
	 */
	public Spot createChess(int row, int col) {
		return new Spot(row, col, color);
	}

	/**
	 * ��ʼ��Ϸ,�õ���ɫ
	 */
	public void start() {
		// ���صõ�����ɫ
		color = TableData.start();
		System.out.println(name + " �����ɫ " + getColorString());
	}

	public int getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getColorString() {
		return Spot.getColorString(color);
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int addGrade(int add) {
		grade += add;
		return grade;
	}
}
