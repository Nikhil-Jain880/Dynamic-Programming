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

    static int knapSackMemoization(int W, int wt[], int val[], int n) { // Fucntion to call helper Function
        dp = new int[n + 1][W + 1];
        int ans = helper(W, wt, val, n);
        for (int[] i : dp) { // To Print the result Matrix
            System.out.println(Arrays.toString(i));
        }
        return ans;
    }

    static int helper(int weight, int[] wt, int[] val, int n) {
        if (n == 0 || weight == 0) {
            return 0;
        }
        if (dp[n - 1][weight] != 0) // If the value is already calculated then dont recompute it just return the
                                    // value
            return dp[n - 1][weight];
        // curr item weight is less or equal to current capacity to hold then access if
        if (wt[n - 1] <= weight) { // curr val + called(reduced weight, wt arr, val arr, one less index to
                                   // calculate)
            return dp[n - 1][weight] = Math.max(val[n - 1] + helper(weight - wt[n - 1], wt, val, n - 1), // taking
                    helper(weight, wt, val, n - 1)); // Not taking/skip
        } else
            return helper(weight, wt, val, n - 1); // current item weight > capacity -> skip this item || move on
    }

    // Top-Down - Tabulation
    static int knapSackTabulation(int w, int wt[], int val[], int n) {
        int[][] dp = new int[n + 1][w + 1];
        /*
         * Base Case - if n = 0 || w == 0 the ans is 0 we dont need to mention that
         * because default value of our d array is 0
         * That's why we start our loop from 1 instead of 0 so while doing i-1 or j-1 it
         * does not get thrown off
         * n =0 means there are no item to begin with so if there is no item to carry so
         * the val will also be 0, w =0 means
         * our bag capacity is zero so we cant carry anything so the ans is zero in that
         * case
         */
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < w + 1; j++) {
                if (wt[i - 1] <= j) { // REMBER : j is the current weight in the 2d array. only when j >= weight of
                                      // the
                                      // current object this if will execute
                    dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]); // including and not
                                                                                              // including
                } else // [weight - weight[n-1]]
                    dp[i][j] = dp[i - 1][j]; // required weight(weight of current object) is greater than current
                                             // capacity
                                             // dont add, skip it
            }
        }
        for (int[] i : dp) {
            System.out.println(Arrays.toString(i));
        }
        return dp[n][w];
    }

    // Main Method
    public static void main(String[] args) {

        int N = 4; // Length of the array
        int W = 7; // Total weight capactity
        int values[] = { 1, 4, 5, 7 };
        int weight[] = { 1, 3, 4, 5 };

        // int ans = knapSack(W, weight, values, N); // Recursion
        // int ansMemoization = knapSackMemoization(W, weight, values, N); //
        // Memoization
        int ansTabulation = knapSackTabulation(W, weight, values, N);

        // System.out.println(ans);
        // System.out.println(ansMemoization);
        System.out.println(ansTabulation);
    }
}
