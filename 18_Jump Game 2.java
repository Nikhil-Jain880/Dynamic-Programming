// https://leetcode.com/problems/jump-game-ii/

import java.util.Arrays;

class Jump_Game_2 {

    static int[] dp;

    static int jump(int[] nums) {

        dp = new int[nums.length];
        Arrays.fill(dp, 100000); // imagine its Integer.MAX_VALUE
        return helper(nums, 0);
    }

    static int helper(int[] nums, int index) {
        if (index >= nums.length - 1) // Index out of bounds or last index means reachable so return zero
        {
            return 0; // increment is already done when calling
        }

        if (dp[index] != 100000)
            return dp[index];

        for (int i = 1; i <= nums[index]; i++) { // if the value is 2 we can jump 1 or 2 step, for 3 -> 1 2 3
            dp[index] = Math.min(dp[index], 1 + helper(nums, index + i));
        }
        return dp[index];
    }

    public static void main(String[] args) {
        int[] nums = { 2, 3, 1, 1, 4 };
        System.out.println(jump(nums));
    }

}
