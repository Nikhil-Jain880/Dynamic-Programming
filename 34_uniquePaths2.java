// https://leetcode.com/problems/unique-paths-ii/

/**
 * Same question as unique sum just obstacles are added through-out the grid
 * to tackle it we just need to check if the curr index which we are in is an obstacle or not
 * if it's an obstacle we just assign it with 0 value denoting no possible path from there 
 * if it's not then execute as usual (left + up)
 * Note : one special case is there if size of the grid is 1,1 and the only value present is 
 * 1 (an obstacle so print 0) all the rest recursion or the for loop will handle
 */


import java.util.Arrays;

class uniquePaths2 { 

    static int[][] dp;
    private static int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        dp = new int[m][n];
        for(int[] val : dp)
        {
            Arrays.fill(val,-1);
        }
        
        int count = helper(grid, m-1, n-1);
        return count;
    }
    
    private static int helper(int[][] grid, int i, int j)
    {
        
        if(i == 0 && j == 0 && grid[i][j] == 0)
        {
            return 1;
        }
        
        if(i<0 || j<0) return 0;
        
        if(grid[i][j] == 1) 
        {
            dp[i][j] = 0;
            return 0;
        }
        
        if(dp[i][j] != -1) return dp[i][j];
        
        return dp[i][j] = helper(grid, i,j-1) + helper(grid, i-1,j);
    }


    // Tabulation
    private static int tabulation(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] dp = new int[m][n];
        if(grid[0][0] == 0) dp[0][0] = 1;
        else return 0;
        
        for(int i =1;i<n;i++)
        {
            if(grid[0][i] == 1)
            {
                dp[0][i] = 0; //when considering a linear path when we come across a obstacle thats the end
                break; // cause we can't jump over an obstacle so curr val and all the value after that be 0,
            } // which we don't need to assign as jav's default value is 0
            dp[0][i] = dp[0][i-1]; 
        }
        
        for(int i =1;i<m;i++)
        {
            if(grid[i][0] == 1) {
                dp[i][0] = 0;
                break;
            }
            dp[i][0] = dp[i-1][0];
        }
        
        for(int i =1;i<m;i++)
        {
            for(int j =1;j<n;j++)
            {
                if(grid[i][j] == 1) continue;
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {

        int[][] grid = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(uniquePathsWithObstacles(grid));
        System.out.println(tabulation(grid));
    }
    
}
