/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.socket.server;

import JavaBase.socket.ChatProtocol;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Java聊天服务器
 */
public class ChatServer {

  /**
   * 在系统消息调用自己
   */
  protected final static String CHATMASTER_ID = "ChatMaster";

  /**
   * 任何handle和消息之间
   */
  protected final static String SEP = ": ";

  /**
   * 服务套接字
   */
  protected ServerSocket serverSocket;

  /**
   * 当前客户端列表
   */
  protected List<ChatHandler> clients;

  /**
   * 调试状态
   */
  private static boolean DEBUG = false;

  /**
   * main方法,仅构造一个ChatServer,永远不返回
   */
  public static void main(String[] args) throws IOException {
    System.out.println("ChatServer 0.1 starting...");
    if (args.length == 1 && args[0].equals("-debug")) {
      DEBUG = true;
    }
    ChatServer chatServer = new ChatServer();
    chatServer.runServer();
    System.out.println("**Error* ChatServer 0.1 quitting");
  }

  /**
   * 构造并运行一个聊天服务
   *
   * @throws IOException
   */
  public ChatServer() throws IOException {
    clients = new ArrayList<>();
    serverSocket = new ServerSocket(ChatProtocol.PORTNUM);
    System.out.println("Chat Server Listening on port " + ChatProtocol.PORTNUM);
  }

  public void runServer() {
    try {
      while (true) {
        Socket userSocket = serverSocket.accept();
        String hostName = userSocket.getInetAddress().getHostName();
        System.out.println("Accepted from " + hostName);
        ChatHandler client = new ChatHandler(userSocket, hostName);
        String welcomeMessage;
        synchronized (clients) {
          // 把处理用户连接信息的线程引用保存起来
          clients.add(client);
          // 构建欢迎信息
          if (clients.size() == 1) {
            welcomeMessage = "Welcome! you're the first one here";
          } else {
            welcomeMessage = "Welcome! you're the latest of " + clients.size() + " users.";
          }
        }
        client.start();
        client.send(CHATMASTER_ID, welcomeMessage);
      }
    } catch (IOException ex) {
      log("IO　Exception in runServer:  " + ex.toString());
    }
  }

  /**
   * 日志打印
   * @param logMessage 需要打印的信息
   */
  protected void log(String logMessage) {
    System.out.println(logMessage);
  }




  /**
   * 每个线程处理一个用户对话
   */
  protected class ChatHandler extends Thread {

    /** 客户端套接字 */
    protected Socket clientSocket;

    /** 从套接字读取数据 */
    protected BufferedReader is;

    /** 从套接字上发送行数据 */
    protected PrintWriter pw;

    /** 客户端的主机名 */
    protected String clientIp;

    /** 用户句柄（名称）*/
    protected String login;

    /** 构造一个聊天程序 */
    public ChatHandler(Socket clientSocket, String clientIp) throws IOException {
      this.clientSocket = clientSocket;
      this.clientIp = clientIp;
      is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      pw = new PrintWriter(clientSocket.getOutputStream(), true);
    }


    @Override
    public void run() {
      String line;
      try {
        /**
         * 只要客户端保持连接，我们就应该一直处于这个循环
         * 当循环结束时候，我们断开这个连接
         */
        while ((line = is.readLine()) != null) {
          // 消息的第一个字符是消息类型
          char messageType = line.charAt(0);
          line = line.substring(1);
          switch (messageType) {
            case ChatProtocol.CMD_LOGIN:  // 登录消息类型：A + login（登录名）
              // 不包含登录名
              if (!ChatProtocol.isValidLoginName(line)) {
                // 回复登录消息
                send(CHATMASTER_ID, "LOGIN " + line + " invalid");
                // 日志记录
                log("LOGIN INVALID from " + clientIp);
                continue;
              }
              // 包含登录名
              login = line;
              broadcast(CHATMASTER_ID, login + " joins us, for a total of" +
                  clients.size() + " users");
              break;
            case ChatProtocol.CMD_MESG:  // 私人消息类型：B + 接受用户名 + ：+ message（消息内容）
              // 未登录，无法发送消息
              if (login == null) {
                send(CHATMASTER_ID, "please login first");
                continue;
              }
              // 解析出接收信息的用户名，消息内容
              int where = line.indexOf(ChatProtocol.SEPARATOR);
              String recip = line.substring(0, where);
              String message = line.substring(where + 1);
              log("MESG: " + login + "-->" + recip + ": " + message);
              // 查找接收消息的用户线程
              ChatHandler client = lookup(recip);
              if (client == null) {
                // 找不到后，发送该用户未登陆
                psend(CHATMASTER_ID, recip + "not logged in");
              } else {
                // 找到用户，把信息私人发送过去
                client.psend(login, message);
              }
              break;
            case ChatProtocol.CMD_QUIT: // 离线消息类型： C
              broadcast(CHATMASTER_ID, "Goodbye to " + login + "@" + clientIp);
              close();
              return; // 这个时候，该ChatHandler线程结束
            case ChatProtocol.CMD_BCAST: // 广播消息类型： D + message（消息内容）
              if (login != null) {
                this.send("All member", line);
                broadcast(login, line);
              } else {
                log("B<L FROM " + clientIp);
              }
              break;
            default: // 消息类型无法识别
              log("Unknown cmd " + messageType + " from" + login + "@" + clientIp);
          }
        }
      } catch (IOException ex) {
        log("IO Exception: " + ex.toString());
      } finally {
        // 客户端套接字结束（客户端断开连接，用户下线）
        System.out.println(login + SEP + "All Done");
        String message = "This should never appear";
        synchronized (clients) {
          // 移除离线的用户
          clients.remove(this);
          if (clients.size() == 0) {
            System.out.println(CHATMASTER_ID + SEP + "I'm so lonely I could cry...");
          } else if (clients.size() == 1) {
            message = "Hey, you're talking to yourself again";
          } else {
            message = "There are now " + clients.size() + " users";
          }
        }
        // 广播目前的聊天室状态
        broadcast(CHATMASTER_ID, message);
      }
    }

    /**
     * 断开客户端连接
     */
    protected void close() {
      // 客户端socket本来为null
      if (clientSocket == null) {
        log("close when not open");
        return;
      }

      try {
        // 关闭连接的客户端socket
        clientSocket.close();
        clientSocket = null;
      } catch (IOException ex) {
        log("Failure during close to " + clientIp);
      }
    }

    /**
     * 某个用户发送消息
     * @param sender 发送消息的用户
     * @param message 消息内容
     */
    public void send(String sender, String message) {
      pw.println(sender + SEP + message);
    }

    /**
     * 发送私人消息
     * @param sender 接受消息的用户
     * @param message 消息内容
     */
    public void psend(String sender, String message) {
      send("<*" + sender + "*>", message);
    }

    /**
     * 向所有用户发送一条消息
     * @param sender 发送者
     * @param message 消息内容
     */
    public void broadcast(String sender, String message) {
      System.out.println("Boradcasting " + sender + SEP + message);
      // 对client遍历，调用其send方法
      clients.forEach(client -> {
        if (DEBUG) {
          // 日志打印向某用户发送消息
          System.out.println("Sending to " + client);
          client.send(sender, message);
        }
      });
      // 打印日志，完成了广播
      if (DEBUG) {
        System.out.println("Done broadcast");
      }
    }

    /**
     * 通过用户昵称，查找某用户
     * @param nick 用户昵称
     * @return 返回用户的处理线程
     */
    protected ChatHandler lookup(String nick) {
      // 同步，遍历查找
      synchronized (clients) {
        for (ChatHandler client: clients) {
          if (client.login.equals(nick)) {
            return client;
          }
        }
      }
      // 找不到返回null
      return null;
    }

    /** ChatHandler的字符串形式 */
    public String toString() {
      return "ChatHandler[" + login +"]";
    }
  }
}
