package Algorithm.offer;

/**
 * 数组中重复的数字
 * 思想:hash函数实现O(1)查找,多费一部分空间存储下标
 */
public class Main_ex1 {
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if(numbers == null || numbers.length <= 1 || length <= 1){
            return false;
        }
        int[] indexs = new int[length];
        for(int i = 0; i < length; i++){
            indexs[i] = -1;
        }
        int[] counts = new int[length];
        for(int i = 0; i < length; i++){
            counts[numbers[i]]++;
            if(indexs[numbers[i]] == -1){
                indexs[numbers[i]] = i;
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < length; i++){
            if(counts[i] > 1 && min > indexs[i]){
                min = indexs[i];
            }
        }

        if(min < Integer.MAX_VALUE){
            duplication[0] = numbers[min];
            return true;
        }
        duplication[0] = -1;
        return false;



    }
}