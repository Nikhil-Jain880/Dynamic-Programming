import java.util.Arrays;

class MatrixBlockSum {

    // Tabulation
    private static int[][] matrixBlockSum(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] sum = new int[n][m]; // for prefix sum (from 0 0 to i j)
        int[][] ans = new int[n][m];
        
        for(int i =0;i<n;i++) // Prefix sum
        {
            int curr = 0;
            for(int j=0;j<m;j++)
            {
                curr+=mat[i][j];
                if(i == 0)
                {
                    sum[i][j] = curr;
                }
                else
                {
                    sum[i][j] = curr + sum[i-1][j];
                }
            }
        }
        
        
        for(int i =0;i<n;i++)
        {
            for(int j =0;j<m;j++)
            {
                int rowStart = i-k; // all the base cases
                int rowEnd = i+k;
                
                int colStart = j-k;
                int colEnd = j+k;
                
                if(rowStart < 0) rowStart =0;
                if(colStart < 0) colStart =0;
                
                if(rowEnd >= n) rowEnd = n-1;
                if(colEnd >= m) colEnd = m-1;
                
                if(colStart == 0 && rowStart == 0) // simplest one
                {
                    ans[i][j] = sum[rowEnd][colEnd];
                    continue;
                }
                else if(rowStart == 0 && colStart != 0) // minus the previous col last row coz it contains the sum of 00 to just before col
                {
                    // System.out.println(rowEnd+" "+colEnd+" "+colStart);
                    ans[i][j] = sum[rowEnd][colEnd] - sum[rowEnd][colStart-1];
                    continue;
                }
                else if(rowStart != 0 && colStart == 0)
                { // Minus the prev row last col :last col here means j+k (colEnd)
                  /**  suppose for 3*3 matrix and we need to cal for 10 to 22
                   *   00 01 02
                   *   10 11 12
                   *   20 21 22
                   * we want to exclude the sum form 00 to 02; so we simply minus it
                   * we need to minus 02th index cause it contains the sum of 00 01 & 02
                   * we can get row by rowStart-1 and colEnd (not m coz we need to remove the last in range col for ij)
                   * suppose, for calculating till 01 we dont want to remove the till 02 or may be even 03  thats why we use colEnd 
                   *  */

                    ans[i][j] = sum[rowEnd][colEnd] - sum[rowStart-1][colEnd];
                    continue;
                }
                else if(rowStart != 0 && colStart !=0) // same trim out top part & left part and ADD the intersection of both paths
                {                                      // cause they have been removed twice
                    ans[i][j] = sum[rowEnd][colEnd] - sum[rowStart-1][colEnd] - sum[rowEnd][colStart-1] + sum[rowStart-1][colStart-1];
                    continue;
                }
            
            }
        }
        
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}}; // the size of mat can be n*m
        int k = 1;

        int[][] ans = matrixBlockSum(matrix, k);

        for(int[] row : ans)
        {
            System.out.println(Arrays.toString(row));
        }
    }
    
}
