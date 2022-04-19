import java.util.Arrays;

// https://leetcode.com/problems/house-robber/

class HouseRobber {

    // Memoization
    static int[] dp;

    public static int memoization(int[] nums) {
        dp = new int[nums.length];
        Arrays.fill(dp, -1);
        int ans = helper(nums, nums.length - 1, 155);
        // System.out.println(Arrays.toString(dp));
        return ans;
    }

    public static int helper(int[] nums, int n, int i) {
        if (n < 0) { // well there is no index less than 0
            return 0;
        }
        if (dp[n] != -1) // if already calculated
            return dp[n];

        int nTake = helper(nums, n - 1, i); // not including this one so I can include the next one so n-1
        int take = 0; // not sure it will be calculated because of, if condition, so initializing it
                      // with a value

        if (i - n > 1)
            take = nums[n] + helper(nums, n - 2, n); // if the difference in index of the last value picked and curr
                                                     // index is greater than 1 then only pick the value, and if I pick
                                                     // this value so I need to update i to curr index and n to n-2 (n-1
                                                     // will check the next and we know that we cant take next so we
                                                     // will take before that )
        return dp[n] = Math.max(take, nTake);
    }

    // Tabulation
    public static int Tabulation(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]); // If array has two numbers then the greater one will be the ans
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]); // Assessing the last (dont Take) and take; Adding nums[i]
                                                              // to take becuse we are taking the value and adding it to
                                                              // -2 because adjcent is not allowed
        }
        return dp[n - 1];
    }

    // Space Optimized
    // Space Compleity: O(1)
    // Time Complexity: O(N)
    public static int Optimized(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];
        // Actually we only needed last and second last value so, dp array can be
        // removed and two new variables cam be declared
        int secondPrevious = nums[0];
        int previous = Math.max(nums[1], nums[0]);
        int curri = previous;
        for (int i = 2; i < n; i++) {
            curri = Math.max(previous, secondPrevious + nums[i]);
            secondPrevious = previous;
            previous = curri;

        }
        return curri;
    }

    // Main
    public static void main(String[] args) {
        int[] nums = { 2, 7, 9, 3, 1 };
        // System.out.println(memoization(nums));
        // System.out.println(Tabulation(nums));
        System.out.println(Optimized(nums));
    }
}