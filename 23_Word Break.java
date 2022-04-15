// https://leetcode.com/problems/word-break/

import java.util.List;

class Word_Break {


    // Backtracking (TLE)
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

    public static void main(String[] args) {
        String s = "applepenapple";
        List<String> wordDict = List.of("apple","pen");

        System.out.println(wordBreak(s, wordDict));
    }
}
