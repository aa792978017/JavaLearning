/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.Reflect;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 扫描某包下的所有文件
 */
public class ClassesInPackage {

  public static String[] getPackageContent(String packageName) throws IOException {
    // 把包分隔符"."替换成“/”
    final String packageAsDirName = packageName.replace(".", "/");
    final List<String> list = new ArrayList<>(); // 换为set，去除重复扫描的情况（在构建目录和Jar文件都能找到这些成员）
    // 获取所有文件的url
    final Enumeration<URL> urls = Thread.currentThread().
        getContextClassLoader().
        getResources(packageAsDirName);
    while (urls.hasMoreElements()) {
      URL url = urls.nextElement();
      System.out.println("URL = " + url);
      // windows下防止在盘符前多加“/”
      String file = url.getFile().substring(1);
      switch (url.getProtocol()) {
        case "file":
          // 常出现的情况，"file"是到类路径中目录的完整路径
          File dir = new File(file);
          for ( File f : dir.listFiles()) {
            list.add(packageAsDirName + "/" + f.getName());
          }
          break;
        case "jar":
          // 这是一个比较难的情况，对于一些jar文件包含至少一个给定包的类时
          // "file" 具有 “jar:/home/ian/bleah/darwinsys.jar!com/darwinsys/io” 这样的形式
          int colon = file.indexOf(':');
          int bang = file.indexOf('!');
          // jar文件名
          String jarFileName = file.substring(colon + 1, bang);
          JarFile jarFile = new JarFile(jarFileName);
          Enumeration<JarEntry> entries = jarFile.entries();
          while (entries.hasMoreElements()) {
            JarEntry e = entries.nextElement();
            String jarEntryName = e.getName();
            if (!jarEntryName.endsWith("/") &&
                jarEntryName.startsWith(packageAsDirName)) {
              list.add(jarEntryName);
            }
          }
          break;
        default:
          throw new IllegalStateException("Dunno what to do with URL " + url);
      }
    }
    return list.toArray(new String[] {});
  }

  public static void main(String[] args) throws IOException {
    String[] names = getPackageContent("java.JavaBase.ParallelPattern");
    for (String name : names) {
      System.out.println(name);
    }
    System.out.println("Done");
  }

}
