import java.util.Arrays;
// same as 02_subset sum problem or comments check that one

// https://practice.geeksforgeeks.org/problems/perfect-sum-problem5633/1#

class PerfectSum {

    // Backtracking
    static int count;

    public static int Backtracking(int arr[], int n, int sum) {
        count = 0;
        helper(arr, n, sum, 0, 0);
        return count;
    }

    public static void helper(int[] arr, int n, int sum, int j, int curr) {
        if (sum == curr) {
            count++;
            return;
        }
        for (int i = j; i < n; i++) {
            curr += arr[i];
            helper(arr, n, sum, i + 1, curr);
            curr -= arr[i];
        }
    }

    // Memoization
    static int[][] dp;
    static int mod = 1000000007;

    public static int memoization(int arr[], int n, int sum) {
        dp = new int[n + 1][sum + 1];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        return helper(n, arr, sum);
    }

    public static int helper(int n, int[] arr, int sum) {
        if (n == 0 && sum != 0)
            return 0;
        if (n == 0 && sum == 0)
            return 1;

        if (dp[n][sum] != -1)
            return dp[n][sum];

        if (arr[n - 1] <= sum) {
            dp[n][sum] = (helper(n - 1, arr, sum - arr[n - 1]) % mod + helper(n - 1, arr, sum)) % mod;
        } else {
            dp[n][sum] = helper(n - 1, arr, sum) % mod;
        }
        return dp[n][sum] % mod;
    }

    // Tabulation
    static int tabulation(int[] arr, int N, int sum) {
        int[][] dp = new int[N + 1][sum + 1];
        int mod = 1000000007;
        for (int j = 0; j <= sum; j++) {
            dp[0][j] = 0; // base case see from memoization
        }
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1; // base case see from memoization
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= sum; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = (dp[i - 1][j]) % mod + (dp[i - 1][j - arr[i - 1]]) % mod;
                } else
                    dp[i][j] = (dp[i - 1][j]) % mod;
            }
        }
        // for (int[] i : dp) { // To Print the result Matrix
        // System.out.println(Arrays.toString(i));
        // }
        return (dp[N][sum]) % mod;
    }

    // Main
    public static void main(String[] args) {
        int N = 6;
        int Sum = 10;
        int[] arr = { 1, 2, 3, 4, 5, 6 };

        System.out.println(Backtracking(arr, N, Sum));
        System.out.println(memoization(arr, N, Sum));
        System.out.println(tabulation(arr, N, Sum));
    }
}
