// https://leetcode.com/problems/house-robber-ii/
// One new clause is added to the question which is imagine the array as a 
// circular array i.e. !st and the last element are neighbours

class HouseRobber2 {

    int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int arr1[] = new int[nums.length - 1];
        int arr2[] = new int[nums.length - 1];
        int arr1ind = 0;
        int arr2ind = 0;
        for (int i = 0; i < nums.length; i++) { // Create 2 arrays
            if (i != 0) { // one dont have the 1st element
                arr1[arr1ind] = nums[i];
                arr1ind++;
            }
            if (i != nums.length - 1) { // And the other does not have the last element
                arr2[arr2ind] = nums[i];
                arr2ind++;
            }
        } // Pass both of them and return the one giving more money than another
        return Math.max(Optimized(arr1), Optimized(arr2));
    }

    public int Optimized(int[] nums) { // Same as House Robber
        int n = nums.length;
        if (n == 1)
            return nums[0];

        int secondPrevious = nums[0];
        int previous = Math.max(nums[1], nums[0]);
        int curri = previous;
        for (int i = 2; i < n; i++) {
            curri = Math.max(previous, secondPrevious + nums[i]);
            secondPrevious = previous;
            previous = curri;

        }
        return curri;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 7, 9, 3, 1 };
        HouseRobber2 obj = new HouseRobber2();
        System.out.println(obj.rob(nums));
    }
}
