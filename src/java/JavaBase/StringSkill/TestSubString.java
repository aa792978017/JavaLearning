package java.JavaBase.StringSkill;

import java.util.ArrayList;

/**
 * subString内存溢出问题，原因：使用了空间换时间的方式
 */
public class TestSubString {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        hugeStr h = new hugeStr();
//        ImproveHugeStr h = new ImproveHugeStr();
        for (int i = 0; i < 1000000; i++){
            list.add(h.getSubString(1,5));
        }
    }

    /**
     * 调用的是String(offset, count, char[])方法，这个方法采用的是空间换时间策略，
     * 通过复制String所有值，改变偏移量来实现新建一个字符串，会把不需要的部分也复制过来，大量浪费空间
     */
    static class hugeStr{
        private String str = new String(new char[10000000]);
        public String getSubString(int begin, int end){
            return str.substring(begin, end);
        }
    }

    /**
     * 安全的的subString使用
     * 因为每次它最后都是通过String（String str）的构造方法来新建返回字符串
     * 因此没有采取空间换时间的策略，是只复制需要的部分
     */
    static class ImproveHugeStr{
        private String str = new String(new char[10000000]);
        public String getSubString(int begin, int end){
//            使得str.subString()生成的对象失去了强引用，gc可以回收
            return new String(str.substring(begin, end));
        }
    }
}
