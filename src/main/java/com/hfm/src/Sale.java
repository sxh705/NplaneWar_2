package com.hfm.src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Sale extends JFrame implements ActionListener{
		private int num,price,gold;
		JLabel jlb;
		public Sale(int num,int price,int gold,JLabel a){
			this.jlb = a;
			this.num = num;
			this.price = price;
			this.gold = gold;
			JPanel jp = new JPanel();
			jp.setBorder(new EmptyBorder(5, 5, 5, 5));
			this.setContentPane(jp);
			jp.setLayout(null);
			this.setBounds(100, 100, 350, 200);
			setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);
			this.setResizable(false);//设置不可拉伸大小
			
			JLabel jlb = new JLabel("是否确认购买？");
			jlb.setFont(new Font("SimSun", Font.PLAIN, 28));
			jlb.setHorizontalAlignment(SwingConstants.CENTER);
			jlb.setBounds(62, 10, 233, 58);
			jp.add(jlb);
			
			JButton bt1 = new JButton("是");
			bt1.setBounds(33, 95, 120, 35);
			jp.add(bt1);
			bt1.addActionListener(this);
			bt1.setFocusPainted(false);
			bt1.setContentAreaFilled(false);
			
			JButton bt2 = new JButton("否");
			bt2.setBounds(194, 95, 120, 35);
			jp.add(bt2);
			bt2.addActionListener(this);
			bt2.setFocusPainted(false);
			bt2.setContentAreaFilled(false);
		}
		public void actionPerformed (ActionEvent e) {
			if(e.getActionCommand().equals("是")) {
					if(num*price<=gold) {	
						JOptionPane.showMessageDialog(new JPanel(), "购买成功！",null, JOptionPane.PLAIN_MESSAGE);  
						gold=gold-num*price;
						try {
							new Warehouse().write(gold);
							jlb.setText(""+gold);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(new JPanel(), "金币余额不足！",null, JOptionPane.WARNING_MESSAGE);
					}
					dispose();
			}
			else if(e.getActionCommand().equals("否")) {
				dispose();
			}
		}
	}
