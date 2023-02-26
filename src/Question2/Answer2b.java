//You are given an array of binary trees that represent different cities where a certain corporation has its branch office and the organization wishes to provide service by constructing a service center. Building service centers at any node, i.e., a city can give service to its directly connected cities where it can provide service to its parents, itself, and its immediate children. Returns the smallest number of service centers required by the corporation to provide service to all connected cities. Note that: the root node represents the head office and other connected nodes represent the branch office connected to the head office maintaining some kind of hierarchy.
//
//        Input: tree= {0,0, null, 0, null, 0, null, null, 0}
//        Output: 2
//        Explanation: construction of two service centers denoted by black markk will be enough to provide service to all cities.



package Question2;
class TreeNode {
        int val;         // Value of the node
        TreeNode left;   // Left child of the node
        TreeNode right;  // Right child of the node

        TreeNode(int val) {
                this.val = val;
        }
}

// Define the main class
public class Answer2b {
        // Define a method to compute the minimum number of service centers required
        public int minServiceCenters(TreeNode root) {
                // If the root is null, then no service centers are required
                if (root == null) {
                        return 0;
                }

                // Initialize the number of service centers to 0
                int numCenters = 0;

                // If the left child is not null, then recursively compute the minimum number
                // of service centers required for its grandchildren and add that to the total
                if (root.left != null) {
                        numCenters += minServiceCenters(root.left.left) + minServiceCenters(root.left.right) + 1;
                }

                // If the right child is not null, then recursively compute the minimum number
                // of service centers required for its grandchildren and add that to the total
                if (root.right != null) {
                        numCenters += minServiceCenters(root.right.left) + minServiceCenters(root.right.right) + 1;
                }


                // Return the minimum of the total number of service centers required for the
                // left and right subtrees plus 1, and the total number of service centers
                // required for the current node and its immediate children
                return Math.min(numCenters, 1 + minServiceCenters(root.left) + minServiceCenters(root.right));
        }

        // Define the main method to create a binary tree and compute the minimum number of service centers required
        public static void main(String[] args) {
                // Create the binary tree based on the input given in the question
                TreeNode root = new TreeNode(0);
                root.left = new TreeNode(0);
                root.right = new TreeNode(0);
                root.left.left = null;
                root.left.right = new TreeNode(0);
                root.right.left = null;
                root.right.right = null;
                root.left.right.left = new TreeNode(0);

                // Create an instance of the Solution class
                Answer2b que2b = new Answer2b();

                // Call the minServiceCenters method to compute the minimum number of service centers required
                int minCenters = que2b.minServiceCenters(root);

                // Print the result to the console
                System.out.println("Minimum number of service centers required: " + minCenters);
        }
}