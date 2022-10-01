package com.hfm.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Register extends JFrame implements ActionListener{
	JButton jb1, jb2;	//按钮
	JLabel jlb3,jlb4,jlb5,jlb7;	//标签
	JTextField jtf3;	//文本框
	JPasswordField jpf4,jpf5;	//密码框
	JPanel jp3, jp4,jp5,jp6,jp7;	//面板
	public Register() {
		//按钮
		jb1=new JButton("提交");
		jb2=new JButton("登录");
		//设置按钮监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		//标签信息
		jlb7 = new JLabel("欢迎注册");
		jlb7.setFont(new Font("宋体", Font.PLAIN, 28));
		jlb3=new JLabel("注册账号");
		jlb4=new JLabel("输入密码");
		jlb5=new JLabel("确认密码");
		jtf3=new JTextField(13);
		//密码
		jpf4=new JPasswordField(13);
		jpf5=new JPasswordField(13);
		
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		jp6=new JPanel();
		jp7=new JPanel();
		//将相应的信息加入面板
		jp3.add(jlb3);
		jp3.add(jtf3);
		jp4.add(jlb4);
		jp4.add(jpf4);
		jp5.add(jlb5);
		jp5.add(jpf5);
		jp6.add(jb1);
		jp6.add(jb2);
		jp7.add(jlb7);
		//将JPanel加入JFrame中  
		this.add(jp7);
		this.add(jp3); 
	    this.add(jp4);
	    this.add(jp5);
	    this.add(jp6);
	    //设置布局
	    this.setTitle("注册信息");
	    this.setLayout(new GridLayout(5,1));
	    this.setSize(250,240);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setVisible(true);
	    this.setResizable(false);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="提交") {
			try {
				register();
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(e.getActionCommand()=="登录") {
			new Login();
			dispose();
		}
	}
	//清空账号和密码框
	private void clear() {
		jtf3.setText("");
		jpf4.setText("");
	}
	//验证注册信息,并作处理
	public void register() throws IOException{
		//判断信息是否补全
		if(jtf3.getText().isEmpty()||jpf4.getText().isEmpty()||jpf5.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "信息有空缺，请补全！","消息提示",JOptionPane.WARNING_MESSAGE);
		}else if (new Check().checkcountname(jtf3.getText())||new Check().checkcountname(jpf4.getText())||new Check().checkcountname(jpf5.getText())){
			JOptionPane.showMessageDialog(null, "账号或密码存在中文，不合法!","消息提示",JOptionPane.WARNING_MESSAGE);
		}else if(!jpf4.getText().equals(jpf5.getText())) {
			JOptionPane.showMessageDialog(null, "两次输入密码不一致！","消息提示",JOptionPane.WARNING_MESSAGE);
		}else if (!jtf3.getText().isEmpty()&&!jpf4.getText().isEmpty()&&!jpf5.getText().isEmpty()) {
			//注册成功， 打包为信息数组传递给UserMessage进行更新操作
			String []message = new String[2]; 
			message[0] = jtf3.getText();
			message[1] = jpf4.getText();
			if (!new Check().check2(message[0])){   //调用Check的check方法检测用户是否存在， 如果不存在执行
				new UserMessage().write(message);   //调用UserMseeage的write方法进行写操作， 将信息格式化存入
				JOptionPane.showMessageDialog(null,"注册成功！","提示消息",JOptionPane.WARNING_MESSAGE);
				dispose();  //使窗口消失
				new Login();
			}else {
				JOptionPane.showMessageDialog(null,"账号已存在，请重新输入！","提示消息",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
