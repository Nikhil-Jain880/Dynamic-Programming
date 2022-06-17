// https://leetcode.com/problems/minimum-sideway-jumps/

import java.util.Arrays;

class Minimum_Sideway_Jumps {

    //Recursion
    static int Recursion(int[] obs) {
        int frog =0; // starting point
        int lane = 2; // starting lane
        int min = helperRecur(obs, frog, lane);
        return min;
    }
    
    static int helperRecur(int[] obs, int frog, int lane)
    {
        // System.out.println("Frog:"+frog+" Lane:"+lane+" ");
        //Base Case
        if(frog == obs.length-1) // reached
        {
            return 0;
        }
        //No obs in front
        if(frog < obs.length-1 && obs[frog+1] != lane)
        {
            return 0+ helperRecur(obs, frog+1, lane); // if there is no obs in front then move one step for free
        }
        else // if(obs[frog+1] == lane) // obs in front
        {
            int temp = Integer.MAX_VALUE;
            for(int i =1;i<=3;i++)
            {
                
                if(i!=lane && obs[frog] != i) // checking for not on Same Lane and Obs above/below
                {
                    
                    temp = Math.min(temp, helperRecur(obs, frog, i)); // call with changed lane ans take the min path
                }
                // System.out.println(temp);
               
            }
            return 1 + temp;
        }
    }
    

    //Memoization
    static int[][] dp;
    static int memo(int[] obs) 
    {
        dp = new int[obs.length][4];

        for(int[] row: dp)
        {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        int frog =0;
        int lane = 2;
        int min = helpermemo(obs, frog, lane);
        return min;
    }
    
    static int helpermemo(int[] obs, int frog, int lane)
    {
        //Base Case
        if(frog == obs.length-1)
        {
            return 0;
        }
        //Already checked
        if(dp[frog][lane] != Integer.MAX_VALUE)
        {
            return dp[frog][lane];
        }
        //No obs in front
        if(frog < obs.length-1 && obs[frog+1] != lane)
        {
            return dp[frog][lane] = helpermemo(obs, frog+1, lane); // if no obs in font then just move front
        }
        else // if(obs[frog+1] == lane)
        {
            int temp = Integer.MAX_VALUE;
            for(int i =1;i<=3;i++)
            {
                // i should not be the same lane and should not have an obstacle
                if(i!=lane && obs[frog] != i) //Same Lane and Obs above/below
                {
                    
                    temp = Math.min(temp, helpermemo(obs, frog, i));
                }               
            }
            return dp[frog][lane] = 1 + temp;
        }
    }
    static int tabulation(int[] obs) {
        /*
         * just aintaining a 1d array of size 3 : he frog initially starts form 2nd lane so the value of
         * 2nd lane is initialized as 0 and to move from 2 to 1 or 3 we need to jump 1 time so the value of 
         * 1 or 3 is 1
         * for every iteration we will keep track of minimum value/jumps needed to be there if faced with an
         * obstacle we will place max value
         */
        int[] dp = new int[] {1, 0, 1};
        for(int j =0;j<obs.length-1;j++)
        {
            int o = obs[j]; // o is obstacle lane 
            if(o != 0)
            {
                dp[o-1] = 999999; // placing the obstacle value to max
            }
            for(int i =0;i<3;i++)
            {
                if(i != o-1) // not calculating for obstacle position
                { // current dp[i] or min form the other 2 and +1(for jump)
                    dp[i] = Math.min(dp[i], Math.min(dp[(i+1)%3]+1, dp[(i+2)%3]+1));
                }
            }
            // the dp[i] is taken as it is because to travel linearly we dont need to jump
            // System.out.println(Arrays.toString(dp));            
        }
        return Math.min(dp[0], Math.min(dp[1], dp[2]));
    }

    public static void main(String[] args) {
        int[] obstacles = {0,2,1,0,3,0};
        System.out.println(Recursion(obstacles));
        System.out.println(memo(obstacles));
        System.out.println(tabulation(obstacles));
    }
}
