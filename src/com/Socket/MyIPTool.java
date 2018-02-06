package com.Socket;

import java.net.*;
import java.util.*;

public class MyIPTool {

	/**
	 * ��ȡ��������IP
	 */
	public static ArrayList<String> getAllLocalHostIP() {
		ArrayList<String> res = new ArrayList<String>();
		try {
			Enumeration<?> netInterfaces = NetworkInterface
					.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netInterfaces
						.nextElement();
				Enumeration<?> nii = ni.getInetAddresses();
				while (nii.hasMoreElements()) {
					InetAddress ip = (InetAddress) nii.nextElement();
					if (ip.getHostAddress().indexOf(":") == -1) {
						res.add(ip.getHostAddress());
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * ���ɾ�����ɨ��IP��ַ����
	 */
	public static List<String> getLANIP(List<String> slist) {
		List<String> list = new ArrayList<String>();
		for (String string : slist) {
			if (string.contains("127.0")) {
				continue;
			}
			int k = string.lastIndexOf(".");
			String str = string.substring(0, k + 1);
			for (int i = 0; i <= 255; i++) {
				String s = str + i;
				list.add(s);
			}
		}
		for (String string : slist) {
			// �Ƴ�����IP
			list.remove(string);
		}
		return list;
	}

	/**
	 * ���ɾ���������IP
	 */
	public static List<String> getAbleIP(List<String> sList) {
		System.out.println("��ʼɨ�������IP");
		long start = System.currentTimeMillis();
		int sLen = sList.size();
		if (sLen <= 0) {
			System.out.println("��ɨ��IP�б�");
			return null;
		}
		int n = (int) Math.sqrt(sLen); // �߳�����
		int m = sLen / n; // ÿһ���߳�ִ�е�Ŀ����
		int j = sLen - m * n;
		int k[] = new int[n];
		Thread t[] = new Thread[n];
		// ��ʼ��ÿһ���߳�ִ�е�Ŀ����
		for (int i = 0; i < n; i++) {
			k[i] = m;
			if (i < j)
				k[i]++;
			System.out.print(k[i] + " ");
		}

		System.out.println("\n��������(������IP)��" + sLen + ", �߳�����" + n
				+ ", �߳�ƽ��ִ����������" + (double) sLen / n);

		final List<String> list = new ArrayList<String>();
		for (int i = 0; i < t.length; i++) {
			final List<String> subList = sList.subList(0, k[i]);
			if (i < t.length - 1)
				sList = sList.subList(k[i], sList.size());

			t[i] = new Thread() {
				public void run() {
					int subLen = subList.size();
					Thread t[] = new Thread[subLen];
					for (int i = 0; i < subLen; i++) {
						final int k = i;
						t[i] = new Thread() {
							public void run() {
								if (isPing(subList.get(k))) {
									list.add(subList.get(k));
									System.out.println("������IP��ַ>>>>"
											+ subList.get(k));
								}
							};
						};
					}
					for (int i = 0; i < subLen; i++) {
						t[i].start();
					}
					for (int i = 0; i < subLen; i++) {
						try {
							t[i].join();
						} catch (InterruptedException e) {
						}
					}
				};
			};
		}
		for (int i = 0; i < n; i++) {
			t[i].start();
		}
		for (int i = 0; i < n; i++) {
			try {
				t[i].join();
			} catch (Exception e) {
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("�������ɣ�ɨ����ϣ����н�����ֹͣ," + (end - start));
		return list;
	}

	/**
	 * ������߿ͻ���IP��ַ
	 */
	public static List<String> getOnline(List<String> sList, final int port) {

		final List<String> list = new ArrayList<String>();
		int len = sList.size();
		Thread t[] = new Thread[len];

		for (int i = 0; i < len; i++) {
			final String str = sList.get(i);
			t[i] = new Thread() {
				public void run() {
					if (isSocket(str, port)) {
						list.add(str);
					}
				};
			};
		}

		for (int i = 0; i < len; i++) {
			t[i].start();
		}
		for (int i = 0; i < len; i++) {
			try {
				t[i].join();
			} catch (InterruptedException e) {
			}
		}
		System.out.println("���ߣɣ�ɨ����ϣ����н�����ֹͣ��");
		return list;
	}

	/**
	 * �ж��Ƿ��Ǳ���IP
	 */
	public static boolean isLocalHost(String str) {
		Set<String> s = new HashSet<String>();
		s.addAll(getAllLocalHostIP());
		if (!s.add(str)) {
			return true;
		}
		return false;
	}

	/**
	 * �ж�IP�Ƿ����
	 */
	public static boolean isPing(String str) {
		boolean status = false;
		try {
			status = InetAddress.getByName(str).isReachable(3000);
			if (status) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * �ж�IP�Ƿ��������Socket
	 */
	public static boolean isSocket(String str, int port) {
		try {
			new Socket(str, port);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	// public static void main(String[] args) {
	// for (int i = 0; i < 10; i++) {
	// System.out.println(isPing2("192.168.118.9"));
	// }
	// }
}
