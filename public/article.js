// For now, the articles are in js file. Because fetch throws CORS error
// Will probably have to cionfigure CORS where the site is hosted
// We can move this array to somewhere inside ArticleList under public

const articles = [
    {
        id: 1,
        title: "Binary Lifting",
        subtitle: "How does Binary Lifting utilizes power of 2 jumps?",
        image: "https://unialgo.web.app/asset/logo.png",
        description: [
            "Suppose you are given a tree and then Q queries. In each query we are given two nodes of the tree and are asked to find the lowest common ancestor of both of them. Now finding LCA is easy and can be done in O(N) (which is simple to understand), but for q queries the time complexity become O(Q*N). ",
            "So we need to preprocess the tree and then calculate the LCA of two nodes in O(log(N)) . Whenever we need to have log(N) complexity which can be achieved if we somehow used powers of 2 (seems obvious).",
            "Firstly, let us see what the algorithm do. So instead of going over every node for each query , we create a matrix of n * (log2(Depth of tree)) approximately. And for each node's row we store the 2^0 th parent (i.e 1st) , 2^1 st parent(i.e 2nd) , then 2^2 ,.., and more until the root node is crossed. Another thing we need to precompute is the depth of each of the node which can be done in O(N) and need to be done once only. And Creation matrix will take O(N*log(depth)). Generally the log(depth) would be less than 20 even.      NOTE: Don't worry if you feel like why are we doing this.",
            "Now we can use this precomputed information for each query as - let us take two node a and b. If a has a depth d1 and b has a depth d2 (assuming d1&gt;d2), then it is intuitive that we must at least cover the difference in depth of a and b, because LCA will anyhow lie above b. ",
            "So we need the (d1-d2) the parent of a which is very simple to find. If we represent (d1-d2) in binary representation say 0101 then it means the 5th parent we need can be achieved by 1st parent and then its fourth parent. Hence we can see that we are just taking the parent (some 2^j th parent) which we already precomputed. So this way we just took log2(d1-d2) to cover the difference in depth.",
            "Now there may be case that the d1-d2 th parent of a is b itself, so we may check the case if a==b, else it means that the two nodes are in separate branches currently.      One feature of LCA we use here in tree is that above the LCA all other nodes that come above it are always their common parents. So again we will use each bit of binary representation of depth of two nodes (which is essentially the same now) and if the jumping by that power of 2 gives us a common parent , then it means that either this is the LCA or it lies below it, so we need to look below, so we reduces the power by 1 and then jump, if the parent are different then definitely the LCA lies above , so we upgrade our parent of a = mat[a][j] (where mat is the matrix we created and mat[a][j] representing the 2^j th parent of a) and similarly b = mat[b][j]. ",
        ],
        writtenBy: "UniAlgo Owner",
    },
    {
        id: 2,
        title: "Dijkstra Algorithm",
        subtitle: "How Dijkstra's Algorithm Works",
        image: "https://unialgo.web.app/asset/logo.png",
        description: [
            "The algorithm computes the shortest path from a starting node to all other nodes in the graph. It selects the node with the smallest known distance, updates the distances of its neighbors, and repeats this process until all nodes have been visited.",
            "Steps of Dijkstra's Algorithm: ",
            "1. Set the distance to the source node as 0 and to all other nodes as infinity. ",
            "2. Mark the source node as visited. For all its neighbors, calculate the tentative distance using the current nodeâ€™s distance. If this new distance is smaller than the previously known distance, update it. ",
            "3. Move to the unvisited node with the smallest tentative distance and repeat the process of updating distances for its neighbors. ",
            "4. Continue this process until all nodes are visited, ensuring that the shortest path to each node is found.     ",
            "Example: Consider a graph where nodes represent cities and edges represent the distance between them. Starting from city A, Dijkstra's Algorithm will calculate the shortest distance to all other cities, considering the sum of edge weights in each step. ",
            "Time Complexity: The time complexity of Dijkstra's Algorithm is O((V + E) log V), where V is the number of vertices and E is the number of edges in the graph.",
            "Limitations: Dijkstra's Algorithm does not work with graphs that have negative edge weights because the greedy approach might not always lead to the optimal solution.",
        ],
        writtenBy: "UniAlgo Owner",
    },
    {
        id: 3,
        title: "Huffman Coding",
        subtitle: "How Huffman Coding works?",
        image: "https://unialgo.web.app/asset/logo.png",
        description: [
            "Suppose there is a problem in which you have wood pieces of different length (a1,a2,a3,...,an) and you want to merge to make a new stick. But there is a constraint, that is in each operation the cost of merging is the sum of lengths of 2 sticks and you need to minimize to overall cost of merging.      So we need an to optimally take the sticks according to their lengths. ",
            "Suppose we are given a=1, a2=4, a3=9, a4=11. Now, let's take a2 and a3 and merge them into one so their sum = 4+9=13 , and cost = 13. Then let us take a1 , since previous sticks were merged to form a stick of length 13, let us take the previous stick and a1 = 1, so new sum = 1+13=14, and cost += 14 means cost = 13 + 14 = 27. Then we are left with a4 so new stick length sum = 14+11 = 25. And total cost = becomes 27 + 25 = 52.      But instead if we had taken first a1 and a2 , then a1+a2=5, then cost = 5. Then going in sorted order we sum the next two smallest length which are currently 5 and 9, so length = 14, and cost = 5+15 = 19. Now we are left with two sticks of length 14 and 11 , so summing them the length becomes 25 and cost = 25 + 19 = 44.      ",
            "So, if we observe that the greater the number we take the earlier the cost increases. Because every time we add new length to current length, the big element would repeat every time. So this is the intuition behind taking the smallest elements first which ensures that we are minimizing the immediate cost of each merge operation.      ",
            "Now this is actually the concept behind actual Huffman Coding which is a way to compress a string into a segment of 0s and 1s and of the smallest length so that there would be less chance of compressed string to exceed the integer or long range.     Can you find how? ",
            "A hint is to find the bits representation for each unique character, and note none of the character's bit representation should be a prefix of another !",
        ],
        writtenBy: "UniAlgo Owner",
    },
    {
        id: 4,
        title: "Breadth First Search",
        subtitle: "Time Complexity of Breadth First Search",
        image: "https://unialgo.web.app/asset/logo.png",
        description: [
            "DFS is a graph traversal technique used to explore nodes and edges of a graph. It can be implemented using recursion or an explicit stack. The algorithm works by starting from a source node, marking it as visited, and then recursively visiting all its unvisited neighbors.",
            " 1. Looping Through Nodes: O(V)",
            "â€¢ When you start the DFS, you typically loop through  all the vertices in the graph. This is necessary to ensure that even disconnected components of the graph are visited.",
            "â€¢ For each node, you check if it has been visited.  This check is O(1) for each node, and since you do this for all V nodes, this part contributes O(V) to the time complexity.",
        ],
        writtenBy: "UniAlgo Owner",
    },
];

document.addEventListener("DOMContentLoaded", function () {
    let selectedNavItem = null;
    const navbar = document.getElementById("navbar");
    const articleTitle = document.querySelector("h2");
    const articleSubtitle = document.querySelector("h4");
    const articleImage = document.getElementById("article-image"); // For future use with custom image or user uploaded image
    const articleDescription = document.getElementById("article-description");
    const articleWrittenBy = document.getElementById("article-writtenBy");

    // Once an article is selected, the selected one should be highlighted and populated
    function articleSelect(id, articleItem) {
        const article = articles.filter((a) => a.id === id)[0];
        articleTitle.textContent = article.title;
        articleSubtitle.textContent = article.subtitle;
        // articleImage.src = article.image;
        articleDescription.innerHTML = "";
        article.description.forEach((paragraph) => {
            const p = document.createElement("p");
            p.textContent = paragraph;
            articleDescription.appendChild(p);
        });
        articleWrittenBy.textContent = "- " + article.writtenBy;
        if (selectedNavItem) {
            selectedNavItem.classList.remove("selected");
        }
        articleItem.classList.add("selected");
        selectedNavItem = articleItem;
    }

    articles.forEach((article) => {
        const articleItem = document.createElement("div");
        articleItem.classList.add("navbar-item");
        articleItem.textContent = article.title;
        navbar.appendChild(articleItem);
        articleItem.addEventListener("click", () =>
            articleSelect(article.id, articleItem)
        );
    });

    // For default article selection at first load
    if (articles.length > 0) {
        const firstArticle = articles[0];
        articleTitle.textContent = firstArticle.title;
        articleSubtitle.textContent = firstArticle.subtitle;
        // articleImage.src = firstArticle.image;
        articleDescription.innerHTML = "";
        firstArticle.description.forEach((paragraph) => {
            const p = document.createElement("p");
            p.textContent = paragraph;
            articleDescription.appendChild(p);
        });
        articleWrittenBy.textContent = "- " + firstArticle.writtenBy;
        const firstNavItem = navbar.firstChild;
        firstNavItem.classList.add("selected");
        selectedNavItem = firstNavItem;
    }
});
