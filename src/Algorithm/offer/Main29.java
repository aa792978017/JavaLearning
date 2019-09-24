package Algorithm.offer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 顺时针代印矩阵
 * 画图找规律
 */
public class Main29 {

    public static ArrayList<Integer> list ;
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if (matrix != null){
            list = new ArrayList<>();
            int rows = matrix.length;
            int columns = matrix[0].length;
            int start = 0;
            while(rows > start*2 && columns > start*2){
                getAns(matrix,rows,columns,start);

                start++;
            }

        }
        return list;

    }
    public static void getAns(int[][] matrix, int rows, int columns,int start){
        int endY = columns - 1 - start;
        int endX = rows - 1 - start;

        //判断开始列是不是小于等于结束列，如果是，进行第一步，当印第一行
        if (start <= endY){
            for(int i = start; i <= endY; i++){
                list.add(matrix[start][i]);
            }
            //判断开始行是不是小于结束行，如果是，进行大二不，打印第二行
            if(start < endX){
                for(int i = start+1; i <= endX; i++){
                    list.add(matrix[i][endY]);

                }
                //判断开始列小于结束列，则进入第三步，打印第三行
                if(start<endY && start<endX){
                    for(int i = endY-1 ;i >= start; i--){
                        list.add(matrix[endX][i]);

                    }
                }
                //判断行差是不是大于等于三，如果是进入第四步
                if(start < endY && start<endX-1){
                    for(int i = endX-1; i >start; i--){
                        list.add(matrix[i][start]);

                    }
                }
            }


        }







    }
}
