import java.util.Scanner;

public class Main31 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        String x = Integer.toBinaryString(m);
        x = x.replace("0","");
        System.out.println(x.length());


    }
}
