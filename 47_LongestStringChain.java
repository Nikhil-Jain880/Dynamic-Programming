// https://leetcode.com/problems/longest-string-chain/

import java.util.*;

class LongestStringChain {

    static int longestStrChain(String[] words) {
        
	    if(words == null || words.length == 0) {
            return 0;
        }
	
        int res = 0;
	
        Arrays.sort(words, (a,b)-> a.length()-b.length()); // sort accoring to length of words
	    HashMap<String, Integer> map = new HashMap(); 
	    
        for(String w : words) {                            
		    map.put(w, 1); // every word alone can also be a longest String Chain of len 1                                 
		
            for(int i=0; i<w.length(); i++) { // for every char in current word remove each and check if the 
                // word is present 
                
    			StringBuilder sb = new StringBuilder(w);
	    		String next = sb.deleteCharAt(i).toString();
                
		    	if(map.containsKey(next) && map.get(next)+1 > map.get(w)) // if present and the value is greater than curr
			    	map.put(w, map.get(next)+1);       // then update the value +1 is for the new word added
        	}
		    res = Math.max(res, map.get(w)); // take max for each word
	    }
	    return res;
    }

    public static void main(String[] args) {

        String[] words = {"xbc","pcxbcf","xb","cxbc","pcxbc"};
        System.out.println(longestStrChain(words));
    }
}
