package java.JavaBase.jvm;

public class TestStack {

    /**
     * 测试栈的深度
     */
    public static int count = 0;
    public static void recursion(){
        count++;
        System.out.println(count);
        recursion();
    }

    /**
     * 增加了局部变量，栈帧扩大了自己的局部变量表，递归的次数明显变小
     * -Xss1M  设置栈空间的最大值为1M
     * @param a
     * @param b
     * @param c
     */
    public static void recursion(long a,long b, long c){
        long d = 0;
        long e = 0;
        long f = 0;
        count++;
        System.out.println(count);
        recursion(a,b,c);
    }

    public static void test1(){
        {
            byte[] b = new byte[6*1024*1024];
        }
        System.gc();
        System.out.println("first explict gc over");
    }
    public static void main(String[] args) {
        test1();
    }
}
