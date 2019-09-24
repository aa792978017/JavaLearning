package Algorithm.offer;

/**
 * 左旋转字符串
 * 思想:三次翻转得到答案
 */
public class Main_ex2 {
    public String LeftRotateString(String str,int n) {
        if(str == null || str.length() <= 0){
            return str;
        }else if(n <= 0){
            return str;
        }
        char[] chars = str.toCharArray();
        turn(chars,0,n-1);
        turn(chars, n, chars.length-1);
        turn(chars, 0, chars.length-1);
        return new String(chars);
    }

    public void turn(char[] str, int begin, int end){

        while (begin < end){
            char temp = str[begin];
            str[begin++] = str[end];
            str[end--] = temp;
        }
    }
}
