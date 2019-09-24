package Algorithm.offer;

/**
 * 反转字符串
 * 反转两次，先整体反转，然后根据空格局部反转
 */
public class Main58 {
    public static String ReverseSentence(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        int begin = 0;
        int end = 0;
        int l = str.length()-1;
        while(end <= l){
            while(end <= l && sb.charAt(end) != ' ' ){
                end++;
            }

            int i = begin;
            int j = end-1;
            if(end > l){
                j = l;
            }
            while(i < j){
                char b1 = sb.charAt(i);
                char e1 = sb.charAt(j);
                sb.setCharAt(i,e1);
                sb.setCharAt(j,b1);
                i++;
                j--;
            }
            begin = end+1;
            end = begin;
            if(end >= l-1){
                break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ReverseSentence("Hello World!");
    }
}
