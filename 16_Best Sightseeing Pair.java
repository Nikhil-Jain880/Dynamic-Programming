// https://leetcode.com/problems/best-sightseeing-pair/

class BestSightseeingPair {
    static int maxScoreSightseeingPair(int[] nums) {
        int maxScore = 0;
        int maxLeft = nums[0] + 0; // J value can never be 0 coz i < j I has to be some prev val from j
        for (int j = 1; j < nums.length; j++) { // So the j is starting from one

            int score = maxLeft + nums[j] - j; // Checking the ans for current index

            maxScore = Math.max(maxScore, score); // Storing the biggest ans so far

            maxLeft = Math.max(maxLeft, nums[j] + j); // here i is referenced as j;
            // We only need the greatest left value
        }

        return maxScore;
    }

    public static void main(String[] args) {
        int[] nums = { 8, 1, 5, 2, 6 };
        System.out.println(maxScoreSightseeingPair(nums));
    }
}