// https://leetcode.com/problems/maximal-square/

import java.util.Arrays;

class MaximalSquare {


    // memoization
    static int[][] dp;
    public static int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        dp = new int[m][n];
        
        for(int[] row : dp)
        {
            Arrays.fill(row,-1);
        }
        
        int ans =0;
        for(int i =0;i<n;i++) //last col as it is
        {
            dp[m-1][i] = matrix[m-1][i] - '0'; // Matrix is in char so - '0' will convert it to int
            
            ans = Math.max(ans, matrix[m-1][i]);
        }
        for(int i =0;i<m;i++) //last row as it is
        {
            dp[i][n-1] = matrix[i][n-1] - '0';
            
            ans = Math.max(ans, matrix[i][n-1]);
        }
        if(m == 1 || n == 1) return ans - '0'; // Base Case
        
        for(int i=0;i<m;i++)
        {
            for(int j =0;j<n;j++)
            {
                if(matrix[i][j] == '0') //putting zero's as it is
                {
                    dp[i][j] = 0;
                }
            }
        }
        
        for(int i=0;i<m-1;i++)
        {
            for(int j =0;j<n-1;j++)
            {
                ans = helper(matrix, i, j); // Call for each element; if we do not call for each element it is not traversing full matrix
            }
        }
        
        
        int max = 0;
        for(int i=0;i<m;i++)
        {
            for(int j =0;j<n;j++)
            {
                max = Math.max(max, dp[i][j]); // picking out max value
            }
        }
        for(int[] row : dp)
        {
            System.out.println(Arrays.toString(row));
        }
        return max * max; // Square
    }
    
    public static int helper(char[][] mat, int i, int j)
    {
        // if(i == mat.length-1 || j == mat[0].length-1) return dp[i][j];

        if(dp[i][j] != -1) return dp[i][j];
        // if and only if mat[i][j] is 1 this line will be accessed
        //  add one to min of right or down or right digonal cause for a square this 3 needs to b 1 
        // if any one is 0 then 0 will be returned as we are taking min so 1 + 0 would be 1 which is the default
        // as mat[i][j] is 1 so that index alone can also make a square of 1 side
        return dp[i][j] = 1+ Math.min(helper(mat, i, j+1), Math.min(helper(mat, i+1, j), helper(mat, i+1, j+1)));
        
    }

    // Tabulation
    private static int Tabulation(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int maxSquare =0;
        
        
        for(int i =0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                if(i == 0 || j == 0) dp[i][j] = matrix[i][j] - '0';
                else if(matrix[i][j] == '0') dp[i][j] = 0;
                else
                {
                    
                    int min = Math.min(dp[i-1][j], dp[i][j-1]);
                    int finalMin = Math.min(min, dp[i-1][j-1]);
                    dp[i][j] = 1 + finalMin;
                }
                maxSquare = Math.max(maxSquare, dp[i][j] * dp[i][j]);
            }
        }
        
//         for(int[] rows : dp)
//         {
//             System.out.println(Arrays.toString(rows));
//         }
        
        return maxSquare;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        int backtacking = maximalSquare(matrix);
        System.out.println(backtacking);
        System.out.println(Tabulation(matrix));
    }
    
}
