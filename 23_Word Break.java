// https://leetcode.com/problems/word-break/

import java.util.List;

class Word_Break {


    // Recursion (TLE)
    static boolean flag = false;
    
    public static boolean wordBreak(String s, List<String> wordDict) {
        helper(s, "", wordDict); // The code is Self Explanatory 
        return flag; // If there is a problem in understanding, uncomment those print statements
    }
    
    public static void helper(String s, String word, List<String> list)
    {
        if(s.length() == 0)
        {
            flag = true;
            // System.out.println(word);
        }
        
        for(int i =0;i<s.length();i++)
        {
            String left = s.substring(0, i+1);
            // System.out.println(left+" "+s.length());

            if(list.contains(left))
            {
                String right = s.substring(i+1);
                helper(right, word+left+" ", list);
            }
        }
    }

    // Tabulation
    static boolean Tabulation(String s, List<String> wordDict) {
        int[] dp = new int[s.length()];
        /* 
        Move the main index pointer i, for each i check all the suffix suppose apple 
        and i is at l then check if appl is present, if present then at index i put the j-1 index value
        (all dp[i] will initially be zero)
        check if j =1 i at l ppl is present, if present then at index i put the j-1 index value
        check if pl is present, check if l is present


        suppose catandmouse, array - cat, mouse, then at dp[2] will be 1
        when i will come to d, it will check for catand, atand, tand, and, nd, d none of them are in the array
        so all d[i] is zero except for 1, then when i comes to e it will check for
        catandmouse, atandmouse, tandmouse, andmouse .... mouse 
        when it comes to mouse it is present so it will go like
        dp[10] = dp[j-1]-> dp[5] 
        it will be zero because dp[5] is zero that's because and is missing if andmouse were in the array then 
        will would have calculated like dp[10] = dp[2] which would have been true
        */
        for(int i =0;i<s.length();i++)
        {
            for(int j =0; j<=i;j++)
            {
                String word = s.substring(j, i+1);
                if(wordDict.contains(word))
                { // Below if is there to handle a edge case  
                    if(j > 0)
                        dp[i] = dp[j-1];
                    else dp[i] =1; // If j == 0 
                }
            }
        }
        
        return dp[dp.length-1] > 0;
    }

    public static void main(String[] args) {
        String s = "applepenapple";
        List<String> wordDict = List.of("apple","pen");

        System.out.println(wordBreak(s, wordDict));
        System.out.println(Tabulation(s, wordDict));
    }
}
