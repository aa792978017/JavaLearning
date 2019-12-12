/*
 * Copyright 2019-2022 the original author or authors.
 */

package java.JavaBase.socket.client;

import java.JavaBase.socket.ChatProtocol;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;

/**
 * 聊天客户端
 * 其他更好的结构化方法：WebSocket、RMI、JMS
 * java -D"user.name=Deep"
 */
public class ChatClient extends JFrame {

  private static final long serialVersionUID = -2270001423738681797L;

  private static final String userName =
      System.getProperty("user.name", "User With No Name");

  /** logged-in-ness的状态 */
  protected boolean loggedIn;

  /** 界面主框架*/
  protected JFrame cp;

  /** 默认端口号*/
  protected static final int PORTNUM = ChatProtocol.PORTNUM;

  /** 实际端口号*/
  protected int port;

  /** 网络套接字*/
  protected Socket sock;

  /** 用于从套接字读取数据 */
  protected BufferedReader is;

  /** 用于在套接字上发送行 */
  protected PrintWriter pw;

  /** 用于输入TextField tf */
  protected TextField tf;

  /** 用于显示对话的TextArea */
  protected TextArea ta;

  /** 登陆按钮 */
  protected Button loginButton;

  /** 注销按钮 */
  protected Button logoutButton;

  /** 标题栏标题*/
  final static String TITLE = "Chat Room Client";

  /**
   * 设置GUI
   */
  public ChatClient() {
    cp = this;
    cp.setTitle(TITLE);
    cp.setLayout(new BorderLayout());
    port = PORTNUM;

    //GUI
    ta = new TextArea(14, 80);
    ta.setEditable(false);
    ta.setFont(new Font("Monospaced", Font.PLAIN, 11));
    cp.add(BorderLayout.NORTH, ta);

    Panel p = new Panel(); //创建一个板块

    //登陆按钮
    p.add(loginButton = new Button("Login"));
    loginButton.setEnabled(true);
    loginButton.requestFocus();
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        login();
        loginButton.setEnabled(false);
        logoutButton.setEnabled(true);
        tf.requestFocus(); // 将键盘焦点设置在右边
      }
    });

    // 注销按钮
    p.add(logoutButton = new Button("Logout"));
    logoutButton.setEnabled(false);
    logoutButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        logout();
        loginButton.setEnabled(true);
        logoutButton.setEnabled(false);
        loginButton.requestFocus();
      }
    });

    p.add(new Label("Message here."));
    tf = new TextField(40);
    tf.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (loggedIn) {
          pw.println(ChatProtocol.CMD_BCAST + tf.getText());
          tf.setText("");
        }
      }
    });

    p.add(tf); // 把textField加入板块
    cp.add(BorderLayout.SOUTH, p); // 把板块加入主界面的最下方

    cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    cp.pack();
  }

  protected String serverHost = "localhost";

  /** 登陆到聊天室 */
  public void login() {
    showStatus("In login!");
    if (loggedIn) {
      return;
    }
    try {
      sock = new Socket(serverHost, port);
      is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
      pw = new PrintWriter(sock.getOutputStream(),true);
      showStatus("Got socket");
      // 现在假登录，不需要输入密码
      pw.println(ChatProtocol.CMD_LOGIN + userName);
      loggedIn = true;
    } catch (IOException ex) {
      showStatus("Can't get socket to " + serverHost + "/" + port + ": " + ex.toString());
      cp.add(new Label("Can't get socket: " + ex.toString()));
      return;
    }

    //构建和启动reader： 从服务器到文本区
    //使用一个线程来避免死锁
    new Thread(new Runnable() {
      @Override
      public void run() {
        String line;
        try {
          while (loggedIn && ((line = is.readLine()) != null)) {
            ta.append(line + "\n");
          }
        } catch (IOException ex) {
          showStatus("Lost another client!\n" + ex.toString());
          return;
        }
      }
    }).start();
  }

  /** 登出聊天室 */
  public void logout() {
    if(!loggedIn) {
      return;
    }
    loggedIn = false;
    try {
      if (sock != null) {
        sock.close();
      }
    } catch (Exception ex) {
      // 处理异常
      System.out.println("聊天室关闭异常： " + ex.toString());
    }
  }

  public void showStatus(String message) {
    System.out.println(message);
  }

  /** main方法 允许客户端作为一个应用程序*/
  public static void main(String[] args) {
    ChatClient room101 = new ChatClient();
    room101.pack();
    room101.setVisible(true);
  }

}
