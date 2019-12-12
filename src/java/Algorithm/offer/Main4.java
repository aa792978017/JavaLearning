package java.Algorithm.offer;

/**
 * 在行列元素依次递增的二维数组里面快速寻找是否存在某元素
 * 方法：通过右上角或左下角元素迅速每次排除一列或一行，减少遍历次数
 */
public class Main4 {
    public boolean Find(int target, int [][] array) {
        if(array == null){
            return false;
        }

        int rows = array.length-1;
        int cols = array[0].length-1;
        int row = 0;
        int col = cols;
        while(true){
            if(row>=0 && row <= rows && col >= 0 && col<=cols){
                int c = array[row][col];
                if(c == target){
                    return true;
                }else if(c > target){
                    col--;
                }else{

                    row++;
                }
            }else {
                return false;
            }


        }

    }
}
