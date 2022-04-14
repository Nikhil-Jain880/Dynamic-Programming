// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/submissions/

class best_time_to_buy_and_sell_stock_ii {

    static int maxProfit(int[] prices) {
        int buy = prices[0];
        int profit = 0; // accumulatedProfit

        for (int i = 1; i < prices.length; i++) {
            if (buy < prices[i]) // if buy is less than current price then sell
            {
                profit += prices[i] - buy;
                buy = prices[i];
            } else // if i am making a loss in other words
            { // todays price is cheaper then I will update my buy to todays as
                buy = prices[i]; // if I did not buy anything yesterday
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] nums = { 7, 1, 5, 3, 6, 4 };
        System.out.println(maxProfit(nums));
    }
}