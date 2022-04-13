// https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/

class MaximumLengthOfSubarrayWithPositiveProduct {

    static int getMaxLen(int[] nums) {
        int front = 1;
        int left = 0;
        int right = 0;
        int max = 0;

        for (int i = 0; i < nums.length; i++) // checking from front
        {
            if (front == 0) { // whenever the product becomes zreo re-set the front and put a place holder to
                              // the current index, this means that when we will calculate the distace at that
                              // time we will calculate from left to curr index i
                front = 1;
                left = i;
            }
            // We just need to track whether its positive or negative so no need to actually
            // multiply the values
            if (nums[i] == 0)
                front *= 0;
            else if (nums[i] > 0)
                front *= 1;
            else
                front *= -1;

            if (front > 0)
                max = Math.max(max, i - left + 1); // Calculating length of SubArray

        }

        right = nums.length - 1;
        front = 1;
        for (int i = nums.length - 1; i >= 0; i--) // checking from back; same as above
        {
            if (front == 0) {
                front = 1;
                right = i;
            }

            if (nums[i] == 0)
                front *= 0;
            else if (nums[i] > 0)
                front *= 1;
            else
                front *= -1;

            if (front > 0)
                max = Math.max(max, right - i + 1);

        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = { -1, -2, -3, 0, 1 };
        System.out.println(getMaxLen(nums));
    }
}
