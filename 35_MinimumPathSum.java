// https://leetcode.com/problems/minimum-path-sum/

import java.util.*;

class MinimumPathSum {

    static int[][] dp;
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        dp = new int[m][n];
        
        for(int[] row : dp)
        {
            Arrays.fill(row,-1);
        }
        dp[0][0] = grid[0][0];
        int minSum = helper(grid, m-1, n-1);
        return minSum;
    }
    
    public static int helper(int[][] grid, int i, int j)
    {
        if(i == 0 && j == 0) return grid[i][j]; // Base case
        if(i<0 || j<0 || i>= grid.length || j>=grid[0].length) return Integer.MAX_VALUE; // out ob bounds
        
        if(dp[i][j] != -1) return dp[i][j];
        
        return dp[i][j] = grid[i][j]+ Math.min(helper(grid, i-1, j), helper(grid, i, j-1)); // take min of left and up
    }

    // Tabulation
    public static int tabulation(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        
         for(int i =1;i<n;i++)
        { 
            dp[0][i] = grid[0][i] + dp[0][i-1]; 
        }
        for(int i =1;i<m;i++)
        {
            dp[i][0] = grid[i][0] + dp[i-1][0]; 
        }
        
        for(int i =1;i<m;i++)
        {
            for(int j =1;j<n;j++)
            {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1,2,5},
                        {4,2,1},
                        {8,9,1}};
        System.out.println(minPathSum(grid));
        System.out.println(tabulation(grid));

    }
}
