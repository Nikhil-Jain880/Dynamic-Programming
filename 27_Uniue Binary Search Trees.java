// https://leetcode.com/problems/unique-binary-search-trees/

// Also known as catalan series

class Uniue_Binary_Search_Trees {

    public static int helper(int n)
    {
        int[] dp = new int[n+1];
        dp[0] = 1; // for 0 node the number of ways to arrange is 1 (dont do anything)
        dp[1] = 1; // for 1 node the number of ways to arrange is 1 (just place it)
        
        for(int i =2;i<=n;i++) // i is deciding the root node
        {
            for(int j=1;j<=i;j++) // j is representing the left and right of root
            {
                dp[i] += dp[i-j]* dp[j-1]; // right * left & we are adding up all the possiblites
            }
        }
        return dp[n];
    }

    /**
     * for a number 3 
     * lets elaboate it for 3, we have 3 nodes 1 2 3 
     * when we take 1 as a head node we have 2 nodes on the right
     * - that can be arranged in two ways 2 3 or 3 2 so whrn 1 is root there are two possibilites
     * when 2 is the root node we have 1 on the left and 1 on the right 
     * - that can be arranged in only one way 1 2 3
     * when we take 3 as root we have 2 nodes on the left
     * - taht can be arrnaged in 2 ways 1 2 3 or 2 1 3 
     * if we add all this we get 5 possibilites which is the answers
     */

    public static void main(String[] args) {
        int n = 6;
        System.out.println(helper(n));
    }
}
