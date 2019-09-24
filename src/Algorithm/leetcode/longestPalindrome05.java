package Algorithm.leetcode;

public class longestPalindrome05 {
    public static String longestPalindrome(String s){
        if (s == null) {
            return null;
        }else if(s.equals("") || s.equals(" ")){
            return "";
        }
        int start = 0,end = 0;
        int n = s.length();
        for (int i = 0; i < n; i++){
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i+1);
            int len = Math.max(len1, len2);
            if (len > end - start){
                start = i - ((len - 1)>>1);
                end = i + ((len - 1) >>1);
            }
        }
        return s.substring(start, end+1);
    }


    public static int expand(String s, int i, int j){
        int L = i;
        int R = j;
        int n = s.length();
        while ( L >= 0 && R < n && s.charAt(L) == s.charAt(R)){
            L--;
            R++;
        }
        return R - L - 1;
    }


}
