package Question8;

public class Answer8a {
    public static int maxSquareArea(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols]; // create a 2D array to store the maximum area of square that can be formed using (i,j) as the bottom-right corner

        // Initialize the first row and first column of the dp matrix
        for (int i = 0; i < rows; i++) {
            dp[i][0] = matrix[i][0];
        }
        for (int j = 0; j < cols; j++) {
            dp[0][j] = matrix[0][j];
        }

        // Compute the rest of the dp matrix
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 1) { // if the current element is 1, the maximum area of square that can be formed using (i,j) as the bottom-right corner is 0
                    dp[i][j] = 0;
                } else { // if the current element is 0, the maximum area of square that can be formed using (i,j) as the bottom-right corner is the minimum of the maximum area of squares that can be formed using (i-1,j), (i,j-1), and (i-1,j-1) as the bottom-right corners, plus 1
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
            }
        }

        // Find the maximum value in the dp matrix
        int maxArea = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dp[i][j] > maxArea) {
                    maxArea = dp[i][j];
                }
            }
        }

        return maxArea * maxArea; // return the area of the maximum square
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,0,1,0,0},
                {0,1,1,1,1},
                {0,0,0,0,1},
                {0,0,0,1,1},
                {0,1,0,1,1}};
        System.out.println(maxSquareArea(matrix)); // Output: 4
    }
}
