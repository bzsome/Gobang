package com.ShowUI;

import com.Socket.*;
import com.chao.*;
import com.control.UserLogin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * ������ʾ ֮ ������壬��ʾ���̣���������
 ** 
 * ���������±�����ʱ���޷��������Ƴ��������ӣ�(�����̺߳������⣡)
 ** 
 * ͬ���ģ���������Եػ���ʧ��(�����̺߳������⣡)
 ** 
 * ��ʤ���жϼ��ڻ��������������£���Ϸ�����󣬽������»���ʱ�����¶���ж�ʤ��
 ** 
 * ��ˣ�����һ�������������ж��ύ�Ļ��������Ƿ����Ҫ��
 ** 
 * ����Բ��fillOval,ָ����λ�����󶥵��λ�ã�
 ** 
 * �������������������
 ** 
 * ���������»��ƺ����̻��Ʋ�����(�����߳�,�ȴ����̻������,�ȴ����ӻ������,�ٻ��ƽ��)
 */
public class ChessBroad extends JPanel {

	private static final long serialVersionUID = 1L;

	/** ���Ӵ�С */
	protected static int chessSize;
	public static ChessBroad my;
	/** �̣߳�������̸��ǵ����� */
	static Thread thread, thread2;

	public ChessBroad() {
		this.setVisible(true);
		// ���������״Ϊ����
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setBackground(new Color(249, 214, 91));
		this.addListener();
		my = this;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		paintTable();
		painAllChess();
		paintResult();
	}

	/**
	 * ������ʾ���ؼ�������Ϻ�ִ��
	 */
	public static void init() {
		chessSize = my.getWidth() / 19;
		paintTable();
		my.repaint();
	}

	public static void addGrade() {
		if (Player.my.getColor() != TableData.getTheColor()) {
			if (UserLogin.getOnline()) {
				UserLogin.addGrade(100);// �Ѹ�����һ��֣�
				int grade = Player.my.getGrade();
				UserPanel.setGrade(grade, UserPanel.left);
			}
			int grade = Player.my.addGrade(100);
			UserPanel.setGrade(grade, UserPanel.left);
		} else {
			int grade = Player.pe.addGrade(100);
			UserPanel.setGrade(grade, UserPanel.right);
		}
	}

	/**
	 * ��Ӽ����¼�
	 */
	private void addListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				// TODO Auto-generated method stub
				int cx = e.getX();
				int cy = e.getY();
				System.out.print("�������" + cx + ":" + cy);

				// �������꣬�������

				int row = Coordinate.yToRow(cy);
				int col = Coordinate.xToCol(cx);
				Spot spot = new Spot(row - 1, col - 1, TableData.getTheColor());
				System.out.println("�� �������" + row + ":" + col);

				if (TableData.isSpot(row - 1, col - 1)) {
					System.out.println("ChessBroad �˵��������ӣ�");
					return;
				}

				if (GameCenter.getMode() == GameCenter.MODE_ONLINE) {
					if (Player.my.getColor() == TableData.getTheColor()) {
						DataSocket.send(spot);
					} else {
						JOptionPane.showMessageDialog(ShowUI.showUI,
								"��������ս�У����ȵȴ��Է�����", "��ȴ�..",
								JOptionPane.CANCEL_OPTION);
						System.out.println(Player.my.getColor() + ":"
								+ TableData.getTheColor());
						return;
					}
				}

				submitPaint(spot);
			}
		});
	}

	/**
	 * ���ջ���������������֤�������
	 * 
	 * @param spot
	 */
	public static void submitPaint(Spot spot) {
		if (spot != null) {
			{
				// �жϷ�������Ҫ��
				if (GameCenter.isEnd()) {
					JOptionPane.showMessageDialog(null,
							"��Ϸδ��ʼ�����ѽ�����\n��ѡ����Ϸģʽ���Կ�ʼ��Ϸ��", "��Ϸδ��ʼ",
							JOptionPane.CANCEL_OPTION);
					return;
				}

				paintChess(spot);
				TableData.putDownChess(spot);
			}
		}

		if (TableData.isOver()) {
			addGrade();// �����û�����
			GameCenter.setMode(GameCenter.MODE_END);
			paintResult();
			if (TableData.getTheColor() == Spot.whiteChess) {
				// ��ǰӦ���°��壬������ʤ
				JOptionPane.showMessageDialog(ShowUI.showUI, "�����ʤ������100���֣�",
						"��Ϸ����", JOptionPane.CANCEL_OPTION);
			} else {
				JOptionPane.showMessageDialog(ShowUI.showUI, "�����ʤ������100���֣�",
						"��Ϸ����", JOptionPane.CANCEL_OPTION);
			}
		}
	}

	/**
	 * ���Ƶ�������,ִ����������ӣ����ж����ӵ���ȷ��
	 * 
	 * @param spot
	 */
	private static void paintChess(Spot spot) {
		if (spot != null) {
			int row = spot.getRow() + 1;
			int col = spot.getCol() + 1;

			int cx = Coordinate.colToX(col);
			int cy = Coordinate.rowToY(row);
			Graphics g = my.getGraphics();
			int color = spot.getColor();
			switch (color) {
			case Spot.blackChess:
				g.setColor(Color.black);
				break;
			case Spot.whiteChess:
				g.setColor(Color.white);
				break;
			default:
				return;
			}
			g.fillOval(cx - chessSize / 2, cy - chessSize / 2, chessSize,
					chessSize);
		}
	}

	/**
	 * ��������
	 * 
	 * @param g
	 */
	private static void paintTable() {
		final Graphics g = my.getGraphics();
		g.setFont(new Font("����", Font.BOLD, 20));
		// ���߳��л�������
		thread = new Thread() {
			public void run() {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
				for (int i = 0; i < 19; i++) {
					g.drawString("" + (i + 1), 0, 20 + chessSize * i);
					g.drawLine(chessSize / 2, chessSize / 2 + chessSize * i,
							chessSize / 2 + chessSize * 18, chessSize / 2
									+ chessSize * i);

					g.drawString("" + (i + 1), chessSize * i + 8, 15);
					g.drawLine(chessSize / 2 + chessSize * i, chessSize / 2,
							chessSize / 2 + chessSize * i, chessSize / 2
									+ chessSize * 18);
				}
			};
		};
		thread.start();
	}

	/**
	 * ������������
	 */
	private static void painAllChess() {
		// �������������̣߳�û���߳�ʱ���ӿ��ܻ���ʧ�ܣ�
		thread2 = new Thread() {
			@Override
			public void run() {
				try {// �ȴ������������
					thread.join();
				} catch (InterruptedException e) {
				}
				for (int i = 0; i < 19; i++) {
					for (int j = 0; j < 19; j++) {
						Spot spot = TableData.getSpot(i, j);
						paintChess(spot);
					}
				}
			}
		};
		thread2.start();
	}

	/**
	 * ����Ӯ������������״��
	 */
	private static void paintResult() {
		new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				try {
					thread2.join();
				} catch (InterruptedException e) {
				}
				if (GameCenter.isEnd()) {
					if (TableData.endRow + TableData.endCol < 5) {
						return;
					}

					System.out.println("Ӯ����ʼλ�ã�" + TableData.indexRow + " "
							+ TableData.indexCol);
					System.out.println("Ӯ����ֹλ�ã�" + TableData.endRow + " "
							+ TableData.endCol);

					Graphics2D g = (Graphics2D) my.getGraphics();
					int indexX = Coordinate.colToX(TableData.indexCol + 1);
					int indexY = Coordinate.rowToY(TableData.indexRow + 1);
					int endX = Coordinate.colToX(TableData.endCol + 1);
					int endY = Coordinate.rowToY(TableData.endRow + 1);
					g.setColor(Color.yellow);
					g.setStroke(new BasicStroke(5.0f));
					g.setFont(new Font("����", Font.BOLD, 150));
					g.drawLine(indexX, indexY, endX, endY);
				}
			}
		}.start();

	}
}

class Coordinate {
	/** ����ת��ΪY���� */
	public static int rowToY(int row) {
		return ChessBroad.chessSize * (row - 1) + ChessBroad.chessSize / 2;
	}

	/** ����ת��ΪX���� */
	public static int colToX(int col) {
		return ChessBroad.chessSize * (col - 1) + ChessBroad.chessSize / 2;
	}

	/** Y����ת��Ϊ�� */
	public static int yToRow(int y) {
		return (y + ChessBroad.chessSize) / ChessBroad.chessSize;
	}

	/** ��X����ת��Ϊ���� */
	public static int xToCol(int x) {
		return (x + ChessBroad.chessSize) / ChessBroad.chessSize;
	}
}
