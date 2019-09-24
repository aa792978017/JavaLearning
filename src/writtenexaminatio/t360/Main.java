package writtenexaminatio.t360;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int string2int(String str) {
        if (str == null ){
            return 0;
        }

        str = str.replace(" ","");
        if (str == " " || !Pattern.matches("(-?|[+]?|)[0-9]+(([.][0-9]+)|[0-9]*)",str) ){
            return 0;
        }
        int index ;
        if(  (index = str.indexOf(".")) != -1){
            str = str.substring(0,index);
        }
        return Integer.valueOf(str);


    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        String _str;
        try {
            _str = in.nextLine();
        } catch (Exception e) {
            _str = null;
        }

        res = string2int(_str);
        System.out.println(String.valueOf(res));

    }
}

