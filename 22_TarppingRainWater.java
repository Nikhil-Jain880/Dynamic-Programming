// https://leetcode.com/problems/trapping-rain-water/

class TrapptingRainWater
{
    static int trap(int[] height) {
        int len = height.length;
        int[] left = new int[len];
        int[] right = new int[len];
        
        left[0] = height[0];
               
        for(int i =1;i<len;i++)
        {
            left[i] = Math.max(left[i-1], height[i]); //Maximum Value for that index from left
        }
        
        right[len-1] = height[len-1];
        
        for(int i = len-2; i>=0; i--)
        {
            right[i] = Math.max(right[i+1], height[i]); //Maximum Value for that index from Right
        }
        
        int water =0;
        for(int i =0;i<len;i++)
        { // Taking the minimum of left and right highest peak from that index; min coz if I take max then water will to overflow from the min one
            water += Math.min(left[i], right[i]) - height[i]; // we only want water so minus the ground which is beneath the ground
        }
        
        return water;
    }

    public static void main(String[] args) {
        int[] height ={0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }
}