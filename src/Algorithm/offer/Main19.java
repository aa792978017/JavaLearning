package Algorithm.offer;

/**
 * 正则表达式匹配
 * 注意点：考虑到所有匹配的情况
 * 思想：递归进行
 */
public class Main19 {
    public static boolean match(char[] str, char[] pattern)
    {
        if(str == null || pattern == null){
            return false;
        }
        int strIndex = 0;
        int patIndex = 0;
        return matchCore(str, strIndex, pattern, patIndex);
    }

    public static boolean matchCore(char[] str, int strIndex, char[] pat, int patIndex){
        //字符串和模式的匹配指针都大于其长度，匹配成功
        if(strIndex == str.length && patIndex == pat.length){
            return true;
        }
        //如果模式指针提前结束，匹配失败
        if(strIndex != str.length && patIndex == pat.length){
            return false;
        }
        //如果下一个模式匹配为*
        if(patIndex+1 < pat.length && pat[patIndex+1] == '*'){
            if(str.length > strIndex && ( pat[patIndex] == str[strIndex] || pat[patIndex] == '.')   ){
                return matchCore(str,strIndex+1,pat,patIndex)   //如果匹配了strIndex，而且还能继续匹配
                        || matchCore(str,strIndex+1,pat,patIndex+2)  //匹配了strIndex，但是已经不能继续匹配了
                        || matchCore(str,strIndex,pat,patIndex+2); // 没有匹配strIndex

            }else{
                return matchCore(str,strIndex,pat,patIndex+2);  //没有匹配strIndex
            }
        }
        //如果下一个模式不是*,只需要考虑当前的字符
        if(str.length > strIndex && (pat[patIndex] == '.' || pat[patIndex] == str[strIndex])){
            return matchCore(str,strIndex+1,pat,patIndex+1);
        }
        //不匹配，返回false
        return false;
    }

    public static void main(String[] args) {
        char[] str = {','};
        char[] pat = {'.','*'};
        match(str,pat);
    }
}
