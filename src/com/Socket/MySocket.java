package com.Socket;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class MySocket {
	public static Socket socket;
	public static ServerSocket server;
	public final static int port = 8000;
	/** �Ƿ���������ӣ���Ҫ���ڴ�����ʾ,�Է��Ƿ���� */
	public static boolean isStart = false;
	public static String peAddress;

	/**
	 * ����Socket��������Ǵ������򱣴����ӵ���socket
	 * 
	 * @throws IOException
	 */
	public static void startServer() throws IOException {
		server = new ServerSocket(port);
		MySocket.socket = server.accept();
		getData();// ����Ҽ��뷿����׼����������
		isStart = true;
		peAddress = socket.getInetAddress().getHostAddress();
		System.out.println("startServer() �Է��������ӳɹ���׼�����նԷ�����");
	}

	/**
	 * ����ָ��IP��ַ��Socket���񣬲��������ӵ���socket
	 * 
	 * @throws Exception
	 */
	public static void getSocket(final String address) throws Exception {
		socket = new Socket();
		// socket.setSoTimeout(5000);
		socket.connect(new InetSocketAddress(address, port));
		getData();// �ɹ����뷿�����������
		isStart = true;
		peAddress = socket.getInetAddress().getHostAddress();
		System.out.println("getSocket() ����Է����ӳɹ�,׼�����նԷ�����");
	}

	/**
	 * �������ݣ�������������ֹ���������ݴ�������ã�
	 * 
	 * @param object
	 */
	protected static void putData(final Object object) {
		new Thread() {
			public void run() {
				try {
					ObjectOutputStream os = new ObjectOutputStream(
							socket.getOutputStream());
					os.writeObject(object);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("putData() ���ݷ����쳣����ֹ����");
					isStart = false;
					return;
				}
			}
		}.start();
	}

	/**
	 * ��������,������������ֹ���������ݴ�������ã�
	 */
	protected static void getData() {
		new Thread() {
			public void run() {
				while (true) {
					try {
						ObjectInputStream in = new ObjectInputStream(
								MySocket.socket.getInputStream());
						Object object = in.readObject();
						if (object instanceof ArrayList) {
							@SuppressWarnings("unchecked")
							ArrayList<String> list = (ArrayList<String>) object;
							DataSocket.recieve(list);
						}
					} catch (Exception e) {
						System.out.println("getData() ���ݽ����쳣����ֹ����");
						isStart = false;
						return;
					}
				}
			}
		}.start();
	}

	public static void close() {
		try {
			server.close();
			isStart = false;
		} catch (Exception e) {
		}
		try {
			socket.close();
			isStart = false;
		} catch (Exception e) {
		}
	}
}