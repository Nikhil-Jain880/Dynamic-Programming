// https://leetcode.com/problems/is-subsequence/

class isSubsequence {

    //TWO POINTER 
    private static boolean twoPionter(String s, String t) {
        int m = t.length();
        int n = s.length();
        
        if(n == 0) return true;
        if(m == 0) return false;
        
        int sPointer = 0;
        for(int i=0;i<m;i++)
        {
            if(s.charAt(sPointer) == t.charAt(i))
            {
                sPointer++;
                if(sPointer == n) break;
            }
        }
        
        if(sPointer == n) return true;
        return false;
    }
    
    //DP TOP DOWN LCS APPROACH
    private static boolean Tabulation(String s, String t) {
        int n = s.length() , m = t.length();
        int[][] dp = new int[n+1][m+1];
        for(int i = 1; i <= n;i++){
            for(int j = 1; j <= m;j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
                }
            }
        }
        // System.out.println(dp[n][m] + " " + dp[n-1][m-1]);
        return dp[n][m] == n;
    }
    
    
    //RECURSIVE SOLUTION
    private static boolean recursion(String s, String t) {
        return solve(s , 0 , t, 0);
    }
    
    private static boolean solve(String s , int i , String t, int j){
        if(i == s.length()){
            return true;
        }
        if(i < s.length() && j == t.length()) return false;
        if(s.charAt(i) == t.charAt(j)){
            return solve(s , i+1, t , j+1);
        }else {
            return solve(s , i, t, j+1);
        }
        
    }


    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";

        System.out.println(twoPionter(s, t));
        System.out.println(Tabulation(s, t));
        System.out.println(recursion(s, t));
    }
    
}
