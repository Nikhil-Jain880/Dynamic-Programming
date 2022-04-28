// https://leetcode.com/problems/longest-increasing-subsequence/

/**
 * Current Number should be greater than tha prev number which you have considered so it kindda makes
 * pick or not pick question; whether you want to add a number or you don't explore all the ways and 
 * see which path gives you the best ans.
 * 
 * For the 1st element you dont have any prev element so prev element is considered as a smallest value
 * so that you can add your 1st element so in this question the values ranges from 0 to 10^5
 * we need to take our prev element less that 0 so throughout the solutions prev element is considered as -1
 * If we are solving pick or not pick there will be cases where we have not picked the 1st element and if so then
 * prev element for the next value will also be -1. Basically if no other value if considered then the prev is -1
 */

import java.util.*;

class LongestIncreasingSubsequence {

    // Recursion
    // TIME : O(2^len)
    private static int lengthOfLISRecursion(int[] nums) {

        int len = nums.length;
        return helperRecurs(nums, 0, -1); // array curr index, prev index
    }
    
    private static int helperRecurs(int[] nums, int index, int prev_ind)
    {
        if(index == nums.length) return 0; // reached the end
        
        int notTake = 0 + helperRecurs(nums, index+1, prev_ind); //increse the index prev will not be changed
        int take = 0;
        if(prev_ind == -1 || nums[index] > nums[prev_ind]) // prev = -1 indicates that no values has been considered
        {                                 // or if curr val is greater than prev we can consider the curr val
            take = 1 + helperRecurs(nums, index+1, index); // increse the ans as we are conidering this val
        } // array, increment index as usual, and assign current val to prev as this is the latest val considered
        
        return Math.max(notTake, take);
    }


    // Memoization 
    // TIME : O(LEN * LEN)
    // SPACE : O(LEN *LEN + Len(stack space))
    static int[][] memo;
    private static int lengthOfLISMemoization(int[] nums) {
        int len = nums.length;
        memo = new int[len][len+1]; // prev index ranges from -1 to n thats why len is +1 to accomodate -1
        for(int[] row: memo)
        {
            Arrays.fill(row, -1);
        }
        int count = helper(nums, 0, -1);
        return count;        
    }
    // EveryTime when storing or accessing prev index from dp array foorr prev index we do +1 because for index -1
    // there is no index so we used indexShifting techinque we store the value of index -1 to index 0,
    // value of index 0 to 1, 1 to 2 ...
    private static int helper(int[] nums, int index, int prev_ind)
    {
        if(index == nums.length) return 0;
        
        if(memo[index][prev_ind+1] != -1) return memo[index][prev_ind+1];
        
        int notTake = 0 + helper(nums, index+1, prev_ind);
        int take = 0;
        if(prev_ind == -1 || nums[index] > nums[prev_ind])
        {
            take = 1 + helper(nums, index+1, index);
        }
        
        return memo[index][prev_ind+1] = Math.max(notTake, take);
    }


    // Tabulation
    // TIME : O(len * len)
    // SPACE : O(len * len)
    private static int lengthOfLISTabulation(int[] nums) {
        int n = nums.length;
        int[][] tabulation = new int[n+1][n+1];
        
        for(int i =n-1;i>=0;i--)
        {
            for(int prev = i-1; prev >= -1; prev--)
            {
                int len = 0 + tabulation[i+1][prev+1];
                if(prev == -1 || nums[i] > nums[prev])
                { // Here when accessing teh prev Calculate value dp[i+1]["i+1"*]
                // the later i+1 was done insted of prev+1 because
                // firstly, if we are consedering tis element to take then we need to access the value
                // previous to it and if we consider this val then the current index becomes the prev index prev = i
                // +1 comes from the offset of index shifting to access the value of index 2 we need to check index 2+1 
                // because of -1 index  
                    len = Math.max(len, 1 + tabulation[i+1][i+1]);
                }
                tabulation[i][prev+1] = len;
            }
        }
        return tabulation[0][-1 +1];
    }


    // Tabulation Space I
    // TIME : O(len * len)
    // SPACE : O(len + len) = 2len
    private static int lengthOfLISspaceI(int[] nums) {
        int n = nums.length;
        int[] curr = new int[n+1];        
        int[] next = new int[n+1];
        // Standard Space optimization
        
        for(int i =n-1;i>=0;i--)
        {
            for(int prev = i-1; prev >= -1; prev--)
            {
                int len = 0 + next[prev+1];
                if(prev == -1 || nums[i] > nums[prev])
                {
                    len = Math.max(len, 1 + next[i+1]);
                }
                curr[prev+1] = len;
            }
            next = curr;
        }
        return next[-1 +1];
    }


    // Tabulation Space II
     // TIME : O(len * len)
    // SPACE : O(len)
    private static int lengthOfLISSpaceII(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];        
        Arrays.fill(dp, 1); // every value in itself is LIS
    
        int ans =1; 
        for(int i =1;i<n;i++) // start from 1 so that we have prev value to check to 
        {
            for(int j =0;j<i;j++)
            {
                if(nums[i] > nums[j]) // condition suffies
                {// here j is less than i and dp[j] says that how many numbers from 0 to j is less than j
                /**
                 * for 5 4 6 the final dp should be something like 1 1 2
              +   * current dp state 1 1 1 :
                 * - i = 1, j = 0 : nums[i] = 4 nums[j] = 5; 4 is less than 5 so nothing happens
                 *
                 *  current dp state 1 1 1 :
                 * - i = 2, j = 0 : nums[i] = 6, nums[j] = 5; 6 is greater than 5 so
                 * - in nums[2] maximum of nums[i] which is 1 || dp[j] which is 1 + 1 will be stored
                 * 
                 * current dp state 1 1 2 :
                 * i = 2, j = 1 : nums[i] = 6, nums[j] = 4; 6 is greater than 4 so
                 * - in nums[2] maximum of nums[i] which is "2" || dp[j] which is 1 + 1 will be stored
                 * - here both values give 2 only so nothing will matter
                 * 
                 * imagine is dp[j] here was 2 then 2+1 = 3 would have been stored
                 */
                    dp[i] = Math.max(dp[i], dp[j] + 1); // dp[x] defines the max length of lis from 0 to x
                    // in simple terms it is the max of no of numbers less then nums[x]
                    ans = Math.max(ans, dp[i]);
                }
            }
        }
        
        return ans;        
    }


    // Binary Search
     // TIME : O(len * log len)
    // SPACE : O(len)
    private static int lengthOfLISBinSearch(int[] nums) { // over writing the same dp array everytime we see a new val
        int size = 1;
        int[] dp = new int[nums.length]; 
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int bs = Arrays.binarySearch(dp, 0, size, nums[i]); // it will return the lower bound
			// if positive we can skip existing values in dp
            if (bs < 0) {
                int indx = -1 - bs;
				// indx equals to size mean we need to append to the end
                if (indx == size) size++; // when we have 1 4 7 in dp and if we found 8 the we need to increse the size 
                dp[indx] = nums[i];
            }   
        }
        return size;
    }


    // Main
    public static void main(String[] args) {
        int[] nums = {5,4,11,1,16,8};

        System.out.println(lengthOfLISRecursion(nums));

        System.out.println(lengthOfLISMemoization(nums));

        System.out.println(lengthOfLISTabulation(nums));
        System.out.println(lengthOfLISspaceI(nums));
        System.out.println(lengthOfLISSpaceII(nums));

        System.out.println(lengthOfLISBinSearch(nums));
    }    
}
