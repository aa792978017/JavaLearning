package java.Algorithm.sort;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * 线性时间选择算法，
 * 首先传入数组，选定一个随机下标，使用快速排序使下标前的元素都小于下标元素，后的元素都大于下标元素
 * 比较前边元素跟k大小，
 * 是对前面进行递归还是后面进行递归，对前面递归，知道最后左边界等于右边界
 * 该下标对应的元素就是第k小的元素了
 */
public class RandomizeSelect {


    //线性时间选择算法的实现
    public static int RandomizeSelect(int[] array, int k, int left, int right){
        if (right - left == 0){
            return array[left];
        }else{
            Random random = new Random();
            int randIndex = left;
            int currentIndex = partition(array,left,right);
            int minNum = currentIndex - left + 1;
            if (minNum >= k)
                return RandomizeSelect(array,k,left,currentIndex );
            else
                return RandomizeSelect(array,k - minNum, currentIndex + 1, right);
        }
    }
    //在快速排序的基础上实现线性时间选择，功能跟快速排序一样
    private static int partition(int[] arr, int first, int last) {
        int pivot = arr[first];
        int low = first + 1;
        int high = last;
        while(high > low){
            while(low <= high && arr[low] <= pivot){
                low++;
            }
            while(low <= high && arr[high] > pivot){
                high--;
            }
            if (low < high){
                int temp = arr[high];
                arr[high] = arr[low];
                arr[low] = temp;
            }
        }
        while(high > first && arr[high] >pivot){
            high--;
        }
        if(pivot > arr[high]){
            arr[first] = arr[high];
            arr[high] = pivot;
            return high;
        }else{
            return first;
        }
    }

    //算法运行的入口
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        System.out.println("请分别输入要生成的随机数组的长度和元素最大值：");
        int sortNum = input.nextInt();              //设定要随机生成数组的长度
        int MaxNum = input.nextInt();                 //设定随机数组元素的最大值
        System.out.println("请输入你要查找的第k小元素的k值：");
        int k = input.nextInt();                      //搜索第k小的元素
        int[] array = Sort.initArray(sortNum, MaxNum);    ////调用生成随机数组的函数
        System.out.println("元素个数：" + sortNum);
        Date beginTime = new Date();
        System.out.println("线性时间选择开始时间：" + beginTime);
        int ans = RandomizeSelect(array, k,0,array.length - 1);
        System.out.println("第" + k +"小的元素为 : " + ans);
        Date endTime = new Date();
        System.out.println("线性时间选择结束时间：" + beginTime);
        long costTime = endTime.getTime() - beginTime.getTime();    //计算输出算法完成所用的时间
        System.out.println("线性选择共花时间：" + costTime + "毫秒");


    }

}
