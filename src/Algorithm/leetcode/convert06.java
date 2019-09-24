package Algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class convert06 {
    public String convert(String s, int numRows){
        int n = s.length();
        List<StringBuilder> stringBuilderList = new ArrayList<StringBuilder>();
        for (int i = 0; i < Math.max(n,numRows); i++){
            stringBuilderList.add(new StringBuilder());
        }
        boolean direct = false;
        int curRow = 0;
        for (char c : s.toCharArray()){
            stringBuilderList.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows-1) direct = !direct;
            curRow += direct? 1:-1;
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder sb1 : stringBuilderList){
            sb.append(sb1.toString());
        }
        return sb.toString();
    }
}
