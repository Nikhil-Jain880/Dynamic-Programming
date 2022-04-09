// https://leetcode.com/problems/jump-game/

import java.util.Arrays;

class JumpGame {

    // Memoization
    static boolean flag;
    static boolean[] dp;

    public static boolean canJumpMemoi(int[] nums) {
        int n = nums.length;

        flag = false;
        dp = new boolean[n + 1];
        Arrays.fill(dp, false);

        if (n == 1)
            return true;
        helper(nums, 0, n); // array, currIndex, len array/target
        return flag;
    }

    static void helper(int[] nums, int j, int target) {
        if (j >= target - 1) { // If j has reached of crossed the last index then ans is true
            flag = true;
            return;
        }
        if (dp[j] == true) // The index is already explored don't do it again
            return;
        if (flag == false && dp[j] == false) { // not yet reached the last index & not explored the current index
                                               // then explore
            int range = nums[j]; // current range to expore surrpose nums[i] = 2 and the current index is 1 then
                                 // we can jump to 1+1 or 1+2
            for (int i = range; i >= 1; i--) { // range running backwards, we can also rum it from 1 to range the result
                                               // will be the same
                dp[j] = true;
                helper(nums, j + i, target); // calling again for cuur Index + Jump value
            }
        }

    }

    // Tabulation
    static boolean canJumpTabulation(int[] nums) {

        if (nums.length == 1) // Basic base cases
            return true;
        if (nums[0] == 0)
            return false;

        int maxIndex = -1; // Max index where we can go

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && i >= maxIndex) // if we incounter a value 0 and the curr index is less than than the max
                                               // index which we can reach that mean we are on uncharted teritory so
                                               // return false
                return false;
            if (nums.length - 1 <= i + nums[i]) // if we can crossed the length then return true
                return true;

            maxIndex = Math.max(maxIndex, i + nums[i]); // Max index where we can go, suppose we are on index 2 and
                                                        // nums[2] = 3 then the max jum which we can take is 2+3 = 5
                                                        // then we can go till 5 with no issue; and then again on next
                                                        // iteration from index 3 it will search again it will calculate
                                                        // 3+nums[3]
        }
        return false;
    }
    // Note for tabulation : A Situation like (nums[i] != 0 && i>=maxIndex) will
    // never arise because even if nums[i] == 1 then we can jump one index

    // Main
    public static void main(String[] args) {
        int[] test = { 2, 0, 0 };
        int[] test2 = { 3, 2, 1, 0, 4 };

        System.out.println(canJumpMemoi(test));
        System.out.println(canJumpMemoi(test2));

        System.out.println(canJumpTabulation(test));

    }
}
