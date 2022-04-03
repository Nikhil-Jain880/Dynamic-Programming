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

    public static void main(String[] args) {
        int n = 17;
        System.out.println(n);
    }
}