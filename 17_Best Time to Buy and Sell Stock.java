// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

class Best_Time_to_Buy_and_Sell_Stock {

    static int maxProfit(int[] prices) {

        int minLeft = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) { // First you have to buy then you can sell; you can never sell a stock
                                                  // on first day coz you did not buy it yet
            if (minLeft < prices[i]) { // if current day stock price is high from when we have bought calculate the
                                       // profit and store the maximum profit
                maxProfit = Math.max(maxProfit, prices[i] - minLeft);
            } else
                minLeft = prices[i]; // Keeping a track of minCost of sock (buy date)
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 5, 2, 4, 8, 3, 2, 4, 5, 6, 9, 2 };
        System.out.println(maxProfit(nums));
    }
}