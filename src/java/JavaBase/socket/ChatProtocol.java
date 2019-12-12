/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.socket;

public class ChatProtocol {
  public static final int PORTNUM = 8080;
  public static final char CMD_MESG = 'B';
  public static final char CMD_BCAST = 'D';
  public static final char CMD_LOGIN = 'A';
  public static final int SEPARATOR = '|';
  public static final char CMD_QUIT = 'C';

  public static boolean isValidLoginName(String line) {
   return line != null && line.length() != 0;
  }
}

