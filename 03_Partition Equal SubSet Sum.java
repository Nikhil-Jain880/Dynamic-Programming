import java.util.Arrays;

// https://leetcode.com/problems/partition-equal-subset-sum/ 
//same as subset Sum problem just need to check if half of the total array sum is present  
//No need to comment just check 02_Subsey Sum Problem

class Partition_Equal_SubSet_Sum {

    // Backtracking
    static boolean flag;

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int val : nums) {
            sum += val;
        }
        if (sum % 2 == 1)
            return false;
        flag = false;
        helper(nums, nums.length, 0, sum / 2, 0);
        return flag;
    }

    public static void helper(int[] nums, int n, int j, int sum, int curr) {
        if (curr == sum)
            flag = true;

        for (int i = j; i < n; i++) {
            curr += nums[i];
            helper(nums, n, i + 1, sum, curr);
            curr -= nums[i];
        }
    }

    // Memoization
    static int[][] dp;

    public static boolean memoization(int[] nums) {
        int sum = 0;
        for (int val : nums) {
            sum += val;
        }
        if (sum % 2 == 1 || nums.length == 1)
            return false;

        int hsum = sum / 2;
        dp = new int[nums.length + 1][hsum + 1];
        for (int[] val : dp) {
            Arrays.fill(val, -1);
        }

        int ans = helper(nums, nums.length, hsum);
        // for(int[] a : dp)
        // {
        // System.out.println(Arrays.toString(a));
        // }
        return ans == 1 ? true : false;
    }

    public static int helper(int[] nums, int n, int sum) {
        if (sum == 0)
            return 1;
        if (n == 0)
            return 0;

        if (dp[n][sum] != -1)
            return dp[n][sum];

        if (nums[n - 1] <= sum) {
            return dp[n][sum] = Math.max(helper(nums, n - 1, sum), helper(nums, n - 1, sum - nums[n - 1]));
        } else
            return dp[n][sum] = helper(nums, n - 1, sum);
    }

    // Top-Down
    public static boolean topDown(int[] nums) {
        int sum = 0;
        for (int val : nums) {
            sum += val;
        }
        if (sum % 2 == 1)
            return false;

        int n = nums.length;
        int hsum = sum / 2;

        boolean[][] dp = new boolean[n + 1][hsum + 1];

        for (int i = 0; i <= hsum; i++) {
            dp[0][i] = false;
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < hsum + 1; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][hsum];
    }

    public static void main(String[] args) {
        int nums[] = { 1, 5, 11, 5 };

        // System.out.println(canPartition(nums));
        // System.out.println(memoization(nums));
        System.out.println(topDown(nums));
    }

}
