
//a)	Implement Huffman encoding and decoding.

package Question6;
import java.util.PriorityQueue;
import java.util.HashMap;

public class Answer6a {

    private class Node implements Comparable<Node> {
        char ch;
        int freq;
        Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public int compareTo(Node other) {
            return this.freq - other.freq;
        }
    }

    public String encode(String input) {
        HashMap<Character, String> encodingMap = new HashMap<>();
        Node root = buildHuffmanTree(input, encodingMap);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            sb.append(encodingMap.get(input.charAt(i)));
        }
        return sb.toString();
    }

    private Node buildHuffmanTree(String input, HashMap<Character, String> encodingMap) {
        int[] frequency = new int[256];
        for (int i = 0; i < input.length(); i++) {
            frequency[input.charAt(i)]++;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (char i = 0; i < frequency.length; i++) {
            if (frequency[i] > 0) {
                pq.offer(new Node(i, frequency[i], null, null));
            }
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.offer(parent);
        }

        Node root = pq.poll();
        buildEncodingMap(root, "", encodingMap);
        return root;
    }

    private void buildEncodingMap(Node node, String encoding, HashMap<Character, String> encodingMap) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            encodingMap.put(node.ch, encoding);
            return;
        }

        buildEncodingMap(node.left, encoding + "0", encodingMap);
        buildEncodingMap(node.right, encoding + "1", encodingMap);
    }

    public String decode(String encodedString, Node root) {
        StringBuilder sb = new StringBuilder();
        Node current = root;
        for (int i = 0; i < encodedString.length(); i++) {
            if (encodedString.charAt(i) == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            if (current.left == null && current.right == null) {
                sb.append(current.ch);
                current = root;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "hello world";
        Answer6a huffmanEncoding = new Answer6a();
        String encodedString = huffmanEncoding.encode(input);
        System.out.println("Encoded string: " + encodedString);
        String decodedString = huffmanEncoding.decode(encodedString, huffmanEncoding.buildHuffmanTree(input, new HashMap<>()));
        System.out.println("Decoded string: " + decodedString);
    }
}
