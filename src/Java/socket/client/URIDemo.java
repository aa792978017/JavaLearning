/*
 * Copyright 2019-2022 the original author or authors.
 */

package Java.socket.client;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 归一化、相对化及从URI构建一个URL
 */
public class URIDemo {

  public static void main(String[] args) throws URISyntaxException, MalformedURLException {
    URI uri = new URI("http://www.baidu.com");
    System.out.println("Raw: " + uri.toString());
    URI normalized = uri.normalize();
    System.out.println("Normalized: " + normalized);
    final URI base = new URI("http://www.baidu.com");
    System.out.println("Relativized to " + base + ": " + base.relativize(uri));

    // URL是一种类型的URI
    URL url = new URL(normalized.toString());
    System.out.println("URL: " + url);

    //Junk
    URI uri1 = new URI("bean:WonderBean");
    System.out.println(uri1);
  }
}
