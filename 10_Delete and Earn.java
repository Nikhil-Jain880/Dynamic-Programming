// https://leetcode.com/problems/delete-and-earn/

import java.util.Arrays;
import java.util.HashMap;

class Solution {

    // Memoization
    int[] dp;

    public int deleteAndEarnMemo(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>(); // For a frequency map
        for (int a : nums) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int[] arr = new int[map.size()]; // To store all unique Elements
        Arrays.sort(nums);
        int j = 1; // arr array index
        arr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) { // Storing unique elements in arr
            if (arr[j - 1] != nums[i]) {
                arr[j] = nums[i];
                j++;
            }
        }
        dp = new int[map.size() + 1];
        Arrays.fill(dp, -1);
        if (map.size() == 1) { // arr[0] signifies the number and map.get tells frequency
            return arr[0] * map.get(arr[0]); // Returning the ans as earning all the value
        }

        dp[0] = map.get(arr[0]) * arr[0]; // initializing zero index (maximum value earned from 0 to 0 index)
        // Maximum value that can be earned from 0 to 1 index (down line)(one
        // {variable})
        int one = map.get(arr[1]) * arr[1]; // total value of arr[1]
        if (arr[1] - arr[0] > 1) // If the difference is greater than one it means we can also consider previous
            one += dp[0]; // one
        dp[1] = Math.max(dp[0], one);

        int ans = helper(arr, map, arr.length - 1);
        return dp[arr.length - 1];
    }

    public int helper(int[] nums, HashMap<Integer, Integer> map, int n) {
        if (n < 0) // Index 0 and 1 are already defined and stored in dp so no need to check here
            return 0;

        if (dp[n] != -1)
            return dp[n];

        int a = helper(nums, map, n - 1); // it gets the previous value
        int b = map.get(nums[n]) * nums[n]; // calculates the value for current index
        if (nums[n] - nums[n - 1] > 1) { // if prev no and curr no diff > 1 we can add the
            b += helper(nums, map, n - 1); // prev number to this number
        } else // the diff is 1 i.e curr 4 prev 3 we cant take 3 in this case but we can take
            b += helper(nums, map, n - 2); // the value previous to 3 which is 2 (i-2)
        // Add that to b
        dp[n] = Math.max(a, b); // Store the max possible value for every index
        return dp[n];
    }

    // Tabulation

    public int deleteAndEarnTab(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a : nums) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int[] arr = new int[map.size()];
        Arrays.sort(nums);
        int j = 1;
        arr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (arr[j - 1] != nums[i]) {
                arr[j] = nums[i];
                j++;
            }
        }
        int[] dp = new int[map.size() + 1];
        Arrays.fill(dp, -1);
        if (map.size() == 1) {
            return arr[0] * map.get(arr[0]);
        }

        dp[0] = map.get(arr[0]) * arr[0];

        int one = map.get(arr[1]) * arr[1];
        if (arr[1] - arr[0] > 1)
            one += dp[0];
        dp[1] = Math.max(dp[0], one);
        // Comments same as memoization
        for (int i = 2; i < arr.length; i++) {
            int a = dp[i - 1];
            int b = map.get(arr[i]) * arr[i];
            if (arr[i] - arr[i - 1] > 1) {
                b += dp[i - 1];
            } else
                b += dp[i - 2];
            dp[i] = Math.max(a, b);
        }
        return dp[arr.length - 1];
    }

    // space optimization left (can remove dp array as we only need previous and
    // second previous value)

    // Main
    public static void main(String[] args) {
        int[] nums = { 2, 2, 3, 3, 3, 4 };
        Solution obj = new Solution();
        System.out.println(obj.deleteAndEarnMemo(nums));
        System.out.println(obj.deleteAndEarnTab(nums));
    }
}