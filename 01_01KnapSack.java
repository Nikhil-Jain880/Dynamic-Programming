import java.util.Arrays;

// https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1#

class KnapSack {

    static int knapSack(int weight, int wt[], int val[], int n) // Basic Recursion
    {
        if (n == 0 || weight == 0) {
            return 0;
        }
        if (wt[n - 1] <= weight) {
            return Math.max(val[n - 1] + knapSack(weight - wt[n - 1], wt, val, n - 1),
                    knapSack(weight, wt, val, n - 1)); // for every item
            /*
             * we have two choices (take, don't take)
             * 1. If we take then add the val and call again with reduced capacity and
             * decrement the index
             * 2. If we don't then just decrement the index pointer as if nothing happened
             * every time while returning the call stack, it will return the maximum of two
             * operation 1 & 2 this is where memoization can happen
             */
        } else
            return knapSack(weight, wt, val, n - 1); // if the weight of item is greater than the available
                                                     // capacity(weight)
                                                     // then skip/don't take and move on
    }

    // Memoization

    static int[][] dp; // Global Variable to store the maximum value for the situation(weight and
                       // index)
    // Bottom up approach - approaching the problem from the back we are storing the
    // value in the dp from the back
    // Function to return max value that can be put in knapsack of capacity W.

    static int knapSackMemoization(int W, int wt[], int val[], int n) {
        dp = new int[W + 1][n + 1];
        int ans = helper(W, wt, val, n);
        return ans;
    }

    static int helper(int weight, int[] wt, int[] val, int n) {
        if (n == 0 || weight == 0) {
            return 0;
        }
        if (dp[weight][n - 1] != 0) // If the value is already calculated then dont recompute it just return the
                                    // value
            return dp[weight][n - 1];
        if (wt[n - 1] <= weight) {
            return dp[weight][n - 1] = Math.max(val[n - 1] + helper(weight - wt[n - 1], wt, val, n - 1), // taking
                    helper(weight, wt, val, n - 1)); // Not taking/skip
        } else
            return helper(weight, wt, val, n - 1);
    }

    // Main Method
    public static void main(String[] args) {

        int N = 3;
        int W = 5;
        int values[] = { 1, 2, 3 };
        int weight[] = { 4, 5, 1 };

        // int ans = knapSack(W, weight, values, N); // Recursion
        int ansMemoization = knapSackMemoization(W, weight, values, N); // Memoization

        for (int[] i : dp) {
            System.out.println(Arrays.toString(i));
        }
        // System.out.println(ans);
        System.out.println(ansMemoization);
    }
}
