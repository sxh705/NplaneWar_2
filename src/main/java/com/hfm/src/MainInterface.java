package com.hfm.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainInterface extends JFrame implements ActionListener{
	public MainInterface()  {
		ImageIcon bg =new ImageIcon("images/background.png");
		setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		JLabel label=new JLabel(bg);
		label.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		JPanel jp = (JPanel)this.getContentPane();
		jp.setOpaque(false);
		jp.setLayout(null);
		add(label);
		
		setTitle("主界面");
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
        getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		this.setResizable(false);//设置不可拉伸大小
		getContentPane().setLayout(null);

		JButton bt1 = new JButton("开始游戏");
		bt1.setForeground(new Color(0,0,0));
		bt1.setFont(new Font("宋体", Font.PLAIN, 70));

		bt1.setBounds(35, 200, 400, 200);
		bt1.setFocusPainted(false);
		
		bt1.setContentAreaFilled(false);
		bt1.addActionListener(this);
		getContentPane().add(bt1);


		JButton bt2 = new JButton("商城");
		bt2.setForeground(new Color(0,0,0));
		bt2.setFont(new Font("方正舒体", Font.PLAIN, 30));

		bt2.setBounds(70,450,100,100);
		bt2.setFocusPainted(false);
		
		bt2.setContentAreaFilled(false);
		bt2.addActionListener(this);
		getContentPane().add(bt2);
		
		
		JButton bt3 = new JButton("排行榜");
		bt3.setForeground(new Color(0,0,0));
		bt3.setFont(new Font("方正舒体", Font.PLAIN, 30));

		bt3.setBounds(260,450,140,100);
		bt3.setFocusPainted(false);
		
		bt3.setContentAreaFilled(false);
		bt3.addActionListener(this);
		getContentPane().add(bt3);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="开始游戏") {
			try {

				dispose();
			}catch (HeadlessException e1) {
				e1.printStackTrace();
			}
		}else if (e.getActionCommand()=="排行榜") {
			dispose();
		}else if (e.getActionCommand()=="商城") {
			new Mall();
		}
	}

	
	
	
	
	
	public static void main(String[] args) throws IOException{
		new MainInterface();//登录界面
	}
	
}
