package java.JavaBase.StringSkill;

public class TestString {
    public static void main(String[] args) {
        String a = "helloworld";
        String b =  "hello" + new String("world");  //属于新建对象
        System.out.println(a == b); //false
        System.out.println(a.hashCode() == b.hashCode());  //true hashCode用的是最终引用来计算hash值
        String s = "sfasdfaf";
        String[] slist = s.split("");
        System.out.println(slist);
    }
}
