// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
// Link to Similar Question :- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
class best_time_to_buy_and_sell_stock_with_transaction_fee {

    static int maxProfit(int[] price, int fee) {
        int len = price.length;
        if(len <= 1) return 0;
        
        if(len == 2 && price[1] > price[0] + fee)
            return price[1] - price[0] - fee;
        else if(len == 2 && price[1] < price[0]) 
            return 0;
        
        int[][] dp = new int[len][2]; // dp[i][0] - dont have stock; dp[i][1] have stock
        
        dp[0][0] = 0;
        dp[0][1] = -price[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + price[1] - fee);
        dp[1][1] = Math.max(dp[0][1], dp[0][0] - price[1]);
        
        for(int i =2;i<len;i++)
        {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + price[i] - fee);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - price[i]);
        }
        
        return dp[len-1][0];
    }
    public static void main(String[] args) { // Same logic and everything as last one with modification
        int[] nums ={ 1,3,7,5,10,3};
        int fee = 2;
        System.out.println(maxProfit(nums, fee));
    }
}
