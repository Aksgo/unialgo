import java.util.Arrays;

public class MergeSort {

    // Function to sort an array using Merge Sort
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return; // Base case: an array of size 0 or 1 is already sorted
        }

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);   // Left half of the array
        int[] right = Arrays.copyOfRange(arr, mid, arr.length); // Right half of the array

        mergeSort(left);   // Recursively sort the left half
        mergeSort(right);  // Recursively sort the right half

        merge(arr, left, right);  // Merge the sorted halves
    }

    // Helper function to merge two sorted arrays
    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Merge the two arrays into the original array
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        // Copy any remaining elements from the left array (if any)
        while (i < left.length) {
            arr[k++] = left[i++];
        }

        // Copy any remaining elements from the right array (if any)
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // Function to print the array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7};
        
        System.out.println("Original Array:");
        printArray(array);

        mergeSort(array);

        System.out.println("Sorted Array:");
        printArray(array);
    }
}

/*
    Original Array:
    12 11 13 5 6 7 
    Sorted Array:
    5 6 7 11 12 13 
 */
