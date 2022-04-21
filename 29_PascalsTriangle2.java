// https://leetcode.com/problems/pascals-triangle-ii/

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSplitPaneUI;

class PascalsTriangle2 {

    // Recursion
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        helper(ans, rowIndex);
        return ans;
    }
    
    public static void helper(List<Integer> ans, int row)
    {
        if(row == 0)
        {
            ans.add(1);
            return;
        }
        helper(ans, row-1);
        
        for(int j =row-1; j>=1; j--)
        {
            int t1 = ans.get(j);
            int t2 = ans.get(j-1);
            ans.set(j, t1+t2);
        }
        ans.add(1);
    }


    // Tabulation
    public static List<Integer> tabulation(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();
        
        for(int i=0;i<= rowIndex;i++)
        {
            sub = new ArrayList<>();
            for(int j =0 ; j<=i;j++)
            {
                if(j==0 || j == i) // The 1st and last element is always one
                {
                    sub.add(1);
                    continue;
                }
                int prev = ans.get(j-1);
                int after = ans.get(j);
                sub.add(prev+after);
            }
            
            ans = sub;
            
        }
        return ans;
    }

    public static void main(String[] args) {
        int row = 12;
        System.out.println(getRow(row));
        System.out.println(tabulation(row));        
    }
}
