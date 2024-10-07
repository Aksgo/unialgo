import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

class HuffmanNode implements Comparable<HuffmanNode> {
    int frequency;
    char character;
    HuffmanNode left, right;

    HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        left = right = null;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.frequency, other.frequency);
    }
}

class HuffmanCoding {
    private Map<Character, String> huffmanCodes = new HashMap<>();

    public void generateCodes(HuffmanNode root, String code) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
            return;
        }

        generateCodes(root.left, code + "0");
        generateCodes(root.right, code + "1");
    }

    public HuffmanNode buildHuffmanTree(char[] characters, int[] frequencies) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        for (int i = 0; i < characters.length; i++) {
            pq.add(new HuffmanNode(characters[i], frequencies[i]));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode newNode = new HuffmanNode('\0', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;
            pq.add(newNode);
        }

        return pq.poll(); // root of the Huffman tree
    }

    public void printHuffmanCodes() {
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        char[] characters = {'a', 'b', 'c', 'd', 'e'};
        int[] frequencies = {5, 9, 12, 13, 16};

        HuffmanCoding huffman = new HuffmanCoding();
        HuffmanNode root = huffman.buildHuffmanTree(characters, frequencies);
        huffman.generateCodes(root, "");
        huffman.printHuffmanCodes();
    }
}