import java.util.Scanner;

public class Main22 {
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

        int ans = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (k > ifcan(i) + ifcan(j)){
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}
