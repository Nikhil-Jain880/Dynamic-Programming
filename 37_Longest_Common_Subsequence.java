// https://leetcode.com/problems/longest-common-subsequence/


/**
 * For every Instant we have two Futures :- both the characters are same, They are not same 
 * When both the characters are same 
 * - Increment the ans by 1 and move(decrement) both the pointer
 * - func(s1, s2, m-1, n-1) + 1
 * When both the characters are not same we again are faced with a dilema which one to decrement m or n
 * - call the function two times in which keep m as same and decrement n & see what happens
 * - call the function two times in which keep n as same and decrement m & see what happens
 * - Take the max of two
 * - max(func(s1, s2, m, n-1), func(s1, s2, m-1, n)) 
 * 
 * to prove the above claim suppose we have inputs as s1 = abcg, s2 = abc; m for s1 and n for s2
 * both g and c are not same here the best way would be to decrement g(m pointer) so that the value become c
 * but the situation can be reversed also we cant always deceremnt m, there may be a case further in the array
 * in which decrementing n whould have been more fruitfull, so we decrement both seperately and check for all 
 * possible cases 
 */


import java.util.Arrays;

class Longest_Common_Subsequence {

    // Recursion
    private static int recursion(String str1, String str2) {
        int ans = helperRecursion(str1, str2, str1.length(), str2.length());
        
        return ans;
    }
    
    // m & n denotes the len of string when they are at 0 that means there is no char left to explore
    private static int helperRecursion(String str1, String str2, int m, int n)
    {
        if(m == 0 || n ==0) return 0; // Base case
        
        if(str1.charAt(m-1) == str2.charAt(n-1)) // Correction for 0 based indexing to get nth char you need to ask for n-1 
        { // if both the char are same add one to the ans and decrement both char
            return 1 + helperRecursion(str1, str2, m-1, n-1);
        } // Else keep n in its place and decrement m and vice versa and pick the one with most value
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
        
        // for all i ==0  and all j ==0 the value will be zero as they indicate that there are no elements to compare 
        // Check the base case of recursion
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
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]); // (m-1, n) + (m, n-1)
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