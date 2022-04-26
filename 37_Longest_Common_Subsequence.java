import java.util.Arrays;

class Longest_Common_Subsequence {

    // Recursion
    private static int recursion(String str1, String str2) {
        int ans = helperRecursion(str1, str2, str1.length(), str2.length());
        
        return ans;
    }
    
    private static int helperRecursion(String str1, String str2, int m, int n)
    {
        if(m == 0 || n ==0) return 0;
        
        if(str1.charAt(m-1) == str2.charAt(n-1))
        {
            return 1 + helperRecursion(str1, str2, m-1, n-1);
        }
        return Math.max(helperRecursion(str1, str2, m-1,n), helperRecursion(str1, str2, m, n-1));
    }


    // Memoization
    static int[][] dp;
    private static int Memoization(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        dp = new int[m][n];
        
        for(int[] row : dp)
        {
            Arrays.fill(row, -1);
        }
        
        int ans = helper(str1, str2, m, n);
        
        return ans;
    }
    
    private static int helper(String str1, String str2, int m, int n)
    {
        if(m == 0 || n ==0) return 0;
        
        if(dp[m-1][n-1] != -1) return dp[m-1][n-1];
        
        if(str1.charAt(m-1) == str2.charAt(n-1))
        {
            return dp[m-1][n-1] = 1 + helper(str1, str2, m-1, n-1);
        }
        return dp[m-1][n-1] = Math.max(helper(str1, str2, m-1,n), helper(str1, str2, m, n-1));
    }




    // Tabulation
    private static int tabulation(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m+1][n+1];
        
        int longestLength = 0;
        
        for(int i = 1;i<=m;i++)
        {
            for(int j =1;j<=n;j++)
            {
                if(str1.charAt(i-1) == str2.charAt(j-1))
                {
                    dp[i][j] = 1+ dp[i-1][j-1];
                }
                else 
                {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        // for(int[] row : dp)
        // {
        //     System.out.println(Arrays.toString(row));
        // }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        
        System.out.println(recursion(text1, text2));
        System.out.println(Memoization(text1, text2));
        System.out.println(tabulation(text1, text2));

    }
}