package com.control;

import com.ShowUI.MyDialogx;
import com.ShowUI.UserPanel;
import com.Socket.MyDataBase;
import com.chao.Player;

public class UserLogin {

	private static String name;
	private static String pass;
	private static boolean isOnline = false;
	private static Thread addThread, onThread;

	public UserLogin(String mName, String mPass) {
		name = mName;
		pass = mPass;
		addOnline();
	}

	/** ���ӻ��֣��������ݻ��֣��������ݻ��֣�������UI */
	public static void addGrade(int add) {
		MyDataBase.addGrade(name, pass, add);
		int grade = MyDataBase.getGrade(name, pass);
		Player.my.setGrade(grade);
	}

	/** �����ݿⷢ�ͱ�ǩ����ʾ�û����� */
	public static void isOnline() {
		onThread = new Thread() {
			public void run() {
				while (isOnline) {
					try {
						MyDataBase.setTip(name, pass);
						Thread.sleep(3000);
					} catch (Exception e) {
					}
				}
			};
		};
		onThread.start();
	}

	/** ���߻��1���ӻ��1���� */
	public static void addOnline() {
		System.out.println("ADDONLINE");
		addThread = new Thread() {// ���ڼ����Ϸ�Ƿ����
			public void run() {
				try {
					// �ȴ���һ���߳̽���
					if (isOnline) {
						isOnline = false;
						Thread.sleep(61000);
					}
					isOnline = true;
					isOnline();
				} catch (InterruptedException e1) {
				}
				while (isOnline) {
					try {
						MyDataBase.addGrade(name, pass, 1);
						updatePanelGrade();
						Thread.sleep(60000);
					} catch (Exception e) {
					}
				}
				MyDialogx.monitor();
				System.out.println("���߼���߳� ����ֹ��");
			};
		};
		addThread.start();
	}

	/** ������������һ��� */
	public static void updatePanelGrade() {
		int grade = MyDataBase.getGrade(name, pass);
		UserPanel.setGrade(grade, UserPanel.left);
	}

	public static String getName() {
		return name;
	}

	public static String getPass() {
		return pass;
	}

	public static boolean getOnline() {
		return isOnline;
	}
}
