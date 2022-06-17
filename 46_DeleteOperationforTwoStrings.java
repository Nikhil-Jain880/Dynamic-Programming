// https://leetcode.com/problems/delete-operation-for-two-strings/

import java.util.Arrays;

class DeleteOperationforTwoStrings {
    // a simple lcs question just calculate the lcs and substract the total len with 2*lcs 
    
    static int[][] dp;
    static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        dp = new int[m][n];
        for(int[] row: dp)
        {
            Arrays.fill(row, -1);
        }
        int len = helper(word1, word2, 0, 0);
        // System.out.println(len);
        return (m+n) - len-len;
    }
    static int helper(String word1, String word2, int i, int j)
    {
        if(i == word1.length() || j == word2.length()) return 0;
        if(dp[i][j] != -1) return dp[i][j];
        if(word1.charAt(i) == word2.charAt(j))
        {
            return dp[i][j] = 1+ helper(word1, word2, i+1, j+1);
        }
        return dp[i][j] = Math.max(helper(word1, word2, i+1, j), helper(word1, word2, i, j+1));
    }
    public static void main(String[] args) {
        String word1 = "leetcode";
        String word2 = "etco";
        System.out.println(minDistance(word1, word2));
    }
}
