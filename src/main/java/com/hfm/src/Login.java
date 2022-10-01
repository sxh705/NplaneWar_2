package com.hfm.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Login extends JFrame implements ActionListener {
	JButton jb1,jb2,jb3;//按钮
	JPanel jp1,jp2,jp3,jp4;//面板
	JTextField jtf;//文本框
	JLabel jlb1,jlb2,jlb3;//标签
	JPasswordField jpf;//密码框
	static public String username;
	public Login() {
		
		jb1=new JButton("登录");
		jb2=new JButton("注册");
		//设置按钮监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		//创建面板
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		//添加标签
		jlb1=new JLabel("账号	");
		jlb2=new JLabel("密码	");
		//创建文本框和密码框
		jtf= new JTextField(10);
		jpf=new JPasswordField(10);
		//加入面板
		jp1.add(jlb1);
		jp1.add(jtf);
		
		jp2.add(jlb2);
		jp2.add(jpf);
		
		jp3.add(jb1);
		jp3.add(jb2);
		//将JPanel加入JFrame
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		//设置布局
		this.setTitle("用户登录");
		this.setLayout(new GridLayout(3,1));
		this.setSize(300,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);//设置可见
		this.setResizable(false);//设置不可拉伸大小
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="登录") {
			try {
				login();
			}catch (HeadlessException|IOException e1) {
				e1.printStackTrace();
			}
		}else if (e.getActionCommand()=="注册") {
			new Register();
			dispose();
		}
	}
	//清空账号和密码框
	private void clear() {
		jtf.setText("");
		jpf.setText("");
	}
	//验证登录信息并作处理
	@SuppressWarnings("deprecation")
	public void login() throws HeadlessException,IOException{
		//判断账户名和密码是否含中文
		if(new Check().checkcountname(jtf.getText()) ||new Check().checkcountname(jpf.getText())) {
			JOptionPane.showMessageDialog(null,"账号或密码存在中文，不合法！","消息提示",JOptionPane.WARNING_MESSAGE);
		}else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(null,"账号和密码为空，请输入！","消息提示",JOptionPane.WARNING_MESSAGE);
		}else if (jtf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "账号为空，请输入！","消息提示",JOptionPane.WARNING_MESSAGE);
		}else if (jpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "密码为空，请输入！","消息提示",JOptionPane.WARNING_MESSAGE);
		}else if(new Check().check1(jtf.getText(),jpf.getText())) {
			JOptionPane.showMessageDialog(null, "登录成功","消息提示",JOptionPane.WARNING_MESSAGE);
			username=jtf.getText();
			dispose();
			new MainInterface();
		}else {
			JOptionPane.showMessageDialog(null, "账号密码错误，请重新输入！","消息提示",JOptionPane.WARNING_MESSAGE);
			clear();
		}
	}

}
