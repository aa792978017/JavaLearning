package java.Algorithm.sort;



/**
 * 快速排序
 * 算法思想：把选定主元，然后把小于主元的元素放到主元前面，把大于主元的元素放到主元后面，
 * 然后分别对前面和后面的元素递归，再次选定主元，继续操作，递归求解，算法复杂度跟主元的选取有关，
 * 算法复杂度为：平均时间：O（nlogn）
 * 该类继承Sort类，
 * Sort类里面由生成随机数组的方法
 *
 */
public class QuickSort extends Sort {

    //算法运行的主方法
    public static void main(String args[]){
//        Scanner input = new Scanner(System.in);
//        System.out.println("请分别输入要生成的随机数组的长度和元素最大值：");
//        int sortNum = input.nextInt();              //设定要随机生成数组的长度
//        int MaxNum = input.nextInt();                 //设定随机数组元素的最大值
//        int[] arr = Sort.initArray(sortNum, MaxNum); //调用生成随机数组的函数
//        System.out.println("元素个数：" + sortNum);
//        Date beginTime = new Date();
//        System.out.println("快速排序开始时间：" + beginTime);
//        quickSort(arr);
//        Date endTime = new Date();
//        System.out.println("快速排序结束时间：" + beginTime);
//        long costTime = endTime.getTime() - beginTime.getTime();  //计算输出排序所用的时间
//        printTime("QuickSort",costTime,arr.length);


    }

    /**
     * 快速排序入口
     * 传入要排序的数组，然后交给它的重载方法，开始快速排序
     * @param arr
     */
    public static void quickSort(int[] arr){
        quickSort(arr,0,arr.length - 1);
    }

    /**
     * 真正的快速排序算法，
     * 使用递归的方式实现
     * @param arr
     * @param first
     * @param last
     */
    private static void quickSort(int[] arr, int first, int last) {
        if(last > first){
            //通过partition函数为主要的算法体
            int pivotIndex = partition(arr,first,last);
            //主元左边开始递归调用
            quickSort(arr,first,pivotIndex - 1);
            //主元右边开始递归调用
            quickSort(arr,pivotIndex + 1,last);
        }
    }

    /**
     * 算法核心代码，
     * 用于把大于主元的元素放到主元的右边，小于主元的元素放到左边
     * @param arr
     * @param first
     * @param last
     * @return
     */
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


}
