package java.JavaBase.JUC;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 测试ThreadLocalRandom
 * 为了减少Random的自旋重试
 */
public class ThreadLocalRandomTest {
    public static void main(String[] args) {
        //获取随机数生成器
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 5; i++){
            System.out.println(random.nextInt(5));
        }


    }
}
