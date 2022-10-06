import java.util.Arrays;

class TargetSum {

    //recursion
    static int count;
    static int recursion(int[] nums, int target)
    {
        count =0;
        helper(nums, 0, target);
        return count;
    }

    static void helper(int[] nums, int i, int target)
    {
        if(i == nums.length)
        {
            if(target == 0)
            {
                count++;
            }
            return;
        }
        helper(nums, i+1, target- nums[i]);
        helper(nums, i+1, target+ nums[i]);
    }

    //memoization
    static int total;
    static int[][] dp;
    static int findTargetSumWays(int[] nums, int target) {
        total = Arrays.stream(nums).sum();
        dp = new int[nums.length][total*2 +1];
        for(int[] rows: dp)
        {
            Arrays.fill(rows, -1);
        }
        return helpermemo(nums, 0, 0, target);
    }
    
    static int helpermemo(int[] nums, int i, int sum, int target)
    {
        if(i == nums.length)
        {
            if(target == sum)
            {
                return 1;
            }
            return 0;
        }
        if(dp[i][sum+total] != -1) return dp[i][sum+total];
        int add = helpermemo(nums, i+1, sum- nums[i], target);
        int sub = helpermemo(nums, i+1, sum+ nums[i], target);
        // System.out.println(i+" "+(sum+total));
        return dp[i][sum+total] = add+sub;
        
    }

    //Tabulation
    static int findTargetSumWaysTabulation(int[] nums, int target) {
        int total = Arrays.stream(nums).sum();
        int[][] dp = new int[nums.length][total*2 +1];

        dp[0][nums[0]+total] = 1;
        dp[0][-nums[0]+total] += 1;
        
        for(int i =1;i<nums.length;i++)
        {
            for(int sum = -total; sum<= total;sum++)
            {
                if(dp[i-1][sum+total] > 0)
                {
                    dp[i][sum + total + nums[i]] += dp[i-1][sum+total];
                    dp[i][sum+total - nums[i]] += dp[i-1][sum+total];
                }
            }
        }
        // for(int[] r: dp) System.out.println(Arrays.toString(r));
        if(Math.abs(target) > total) return 0;
        return dp[nums.length-1][target+total];
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int target = 3;
        System.out.println(recursion(nums, target));
        System.out.println(findTargetSumWays(nums, target));
        System.out.println(findTargetSumWaysTabulation(nums, target));
    }
}
