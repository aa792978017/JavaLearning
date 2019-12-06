/*
 * Copyright 2019-2022 the original author or authors.
 */

package Java.socket.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * HTTP链接检查器
 */
public class LinkStatusCheck {

  /**
   * 检查一个HTTP连接，非递归。返回一个有布尔值的LinkStatus
   *
   * @param urlString 需要检查的HTTP链接
   * @return 返回HTTP链接的连接状态
   */
  public LinkStatus check (String urlString) {
    URL url;
    HttpURLConnection conn = null;
    HttpURLConnection.setFollowRedirects(false);
    try {
      url = new URL(urlString);
      conn = (HttpURLConnection) url.openConnection();
      switch (conn.getResponseCode()) {
        case 200:
          return new LinkStatus(true, urlString);
        case 403:
          return new LinkStatus(false,"403: " + urlString);
        case 404:
          return new LinkStatus(false,"404: " + urlString);
      }
      // 是否需要获取返回响应内容
      conn.getInputStream();
      return new LinkStatus(true, urlString);
    }catch (IllegalArgumentException | MalformedURLException ex ) {
      // 如果不能从URL字符串确定主机，Oracle的JDK抛出IAE异常
      return new LinkStatus(false, "Malformed URL: " + urlString);
    } catch (UnknownHostException ex) {
      return new LinkStatus(false, "Host invalid/dead: " + urlString);
    } catch (FileNotFoundException ex) {
      return new LinkStatus(false, "NOT FOUNT (404) " + urlString);
    } catch (ConnectException ex) {
      return new LinkStatus(false, "Server not listening: " + urlString);
    } catch (SocketException ex) {
      return new LinkStatus(false, ex.toString() + ": " + urlString);
    } catch (IOException ex) {
      return new LinkStatus(false, ex.toString());
    } catch (Exception ex) {
      return new LinkStatus(false, "Unexpected exception! " + ex.toString());
    } finally {
      if (conn != null) {
        conn.disconnect();
      }
    }
  }

  public static void main(String[] args) {
    System.out.println(new LinkStatusCheck().check("https://www.baidu.com/").getMessage());
  }
}
class LinkStatus {

  /**
   * 连接状态信息
   */
  private String message;

  /**
   * 响应状态是否成功
   * true为成功
   * false为失败
   */
  private boolean visitStatus;


  public LinkStatus(boolean visitStatus, String message) {
    this.visitStatus = visitStatus;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isVisitStatus() {
    return visitStatus;
  }

  public void setVisitStatus(boolean visitStatus) {
    this.visitStatus = visitStatus;
  }

}
