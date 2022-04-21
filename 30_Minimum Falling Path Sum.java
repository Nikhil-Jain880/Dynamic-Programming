// https://leetcode.com/problems/minimum-falling-path-sum/

import java.util.Arrays;

class Minimum_Falling_Path_Sum {

    // Memoization
    static int[][] dp;
    static int n;

    public static int minFallingPathSum(int[][] matrix) {
        
        n = matrix.length;

        dp = new int[n][n];
        int ans = Integer.MAX_VALUE;
        for(int i =0; i<n; i++)
        {
            ans = Math.min(ans, helper(matrix, n-1, i)); // For every Starting index the value may be different
        }
        
        // for(int[] val : dp)
        // {
        //     System.out.println(Arrays.toString(val));
        // }
        return ans;
    }
    
    public static int helper(int[][] mat, int i, int j)
    {
        if(i < 0 || j < 0 || j>=n) // out of bounds
        {
            return Integer.MAX_VALUE;
        }
        if( i== 0) dp[i][j] = mat[i][j]; // If first index is encounterd then return that cause there no room left go and search up for min
        
        if(dp[i][j] != 0) return dp[i][j];

        // System.out.println(i+" "+j);

        // store = curr index vale + min path of topLeft || top || topRight
        return dp[i][j] =  mat[i][j]+ Math.min( helper(mat, i-1, j-1), Math.min( helper(mat, i-1, j), helper(mat, i-1, j+1)));
    }


    // Tabulation
    public static int tabulation(int[][] matrix) {
        
        int n = matrix.length;

        int[][] dp = new int[n][n];
        int ans = Integer.MAX_VALUE;
        
        for(int i =0;i<n;i++) // store the value of 1st index as its the base
        {
            dp[0][i] = matrix[0][i];
        }
        
        for(int i =1;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                int left = Integer.MAX_VALUE; // can be out of bounds (OB)
                int right = Integer.MAX_VALUE; // "

                int up = dp[i-1][j]; // never will be OB becasue we are looking directly up 
                if(j != 0) left = dp[i-1][j-1]; // if not ob the assign desired val top left
                if(j != n-1) right = dp[i-1][j+1]; // if not ob the assign desired val top right

                dp[i][j] =matrix[i][j]+ Math.min(up, Math.min(left, right));
            }
        }
        
        for(int i =0;i<n;i++)
        {
            ans = Math.min(ans, dp[n-1][i]);
        }
        
        return ans;
    }

    // Optimized : Space optimization done over tabulation
    public static int optimized(int[][] matrix) {
        int n = matrix.length;

        int[] cache = new int[n]; // We dont need a 2D array when we are just checking for just the upward row
        // chache will store the val of the upward row (act as a replacement on dp array)
        int ans = Integer.MAX_VALUE;
        
        for(int i =0;i<n;i++)
        {
            cache[i] = matrix[0][i];
        }
        
        for(int i =1;i<n;i++)
        {
            int[] current = new int[n]; //cal the values of current row
            for(int j=0;j<n;j++)
            {
                int left = Integer.MAX_VALUE;
                int right = Integer.MAX_VALUE;
                int up = cache[j];
                
                if(j > 0) left = cache[j-1];
                if(j < n-1) right = cache[j+1];
                
                current[j] =matrix[i][j]+ Math.min(up, Math.min(left, right));
            }
            // one all the curr row values are calculated transfer it to cache
            // as the current row will become the prev row of the upcoming row
            // the val of current row 1 will become the prev row of row 2 

            cache = current; // we store the current row val to cache 
            // because we will need the current rpw to calculate the next row
        }

        for(int i =0;i<n;i++)
        {
            ans = Math.min(ans, cache[i]);
        }
        
        return ans;
    }
    

    public static void main(String[] args) {
        
        int[][] matrix = {{-19,57},{-40,-5}};
        System.out.println(minFallingPathSum(matrix));
        System.out.println(tabulation(matrix));
        System.out.println(optimized(matrix));
    }
    
}
