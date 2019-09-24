package Algorithm.offer;

/**
 * 机器人路径问题
 * 使用回溯法解决
 */
public class Main13 {

    //记录能走的格子总数
    public  int total = 0;

    //函数入口
    public int movingCount(int threshold, int rows, int cols) {
        //定义格子,用来判断该格子是否尝试走过,初始化为0,标识没有尝试走过该格子
        int[][] notes = new int[rows][cols];
        for (int i = 0; i < notes.length; i++){
            for (int j = 0; j < notes[0].length; j++){
                notes[i][j] = 0;
            }
        }
        //从（0,0）开始走
        moving(threshold, 0, 0, notes);
        //返回能走的总格数
        return total;
    }

    public void moving(int threshold, int row, int col, int[][] notes){
        //如果越界或者已经遍历过,就返回退出
        if (row >= notes.length || col >= notes[0].length || row < 0 || col < 0 || notes[row][col] == 1){
            return;
        }
        //该格子尝试走过
        notes[row][col] = 1;
        //计算是否能走
        if (!canMove(threshold, row, col)){
            //如果不能走返回
            return;
        }else{
            //能走,总数+1
            total++;
            //继续走,四个方向
            //往上走
            moving(threshold, row-1, col, notes);
            //往下走
            moving(threshold, row+1, col, notes);
            //往左走
            moving(threshold, row, col-1, notes);
            //往右走
            moving(threshold, row, col+1, notes);
        }
    }

    /**
     * 判断是当前格是否能走
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public boolean canMove(int threshold, int rows, int cols){
        //定义计数器
        int sum = 0;
        int lest = rows;
        //求出行的每位之和
        while (lest > 0){
            sum += lest%10;
            lest = lest/10;
        }
        //求出列的每位之和
        lest = cols;
        while (lest > 0){
            sum += lest%10;
            lest = lest/10;
        }
        //如果超过则返回false
        if (sum > threshold){
            return false;
        }
        //符合返回true
        return true;

    }
}
