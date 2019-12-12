package java.Algorithm.leetcode;

public class Main125 {
    public static boolean ifchar(int i,String s){

        char ichar = s.charAt(i);
        if (!((ichar >= 65 && ichar <= 90) || (ichar >= 48 && ichar <= 57))){
            return false;
        }else{
            return true;
        }
    }
    public static boolean main(String s){
        if (s == null){
            return false;
        }
        s = s.toUpperCase();
        int j = s.length() - 1;
        int i = 0;
        while (true){
            while (i < j && !ifchar(i,s)){
                i++;
            }
            while (i < j && !ifchar(j,s)){
                j--;
            }
            if (i < j){
                if (s.charAt(i) != s.charAt(j)){
                    return false;
                }else {
                    i++;
                    j--;
                }
            }else{
                break;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(main("A man, a plan, a canal: Panama"));
    }
}
