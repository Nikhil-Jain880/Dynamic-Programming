import java.util.*;


class Pascals_Triangle {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();
        
        for(int i=0;i< numRows;i++)
        {
            sub = new ArrayList<>();
            for(int j =0 ; j<=i;j++)
            {
                if(j==0 || j == i)
                {
                    sub.add(1);
                    continue;
                }
                int prev = ans.get(i-1).get(j-1);
                int after = ans.get(i-1).get(j);
                sub.add(prev+after);
            }
            
            ans.add(sub);
            
        }
        return ans;
    }
    public static void main(String[] args) {
        
        int rows = 5;
        List<List<Integer>> results  = generate(rows);

        for(List<Integer> list : results)
        {
            System.out.println(list);
        }
    }
    
}
