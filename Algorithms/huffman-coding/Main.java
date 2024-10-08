import java.util.PriorityQueue;
import java.util.Comparator;

// Class to represent a node in the Huffman Tree
class HuffmanNode {
    int frequency;
    char character;

    HuffmanNode left;
    HuffmanNode right;

    HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
}

// Comparator for the priority queue
class HuffmanComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.frequency - y.frequency;
    }
}

class HuffmanCoding {

    // Function to print Huffman codes from the root of the Huffman Tree
    public void printCode(HuffmanNode root, String code) {
        if (root == null) {
            return;
        }

        // If the node is a leaf node, print the character and its code
        if (root.left == null && root.right == null && root.character != '-') {
            System.out.println(root.character + ": " + code);
            return;
        }

        // Recur to the left and right of the tree
        printCode(root.left, code + "0");
        printCode(root.right, code + "1");
    }

    // Function to build Huffman Tree and return the root
    public HuffmanNode generateHuffmanTree(char[] charArray, int[] charFreq) {
        // Priority queue to store nodes of the Huffman tree
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(charArray.length, new HuffmanComparator());

        // Create leaf nodes for each character and add to the queue
        for (int i = 0; i < charArray.length; i++) {
            HuffmanNode node = new HuffmanNode(charArray[i], charFreq[i]);
            queue.add(node);
        }

        // Iterate while there is more than one node in the queue
        while (queue.size() > 1) {
            // Remove two nodes with the smallest frequency
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();

            // Create a new internal node with the sum of the two smallest frequencies
            HuffmanNode sumNode = new HuffmanNode('-', left.frequency + right.frequency);
            sumNode.left = left;
            sumNode.right = right;

            // Add the new node to the queue
            queue.add(sumNode);
        }

        // The final node in the queue is the root of the Huffman Tree
        return queue.poll(); // Return the root of the tree
    }
}

public class Main
{
	public static void main(String[] args) {
		// Create an instance of HuffmanCoding
        HuffmanCoding huffman = new HuffmanCoding();

        // Example frequency table for characters
        char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] charFreq = { 5, 9, 12, 13, 16, 45 };

        // Generate the Huffman Tree
        HuffmanNode root = huffman.generateHuffmanTree(charArray, charFreq);

        // Print the Huffman codes by traversing the tree
        System.out.println("Huffman Codes:");
        huffman.printCode(root, "");
	}
}