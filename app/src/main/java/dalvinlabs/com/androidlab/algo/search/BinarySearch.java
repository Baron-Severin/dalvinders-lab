package dalvinlabs.com.androidlab.algo.search;

import java.util.Arrays;

/**
 * 1. Input must be in sorted order
 * 2. Divide the input in 2 parts
 * 3. Compare the mid
 * 4. If key > mid then process right side part
 * 5. If key < mid then process left side part
 * 6. If key == mid then key has been searched successfully
 * 7. Complexity = LOG(N)
 */

public class BinarySearch {

    public static boolean search(int[] input, int key) {
        int low = 0;
        int high = input.length - 1;
        int mid;
        System.out.println("input = " + Arrays.toString(Arrays.copyOfRange(input, low, input.length)));
        System.out.println("key = " + key);
        System.out.println("- - - - -");
        while (low <= high) {
            System.out.println("low index = " + low + " , value = " + input[low]);
            System.out.println("high index = " + high + " , value = " + input[high]);
            mid = (low + high) / 2;
            System.out.println("mid index = " + mid + " , value = " + input[mid]);
            System.out.println("- - - - -");
            if (key > input[mid]) {
                low = mid + 1;
            } else if (key < input[mid]) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /*
        Works
        Drawback : In every recursion creates a new copy of array, hence unnecessary memory allocation.
     */
    public static boolean searchRecursion(int[] input, int key) {
        int low = 0;
        System.out.println("input = " + Arrays.toString(Arrays.copyOfRange(input, low, input.length)));
        System.out.println("key = " + key);
        if (input.length > 0) {
            int high = input.length - 1;
            int mid = (low + high) / 2;
            System.out.println("low index = " + low + " , value = " + input[low]);
            System.out.println("high index = " + high + " , value = " + input[high]);
            System.out.println("mid index = " + mid + " , value = " + input[mid]);
            System.out.println("- - - - -");
            if (key > input[mid]) {
                return searchRecursion(Arrays.copyOfRange(input, mid + 1, input.length), key);
            } else if (key < input[mid]) {
                return searchRecursion(Arrays.copyOfRange(input, low, mid), key);
            } else {
                System.out.println("Key is searched");
                return true;
            }
        } else {
            System.out.println("Key is NOT searched");
            return false;
        }
    }

    public static boolean searchRecursionFinal(int[] input, int low, int high, int key) {
        int mid;
        System.out.println("input = " + Arrays.toString(Arrays.copyOfRange(input, low, high + 1)));
        System.out.println("key = " + key);
        System.out.println("low index = " + low + " , value = " + input[low]);
        System.out.println("high index = " + high + " , value = " + input[high]);
        System.out.println("- - - - -");
        if (low <= high) {
            mid = (low + high) / 2;
            System.out.println("mid index = " + mid + " , value = " + input[mid]);
            System.out.println("- - - - -");
            if (key > input[mid]) {
                low = mid + 1;
                return searchRecursionFinal(input, low, high, key);
            } else if (key < input[mid]) {
                high = mid - 1;
                return searchRecursionFinal(input, low, high, key);
            } else {
                return true;
            }
        }
        return false;
    }

    //TODO: Is it possible to figure out through code
    private static void complexity(int operations, int size) {
        System.out.println("operations = " + operations +  " , size = " + size);
        String complexity = "Unknown";
        if (operations == 1) {
            complexity = "1";
        } else if (operations <= logBase2(size)) {
            complexity = "Log(N)";
        } else if (operations == size) {
            complexity = "N";
        } else if (operations <= size * logBase2(size)) {
            complexity = "N Log(N)";
        } else if (operations <= size * size) {
            complexity = "N^2";
        }
        System.out.println("Complexity = " + complexity);
    }

    private static double logBase2(int value) {
        return Math.log10(value)/Math.log10(2d);
    }


}
