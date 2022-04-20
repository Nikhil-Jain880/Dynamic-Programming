import java.util.Arrays;

class DecodeWays {
    static int[] dp;
    static int decode(String s, int point, String parts)
    {
        if(point == s.length()) return 1;

        if(s.charAt(point) == '0') { 
            // for handling 0_ what is happening is suppose for a string 102 and my parts is having 13 or 3
            // but my point is pointing always one ahead after 3 its 0 so that way its returning one

            // to summarize my point will always point to the next index which will be calcutated, so
            // whenever it points to 0 then return 0
            // however it will still work 3102  when point is at 1, when it will call
            // -one then parts will hold 1 and point will point 0 (return 0)
            // -two then parts will hold 10 and point will point 2 (miracle right!) and then this subtree will go on

            // System.out.println(parts+" "+ point);
            return 0;
        }

        if(point == s.length()-1) return 1;
        
        if(dp[point] != -1) 
        {
            // System.out.println("here");
            return dp[point];
        }


        int one = decode(s, point+1,s.substring(point, point+1)); // one step - it will necer cross 26
        int two = 0; // two step - it may cross 26 thats why we need to check first

        if(Integer.parseInt(s.substring(point, point+2)) <=26)
        {
            two = decode(s, point+2, s.substring(point, point+2));
        }

        return dp[point] = one+two;
    }
    
   public static void main(String[] args) {
       String s = "1242052";
       dp = new int[s.length()];
       Arrays.fill(dp,-1);
       int count = decode(s, 0, "");
       System.out.println(count);
   }
}