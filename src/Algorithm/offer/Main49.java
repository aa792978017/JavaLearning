package Algorithm.offer;

/**
 * 丑数
 * 考点:短时间理解新概念,然后求解
 *
 */
public class Main49 {

    public static int GetUglyNumber_Solution(int index) {
        if(index <= 0){
            return 0;
        }else if(index == 1){
            return 1;
        }
        int[] uglys = new int[index+1];
        uglys[1] = 1;
        int index2 = 1;
        int index3 = 1;
        int index5 = 1;
        int indexAns = 1;
        while (indexAns < index){
            int min = getMin(uglys[index2]*2, uglys[index3]*3, uglys[index5]*5);
            uglys[indexAns+1] = min;
            indexAns++;
            while(uglys[index2]*2 <= min){
                index2++;
            }
            while(uglys[index3]*3 <= min){
                index3++;
            }
            while(uglys[index5]*5 <= min){
                index5++;
            }
        }

        return uglys[index];
    }

    public  static int getMin(int x, int y, int z){
        int min = x > y?y:x;
        min = min > z?z:min;
        return min;
    }


}
