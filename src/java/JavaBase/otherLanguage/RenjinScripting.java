/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.otherLanguage;

import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 执行一段Renjin代码，并获取其返回值
 */
public class RenjinScripting {

  public static void main(String[] args) throws ScriptException {
    // 获取脚本引擎管理器
    ScriptEngineManager manager = new ScriptEngineManager();
    // 获取以安装的语言引擎
    manager.getEngineFactories().forEach(
        scriptEngineFactory -> System.out.println(scriptEngineFactory.getLanguageName()));
    // 从管理器获取脚本引擎
    ScriptEngine engine = manager.getEngineByName("ECMAScript");
    // 给引擎添加变量
    engine.put("a", 42);
    // 执行脚本，获取返回值
    Object ret = engine.eval("var b =  2; a*b");
    System.out.println(ret); // 浮点运算，所以为84.0

  }
}
