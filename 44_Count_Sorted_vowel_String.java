import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/count-sorted-vowel-strings/
// https://leetcode.com/problems/count-sorted-vowel-strings/discuss/2029413/DP-Solution-with-Explanation

class Count_Sorted_vowel_String {
    
    //Backtracking
    static char[] arr= {'a','e','i','o','u'};
    static int count =0; 
    private static int countVowelStrings(int n) {
        helper(new ArrayList<Character>(), 0, n);
        return count;
    }
    private static void helper(List<Character> s, int i, int n)
    {
        if(s.size() == n)
        {
            count++;
            return;
        }
        //System.out.println(s);
        
        for(int j=i;j<arr.length;j++) // if curr is i then only i o u can come after it 
        {
            
            s.add(arr[j]);
            helper(s, j, n);
            s.remove(s.size()-1);
        }
        
    }

    //Tabulation
    private static int tabulation(int n) {
        // This approach we will count that how many combinations are possible so that the string end with a particular vowel
        // dp[2][5] rep here 2 rep vowel which is (a e i o u) i and 5 rep the size of string
        // we will calculate this by dp[i-1][j] + dp[i][j-1] which means 
        // - dp[i-1][j] means the number of times the last char was the prev vowels(we can definately remove the prev vowel and
        // and replace it with the curr vowel) suppose aa is there we can replace the last a by e to make it a ae.
        // - dp[i][j-1] means waht was the prev length value ( we can always add the vowel at the end to make a new string)
        // suppose aae, aee is there and now we calculate for len 4 we can add the curr vowel at the end like aaee and aeee and so on

        int[][] dp = new int[5][n]; // 5 - cause there are 5 vowels 0 rep a, 1 rep e and so on
        for(int i =0;i<5;i++)
        {
            dp[i][0] = 1; // when length is 1 there is only one way whcih is that vowel itself
        }
        for(int j =0;j<n;j++) // for vowel a there is always only one possibility to end which is all a's
        { //                    where as any other vowel can have different prefix aei aai eei aoi aui
            dp[0][j] = 1;
        }
        
        for(int i = 1;i<dp.length;i++)
        {
            for(int j =1;j<dp[0].length;j++)
            {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        // for(int[] row : dp)
        // {
        //     System.out.println(Arrays.toString(row));
        // }
        int sum = 0;
        for(int i =0;i<5;i++) // return the sum of no of time's string end with a, e, i, o, u for length n
        {
            sum += dp[i][n-1];
        }
        return sum;
    }

    private static int space(int n)
    {// the idea is we only need the last computed values 
        int a =1;
        int e = 1;
        int i = 1;
        int o = 1;
        int u = 1;
        
        for(int ind = 1;ind<n;ind++)
        { // for every col we need the values of that col only that's why we dont update the values then and there
            int aNew=a;
            int eNew=a+e;
            int iNew=a+e+i;
            int oNew=a+e+i+o;
            int uNew=a+e+i+o+u;
            // after computing all the col value we update every value at once
            a=aNew;
            e=eNew;
            i=iNew;
            o=oNew;
            u=uNew;
        }
        
        return a+e+i+o+u;
    }

    public static void main(String[] args)
    {
        int n = 5;
        System.out.println(countVowelStrings(n));
        System.out.println(tabulation(n));
        System.out.println(space(n));
    }
}
