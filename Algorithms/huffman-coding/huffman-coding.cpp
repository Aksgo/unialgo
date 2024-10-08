#include <iostream>
#include <queue>
#include <unordered_map>
#include <string>

using namespace std;

// Node of the Huffman Tree
class HuffmanNode {
public:
    char character;
    int frequency;
    HuffmanNode* left;
    HuffmanNode* right;

    // Constructor to initialize node
    HuffmanNode(char character, int frequency) : character(character), frequency(frequency), left(nullptr), right(nullptr) {}
};

// Comparator for priority queue (min-heap)
struct Compare {
    bool operator()(HuffmanNode* left, HuffmanNode* right) {
        return left->frequency > right->frequency;
    }
};

class HuffmanCoding {
private:
    unordered_map<char, string> huffmanCodes;

    // Recursive function to generate Huffman codes
    void generateCodes(HuffmanNode* root, const string& code) {
        if (!root) return;

        // If it's a leaf node, assign the code
        if (!root->left && !root->right) {
            huffmanCodes[root->character] = code;
            return;
        }

        generateCodes(root->left, code + "0");
        generateCodes(root->right, code + "1");
    }

public:
    // Build Huffman Tree
    HuffmanNode* buildHuffmanTree(char characters[], int frequencies[], int size) {
        priority_queue<HuffmanNode*, vector<HuffmanNode*>, Compare> pq;

        // Insert all characters into the priority queue
        for (int i = 0; i < size; i++) {
            pq.push(new HuffmanNode(characters[i], frequencies[i]));
        }

        // Combine nodes with the smallest frequencies
        while (pq.size() > 1) {
            HuffmanNode* left = pq.top(); pq.pop();
            HuffmanNode* right = pq.top(); pq.pop();
            HuffmanNode* newNode = new HuffmanNode('\0', left->frequency + right->frequency);
            newNode->left = left;
            newNode->right = right;
            pq.push(newNode);
        }

        // Return root of the Huffman Tree
        return pq.top();
    }

    // Print the Huffman codes
    void printHuffmanCodes() {
        for (const auto& pair : huffmanCodes) {
            cout << pair.first << ": " << pair.second << endl;
        }
    }

    // Generate Huffman codes
    void generateHuffmanCodes(HuffmanNode* root) {
        generateCodes(root, "");
    }
};

int main() {
    char characters[] = {'a', 'b', 'c', 'd', 'e'};
    int frequencies[] = {5, 9, 12, 13, 16};
    int size = sizeof(frequencies) / sizeof(frequencies[0]);

    // Build and generate Huffman tree and codes
    HuffmanCoding huffman;
    HuffmanNode* root = huffman.buildHuffmanTree(characters, frequencies, size);
    huffman.generateHuffmanCodes(root);
    huffman.printHuffmanCodes();

    return 0;
}
