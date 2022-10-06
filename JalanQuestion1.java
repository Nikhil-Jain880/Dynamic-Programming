import java.util.Arrays;
import java.util.Scanner;

public class JalanQuestion1 {

    public static void main(String[] args)
    {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter the size of the Array: ");
        int size = scn.nextInt();

        int arr[] = new int[size];

        System.out.println("Enter the array Elements: ");
        for(int i =0;i<size;i++)
        {
            arr[i] = scn.nextInt();
        }
 
        Arrays.sort(arr);
        rearrange(arr, arr.length);
 
        System.out.println("The Resultant Array is: ");
        System.out.println(Arrays.toString(arr));
    }

    static void rearrange(int[] arr, int n)
    {
        int temp[] = arr.clone();
 
        int small = 0, large = n - 1;
 
        boolean flag = true;
 
        for (int i = 0; i < n; i++) {
            if (flag)
                arr[i] = temp[large--];
            else
                arr[i] = temp[small++];
 
            flag = !flag;
        }
    }   
}

/*
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */

/*
 * Test Cases:
 * 1. If Array is Empty:
 *      The resultant array will be []
 * 2. If all the value are same as [1, 1, 1, 1]
 *      The Resultant Array will be [1, 1, 1, 1] 
 * 3. If Negative Values are present as [-6, -2, 0, 2]
 *      The Resultant Array will be [2, -6, 0, -2]
 * 4. If the values Present are Greater than ot less than Integer Capacity
 *      An Exception will be thrown
 * 5. If the values Entered are not integer 
 *      An Exception will be thrown  
 * 6. If the values Present are Normal(as per Question Example) as [2, 4, 6, 8, 10]
 *      The Resultant Array will be [10, 2, 8, 4, 6]
 */