import java.util.*;

// https://practice.geeksforgeeks.org/problems/minimum-sum-partition3317/1/#

class MinimumSumPArtition {

    // Recursion
    public static int backtracking(int nums[], int n) {
        // Your code goes here
        int sum = 0;
        for (int val : nums)
            sum += val;
        return helperBack(nums, nums.length, sum, 0);
    }

    public static int helperBack(int[] nums, int i, int sum, int curr) {

        if (i == 0)
            return Math.abs(sum - curr - curr);
        return Math.min(helperBack(nums, i - 1, sum, curr), helperBack(nums, i - 1, sum, curr + nums[i - 1]));
    }

    // Memoization is not the solution, because it doesnot explore all the paths it
    // just calculates the path of total sum
    // Tabulation explores all of the paths

    // Tabulation
    public static int minDifference(int nums[], int n) {
        int sum = 0;
        for (int a : nums)
            sum += a;
        int[][] dp = new int[n + 1][sum + 1];
        for (int i = 0; i <= sum; i++) {
            dp[0][i] = -1;
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        // same as knapsack/subset sum...
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i - 1]]);
                } else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        int len = (int) Math.ceil(sum / 2);
        List<Integer> list = new ArrayList<>();
        // for(int[] a: dp) System.out.println(Arrays.toString(a));

        for (int i = 0; i < len + 1; i++) {
            if (dp[n][i] == 1) // in the last row all the sum index will have 1 which is the subset sum of
                               // array
                list.add(i);
        }
        // System.out.println(list);

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            int temp = list.get(i); // (s1)
            ans = Math.min(ans, sum - (temp + temp)); // for all the s1's we are calculating the diff ans storing min
        }
        return ans;
    }

    /*
     * we need to calculate min diff so
     * (1)s2-s1 = min (we need) -> s1+s2 = sum (we know)
     * So s2 = sum-s1
     * so in (1) sum - s1 - s1 =min
     * sum - 2*s1 = min
     * by this we only need to calculate s1 and we need s1 to be lower than or equal
     * to mid of sum because if s1 is > mid then 2s1 will be greater than toatl sum
     * which is not possible
     */
    public static void main(String[] args) {
        int n = 4;
        int arr[] = { 1, 6, 11, 5 };

        System.out.println(backtracking(arr, n));
        // System.out.println(minDifference(arr, n));
    }
}