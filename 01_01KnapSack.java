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

    public static void main(String[] args) {

        int N = 3;
        int W = 4;
        int values[] = { 1, 2, 3 };
        int weight[] = { 4, 5, 1 };

        int ans = knapSack(W, weight, values, N);
        System.out.println(ans);
    }
}
