package Algorithm.offer;

/**
 * 打印从1到最大的n位数
 * 注意点：想考察大数问题，用 int long 常规算术加法输出很可能会溢出
 * 思想：使用char[]字符数组处理，注意进位和溢出点的判断
 */
public class Main17 {
    public static void print1ToMaxOfNDigits(int n){
        if (n <= 0){
            return;
        }
        //声明数组，用来存储要输出的数
        char[] chars = new char[n];
        //初始话数组
        for (int i = 0; i < n; i++){
            chars[i] = '0';
        }
        //如果没有溢出
        while (!increment(chars)){
            printNum(chars);
        }
    }

    public static boolean increment(char[] chars){
        boolean ifOverFlow = false;  //是否溢出
        int nTakeOver = 0;  //进位符
        int l = chars.length - 1;
        for (int i = l; i >= 0; i--){
            int nSum = chars[i] - '0' + nTakeOver;
            //如果是个位，则+1,保证整个循环里面，只有一次+1
            if (i == l){
                nSum++;
            }
            //如果当前位大于10,则考虑是否需要进位
            if(nSum >= 10){
                //判断是否溢出，溢出则退出，返回已经溢出，退出程序
               if (i == 0){
                   ifOverFlow = true;
                   break;
               }
               //没有溢出
                nSum -= 10;
               //每次进位只能为1
                nTakeOver = 1;
                //修改当前位置进位后的余数
                chars[i] = (char) ('0' + nSum);

            }else{
                chars[i] = (char) ('0' + nSum);
                break;
            }

        }
        return ifOverFlow;
    }

    public static void printNum(char[] chars){
        //首字符如果是空，则不输出
        boolean begin = true;
        int l = chars.length - 1;
        for (int i = 0; i <= l; i++){
            if (begin && chars[i] != '0'){
                begin = false;
            }
            //不是为零的首字符，可以输出
            if (!begin){
                System.out.print(chars[i]);
            }
        }
        //完成输出一个数，换行
        System.out.println();
    }

    public static void main(String[] args) {
        print1ToMaxOfNDigits(1);
    }
}
