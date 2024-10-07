#include <iostream>
#include <queue>
#include <unordered_map>
#include <string>

class HuffmanNode {
public:
    char character;
    int frequency;
    HuffmanNode* left;
    HuffmanNode* right;

    HuffmanNode(char character, int frequency) : character(character), frequency(frequency), left(nullptr), right(nullptr) {}
};

struct Compare {
    bool operator()(HuffmanNode* left, HuffmanNode* right) {
        return left->frequency > right->frequency;
    }
};

class HuffmanCoding {
private:
    std::unordered_map<char, std::string> huffmanCodes;

    void generateCodes(HuffmanNode* root, const std::string& code) {
        if (!root) return;

        if (!root->left && !root->right) {
            huffmanCodes[root->character] = code;
            return;
        }

        generateCodes(root->left, code + "0");
        generateCodes(root->right, code + "1");
    }

public:
    HuffmanNode* buildHuffmanTree(char characters[], int frequencies[], int size) {
        std::priority_queue<HuffmanNode*, std::vector<HuffmanNode*>, Compare> pq;

        for (int i = 0; i < size; i++) {
            pq.push(new HuffmanNode(characters[i], frequencies[i]));
        }

        while (pq.size() > 1) {
            HuffmanNode* left = pq.top(); pq.pop();
            HuffmanNode* right = pq.top(); pq.pop();
            HuffmanNode* newNode = new HuffmanNode('\0', left->frequency + right->frequency);
            newNode->left = left;
            newNode->right = right;
            pq.push(newNode);
        }

        return pq.top(); // root of the Huffman tree
    }

    void printHuffmanCodes() {
        for (const auto& pair : huffmanCodes) {
            std::cout << pair.first << ": " << pair.second << std::endl;
        }
    }

    void generateHuffmanCodes(HuffmanNode* root) {
        generateCodes(root, "");
    }
};

int main() {
    char characters[] = {'a', 'b', 'c', 'd', 'e'};
    int frequencies[] = {5, 9, 12, 13, 16};
    int size = sizeof(frequencies) / sizeof(frequencies[0]);

    HuffmanCoding huffman;
    HuffmanNode* root = huffman.buildHuffmanTree(characters, frequencies, size);
    huffman.generateHuffmanCodes(root);
    huffman.printHuffmanCodes();

    return 0;
}
