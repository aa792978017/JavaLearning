package Java.EffitiveJava;

public class Main {
    /**
     * try-catch在循环之中
     * @return
     */
    public static long testTryCatch1(){
        //记录开始时间毫秒数
        long beginTime = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < 9999999999.0; i++){
            try {
                a = 1;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //记录结束时间毫秒数
        long endTime = System.currentTimeMillis();
//        返回这段代码运行时间
        return endTime - beginTime;
    }

    /**
     * try在外边，优化的代码
     * @return
     */
    public static long testTryCatch2(){
        //记录开始时间毫秒数
        long beginTime = System.currentTimeMillis();
        int a = 0;
        try {
            for (long i = 0; i < 9999999999.0; i++){
                a = 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //记录结束时间毫秒数
        long endTime = System.currentTimeMillis();
//        返回这段代码运行时间
        return endTime - beginTime;
    }




    public void test(){
        int a = 0;
        for (int i = 0; i < 100000000; i++){
            try {
                a++;
            }catch (Exception e){
            }
        }
    }

    public void test1(){
        int a = 0;
        try {
            for (int i = 0; i < 100000000; i++){
                 a++;
            }
        }catch (Exception e){
        }
    }

    public static void test3(){
        //记录开始时间毫秒数
        long beginTime = System.currentTimeMillis();
        int a = 0;

        for (long i = 0; i < 100000000; i++){
            a++;
        }

        //记录结束时间毫秒数
        long endTime = System.currentTimeMillis();
//        返回这段代码运行时间
        System.out.println(endTime - beginTime);
    }

    public static int a = 0;

    public static void test4(){
        //记录开始时间毫秒数
        long beginTime = System.currentTimeMillis();


        for (long i = 0; i < 100000000; i++){
            a++;
        }

        //记录结束时间毫秒数
        long endTime = System.currentTimeMillis();
//        返回这段代码运行时间
        System.out.println(endTime - beginTime);
    }

    public void test5(){
        //二维数组
        int[][] doubleArray = new int[100][100];
        for (int k = 0; k < doubleArray.length; k++){
            for (int j = 0; j < doubleArray[0].length; j++){
                doubleArray[k][j] = j;
            }
        }

        //跟上面二维数组等价的一维数组
        int[] array = new int[1000000];
        int re = 0;
        for (int k = 0; k < array.length; k++){
            array[k] = k;
        }
        //优化代码，加循环放大后测试两者性能相差两倍多

        boolean a = false;
        boolean b = true;
        int d = 0;
        //位运算
        for (int i = 0; i < 1000000; i++){
            if (a & b & "JavaScript".contains("Java")){
                d++;
            }
        }
        //布尔运算，优化的方案
        for (int i = 0; i < 1000000; i++){
            if (a && b && "JavaScript".contains("Java")){
                d++;
            }
        }

        int[] oldArray = new int[10000000];
        int n = oldArray.length;
        int[] newArray = new int[n];
        for (int i = 0 ; i < n; i++){
            //调用native方法
            System.arraycopy(oldArray, 0, newArray, 0, n);
        }

    }




}
