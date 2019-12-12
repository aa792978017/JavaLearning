package java.JavaBase.StringSkill;

public class TestAppendString {
   public static void test1(){
       //理论上是会先生成Stringand 然后生成Stringandappend,生成多个对象
       String str = "String" + "and" + "append";
       StringBuilder stringBuilder = new StringBuilder();
       //理论上生成一个对象，应该会比上面的快
       stringBuilder.append("String").append("and").append("append");

       //但是结果是str会比stringBuilder快，因为java在编译时就把String str = “Stringandappend”了
       //java对于编译时便能确定的字符串常量取值工作，在编译的时候就会进行计算
       //即：对于静态字符串的连接操作，java在编译时会进行彻底优化，将多个连接操作的字符串在编译时合成为一个
   }
   public static void test2(){
       String str1 = "String";
       String str2 = "and";
       String str3 = "append";
       String result = str1 + str2 +str3;

       new StringBuilder().append(str1).append(str2).append(str3);

       //这两次字符串的累加结果速度几乎一样
       //可知对于字符串累加，java也对其进行了优化，使用了StringBuilder对象来处理，所以和我们显式用StringBuilder几乎一样
   }

   public static void test3(){
      String str = "";
       for (int i = 0; i < 10000;i++){
           str += i;
       }
       String st1 = "";
       for (int i = 0; i < 10000;i++){
            st1.concat(String.valueOf(i));
       }
       StringBuilder sb = new StringBuilder();
       for (int i = 0; i < 10000;i++){
            sb.append(i);
       }
       //这时候 sb是最快的，其次是concat 最后是第一个  sb比+=快了1000倍
//       说明在 +=时候，java效率是很低的
   }

   public static void test4(){
//       StringBuilder StringBuffer 是有默认容量16的，如果我们可以提前指定正确的大小
//         就可以减少扩容的时候，数组复制的时间损耗 ,扩容的时候每次容量翻倍
        StringBuilder sb = new StringBuilder(100);
        StringBuffer stringBuffer = new StringBuffer(101);
   }
}
