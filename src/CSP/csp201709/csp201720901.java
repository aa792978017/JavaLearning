package CSP.csp201709;

import java.util.Scanner;

public class csp201720901 {

    public static int buy(int money){
        int count = 0;

        //先判断是否大于50元，如果大于50元的话，就先用来都买那个优惠，
        if (money >= 50){
            int num50 = money / 50;
            money = money % 50;
            count += num50 * 7;

        }
        if (money >=30){
            money = money - 30;
            count += 4;
        }
        if (money > 0){
            int num10 = money / 10;
            count += num10;
        }
        //如果不大于50元，那就尝试买30元优惠的，如果30都不够的画，就一瓶一瓶的买。

        return count;
    }

    public static void main(String[] args){
        Scanner input= new Scanner(System.in);
        int money = input.nextInt();
        int num = buy(money);
        System.out.println(num);
    }
}
