package dalvinlabs.com.androidlab.algodatastructure.sort.selection;

/**
 * 1. Selects/find minimum element in the array.
 * 2. Swap it with unsorted element in the array.
 * 3. After swapping find next minimum element and swap it with next unsorted element.
 * 4. Time complexity
 * 5. First loop is going to run for N times => O(N)
 * 6. Second loop is going to run (N-1) times for first iteration of First loop
 * 7. Second loop is going to run (N-2) times for second iteration of First loop
 * 8. Hence second loop runs for (n-1) + (n-2) + (n-3) - - - - 1 => (N^2 / 2)
 * 9. So total time taken => O(N) + O(N^2)/2 => O(N^2)
 *
 * Conclusion
 * 1. It's an in-place sorting i.e. does not need extra space for operation
 * 2. It's ok for small array but not efficient for large data set.
 */

public class SelectionSort {

    static char[] sort(char[] input) {
        System.out.println("input = " + input);
        int minimum;
        for (int i = 0; i < input.length; i++) {
            minimum = i;
            for (int j = i + 1; j < input.length - 1; j++) {
                if (input[j] < input[minimum]) {
                    minimum = j;
                }
            }
            if (minimum != i) {
                char temp = input[i];
                input[i] = input[minimum];
                input[minimum] = temp;
            }
        }
        return input;
    }
}
