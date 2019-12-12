package java.Algorithm.offer;

/**
 * 替换空格
 * 把空格替换成%20
 * 如果不能用辅助空间，则先遍历一次获取空格个数，再开一个确定大小的数组，然后从后往前去空格，这样移动的字符比较少
 */
public class Main5 {
    public String replaceSpace(StringBuffer str) {
        if(str == null){
            return null;
        }
        int l = str.length();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < l; i++){
            if(str.charAt(i) == ' '){
                sb.append("%20");
            }else{
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }
}
