//import java.util.Scanner;
//
//public class MainT2 {
//     class index{
//        int i;
//        index pre;
//    }
//    public static int fin(String s){
//        if (s.length() == 1){
//            return 1;
//        }
//        if (s == null && s.length() == 0){
//            return 0;
//        }
//        int i = 0, j = 1;
//        int index = 0;
//        index root = new index();
//        root.i = 0;
//        int len = s.length();
//        index current = root;
//        while (j<s.length()){
//            if (s.charAt(i) != s.charAt(j)){
//                len = len - 2;
//                i = index;
//                j++;
//            }else{
//                index=i;
//                if (i == j - 1){
//                    i++;
//                    j++;
//                }else{
//                    index i = new index();
//
//                    i = j;
//                    j++;
//                }
//
//            }
//        }
//        return len;
//    }
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
////        int n = in.nextInt();
////        String s = in.next();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 100000; i++){
//            sb.append(1);
//            sb.append(1);
//        }
//        String s = sb.toString();
//        long x = System.currentTimeMillis();
//        System.out.println(fin(s));
//        long y = System.currentTimeMillis();
//        System.out.println(y - x);
//
//
//
//    }
//
//}
