// https://leetcode.com/problems/maximum-sum-circular-subarray/
// Youtube : https://www.youtube.com/watch?v=Q1TYVUEr-wY

class Maximum_Sum_Circular_Subarray {

    public static int maxSubarraySumCircular(int[] nums) {
        int maxSub = Integer.MIN_VALUE;
        int tempMax = 0;

        int sum = 0;

        int minSub = Integer.MAX_VALUE;
        int tempMin = 0;

        for (int val : nums) {
            sum += val;

            tempMax += val;
            maxSub = Math.max(maxSub, tempMax);
            tempMax = Math.max(tempMax, 0);

            tempMin += val;
            minSub = Math.min(minSub, tempMin);
            tempMin = Math.min(tempMin, 0);
        }

        if (sum == minSub)
            return maxSub;
        return Math.max(maxSub, (sum - minSub));
    }

    public static void main(String[] args) {
        int[] nums = { 5, -3, -2, 6, -1, 4 };
        System.out.println(maxSubarraySumCircular(nums));
    }
}