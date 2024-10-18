Dancing Links (Algorithm X) for Exact Cover Problems

Overview
This repository contains implementations of Donald Knuth's Algorithm X using the Dancing Links technique to solve exact cover problems. The algorithm is implemented in both C++ and Java.
Problem Description
The Exact Cover problem is a computational problem where, given a collection S of subsets of a set X, we need to find a subcollection S* of S such that each element in X is contained in exactly one subset in S*.
This problem has various applications, including:

Solving Sudoku puzzles
Pentomino tiling problems
N-Queens problem

Algorithm
The Dancing Links algorithm, also known as DLX (Dancing Links X), is an efficient implementation of Algorithm X, which uses backtracking to find all solutions to the exact cover problem. The key features of this algorithm are:

It uses a sparse matrix representation with doubly-linked lists.
It allows for efficient addition and removal of matrix rows and columns.
It's particularly well-suited for problems that can be expressed as exact cover problems.

Implementation
This repository includes two implementations:

C++ implementation in dancing_links.cpp
Java implementation in DancingLinks.java

Both implementations solve a simple exact cover problem as an example.
Usage
C++
Compile and run the C++ program:
bashCopyg++ -std=c++11 dancing_links.cpp -o dancing_links
./dancing_links
Java
Compile and run the Java program:
bashCopyjavac DancingLinks.java
java DancingLinks
Example Problem
The implementations solve the following exact cover problem:
Copy{1, 0, 0, 1, 0, 0, 1},
{1, 0, 0, 1, 0, 0, 0},
{0, 0, 0, 1, 1, 0, 1},
{0, 0, 1, 0, 1, 1, 0},
{0, 1, 1, 0, 0, 1, 1},
{0, 1, 0, 0, 0, 0, 1}
The solution found by the algorithm is:
CopySolution rows: 1 3 5
This means that rows 1, 3, and 5 of the original matrix form an exact cover.
Extending the Implementation
To use this implementation for other exact cover problems (like Sudoku):

Create a matrix representation of your problem.
Replace the example matrix in the main() function with your problem matrix.
Interpret the solution according to your problem's constraints.

References

Knuth, Donald E. "Dancing Links." arXiv preprint cs/0011047 (2000).