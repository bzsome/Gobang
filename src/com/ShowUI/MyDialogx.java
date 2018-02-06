package com.ShowUI;

import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import com.Socket.DataSocket;
import com.Socket.MyIPTool;
import com.Socket.MySocket;
import com.chao.Player;
import com.control.GameOnline;

/** �Զ���Ի��� */
public class MyDialogx extends JDialog {
	private static final long serialVersionUID = 1L;
	JButton button;
	JLabel jLabel, upLabel;
	JPanel p1, p2, p3;
	JFrame jFrame;
	private static MyDialogx my;
	// ��������ϴ������IP��ַ
	private static String address = "192.168.118.135";

	MyDialogx(JFrame jFrame, String title) {
		super(jFrame, title);
		// �ر���һ���Զ��崰��
		try {
			my.dispose();
		} catch (Exception e) {
		}
		my = this;
		this.jFrame = jFrame;

		jFrame.setEnabled(false);
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		upLabel = new JLabel();// ��ʾ���ݣ���̬�ı���Ĭ�ϲ���ʾ
		upLabel.setVisible(false);
		jLabel = new JLabel("�Զ��崰��,δ��������....");// ��ʾ���ݣ���̬�ı�
		jLabel.setFont(new Font("����", Font.BOLD, 16));
		upLabel.setFont(new Font("����", Font.BOLD, 16));
		button = new JButton("ȡ��");
		p1.add(upLabel);
		p2.add(jLabel);
		p3.add(button);

		this.setSize(300, 300);
		this.add(BorderLayout.NORTH, p1);
		this.add("Center", p2);
		this.add("South", p3);
		int sWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int sHight = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((sWidth - WIDTH) / 2, (sHight - HEIGHT) / 2);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// this.pack();
		this.setVisible(true);
		addListener();
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {
		// TODO Auto-generated method stub
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			jFrame.setEnabled(true);
			dispose();
		}
	}

	public void setUpText(String str) {
		if (str.equals("")) {
			upLabel.setVisible(false);
		} else {
			upLabel.setVisible(true);
		}
		upLabel.setText("<html>" + str + "<html>");
	}

	public void setText(String str) {
		jLabel.setText("<html>" + str + "<html>");
	}

	public void setBtText(String str) {
		button.setText(str);
	}

	private void addListener() {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jFrame.setEnabled(true);
				dispose();
			}
		});
	}

	/**
	 * �Է����ߴ���
	 */
	public static void monitor() {
		final MyDialogx myDialog = new MyDialogx(ShowUI.showUI, "���ӶϿ�");
		myDialog.setVisible(true);
		myDialog.setText("�����Ѿ��Ͽ�<br>�ҷ���Է������ߣ���������Ϸ!");
		myDialog.setBtText("ȷ��");
	}

	/**
	 * ѡ�����߶�ս��ʽ����
	 */
	public static void online() {
		Object[] options = { "��������", "���뷿��" };
		int m = JOptionPane.showOptionDialog(null, "��ѡ�����߶�ս��ʽ", "���߶�ս",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
		if (m == 0) {
			createRoom();
		}

		if (m == 1) {
			joinRoom();
		}
	}

	/**
	 * �������䴰��
	 */
	public static void createRoom() {
		// ���뷿�����ҵ�һ������
		Player.my = new Player();
		Player.my.setName("�ҷ�");
		Player.my.start();

		Player.pe = new Player();
		Player.pe.setName("�Է�");
		Player.pe.start();

		final MyDialogx myDialog = new MyDialogx(ShowUI.showUI, "��������");

		final Thread thread = new Thread() {
			public void run() {
				super.run();
				try {
					System.out.println("���ڴ������䣬�ȴ����...");
					MySocket.startServer();
					// ��������߳�(��ضԷ��Ƿ����� )
					GameOnline.monitor();
					GameOnline.getPeAddress();
					// DataSocket.send(Player.my);
					DataSocket.send("Hello,��ӭ����뷿��");
				} catch (Exception e) {
				}
			};
		};
		thread.start();

		String localIP = "��������IP��ַ:";
		ArrayList<String> res = new ArrayList<String>();
		res = MyIPTool.getAllLocalHostIP();
		
		for (String ip : res) {
			localIP += "<br>" + ip;
		}
		
		final String address = localIP;

		Thread thread2 = new Thread() {
			public void run() {
				while (true) {
					if (!thread.isAlive()) {
						// ���������߳�server��ֹͣ
						if (MySocket.isStart) {
							// ���������߳�������ֹ�����Է����뷿��ɹ�
							myDialog.setText("����Ѽ��뷿�䣡��ʼ��Ϸ��");
							myDialog.setBtText("ȷ������ʼ��Ϸ");
						} else {
							// ���뷿���߳��쳣��ֹ�������뷿��ʧ�ܣ�
							myDialog.setText("��������ʧ�ܣ������Ƕ˿ڱ�ռ�ã�");
							myDialog.setBtText("ȷ��");
						}
						return;
					}

					// �������䴰��δ�ر�
					if (!myDialog.isDisplayable()) {
						// ִ�е������˵�����߳�1δ��ֹ������ȴ�ر��ˣ�
						// ˵��ȡ�����䴴��,����Ҫ��ֹserver
						try {
							System.out.print("�ȴ����ڱ��ֶ��رգ�ǿ����ֹaccept��");
							MySocket.server.close();
							System.out.println(" ��ֹ�ɹ���");
							// �ȴ�thread�߳̽��������ں����ж��߳�״̬��
							thread.join(100);
							break;
						} catch (Exception e2) {
						}
					} else {// ����δ�ر�
						try {
							myDialog.setUpText(address);
							myDialog.setBtText("ȡ���ȴ�!");
							myDialog.setText("���ڵȴ���Ҽ���.");
							Thread.sleep(1000);
							myDialog.setText("���ڵȴ���Ҽ���..");
							Thread.sleep(1000);
							myDialog.setText("���ڵȴ���Ҽ���...");
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}
					}
				}

				System.out.println("createRoom() ���������߳��߳�״̬:"
						+ thread.getState());
			};
		};
		thread2.start();
	}

	/**
	 * ���뷿�䴰��
	 */
	public static void joinRoom() {
		// ���뷿�����ҵڶ�������
		Player.pe = new Player();
		Player.pe.setName("�Է�");
		Player.pe.start();

		Player.my = new Player();
		Player.my.setName("�ҷ�");
		Player.my.start();

		address = JOptionPane.showInputDialog("������IP��ַ", address);
		if (address == null) {
			return;
		}
		final MyDialogx myDialog = new MyDialogx(ShowUI.showUI, "���뷿��");
		myDialog.setBtText("ȡ������");
		myDialog.setText("���ڼ��뷿�䣬��ȴ�..");

		// ���뷿���߳�
		final Thread thread = new Thread() {
			public void run() {
				try {
					System.out.println("���ڼ��뷿�䣬�ȴ���������...");
					MySocket.getSocket(address);
					// ���뷿��ɹ�����������߳�(��ضԷ��Ƿ����� )
					GameOnline.monitor();
					GameOnline.getPeAddress();
					// DataSocket.send(Player.my);
					DataSocket.send("HI,���Ѽ�����ķ���");
				} catch (Exception e1) {
				}
			};
		};
		thread.start();

		// ���뷿�䴰���߳�
		Thread thread2 = new Thread() {
			public void run() {
				while (true) {
					if (!thread.isAlive()) {
						// ���뷿���߳�getSocket��ֹͣ
						if (MySocket.isStart) {
							// ���뷿���߳�������ֹ�������뷿��ɹ�
							myDialog.setText("�������ɹ�����ʼ��Ϸ��");
							myDialog.setBtText("ȷ������ʼ��Ϸ");
						} else {
							// ���뷿���߳��쳣��ֹ�������뷿��ʧ�ܣ�
							myDialog.setText("���뷿�䳬ʱ!!<br>������IP����������������룡");
							myDialog.setBtText("ȷ��");
						}
						return;
					}

					// ���뷿���߳�δֹͣ
					if (!myDialog.isDisplayable()) {// ���屻�ر�
						// ִ�е������˵�����߳�1δ��ֹ������ȴ�ر��ˣ�
						// �����ڱ��ֶ��رգ����뷿��,����Ҫ��ֹsocket
						try {
							System.out.print("�ȴ����ڱ��ֶ��رգ�ǿ����ֹsocket��");
							MySocket.socket.close();
							System.out.println(" ��ֹ�ɹ���");
							// �ȴ��߳̽��������ں����ж��߳�״̬��
							thread.join(100);
							break;
						} catch (Exception e2) {
						}
					} else {// ����δ�ر�
						try {

							myDialog.setBtText("ȡ���ȴ�!");
							myDialog.setText("���ڼ��뷿�䣬��ȴ�.");
							Thread.sleep(1000);
							myDialog.setText("���ڼ��뷿�䣬��ȴ�..");
							Thread.sleep(1000);
							myDialog.setText("���ڼ��뷿�䣬��ȴ�...");
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}
					}
				}
				System.out.println("joinRoom() ���뷿���߳�״̬:" + thread.getState());
			};
		};
		thread2.start();
	}
}
