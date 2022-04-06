import java.util.Arrays;

// https://leetcode.com/problems/min-cost-climbing-stairs/

class MinimumCostClimbingStairs {

    // Memoization
    int[] memo;

    public int Memoization(int[] cost) {
        memo = new int[cost.length];
        Arrays.fill(memo, -1); // As we can either climb one or two steps. So, one to start from
        return Math.min(helper(cost, 0), helper(cost, 1)); // 1st step and other for 2nd step
    }

    public int helper(int[] nums, int n) {
        if (nums.length <= n)
            return 0;
        if (memo[n] != -1)
            return memo[n];
        // Add and current cost, store it and either 1 or 2 step which ever yields min
        // cost
        return memo[n] = nums[n] + Math.min(helper(nums, n + 1), helper(nums, n + 2));
    }

    // Tabulation
    int[] dp;

    public int Tabulation(int[] cost) {
        dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        } // At the end we either end at the last or 2nd last cause we can climb 2 steps
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }

    public static void main(String[] args) {
        int[] nums = { 2, 7, 9, 3, 1 };
        MinimumCostClimbingStairs obj = new MinimumCostClimbingStairs();
        System.out.println(obj.Memoization(nums));
        System.out.println(obj.Tabulation(nums));
    }
}
