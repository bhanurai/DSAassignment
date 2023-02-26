package Question5;
//Question 5
//        a)
//        You are given a 2D array containing coordinates and height of rectangle such that height[i]=[xi,yi,hi], where xi the
//        x coordinate of left edge, yi represents x coordinate of right edge of rectangle and hi represents the height of the
//        peaks of each rectangle. If you want to construct a border line over the peaks of rectangle represented in bar chart,
//        return the key coordinates required to build a border line that contacts the peaks of the given chart.
//        Note: key points are the left coordinates of shape representing peaks where you need to draw boarder line.
//        Input: height={{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}}
//        Output: {{1,10},{2,15},{5,12},{8,0},{9,1},{11,15},{13,0}}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answer5a {
    public static int[][] getBorderLine(int[][] height) {
        List<Integer> xCoords = new ArrayList<>();
        for (int[] h : height) {
            xCoords.add(h[0]);
            xCoords.add(h[1]);
        }
        Collections.sort(xCoords);

        int[][] keyPoints = new int[xCoords.size()][2];
        int prevHeight = 0;
        int index = 0;
        for (int x : xCoords) {
            int maxHeight = 0;
            for (int[] h : height) {
                if (x >= h[0] && x < h[1]) {
                    maxHeight = Math.max(maxHeight, h[2]);
                }
            }
            if (maxHeight != prevHeight) {
                keyPoints[index][0] = x;
                keyPoints[index][1] = maxHeight;
                prevHeight = maxHeight;
                index++;
            }
        }

        // trim keyPoints array to remove unused elements
        int[][] result = new int[index][2];
        for (int i = 0; i < index; i++) {
            result[i] = keyPoints[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] height = {{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}};
        int[][] borderLine = getBorderLine(height);
        for (int[] point : borderLine) {
            System.out.println(point[0] + "," + point[1]);
        }
    }
}