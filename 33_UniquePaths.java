import java.util.Arrays;

class UniquePaths {

    // Memoization
    static int[][] dp;
    public static int uniquePaths(int m, int n) {
        dp = new int[m][n];
        for(int[] val : dp)
        {
            Arrays.fill(val,-1);
        }
        
        int count = helper(m-1,n-1);
        return count;
    }
    
    public static int helper(int i, int j)
    {
        
        if(i == 0 && j == 0)
        {
            return 1;
        }
        
        if(i<0 || j<0) return 0;
        
        if(dp[i][j] != -1) return dp[i][j];
        
        return dp[i][j] = helper(i,j-1) + helper(i-1,j); // up and left
    }

    // Tabulation
    public static int tabulation(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1; //Base case 
        
        for(int i =1;i<n;i++) //Base case  1st row 1st col will be 1
        { // Cause to reach from 00 to 05 or 09 ot=r 0 100 there is only one way "Straight path"
            dp[0][i] = dp[0][i-1]; 
        }
        for(int i =1;i<m;i++) //Base case 
        {
            dp[i][0] = dp[i-1][0]; 
        }
        
        for(int i =1;i<m;i++)
        {
            for(int j =1;j<n;j++)
            {
                dp[i][j] = dp[i-1][j] + dp[i][j-1]; // left + up
            }
        }
        return dp[m-1][n-1];
    }
    public static void main(String[] args) {
        int m =6;
        int n =4;
        System.out.println(uniquePaths(m, n));
        System.out.println(tabulation(m, n));
    }
}
