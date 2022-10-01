package com.sxh;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Mall extends JFrame {
    static int PRICE = 1000;
    static int num1, num2;
    JPanel jp1, jp2, jp3, jp4;
    JLabel lb11, lb12, lb13;
    JLabel lb21, lb22, lb23;
    JLabel lb31, lb32, lb33;
    JLabel lb41, lb42, lb43;
    JTextField tf1, tf2, tf3, tf4;
    JButton bt11, bt12, bt13;
    JButton bt21, bt22, bt23;
    JButton bt31, bt32, bt33;
    JButton bt41, bt42, bt43;
    int gold;

    public Mall(int now) {
        ImageIcon bg = new ImageIcon("images/mall.png");
        setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
        JLabel label = new JLabel(bg);
        label.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
        JPanel jp = (JPanel) this.getContentPane();
        jp.setOpaque(false);
        jp.setLayout(null);
        add(label);

        JLabel lb1 = new JLabel("商城");
        lb1.setForeground(Color.RED);
        lb1.setFont(new Font("华文行楷", Font.PLAIN, 45));
        lb1.setHorizontalAlignment(SwingConstants.CENTER);
        lb1.setBounds(257, 28, 157, 53);
        getContentPane().add(lb1);

        try {
            new Warehouse().write(now);
            gold = new Warehouse().read();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        JLabel jlb1 = new JLabel("金币：");
        jlb1.setFont(new Font("SimSun", Font.PLAIN, 20));
        jlb1.setBounds(32, 35, 87, 46);
        add(jlb1);

        JLabel jlb2 = new JLabel();
        jlb2.setText("" + gold);
        jlb2.setFont(new Font("SimSun", Font.PLAIN, 20));
        jlb2.setBounds(90, 42, 87, 30);
        add(jlb2);

        jp1 = new JPanel();
        jp1.setBounds(62, 91, 280, 145);
        getContentPane().add(jp1);
        jp1.setLayout(null);
        jp1.setOpaque(false);
        jp1.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        bt11 = new JButton("购买");
        bt11.setFont(new Font("宋体", Font.PLAIN, 18));
        bt11.setBounds(113, 102, 157, 35);
        bt11.setFocusPainted(false);
        bt11.setContentAreaFilled(false);
        bt11.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String numString = tf1.getText();
                num1 = Integer.parseInt(numString);

                try {
                    gold = new Warehouse().read();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                new Sale(num1, PRICE, gold, jlb2);
            }
        });
        jp1.add(bt11);


        lb11 = new JLabel("售价：1000");
        lb11.setFont(new Font("SimSun", Font.PLAIN, 20));
        lb11.setHorizontalAlignment(SwingConstants.CENTER);
        lb11.setBounds(113, 10, 157, 42);
        jp1.add(lb11);

        lb12 = new JLabel("清屏武器");
        lb12.setFont(new Font("SimSun", Font.PLAIN, 17));
        lb12.setHorizontalAlignment(SwingConstants.CENTER);
        lb12.setBounds(15, 102, 77, 35);
        jp1.add(lb12);

        lb13 = new JLabel("");
        lb13.setIcon(new ImageIcon("images/bomb_supply.png"));
        lb13.setBounds(20, 27, 66, 96);
        jp1.add(lb13);

        tf1 = new JTextField();
        tf1.setHorizontalAlignment(SwingConstants.CENTER);
        tf1.setText("1");
        tf1.setBounds(170, 62, 45, 30);
        jp1.add(tf1);
        tf1.setColumns(10);
        tf1.setOpaque(false);
        tf1.setBorder(null);
        tf1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                e.consume(); //取消键盘输入
            }
        });

        bt12 = new JButton("+");
        bt12.setBounds(225, 62, 45, 30);
        bt12.setFocusPainted(false);
        bt12.setContentAreaFilled(false);
        jp1.add(bt12);
        bt12.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                String a = tf1.getText();
                int b = Integer.parseInt(a);
                if (b >= 9) {
                } else
                    tf1.setText("" + (b + 1));
            }
        });

        bt13 = new JButton("-");
        bt13.setBounds(113, 62, 45, 30);
        bt13.setFocusPainted(false);
        bt13.setContentAreaFilled(false);
        jp1.add(bt13);
        bt13.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                String a = tf1.getText();
                int b = Integer.parseInt(a);
                if (b <= 0) {
                } else
                    tf1.setText("" + (b - 1));
            }
        });

        jp2 = new JPanel();
        jp2.setBounds(378, 91, 280, 145);
        getContentPane().add(jp2);
        jp2.setLayout(null);
        jp2.setOpaque(false);
        jp2.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        bt21 = new JButton("购买");
        bt21.setFont(new Font("宋体", Font.PLAIN, 18));
        bt21.setBounds(113, 102, 157, 35);
        bt21.setFocusPainted(false);
        bt21.setContentAreaFilled(false);
        bt21.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String numString = tf2.getText();
                num2 = Integer.parseInt(numString);

                try {
                    gold = new Warehouse().read();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                new Sale(num2, PRICE, gold, jlb2);
            }
        });
        jp2.add(bt21);


        lb21 = new JLabel("售价：1000");
        lb21.setFont(new Font("SimSun", Font.PLAIN, 20));
        lb21.setHorizontalAlignment(SwingConstants.CENTER);
        lb21.setBounds(113, 10, 157, 42);
        jp2.add(lb21);

        lb22 = new JLabel("火力武器");
        lb22.setFont(new Font("SimSun", Font.PLAIN, 17));
        lb22.setHorizontalAlignment(SwingConstants.CENTER);
        lb22.setBounds(15, 102, 77, 35);
        jp2.add(lb22);

        lb23 = new JLabel("");
        lb23.setIcon(new ImageIcon("images/bullet_supply.png"));
        lb23.setBounds(20, 27, 66, 86);
        jp2.add(lb23);

        tf2 = new JTextField();
        tf2.setHorizontalAlignment(SwingConstants.CENTER);
        tf2.setText("1");
        tf2.setBounds(170, 62, 45, 30);
        jp2.add(tf2);
        tf2.setColumns(10);
        tf2.setOpaque(false);
        tf2.setBorder(null);
        tf2.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                e.consume(); //取消键盘输入
            }
        });

        bt22 = new JButton("+");
        bt22.setBounds(225, 62, 45, 30);
        bt22.setFocusPainted(false);
        bt22.setContentAreaFilled(false);
        jp2.add(bt22);
        bt22.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                String a = tf2.getText();
                int b = Integer.parseInt(a);
                if (b >= 9) {
                } else
                    tf2.setText("" + (b + 1));
            }
        });

        bt23 = new JButton("-");
        bt23.setBounds(113, 62, 45, 30);
        bt23.setFocusPainted(false);
        bt23.setContentAreaFilled(false);
        jp2.add(bt23);
        bt23.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                String a = tf2.getText();
                int b = Integer.parseInt(a);
                if (b <= 0) {
                } else
                    tf2.setText("" + (b - 1));
            }
        });
	/*
		jp3 = new JPanel();
		jp3.setBounds(62, 264, 280, 145);
		getContentPane().add(jp3);
		jp3.setLayout(null);
		jp3.setOpaque(false);
		jp3.setBorder(BorderFactory.createLineBorder(Color.black,1));

		bt31 = new JButton("购买");
		bt31.setFont(new Font("宋体", Font.PLAIN, 18));
		bt31.setBounds(113, 102, 157, 35);
		bt31.setFocusPainted(false);
		bt31.setContentAreaFilled(false);
		bt31.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String numString=tf3.getText();
				int num=Integer.parseInt(numString);
				System.out.println(num);

				try {
					gold = new Warehouse().read();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new Sale(num,PRICE,gold,jlb2);
			}
		});
		jp3.add(bt31);


		lb31 = new JLabel("售价：1000");
		lb31.setFont(new Font("SimSun", Font.PLAIN, 20));
		lb31.setHorizontalAlignment(SwingConstants.CENTER);
		lb31.setBounds(113, 10, 157, 42);
		jp3.add(lb31);

		lb32 = new JLabel("清屏武器");
		lb32.setFont(new Font("SimSun", Font.PLAIN, 17));
		lb32.setHorizontalAlignment(SwingConstants.CENTER);
		lb32.setBounds(15, 102, 77, 35);
		jp3.add(lb32);

		lb33 = new JLabel("");
		lb33.setIcon(new ImageIcon("JPG/1.png"));
		lb33.setBounds(20, 27, 66, 66);
		jp3.add(lb33);

		tf3 = new JTextField();
		tf3.setHorizontalAlignment(SwingConstants.CENTER);
		tf3.setText("1");
		tf3.setBounds(170, 62, 45, 30);
		jp3.add(tf3);
		tf3.setColumns(10);
		tf3.setOpaque(false);
		tf3.setBorder(null);
		tf3.addKeyListener((KeyListener) new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
					e.consume(); //取消键盘输入
			}
		});

		bt32 = new JButton("+");
		bt32.setBounds(225, 62, 45, 30);
		bt32.setFocusPainted(false);
		bt32.setContentAreaFilled(false);
		jp3.add(bt32);
		bt32.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String a = tf3.getText();
				int b = Integer.parseInt(a);
				if(b>=9) {
				}else
				tf3.setText(""+(b+1));
			}
		});

		bt33 = new JButton("-");
		bt33.setBounds(113,62, 45, 30);
		bt33.setFocusPainted(false);
		bt33.setContentAreaFilled(false);
		jp3.add(bt33);
		bt33.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String a = tf3.getText();
				int b = Integer.parseInt(a);
				if(b<=0) {
				}else
				tf3.setText(""+(b-1));
			}
		});

		jp4 = new JPanel();
		jp4.setBounds(378, 264, 280, 145);
		getContentPane().add(jp4);
		jp4.setLayout(null);
		jp4.setOpaque(false);
		jp4.setBorder(BorderFactory.createLineBorder(Color.black,1));

		bt41 = new JButton("购买");
		bt41.setFont(new Font("宋体", Font.PLAIN, 18));
		bt41.setBounds(113, 102, 157, 35);
		bt41.setFocusPainted(false);
		bt41.setContentAreaFilled(false);
		bt41.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String numString=tf4.getText();
				int num=Integer.parseInt(numString);
				System.out.println(num);

				try {
					gold = new Warehouse().read();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new Sale(num,PRICE,gold,jlb2);
			}
		});
		jp4.add(bt41);


		lb41 = new JLabel("售价：1000");
		lb41.setFont(new Font("SimSun", Font.PLAIN, 20));
		lb41.setHorizontalAlignment(SwingConstants.CENTER);
		lb41.setBounds(113, 10, 157, 42);
		jp4.add(lb41);

		lb42 = new JLabel("清屏武器");
		lb42.setFont(new Font("SimSun", Font.PLAIN, 17));
		lb42.setHorizontalAlignment(SwingConstants.CENTER);
		lb42.setBounds(15, 102, 77, 35);
		jp4.add(lb42);

		lb43 = new JLabel("");
		lb43.setIcon(new ImageIcon("JPG/1.png"));
		lb43.setBounds(20, 27, 66, 66);
		jp4.add(lb43);

		tf4 = new JTextField();
		tf4.setHorizontalAlignment(SwingConstants.CENTER);
		tf4.setText("1");
		tf4.setBounds(170, 62, 45, 30);
		jp4.add(tf4);
		tf4.setColumns(10);
		tf4.setOpaque(false);
		tf4.setBorder(null);
		tf4.addKeyListener((KeyListener) new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
					e.consume(); //取消键盘输入
			}
		});

		bt42 = new JButton("+");
		bt42.setBounds(225, 62, 45, 30);
		bt42.setFocusPainted(false);
		bt42.setContentAreaFilled(false);
		jp4.add(bt42);
		bt42.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String a = tf4.getText();
				int b = Integer.parseInt(a);
				if(b>=9) {
				}else
				tf4.setText(""+(b+1));
			}
		});

		bt43 = new JButton("-");
		bt43.setBounds(113,62, 45, 30);
		bt43.setFocusPainted(false);
		bt43.setContentAreaFilled(false);
		jp4.add(bt43);
		bt43.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String a = tf4.getText();
				int b = Integer.parseInt(a);
				if(b<=0) {
				}else
				tf4.setText(""+(b-1));
			}
		});
		*/


        setTitle("商城");
        setLocationRelativeTo(null);
        getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        this.setResizable(false);//设置不可拉伸大小
        getContentPane().setLayout(null);

    }

    public static void main(String[] args) {
        new Mall(10000);
    }
	
	/*public static void main(String[] args) throws IOException{
		new Mall();//登录界面
	}*/
}
