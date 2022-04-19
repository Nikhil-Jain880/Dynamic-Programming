import java.util.Arrays;

// https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1/

class SubsetSum {

    // BackTracking
    static boolean flag;

    static Boolean backtracking(int N, int arr[], int sum) {
        flag = false;
        helperBack(N, arr, sum, 0, 0);
        return flag;
    }

    // basic subset with a running sum
    static void helperBack(int n, int nums[], int sum, int j, int run) {
        if (sum == run)
            flag = true;
        for (int i = j; i < n; i++) {
            run = run + nums[i];
            helperBack(n, nums, sum, i + 1, run);
            run = run - nums[i];
        }
    }

    // Memoization
    private static int[][] dp;

    static Boolean memoization(int N, int arr[], int sum) {
        dp = new int[N + 1][sum + 1];
        for (int[] a : dp) {
            Arrays.fill(a, -1);// -1 = not calculated; 0 = false; 1 = true
        }
        return helper(N, arr, sum) == 1 ? true : false;
    }

    static int helper(int n, int[] arr, int sum) {
        if (sum == 0) // base case - if the quesion asks for "0" sum no matter how elements we have or
                      // dont have in the array
                      // in case of subsets there is always a empty subset so for sum == 0 its always
                      // true (1)
            return 1;
        if (n == 0) // apart from sum 0 if we dont have any elements to sum then the ans is false
                    // (0)
            return 0;
        if (dp[n][sum] != -1)
            return dp[n][sum];

        if (arr[n - 1] <= sum) { // take dont take; if take reduce the sum
            return dp[n][sum] = Math.max(helper(n - 1, arr, sum - arr[n - 1]), helper(n - 1, arr, sum));
        } else
            return dp[n][sum] = helper(n - 1, arr, sum);
    }

    // Tabulation
    static Boolean isSubsetSum(int N, int arr[], int sum) {
        boolean[][] dp = new boolean[N + 1][sum + 1];
        for (int j = 0; j <= sum; j++) {
            dp[0][j] = false; // base case see from memoization
        }
        for (int i = 0; i <= N; i++) {
            dp[i][0] = true; // base case see from memoization
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j) { // in case form boolean values max dont work so we use OR ||
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                } else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        for (boolean[] i : dp) { // To Print the result Matrix
            System.out.println(Arrays.toString(i));
        }
        return dp[N][sum];
    }
    /*
     * NOTE: in tabulation - line 69 - dp[i][j] = dp[i - 1][j] || dp[i - 1][j -
     * arr[i - 1]]
     * "dp[i - 1][j - arr[i - 1]]" this later half:
     * we are considering this value to take.
     * as we are calculating one by one in a linear fashion, so for a give index
     * i, j
     * all the values from 0,0 to i,j-1 has already been calculated, (in the just
     * before line we have
     * already checked if j is greater than the value so that when we substract it
     * it wont show index
     * out of bounds)
     * when we do j - arr[i-1] it is checking the index of previous value which has
     * been already calculated,
     * the index of prevoius value is suppose currently j is at 7 and arr[i-1] is 4
     * it will go and check for
     * the 3rd (as j-arr[i-1] = 7-4 = 3) index and if the 3rd index is true it will
     * update the dp[i][j] to
     * true because id 3 is true that means subset sum for 3 is availabe at index 0
     * to the curr index -1. if
     * 3 is possible/true then if we add current 4 to it, then it will become 7 so a
     * subset sum for 7 is valid
     * for 0 to n
     */

    public static void main(String[] args) {
        int n = 6;
        int arr[] = { 3, 34, 4, 12, 5, 2 };
        int sum = 9;

        // System.out.println(backtracking(n, arr, sum));
        // System.out.println(isSubsetSum(n, arr, sum));
        System.out.println(memoization(n, arr, sum));
    }
}