package Algorithm.leetcode;

public class reverse07 {
    /**
     * 我的答案
     * @param x
     * @return
     */
    public int reverse(int x){
        String ans = String.valueOf(x);
        if (ans.startsWith("-0") || ans.startsWith("0")){
            return 0;
        }

        int end = ans.length() - 1;
        while(true) {
            if (ans.charAt(end) == '0') end--;
            else break;
        }
        ans = new String(ans.substring(0,end+1));
        if (ans.startsWith("-")){
            ans = new StringBuilder("-").append(new StringBuilder(new String(ans.substring(1))).reverse().toString()).toString();
        }else{
            ans = new StringBuffer(ans).reverse().toString();
        }
        long trueAns = Long.valueOf(ans);
        if (trueAns < -Math.pow(2,31) || trueAns > Math.pow(2,31) -1){
            return 0;
        }
        return (int) trueAns;

    }

    /**
     * 标准答案
     * @param x
     * @return
     */
    public int reverse1(int x){
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;   ///这里很灵活
        }
        return rev;
    }
}
