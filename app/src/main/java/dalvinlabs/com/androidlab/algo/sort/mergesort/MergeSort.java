package dalvinlabs.com.androidlab.algo.sort.mergesort;


/**
 * 1. Formula to find mid between 2 indices = lowIndex + (highIndex - lowIndex)/2
 * 2. First sort the array recursively and apply @see{@link AbstractInPlaceMerge}
 * 3. Complexity for merge function : O(N)
 * TODO: If sort method is getting called N times including all recursive calls, shouldn't it be O(N) then total should be O(N) * O(N) instead of O(NlogN)
 * E.g.
 * 0 1 2 3 4 5 6 7 8 (Index)
 * D B C A F G E I H (Input)
 * A B C D E F G H I (Output)
 *
 * low = 0
 * high = 8
 * mid = 4
 * left = D B C A F
 *      low = 0
 *      high = 4
 *      mid = 2
 *      left = D B C
 *          low = 0
 *          high = 2
 *          mid = 1
 *          left = D B
 *              low = 0
 *              high = 1
 *              mid = 0
 *              left = D
 *                  low = 0
 *                  high = 0
 *                  if (low == high) then there's only 1 element in the array i.e. it's sorted
 *
 *              right = B
 *                  low = 0
 *                  high = 0
 *                  if (low == high) then there's only 1 element in the array i.e. it's sorted
 *
 *              merge 2 sorted arrays i.e. left and right
 *
 *          right = C
 *
 *      right = A F
 *
 *
 *
 * right = G E I H
 *
 *
 *
 */
public class MergeSort {

    static int depth = 0;
    static int merge = 0;

    private static void print(char[] data, int lowIndex, int highIndex) {
        for (int i = lowIndex; i <= highIndex; i++) {
            System.out.print(data[i] + " ");
        }
        //System.out.println("\nlowIndex = " + lowIndex + " , highIndex = " + highIndex);
        System.out.println();
    }

    /*
        1. Recursively divide array until there's only 1 element left.
        2. Then merge 2 single item arrays by comparing
     */
    private static void sort(char[] input, char[] aux, int lowIndex, int highIndex) {
        //System.out.println("INPUT");
        print(input, lowIndex, highIndex);
        if (highIndex == lowIndex) {
            System.out.println("RETURN");
            System.out.println("Depth = " + depth);
            return;
        }
        int mid = lowIndex + (highIndex - lowIndex)/2;
        //System.out.println("mid = " + mid);
        sort(input, aux, lowIndex, mid);
        System.out.println("- - - - -");
        sort(input, aux, mid+1, highIndex);
        // We have got 2 arrays with single items only, so compare and merge them
        merge(input, aux, lowIndex, highIndex, mid);
        //System.out.println("OUTPUT");
        print(input, lowIndex, highIndex);
    }

    /*
        Complexity O(N)
    */
    private static void merge(char[] input, char[] aux, int lowIndex, int highIndex, int mid) {
        int i = lowIndex;
        int j = mid + 1;

        // Copy the elements into aux
        for (int index = lowIndex; index <= highIndex; index++) {
            aux[index] = input[index];
        }

        for (int k = lowIndex; k <= highIndex; k++) {
            if (i > mid) {
                input[k] = aux[j++];
            } else if (j > highIndex) {
                input[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                input[k] = aux[i++];
            } else {
                input[k] = aux[j++];
            }
        }
    }

    static char[] mergeSort(char[] input) {
        sort(input, input.clone(), 0, input.length - 1);
        System.out.println("Depth = " + depth);
        System.out.println("Merge = " + merge);
        return input;
    }
}
