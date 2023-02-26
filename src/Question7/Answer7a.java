package Question7;

import java.util.Date;
import java.util.Random;
public class Answer7a {
    public static void main(String[] args) {

        Date start = new Date();

        int[][] m1 = GenerateMatrix.generateMatrix(3, 3);
        int[][] m2 = GenerateMatrix.generateMatrix(3, 3);

        int[][] result = multiply(m1, m2);

        System.out.println("Matrix 1 : ");
        GenerateMatrix.print(m1);

        System.out.println("\nMatrix 2 : ");
        GenerateMatrix.print(m2);

        System.out.println("\nOutput Matrix : ");
        GenerateMatrix.print(result);

        Date end = new Date();
        System.out.println("\nTime taken in milli seconds: " + (end.getTime() - start.getTime()));

    }

    public static int[][] multiply(int[][] matrix1, int[][] matrix2) {
        int resultRows = matrix1.length;
        int resultColumns = matrix2[0].length;

        int[][] result = new int[resultRows][resultColumns];

        int columns2 = matrix2[0].length;

        for (int i = 0; i < resultRows; i++) {
            for (int j = 0; j < columns2; j++) {
                result[i][j] = 0;
                for (int k = 0; k < resultColumns; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;

    }
}


class GenerateMatrix {

    public static int[][] generateMatrix(int rows, int columns) {

        // output array to store the matrix values
        int[][] result = new int[rows][columns];

        // TO generate a random integer.
        Random random = new Random();

        // adding values at each index.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                result[i][j] = random.nextInt(100) * 10;

            }
        }

        // returning output.
        return result;
    }

    // to print the matrix
    public static void print(int[][] matrix) {

        System.out.println();

        int rows = matrix.length;
        int columns = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }

    }

}