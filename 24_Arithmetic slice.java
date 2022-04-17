// https://leetcode.com/problems/arithmetic-slices/

class Arithmetic_slice {
    
    static int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 2) return 0;
        int result =0;
        int counter = 0;
        int gap = nums[0] - nums[1];
        
        for(int i =2; i<nums.length;i++)
        {
            if(nums[i-1] - nums[i] == gap)
            {
                counter++;
            }
            else
            {
                gap = nums[i-1] - nums[i];
                counter =0;
            }
            result += counter;
        }

        
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,5,8,2,5,8,1,5,9,13,17,5,3,1,-1};
        System.out.println(numberOfArithmeticSlices(nums));
    }
}
