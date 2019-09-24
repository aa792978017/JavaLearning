//package writtenexaminatio.wanmeishijie;
//
//import java.util.Scanner;
//public class Main1 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int m = in.nextInt();
//
//    }
//}
//
//import java.util.Scanner;
//
//public class MyTest2 {
//    static int max=-1;
//    static boolean flag=true;
//    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        int n=sc.nextInt();
//        int m=sc.nextInt();
//        int[][]map=new int[n][m];
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                map[i][j]=sc.nextInt();
//            }
//        }
//        minblood(map,n-1,m-1,-1,0,0,-1);
//        int ans=-1*max;
//        System.out.println(ans);
//    }
//
//    private static void minblood(int[][] map,int n,int m,int curr,int midn,int midm,int min) {
//        curr=curr+map[midn][midm];
//        min=Math.min(min, curr);
//        if(midn==n&&midm<m){//到达行边界未到列边界
//            minblood(map,n,m,curr,midn,midm+1,min);
//        }else if(midm==m&&midn<n){ //到达列边界未到行边界
//            minblood(map,n,m,curr,midn+1,midm,min);
//        }else if(midn==n&&midm==m){//到行列边界
//            if(flag){//第一次到达右下角
//                max=min;
//                flag=false;
//            }else{
//                max=Math.max(min,max);
//            }
//        }else{ //未到达边界
//            minblood(map,n,m,curr,midn+1,midm,min);
//            minblood(map,n,m,curr,midn,midm+1,min);
//        }
//    }
//
//}
