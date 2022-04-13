class MaximumProductSubarray {
    static int maxProduct(int[] nums) {

        int runProdFront = 1; // will check from front
        int runProdBack = 1; // will check from front
        // If we only check from front then {2,3,-5,6,8} this testcase will not work

        int max = nums[0];
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (runProdFront == 0)
                runProdFront = 1;
            if (runProdBack == 0)
                runProdBack = 1;

            runProdFront *= nums[i];
            runProdBack *= nums[n - i - 1];

            max = Math.max(max, Math.max(runProdFront, runProdBack));
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 3, -5, 4, 8 };
        System.out.println(maxProduct(nums));
    }
}