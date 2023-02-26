package Question1;//Answer using java with comments. Assume you were hired to create an application
// for an ISP, and there is n number of network devices, such as routers, that are
// linked together to provides internet access to home user users. You are given a
// 2D array that represents network connections between these network devices such that a[i]=[xi,yi]
// where xi is connected to yi device.  Suppose there is a power outage on a certain device provided
// as int n represents id of the device on which power failure occurred)), Write an algorithm
// to return impacted network devices due to breakage of the link between network devices.
// These impacted device list assists you notify linked consumers that there is a power outage
// and it will take some time to rectify an issue.
// Note that: node 0 will always represent a source of internet or gateway to international network..
//
//        Input: edges= {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}}
//        Target Device (On which power Failure occurred): 4
//        Output (Impacted Device List) = {5,7}
//        Explanation: power failure on network device 4 will disconnect 5 and 7 from internet
//

import java.util.*;

public class Answer1b {
    public static List<Integer> findImpactedDevices(int[][] edges, int targetDevice) {
        // create an adjacency list representation of the graph
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            adjacencyList.putIfAbsent(x, new ArrayList<>());
            adjacencyList.putIfAbsent(y, new ArrayList<>());
            adjacencyList.get(x).add(y);
            adjacencyList.get(y).add(x);
        }        // remove the edge that connects the failed device to its neighbor
        List<Integer> neighbors = adjacencyList.get(targetDevice);
        neighbors.remove((Integer) targetDevice);
        adjacencyList.put(targetDevice, new ArrayList<>());
        // perform a BFS starting from node 0 to find all the connected nodes
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited.add(node);
            List<Integer> nodeNeighbors = adjacencyList.getOrDefault(node, new ArrayList<>());
            for (int neighbor : nodeNeighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
        // any node that is not visited during the BFS is disconnected from the gateway
        List<Integer> impactedDevices = new ArrayList<>();
        for (int node : adjacencyList.keySet()) {
            if (!visited.contains(node)) {
                impactedDevices.add(node);
            }
        }
        return impactedDevices;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 6}, {2, 4}, {4, 6}, {4, 5}, {5, 7}};
        int targetDevice = 4;
        List<Integer> impactedDevices = findImpactedDevices(edges, targetDevice);
        System.out.println(impactedDevices);
    }
}






