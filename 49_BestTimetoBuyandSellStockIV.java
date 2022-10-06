import java.util.Arrays;

class BestTimetoBuyandSellStockIV {

    static int[][][] dp;
    public static int maxProfit(int k, int[] prices) {
        
        dp = new int[prices.length][4][k];
        
        int num = helper(prices, k, 0, 0, 1, 0);

        for(int[][] rows:dp)
        {
            for(int[] row: rows)
            {
                System.out.print(Arrays.toString(row));
            }
            System.out.println("");
        }
        return num;
        //array, k, i, max so far, buy or sell : 1 need to buy/0 need to sell, transaction
    }
    
    public static int helper(int[] arr, int k, int i, int max, int buy, int tran)
    {
        if(i == arr.length-1 || tran == k){
            if (buy == 0)
                return arr[i];
            else
                return 0;
            }
        
        if(dp[i][buy][tran] != 0) 
            return dp[i][buy][tran];
        ///agar buy is 1 we dont have a stock in hand
        int take = 0;
        if(buy == 1 )
        {
            take  = helper(arr, k, i+1, max - arr[i], 0, tran+1);
        }
        else
        {
            take = helper(arr, k, i+1, max+arr[i], 1, tran);
        }

        int notTake =  helper(arr, k, i+1, max, 1, tran);

        dp[i][buy][tran] = Math.max(take, notTake);
        return dp[i][buy][tran];

    }


    public static void main(String[] args) {
        int[] prices = {2, 4, 1};
        int k =2;

        System.out.println(maxProfit(k, prices));
    }
}
