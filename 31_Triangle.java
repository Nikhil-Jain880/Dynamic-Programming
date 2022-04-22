// https://leetcode.com/problems/triangle

import java.util.*;

class Triangle {

    // Memoization
    static int[][] dp;
    private static int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        dp = new int[len][len];
        for(int[] row : dp)
        {
            Arrays.fill(row, -1);
        }
        
        for(int i =0;i<len;i++) // fill up last row as it will work as return point 
        {
            dp[len-1][i] = triangle.get(len-1).get(i);
        }
        
        int minSum = helper(triangle, 0, 0);

        // for(int[] i :dp)
        // {
        //     System.out.println(Arrays.toString(i));
        // }
        return minSum;
    }
    
    private static int helper(List<List<Integer>> triangle, int i, int j)
    {
        if(i == triangle.size()-1 || dp[i][j] != -1) return dp[i][j]; // if last row or already calculated return
        
        // Minimum of down and right diagonal
        return dp[i][j] = triangle.get(i).get(j) + Math.min(helper(triangle, i+1, j), helper(triangle, i+1, j+1));
    }

    // Tabulation
    private static int Tabulation(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[][] dp = new int[len][len];
        for(int[] row : dp)
        {
            Arrays.fill(row, -1);
        }
        
        for(int i =0;i<len;i++)
        {
            dp[len-1][i] = triangle.get(len-1).get(i);
        }
        
        for(int i =0;i<len;i++)
        {
            for(int j =0; j<=i;j++)
            {
                if(i == 0)
                {
                    dp[i][j] = triangle.get(i).get(j);
                }
                else
                { // Checking for out of bounds as we are checking upward row
                    int up = j == i ? Integer.MAX_VALUE : dp[i-1][j];
                    int left = j == 0 ? Integer.MAX_VALUE : dp[i-1][j-1];
                    dp[i][j] = triangle.get(i).get(j) + Math.min(up, left);
                }
            }
        }
        
        int ans =Integer.MAX_VALUE;
        for(int i=0;i<len;i++)
        {
            ans = Math.min(ans, dp[len-1][i]);
        }
        return ans;
    }

    // Space optimization : Tabulation is done in top down, scape optimization is done in bottom up
                        // top down would have been simpler but I chose to try different flow of execution 
    private static int spaceOptimization(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[] dp = new int[len]; // 1D array is used
        Arrays.fill(dp, -1);

        dp[0] = triangle.get(0).get(0);
        
        for(int i =1;i<len;i++)
        {
            int[] current = new int[len];
            for(int j =0; j<=i;j++)
            {
                int up = j == i ? Integer.MAX_VALUE : dp[j];
                int left = j == 0 ? Integer.MAX_VALUE : dp[j-1];
                current[j] = triangle.get(i).get(j) + Math.min(up, left);

            }
            // storing the curr row as dp; this dp will be used to calculate next row cycle will go on
            dp = current; // basic space optimization technique
        }
        
        int ans =Integer.MAX_VALUE;
        for(int i=0;i<len;i++)
        {
            ans = Math.min(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> task = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(4);
        task.add(list);

        list = new ArrayList<>();
        list.add(3);
        list.add(2);
        task.add(list);

        list = new ArrayList<>();
        list.add(5);
        list.add(8);
        list.add(6);
        task.add(list);

        list = new ArrayList<>();
        list.add(3);
        list.add(8);
        list.add(1);
        list.add(1);
        task.add(list);

        System.out.println(minimumTotal(task));
        System.out.println(Tabulation(task));
        System.out.println(spaceOptimization(task));

    }
    
}
