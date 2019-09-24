import java.util.Scanner;

public class Main221 {
    public static int ifcan(int i){
        if (i < 10){
            return i;
        }else if(i >=10 && i < 100){
            return i/10 + i%10;
        }else{
            return 1;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        int min = Math.min(n,m);
        int max = Math.max(n, m);
        int ans = 0;

        int j = 0;
            for (j = 0; j < min; j++){
                if (k < (ifcan(j)*2)){
                    break;
                }
            }
        ans = j*j;
        for (int i = j; i < min; i++){
            for (int l = 0; l < j; l++){
                if (k >= ifcan(l) + ifcan(i)){
                    ans++;
                }
            }
        }

        for (int i = 0; i < min; i++){
            for (int l = j; l < max; l++){
                if (k >= ifcan(l) + ifcan(i)){
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}
