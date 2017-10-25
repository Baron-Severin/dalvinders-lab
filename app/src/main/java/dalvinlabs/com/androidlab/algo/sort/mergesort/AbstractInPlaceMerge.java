package dalvinlabs.com.androidlab.algo.sort.mergesort;


/*
    1. Precondition both halves of array are sorted.
    2. Copy the array into aux array i.e. make a copy.
    3. Compare the entries of 2 halves and put into a copy.
    4. Time complexity : O(N)
    5. Space complexity: O(N), Needs extra array to copy the elements
*/

import dalvinlabs.com.androidlab.BuildConfig;

class AbstractInPlaceMerge {

    static boolean isSorted(int[] data, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (data[i] > data[i+1]) {
                System.out.println();
                System.out.println("Failed at index = " + i);
                return false;
            }
        }
        return true;
    }

    private static void print(int[] data, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            System.out.print(data[i] + ",");
        }
    }

    static int[] merge(int[] original, int startIndex, int midIndex, int endIndex) {

        // Precondition check first half and second half is sorted
        if (BuildConfig.DEBUG) {
            assert isSorted(original, startIndex, midIndex);
            assert isSorted(original, midIndex+1, endIndex);
        }

        System.out.println("First Half");
        print(original, startIndex, midIndex);
        System.out.println();
        System.out.println("Second half");
        print(original, midIndex + 1, endIndex);

        int[] copy = new int[endIndex - startIndex + 1];

        //TODO: Why do we need to copy the entire array
//        for (int i = startIndex; i <= endIndex; i++) {
//            copy[i] = original[i];
//        }

        System.out.println();
        System.out.println("Copy/Aux");
        print(copy, 0, copy.length-1);

        System.out.println();

        int i = startIndex; // Starting point of first half array
        int j = midIndex + 1; // Starting point of second half array
        System.out.println();
        for(int k = 0; k < copy.length; k++) {
            if (i > midIndex) {
                // first half has already been consumed, simply take value from second half, second half is sorted as per precondition
                copy[k] = original[j];
                j+=1;
            } else if (j > endIndex) {
                // second half has already been consumed, simply take value from first half, first half is sorted as per precondition
                copy[k] = original[i];
                i+=1;
            } else if (original[i] < original[j]){
                // first half value is lesser so take it
                copy[k] = original[i];
                i+=1;
            } else {
                // second half value is lesser so take it
                copy[k] = original[j];
                j+=1;
            }

            System.out.println("Iteration = " + k);
            print(copy, 0, k);
            System.out.println();
        }
        return copy;
    }
}
