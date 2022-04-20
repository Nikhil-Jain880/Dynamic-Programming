class uglyNumber2 {

    // Note: we only need the numbers whose prime factors are 2, 3, 5,
    // example: 14 is not an ugly number because its prime factors are 2 * 7 
    // and for a number to be ugly its prime factor should only consist of 2 3 5 
    // here 7 is a prime factor so we cant take 14 

    // so basically a number whose factor is a prime number other than 2 3 5 can not be taken

    public static int nthUglyNumber(int n) {
        int[] nums = new int[n];
        int two = 2;
        int three = 3;
        int five = 5;
        
        int twoInd = 1;
        int threeInd = 1;
        int fiveInd = 1;
        
        nums[0] = 1;
        
        for(int i =1;i<nums.length;i++)
        {
            // System.out.print(two +" "+ three+" "+ five+" ");
            nums[i] = Math.min(Math.min(two, three), five);
            // System.out.println("nums: "+nums[i]);
            
            if(nums[i] == two)
            {
                two = 2 * nums[twoInd]; 
                twoInd++;
            }
            if(nums[i] == three)
            {
                three = 3 * nums[threeInd];
                threeInd++;
            }
            if(nums[i] == five)
            {
                five = 5 * nums[fiveInd];
                fiveInd++;
            }
            
        }
        // System.out.print(Arrays.toString(nums));
        return nums[n-1];
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(12));
    }
}
