Java与其他语言的结合

运行其他语言编写程序的方式

1、通过Runtime.exec() 来调用一个编译程序或执行脚本，但是这个方式没有操作系统无关性。
  一、 用法：
  public Process exec(String command)-----在单独的进程中执行指定的字符串命令。

  public Process exec(String [] cmdArray)---在单独的进程中执行指定命令和变量

  public Process exec(String command, String [] envp)----在指定环境的独立进程中执行指定命令和变量

  public Process exec(String [] cmdArray, String [] envp)----在指定环境的独立进程中执行指定的命令和变量

  public Process exec(String command,String[] envp,File dir)----在有指定环境和工作目录的独立进程中执行指定的字符串命令

  public Process exec(String[] cmdarray,String[] envp,File dir)----在指定环境和工作目录的独立进程中执行指定的命令和变量

  二、cmd命令执行窗口开闭指令

  cmd /c dir 是执行完dir命令后关闭命令窗口。

  cmd /k dir 是执行完dir命令后不关闭命令窗口。

  cmd /c start dir 会打开一个新窗口后执行dir指令，原窗口会关闭。

  cmd /k start dir 会打开一个新窗口后执行dir指令，原窗口不会关闭。

  三、经验总结
  Runtime run = Runtime.getRuntime();
  Process p = run.exec("ping 127.0.0.1");
  这是基本用法，通过p可以获得命令执行过程中的输入流和错误流（也就是打印信息）
  InputStream ins= p.getInputStream();
  InputStream ers= p.getErrorStream();
  这两个流要用不同的线程去处理，不然容易引发阻塞
  当默认的路径（java代码执行默认为class路径）执行bat命令时，如果没有跨盘符，通过bat文件的绝对路径或者相对路径可以执行到对应的bat命令，如果跨盘符，就必须设置执行文件路径了，即需要用到如下命令 public Process exec(String command,String[] envp,File dir)，例如：Process p = run.exec("ceshi.bat",null,new File("d:\ceshi\"));或者Process p = run.exec(new String[]{"cmd","/c","start","ceshi.bat"},null,new File("d:\ceshi\"));

2、使用javax.script 调用脚本语言中的一种来运行所有外部程序
3、使用Java”本机代码“机制到C语言层次，调用C/C++编写的编译后的函数。（JNI，native方法）


---------------------------------------------------
从本机代码调用Java
JNI提供了从C调用Java的接口，步骤如下：
1、创建一个JVM
2、加载一个类
3、查找并调用类中的一个方法