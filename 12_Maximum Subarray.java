// https://leetcode.com/problems/maximum-subarray/
// Kadane's Algorithm Of Running Sum 

class Maximum_Subarray {
    // Running Sum Approach Kadane's Algo
    static int maxSubArray(int[] nums) { // This is a space optimization over Tabulation solution( Line : 21 )
        // Calcuate a running sum when ever sum goes down to zero that means we can
        // exclude the previous portion of the array, even if sum has 1 that means the
        // previous portion has somthing to offer
        int max = nums[0];
        int sum = 0;
        for (int val : nums) {
            sum += val;
            max = Math.max(max, sum);
            if (sum < 0)
                sum = 0;
        }
        return max;
    }

    // Tabulation
    static int maxSubArrayTabulation(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int max = nums[0];
        dp[0] = max;
        for (int i = 1; i < len; i++) {
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0); // if the previous index sum is zero then dont take that
            max = Math.max(max, dp[i]); // or else add it to curr sum
        }
        return max;
    }

    public static void main(String[] args) {

        int[] nums = { 1, 2, 4, -1, -5, -2, 4, 5, 2, 8, -1 };
        System.out.println(maxSubArray(nums));
        System.out.println(maxSubArrayTabulation(nums));
    }
}