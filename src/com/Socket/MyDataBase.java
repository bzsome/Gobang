package com.Socket;

import java.sql.*;

public class MyDataBase {
	private static final String url = "jdbc:mysql://gobangdata.bzchao.win/gobang?useUnicode=true&characterEncoding=UTF-8";
	private static final String name = "com.mysql.jdbc.Driver";
	private static final String user = "gobang";
	private static final String password = "1501511668";
	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet res = null;

	public MyDataBase() {
		Thread thread = new Thread() {
			public void run() {
				try {
					Class.forName(name);// ָ����������
					conn = DriverManager.getConnection(url, user, password);// ��ȡ����
				} catch (Exception e) {
				}
			}
		};
		thread.start();
		try {
			thread.join(7000);
		} catch (InterruptedException e1) {
		}
		System.out.println("���ݿ����ӳ�ʱ��");
	}

	public static void close() {
		try {
			conn.close();
			pst.close();
		} catch (SQLException e) {
		}
	}

	/** ����û����� */
	public static int getGrade(String name, String pass) {
		String sql = "select * from user where (name=?) and pass=?";
		try {// ִ����䣬�õ������
			pst = conn.prepareStatement(sql);// ׼��ִ�����
			pst.setString(1, name);
			pst.setString(2, pass);
			res = pst.executeQuery();
			while (res.next()) {
				int grade = res.getInt("grade");
				return grade;
			}
		} catch (Exception e) {
		}
		return -1;
	}

	/** ע���û� */
	public static boolean setReg(String name, String pass) {
		String sql = "insert into user(name,pass)values(?,?);";
		try {// ִ����䣬�õ������
			pst = conn.prepareStatement(sql);// ׼��ִ�����
			pst.setString(1, name);
			pst.setString(2, pass);
			int count = pst.executeUpdate();
			if (count > 0) {
				System.out.println("setReg ע���û� ���ݲ���ɹ�");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("setReg ע���û� ��������ʧ�ܣ�");
		}
		return false;
	}

	/** �����ݿⷢ�ͱ�ʾ����ʾ���� */
	public static void setTip(String name, String pass) {
		String sql = "update user set tip=tip+1 where name=? and pass=?";
		try {// ִ����䣬�õ������
			pst = conn.prepareStatement(sql);// ׼��ִ�����
			pst.setString(1, name);
			pst.setString(2, pass);
			int count = pst.executeUpdate();
			if (count > 0) {
				// System.out.println("setTip ����״̬ͬ���ɹ�");
			}
		} catch (Exception e) {
			// System.out.println("setTip ����״̬ͬ��ʧ�ܣ�");
		}
	}

	/** ���ӻ��� */
	public static void addGrade(final String name, final String pass,
			final int add) {
		String sql = "update user set grade=grade+? where name=? and pass=?";
		try {// ִ����䣬�õ������
			pst = conn.prepareStatement(sql);// ׼��ִ�����
			pst.setInt(1, add);
			pst.setString(2, name);
			pst.setString(3, pass);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("addGrade ���ӻ���ʧ�ܣ�");
		}

	}
}
