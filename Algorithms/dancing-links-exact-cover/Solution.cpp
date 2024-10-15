#include <iostream>
#include <vector>

class DancingLinks {
private:
    struct Node {
        Node *left, *right, *up, *down;
        Node *column;
        int row, size;
        
        Node(int r = -1) : left(this), right(this), up(this), down(this), column(this), row(r), size(0) {}
    };
    
    Node *header;
    std::vector<Node*> solution;
    
    void cover(Node *col) {
        col->right->left = col->left;
        col->left->right = col->right;
        for (Node *node = col->down; node != col; node = node->down) {
            for (Node *temp = node->right; temp != node; temp = temp->right) {
                temp->down->up = temp->up;
                temp->up->down = temp->down;
                temp->column->size--;
            }
        }
    }
    
    void uncover(Node *col) {
        for (Node *node = col->up; node != col; node = node->up) {
            for (Node *temp = node->left; temp != node; temp = temp->left) {
                temp->column->size++;
                temp->down->up = temp;
                temp->up->down = temp;
            }
        }
        col->right->left = col;
        col->left->right = col;
    }
    
    bool search(int k) {
        if (header->right == header) {
            return true;
        }
        Node *col = header->right;
        for (Node *temp = header->right; temp != header; temp = temp->right) {
            if (temp->size < col->size) {
                col = temp;
            }
        }
        cover(col);
        for (Node *node = col->down; node != col; node = node->down) {
            solution.push_back(node);
            for (Node *temp = node->right; temp != node; temp = temp->right) {
                cover(temp->column);
            }
            if (search(k + 1)) {
                return true;
            }
            node = solution.back();
            solution.pop_back();
            for (Node *temp = node->left; temp != node; temp = temp->left) {
                uncover(temp->column);
            }
        }
        uncover(col);
        return false;
    }

public:
    DancingLinks(std::vector<std::vector<int>>& matrix) {
        header = new Node();
        std::vector<Node*> columns;
        for (int j = 0; j < matrix[0].size(); j++) {
            Node *col = new Node();
            col->left = header->left;
            col->right = header;
            header->left->right = col;
            header->left = col;
            columns.push_back(col);
        }
        for (int i = 0; i < matrix.size(); i++) {
            Node *prev = nullptr;
            for (int j = 0; j < matrix[i].size(); j++) {
                if (matrix[i][j]) {
                    Node *node = new Node(i);
                    node->column = columns[j];
                    node->up = node->column->up;
                    node->down = node->column;
                    node->column->up->down = node;
                    node->column->up = node;
                    node->column->size++;
                    if (prev) {
                        node->left = prev;
                        node->right = prev->right;
                        prev->right->left = node;
                        prev->right = node;
                    }
                    prev = node;
                }
            }
        }
    }
    
    std::vector<int> solve() {
        search(0);
        std::vector<int> result;
        for (Node *node : solution) {
            result.push_back(node->row);
        }
        return result;
    }
};

// Example usage: Solving a simple exact cover problem
int main() {
    std::vector<std::vector<int>> matrix = {
        {1, 0, 0, 1, 0, 0, 1},
        {1, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 1, 1, 0, 1},
        {0, 0, 1, 0, 1, 1, 0},
        {0, 1, 1, 0, 0, 1, 1},
        {0, 1, 0, 0, 0, 0, 1}
    };
    
    DancingLinks dl(matrix);
    std::vector<int> solution = dl.solve();
    
    std::cout << "Solution rows: ";
    for (int row : solution) {
        std::cout << row << " ";
    }
    std::cout << std::endl;
    
    return 0;
}