// https://leetcode.com/problems/maximum-length-of-repeated-subarray/

// Largest Common Substring - Only continuous value
class MaximumLengthOfRepeatedSubarray {

    private static int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1+1][len2+1];
        
        int maxLength = 0;
        for(int i =1;i<=len1;i++)
        {
            for(int j =1; j<=len2; j++)
            {
                if(nums1[i-1] == nums2[j-1])
                {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    maxLength = Math.max(maxLength, dp[i][j]);
                } // if the value is not continuous we put zero 
                else dp[i][j] = 0; // There is no need to wrie this else(all the values are already filled with 0's)
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] num1 = {1,2,3,2,1};
        int[] nums2 = {3,2,1,4,7};
        System.out.println(findLength(num1,nums2));
    }
    
}
