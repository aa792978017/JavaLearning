/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.Reflect;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过注解找到特定的类
 */
public class PluginsViaAnnotation {

  /**
   * 在类级别上找到给定包中找到所有类
   * @param packageName 报名
   * @param annotationClass 需要找到的类
   * @return 特定类
   * @throws Exception
   */
  public static List<Class<?>> findAnnotatedClasses(String packageName,
      Class<? extends Annotation> annotationClass) throws Exception {
    List<Class<?>> ret = new ArrayList<>();
    // 获取扫描到的所有class
    String[] classes = ClassesInPackage.getPackageContent(packageName);
    // 加载遍历所有类，判断有无某注解
    for (String clazz : classes) {
      Class<?> c = Class.forName(clazz);
      if (c.isAnnotationPresent(annotationClass)) {
        ret.add(c);
      }
    }
    return ret;
  }

}
