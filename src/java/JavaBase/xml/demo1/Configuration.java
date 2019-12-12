/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.xml.demo1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 通过JAXB演示XML，用于表示一个典型的图形用户界面中user的配置
 * 通过命令 schemagen -cp $js/target -d . Configuration.java 生成
 * 该命令由JAXB提供的使用工具schemagen提供
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuration", propOrder = {"screenName", "webProxy", "verbose", "colorName"})
@XmlRootElement(name = "config")
public class Configuration {
  private String webProxy;
  private boolean verbose;
  private String colorName;
  private String screenName;

  public String getColorName() {
    return colorName;
  }

  public void setColorName(String colorName) {
    this.colorName = colorName;
  }

}
