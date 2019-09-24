package Algorithm.offer;

/**
 * 矩阵中的路径
 * 回溯法
 */
public class Main12 {
    //函数入口
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str){
        //建立字符串的二维矩阵,方便我们思考问题
        char[][] mat = new char[rows][cols];
        //建立一个记录矩阵，用于记录我们的字符串是否已经被用过,0表示没有用过,1标识已经用过
        int[][] note = new int[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                note[i][j] = 0;
                mat[i][j] = matrix[index];
                index++;
            }
        }
        //在字符矩阵中顺序找一个字符串开始寻找,只要有一个满足,就结束循环,返回结果
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (getPath(mat, str, 0, note, i, j))
                    return true;
            }
        }
        return false;
    }

    /**
     * 具体从字符矩阵的每一个字符开始寻找
     * 如果当前的不相等,则回溯
     * 如果相等,对其上下左右的字符串开始尝试
     * @param mat
     * @param str
     * @param index
     * @param note
     * @param row
     * @param col
     * @return
     */
    public boolean getPath(char[][] mat, char[] str, int index, int[][] note, int row, int col){
        //如果已经寻找到了最后一个,则返回真
        if (index == str.length){
            return true;
        }
        //如果越界或者该字符串已经被用过,则返回假
        if (col < 0 || row < 0 || col >= mat[0].length || row >= mat.length || note[row][col] == 1){
            return false;
        }
        //如果该字符满足,则开始对上下左右的字符继续寻找
        if (mat[row][col] == str[index]){
            //标识该字符已经用过
            note[row][col] = 1;
            //需要比对下一个字符
            index++;
            //尝试向上走
            if(getPath(mat,str,index,note,row-1,col))
                return true;
            //尝试向下走
            if(getPath(mat,str,index,note,row+1,col))
                return true;
            //尝试向左走
            if (getPath(mat,str,index,note,row,col-1))
                return true;
            //尝试向右走
            if (getPath(mat,str,index,note,row,col+1))
                return true;
        }
        //如果上下左右的都不符合,则把该字符标志改为没有用过,然后进行回溯
        note[row][col] = 0;
        return false;


    }


}
