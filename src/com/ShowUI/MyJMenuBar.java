package com.ShowUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import com.Socket.*;
import com.chao.*;
import com.control.Countown;
import com.control.UserLogin;

/**
 * ������ʾ ֮ �˵���
 */
public class MyJMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	JMenuItem chechIP, setInfo, exit, about, version, setTime, setLevel, login,
			register, admin, word;

	public MyJMenuBar() {
		UIManager.put("Menu.font", new Font("����", Font.BOLD, 22));
		UIManager.put("MenuItem.font", new Font("����", Font.BOLD, 22));

		JMenu menu = new JMenu("�˵�(F)");
		menu.setMnemonic('f'); // ���Ƿ�

		JMenu my = new JMenu("��¼(M)");
		my.setMnemonic('m'); // ���Ƿ�

		JMenu setting = new JMenu("����(S)");
		setting.setMnemonic('s'); // ���Ƿ�

		JMenu help = new JMenu("����(H)");
		help.setMnemonic('h'); // ���Ƿ�

		login = new JMenuItem("��¼�˻�");
		register = new JMenuItem("ע���˻�");
		admin = new JMenuItem("����Ա��̨");
		chechIP = new JMenuItem("�鿴����IP");
		exit = new JMenuItem("�˳�");
		about = new JMenuItem("����˵��");
		version = new JMenuItem("����汾");
		setInfo = new JMenuItem("�ҵ���Ϣ");
		setLevel = new JMenuItem("�Ѷ�����");
		setTime = new JMenuItem("����ʱ����");
		word = new JMenuItem("��Ϸ˵��");
		menu.add(chechIP);
		menu.add(exit);
		my.add(login);
		my.add(register);
		my.add(admin);
		setting.add(setInfo);
		setting.add(setLevel);
		setting.add(setTime);
		help.add(about);
		help.add(version);
		help.add(word);

		this.add(menu);
		this.add(my);
		this.add(setting);
		this.add(help);
		addListener();
	}

	public void addListener() {
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ShowUI.close();
			}
		});
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String function = "����������";
				String person = "\n������룺�¹ⳬ 1501511668\n�����㷨��Ԭ־ǿ 1501511668\n������ԣ��a �� 1501511668\n";
				String info = function + person + "\n" + ShowUI.TIME + "\n"
						+ ShowUI.COPR;
				JOptionPane.showMessageDialog(null, info, "�������˵��",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		version.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String info = " ����汾" + ShowUI.VER + "\n �������� " + ShowUI.TIME
						+ "\n" + ShowUI.COPR + "\n" + ShowUI.UPDA;
				JOptionPane.showMessageDialog(null, info, "�������˵��",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		chechIP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String localIP = "��������IP��ַ:";
				ArrayList<String> res = new ArrayList<String>();
				res = MyIPTool.getAllLocalHostIP();
				for (String ip : res) {
					localIP += "\n" + ip;
				}
				JOptionPane.showMessageDialog(ShowUI.showUI, localIP, "�鿴����IP",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		setTime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String input = JOptionPane.showInputDialog("�����볬ʱʱ��(��)", "30");
				try {
					int time = Integer.valueOf(input);
					Countown.startTiming(time);
				} catch (Exception e) {
				}
			}
		});
		setLevel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Object[] options = { "����", "�м�", "�߼�" };
				int m = JOptionPane
						.showOptionDialog(ShowUI.showUI, "��ѡ��AI���̶ܳ�", "��ѡ��",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (m == 0) {
					Algorithm.LEVEL = Algorithm.LEVEL_Low;
					return;
				}
				if (m == 1) {
					JOptionPane.showMessageDialog(ShowUI.showUI,
							"�㷨���,AI���ܣ�Ԭ־ǿ", "��Ҫ��ʾ..",
							JOptionPane.INFORMATION_MESSAGE);
					Algorithm.LEVEL = Algorithm.LEVEL_Middle;
					return;
				}
				if (m == 2) {
					JOptionPane.showMessageDialog(ShowUI.showUI,
							"�㷨���,AI���ܣ�Ԭ־ǿ", "��Ҫ��ʾ..",
							JOptionPane.INFORMATION_MESSAGE);
					Algorithm.LEVEL = Algorithm.LEVEL_Hight;
					return;
				}
			}
		});

		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String name = JOptionPane.showInputDialog("���ڵ�½���������û���", "");
				if (name == null) {
					return;
				}
				String pass = JOptionPane.showInputDialog("���ڵ�½������������", "");
				if (pass == null) {
					return;
				}

				new MyDataBase();
				int grade = MyDataBase.getGrade(name, pass);
				if (grade < 0) {
					JOptionPane.showMessageDialog(ShowUI.showUI, "��¼ʧ�ܣ�",
							"��¼ʧ��", JOptionPane.INFORMATION_MESSAGE);
				} else {
					System.out.println("cccc");
					Player.my.setName(name);
					Player.my.setGrade(grade);
					UserPanel.setUserInfo(Player.my, UserPanel.left);
					JOptionPane.showMessageDialog(ShowUI.showUI, name
							+ "��ӭ��������ǰ������" + grade + "\n����һ���ӿɻ��1���֣�", "��¼�ɹ�",
							JOptionPane.INFORMATION_MESSAGE);
					// �����û�����״̬��ص��߳�
					new UserLogin(name, pass);
				}
			}
		});
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = JOptionPane.showInputDialog("����ע�ᣬ�������û���", "");
				if (name == null) {
					return;
				}
				String pass = JOptionPane.showInputDialog("����ע�ᣬ����������", "");
				if (pass == null) {
					return;
				}
				new MyDataBase();
				if (MyDataBase.setReg(name, pass)) {
					JOptionPane
							.showMessageDialog(ShowUI.showUI, "ע��ɹ���",
									"ע��ɹ����������Ѵ��������Ϣ��",
									JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(ShowUI.showUI,
							"ע��ʧ�ܣ���������ע�ᣡ", "ע��ʧ��",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		admin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					java.net.URI uri = new java.net.URI(
							"http://gobang.bzchao.win/");
					java.awt.Desktop.getDesktop().browse(uri);
				} catch (Exception e) {
				}
			}
		});
		word.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane
						.showMessageDialog(
								ShowUI.showUI,
								"��Ϸ���ƣ�����������\n��Ҫ���ܣ�˫����Ϸ,�˻���ս,���߶�ս\n�ṩ��½�˺ŷ��񣬿ɴ�����Ļ���\n�´ε�½�������Ļ���\n����һ���ӿɻ��һ����",
								"��Ϸ˵��", JOptionPane.INFORMATION_MESSAGE);
			}

		});
	}
}
