import java.util.Arrays;

class Longest_Palindromic_Subsequence {
    
    // Recursion - TLE 
    private static int longestPalindromeSubseq(String s) {
        int len = s.length();
        if(len == 1) return 1;
        int max = 0;
        
        for(int i =0;i<len;i++)
        {
            int ans1 = helper(s, i ,i)-1; // both left adn right are same for the 1s step so -1
            int ans2 = helper(s, i, i+1);
            
            int tempMax = Math.max(ans1, ans2); // Maximum among two
            
            max = Math.max(max, tempMax); // final max length
            // System.out.println(ans1+" "+ans2+" "+max);
        }
        return max;
    }
    
    private static int helper(String s, int left, int right)
    {
        if(left < 0 || right >= s.length()) return 0;
        
        if(s.charAt(left) == s.charAt(right))
        {
            return 2 + helper(s, left-1, right+1);
        }
        return Math.max(helper(s, left-1, right), helper(s, left, right+1));
    }

    /**
     * @param s - type String (input string)
     * This question is fairly simillar to 37_longes common subsequence just there is one slight difference
     * in this question we only have 1 input string but is lcs we were give two input string 
     * in lcs we had to fild the common elements and if you think about it what is palindrome(this question)
     * if be reverse the s then all there is left to find the common elements between s and reverse(s)  
     * 
     * (In the above Recursion Solution I did try to implement LCS but in a different way)
     */

    static int[][] dp;
    private static int RevLCS(String s) {
        
        String revS = "";
        int m = s.length();
        
        for(int i =m-1;i>=0;i--) // Reverse Array
        {
            revS += s.charAt(i);
        }
        
        
        dp = new int[m][m];
        for(int[] rows : dp)
        {
            Arrays.fill(rows,-1);
        }
        
        int ans = helper(s, revS, m, m); // Just Reverse the array and pass len in this case len is same for both
        
        return ans;    
    }
    
    private static int helper(String a, String b, int i, int j) // LCS Memoization function (subsequence)
    {
        if(i == 0 || j == 0) return 0;
        if(dp[i-1][j-1] != -1) return dp[i-1][j-1];
        
        if(a.charAt(i-1) == b.charAt(j-1))
        {
            return dp[i-1][j-1] = 1 + helper(a, b, i-1, j-1);
        }
        return dp[i-1][j-1] = Math.max(helper(a, b, i-1, j), helper(a, b, i,j-1));
    }


    public static void main(String[] args) {
        String s = "bbbab";

        System.out.println(longestPalindromeSubseq(s));
        System.out.println(RevLCS(s));
    }
}
