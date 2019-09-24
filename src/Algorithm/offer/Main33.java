package Algorithm.offer;

/**
 * 二叉搜索树的后序遍历
 * 思想：后序遍历的特点，最后一个节点为根节点，
 * 前面比根节点小的都是其左子树，后面比根节点大的都是右子树
 * 递归判断即可
 */
public class Main33 {
    public static boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence == null || sequence.length == 0){
            return false;
        }else if(sequence.length == 1){
            return true;
        }
        return getAns(sequence,0,sequence.length-1);

    }
    public static boolean getAns(int[] arr, int b, int l){
        if(b == l){
            return true;
        }
        int index = b;
        while(index < l){
            if(arr[index] > arr[l]){
                break;
            }
            index++;
        }
        //有左右子树
        if( b < index && index < l - 1){
            int cu = index+1;
            while(cu < l){
                //如果右子树里面有小于根节点的，返回false
                if(arr[cu] < arr[l]){
                    return false;
                }
                cu++;
            }
            return getAns(arr,b,index-1) && getAns(arr,index,l-1);

        }else if(b == index){
            //只有右子树
            int cu = index+1;
            while(cu < l){
                //如果右子树里面有小于根节点的，返回false
                if(arr[cu] < arr[l]){
                    return false;
                }
                cu++;
            }
            return getAns(arr,b,l-1);
        }
        else{
            //只有左子树
            return getAns(arr,b,index-1);
        }



    }

    public static void main(String[] args) {
        int[] arr = {5,7,6,9,11,10,8};
        VerifySquenceOfBST(arr);
    }
}
