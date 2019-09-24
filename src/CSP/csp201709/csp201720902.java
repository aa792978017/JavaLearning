package CSP.csp201709;

import java.util.Scanner;

// TODO: 18-12-7  
public class csp201720902 {
    class ReturnBox{
        int[] keyList;
        
    }

    public static void setKeyBox(int[] keyBox,int key){

        for(int i = 1; i < keyBox.length; i++){
            if (keyBox[i] == 0) {
                keyBox[i] = key;
                break;
            }
        }
    }

    public static void setReturnBox(int[][] returnBox, int key, int end){
        for (int i = 0; ;i++){
            if (returnBox[0][i] == 0){
                returnBox[0][i] = key;
                returnBox[1][i] = end;
                break;
            }
        }

    }

    public static void getKey(int[] keyBox, int[][] saveClass, int endTime){
        int[][] returnKey = new int[2][keyBox.length];
//        按时刻开始，从1时刻开始，每一时刻先判断 returnkey里面有没有需要还的钥匙
//        有的话，就取keyBox里面最小的空位置，然后把该钥匙放进去

//          然后判断saveClass有没有需要取出来的钥匙，有的话取出来，放到returnkey头部
        for (int i = 0; i <= endTime; i++ ){
            for(int j = 0; j < returnKey[0].length ;j++){
//                如果借出去的钥匙为空
                if (returnKey[0][j] == 0){
                    break;
                }
//                该时刻有要还的钥匙
                if (returnKey[1][j] == i) {
                    setKeyBox(keyBox, returnKey[0][j]);
                    returnKey[0][j] = 0;
                }
            }
//            把该时刻要取走的钥匙取走
            for (int j = 0;j<saveClass.length;j++){
                if (saveClass[j][1] == i){
                    keyBox[saveClass[j][0]] = 0;
                    setReturnBox(returnKey, saveClass[j][0], saveClass[j][1] + saveClass[j][2]);
                }
            }

        }

    }


    public static void main(String[] args){
        Scanner input= new Scanner(System.in);
        int N = input.nextInt();
        int[] keyBox = new int[N + 1];
        for (int i = 1; i <= N; i++){
            keyBox[i] = i;
        }

        int K = input.nextInt();
        int[][] saveClass = new int[K][3];
        int endTime = 0;
        for(int i = 0; i < K; i++){
            saveClass[i][0] = input.nextInt();
            saveClass[i][1] = input.nextInt();
            saveClass[i][2] = input.nextInt();
            if ((saveClass[i][1] + saveClass[i][2]) > endTime) {
                endTime = saveClass[i][1] + saveClass[i][2];
            }
        }
        getKey(keyBox, saveClass,endTime);

        for(int i = 1; i <= N; i++){
            System.out.print(keyBox[i] + " ");
        }

    }
}
