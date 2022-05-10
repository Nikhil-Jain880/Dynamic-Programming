// https://practice.geeksforgeeks.org/problems/rod-cutting0840/1/#

// UNBOUNDED KNAPSACK: I can pick the same element however number of times I wish, while in knapsack I was allowed to pick a element only once
// This whole code is almost same but with a minor tweak in knapsack what we did was we moved our cuurnt index pointer once
// we made of decision, in here if we decide not to consider OR the rem length is smaller to that whats its pointing then
// we move our pointer but if we decde to take that element we will keep on taking it (recursively) this the before two mention
// conditions are met

import java.util.Arrays;

class RodCutting {

    // Memoization
    static int[][] dp;
    private static int cutRod(int price[], int n) {
        
        dp = new int[n+1][n+1];
        
        int ans = helper(n, n, price);
        
        for(int[] row : dp)
        {
            System.out.println(Arrays.toString(row));
        }
        return ans;
    }
    //for price[i] i is offset by one becaure the index start from 0 and it wouldn't make sense if we cut a rod of len 0
    // so the zero index repersent the price of 1 length rod
    
    private static int helper(int i, int j, int[] price)
    {
        if(i ==0 || j == 0) return 0; // Base condition same as knapsack
        
        if(dp[i][j] != 0) return dp[i][j];
        
        if(i <= j)  // J is rem length; i is the pointer pointing to current len
        { // take: current price + calculate the price of the remaining rod OR dont consider
            return dp[i][j] = Math.max(price[i-1] + helper(i, j - i, price), helper(i-1, j, price));
        }
        else 
        { // if the rem length is smaller than the current index len then go a index back 3 -> 2 
            return dp[i][j] = helper(i-1, j, price);
        }
    }


    // Tabulation
    private static int tabulation(int[] arr, int n)
    {
        int[][] dp = new int[n+1][n+1];
        
        for(int i =1; i<=n;i++)
        {
            for(int j =1;j<=n;j++)
            {
                if(i <= j)
                {
                    dp[i][j] = Math.max(dp[i-1][j], arr[i-1] + dp[i][j - i]);
                }
                else dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n][n];
    }


    //Space Optimization
    private static int space(int[] arr, int n)
    {
        int[] prev = new int[n+1];
        int[] curr = new int[n+1];

        for(int i =1; i<=n;i++)
        {
            for(int j =1;j<=n;j++)
            {
                if(i <= j)
                {
                    curr[j] = Math.max(prev[j], arr[i-1] + curr[j - i]);
                }
                else curr[j] = prev[j];
            }
            prev = curr;
        }
        return curr[n];

    }

    public static void main(String[] args)
    {
        int N = 8; // Length of the array
        int Price[] = {1, 5, 8, 9, 10, 17, 17, 20}; // the prices corresponds to the length 
        // for len 1 the price is 1, for len 2 price is 5, if you have a rod of len 3 then the price of that rod will be 8
        System.out.println(cutRod(Price, N));
        System.out.println(tabulation(Price, N));
        System.out.println(space(Price, N));
    }
}
