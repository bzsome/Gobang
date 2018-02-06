package com.ShowUI;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.Socket.*;

public class Chatroom extends JPanel {
	private static final long serialVersionUID = 1L;
	private static JTextArea jArea;
	private static JTextField jText;
	private static JButton btClear, btSend;
	public static Chatroom my;
	/** �ҵ���Ϣ */
	public final static int myText = 0;
	/** �Է�����Ϣ */
	public final static int peText = 1;

	public Chatroom() {
		// TODO Auto-generated constructor stub
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		jArea = new JTextArea(7, 19);
		jText = new JTextField(14);
		btClear = new JButton("���");
		btSend = new JButton("����");
		jArea.setEnabled(false);
		// �Զ�����
		jArea.setLineWrap(true);
		jArea.setText("���������ݣ�");
		jArea.setFont(new Font("����", Font.BOLD, 14));
		JScrollPane jsp = new JScrollPane(jArea);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(jsp);
		this.add(jText);
		this.add(btClear);
		this.add(btSend);
		addListener();
		my = this;
	}

	public void addListener() {
		btClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jArea.setText("���������ݣ�");
			}
		});
		btSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (MySocket.isStart) {
					String text = jText.getText();
					if (text.length() > 0) {
						addText(text, myText);
						DataSocket.send(text);
					}
				} else {
					JOptionPane.showMessageDialog(ShowUI.showUI,
							"��Ϣ����ʧ�ܣ�δ��������ӣ�", "����ʧ��",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}

	public static void addText(String text, int who) {
		String str = jArea.getText();
		switch (who) {
		case myText:
			text = "��˵:" + text;
			break;
		case peText:
			text = "�Է�˵:" + text;
			break;
		default:
			break;
		}
		jArea.setText(str + "\n" + text);
	}
}
