// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
// https://www.youtube.com/watch?v=w6xk5Po-DX0

/*
dp[len][2]
case 1: We have a stock on day i, dp[i][1] max of below:
- I bought it today
    dp[i-2][0] - price[i]
    - i-2 coz we cant take the previous one(cooldown of 1 day)
    - 0 coz d[i][0] states the money we had on i-2 day and if we wnat to buy toady we have had to sell it 2 days prior
    - price[i] coz we are buying it and we are storing it in dp[i][1] because 1 means we have a stock.
- I am carry forwarding, doing nothing
    dp[i-1][1], this is previous amount
case 2: We have no stock on day i, dp[i][0] max of below:
- I sold it today
    dp[i-1][1] + price[i]
    - buy prize(which will likely be negative) + selling prize to calculate profit
- I an carry forwarding, doing nothing
    dp[i-1][0], this is previous amount
*/

class best_time_to_buy_and_sell_stock_with_cooldown {

    static int maxProfit(int[] price) {
        int len = price.length;
        if(len <= 1) return 0;
        
        if(len == 2 && price[1] > price[0])
            return price[1] - price[0];
        else if(len == 2 && price[1] < price[0]) 
            return 0;
        
        int[][] dp = new int[len][2]; // dp[i][0] - dont have stock; dp[i][1] have stock
        
        dp[0][0] = 0;
        dp[0][1] = -price[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + price[1]);
        dp[1][1] = Math.max(dp[0][1], dp[0][0] - price[1]);
        
        for(int i =2;i<len;i++)
        {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + price[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - price[i]);
        }
        
        return dp[len-1][0];
    }

    public static void main(String[] args) {
        int[] nums ={ 1,2,3,0,2 };
        System.out.println(maxProfit(nums));
    }
    
}
