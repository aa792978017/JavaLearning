/*
 * Copyright 2019-2022 the original author or authors.
 */


import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * 参数：表示三个文件名： XML、XSL、ouput(html)
 */
public class JAXPTransform {

  public static void main(String[] args) throws TransformerException {
    if (args.length != 3) {
      System.out.println("Usage: java JAXPTransform input.xml");
      System.exit(1);
    }
    // 创建一个转换对象
    Transformer tx = TransformerFactory.newInstance().newTransformer(
        new StreamSource(new File(args[1])));
    // 使用transform()方法来执行转换
    tx.transform(new StreamSource(new File(args[0])), new StreamResult(new File(args[2])));
  }
}
