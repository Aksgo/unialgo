import java.util.*;

class DancingLinks {
    private static class Node {
        Node left, right, up, down;
        Node column;
        int row, size;
        
        Node(int r) {
            left = right = up = down = column = this;
            row = r;
            size = 0;
        }
    }
    
    private Node header;
    private List<Node> solution = new ArrayList<>();
    
    private void cover(Node col) {
        col.right.left = col.left;
        col.left.right = col.right;
        for (Node node = col.down; node != col; node = node.down) {
            for (Node temp = node.right; temp != node; temp = temp.right) {
                temp.down.up = temp.up;
                temp.up.down = temp.down;
                temp.column.size--;
            }
        }
    }
    
    private void uncover(Node col) {
        for (Node node = col.up; node != col; node = node.up) {
            for (Node temp = node.left; temp != node; temp = temp.left) {
                temp.column.size++;
                temp.down.up = temp;
                temp.up.down = temp;
            }
        }
        col.right.left = col;
        col.left.right = col;
    }
    
    private boolean search(int k) {
        if (header.right == header) {
            return true;
        }
        Node col = header.right;
        for (Node temp = header.right; temp != header; temp = temp.right) {
            if (temp.size < col.size) {
                col = temp;
            }
        }
        cover(col);
        for (Node node = col.down; node != col; node = node.down) {
            solution.add(node);
            for (Node temp = node.right; temp != node; temp = temp.right) {
                cover(temp.column);
            }
            if (search(k + 1)) {
                return true;
            }
            node = solution.remove(solution.size() - 1);
            for (Node temp = node.left; temp != node; temp = temp.left) {
                uncover(temp.column);
            }
        }
        uncover(col);
        return false;
    }
    
    public DancingLinks(int[][] matrix) {
        header = new Node(-1);
        List<Node> columns = new ArrayList<>();
        for (int j = 0; j < matrix[0].length; j++) {
            Node col = new Node(-1);
            col.left = header.left;
            col.right = header;
            header.left.right = col;
            header.left = col;
            columns.add(col);
        }
        for (int i = 0; i < matrix.length; i++) {
            Node prev = null;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    Node node = new Node(i);
                    node.column = columns.get(j);
                    node.up = node.column.up;
                    node.down = node.column;
                    node.column.up.down = node;
                    node.column.up = node;
                    node.column.size++;
                    if (prev != null) {
                        node.left = prev;
                        node.right = prev.right;
                        prev.right.left = node;
                        prev.right = node;
                    }
                    prev = node;
                }
            }
        }
    }
    
    public List<Integer> solve() {
        search(0);
        List<Integer> result = new ArrayList<>();
        for (Node node : solution) {
            result.add(node.row);
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 1},
            {0, 0, 1, 0, 1, 1, 0},
            {0, 1, 1, 0, 0, 1, 1},
            {0, 1, 0, 0, 0, 0, 1}
        };
        
        DancingLinks dl = new DancingLinks(matrix);
        List<Integer> solution = dl.solve();
        
        System.out.print("Solution rows: ");
        for (int row : solution) {
            System.out.print(row + " ");
        }
        System.out.println();
    }
}