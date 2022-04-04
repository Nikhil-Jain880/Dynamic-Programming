// https://practice.geeksforgeeks.org/problems/reach-the-nth-point5433/1/#
// https://leetcode.com/problems/climbing-stairs/submissions/

class climbingStairs {
    static int[] dp;

    public static int nthPoint(int n) {
        dp = new int[n + 1];
        return helper(n);
    }

    public static int helper(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (dp[n] != 0)
            return dp[n];

        return dp[n] = (helper(n - 1) + helper(n - 2)) % 1000000007;
    }

    // Tabulation
    public static int tabulation(int n) {
        if (n <= 2)
            return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 17;
        // System.out.println(nthPoint(n));
        System.out.println(tabulation(n));
    }
}