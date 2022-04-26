class LongestPalindromicSubstring {
    
    private static String longestPalindrome(String s) {
        if(s.length() < 1 || s == null) return "";
        
        int start =0;
        int end =0;
        int len =0;
        for(int i=0;i<s.length();i++)
        {
            int len1 = highestLength(s, i, i);
            int len2 = highestLength(s, i, i+1);
            
            len = Math.max(len1, len2);
            
            if(len > end - start)
            {
                start = i - (len - 1) /2;
                end = i + (len/2);
            }
        }
        return s.substring(start, end+1);
    }
    
    private static int  highestLength(String s, int left, int right)
    {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right))
        {
            left--;
            right++;
        }
        return right -left -1;
    }


    public static void main(String[] args) {
        String s = "babcd";
        System.out.println(longestPalindrome(s));
    }
}
