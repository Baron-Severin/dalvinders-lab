package dalvinlabs.com.androidlab.algodatastructure.sort.mergesort;


import junit.framework.Assert;

import org.junit.Test;

import java.util.Arrays;

public class MergeSortTest {

    @Test
    public void mergeSortTest() {
        char[] input = {'D', 'B', 'C', 'A', 'F', 'G', 'E', 'I', 'H', 'K', 'J', 'L', 'M'};
        char[] expected = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M'};

        //char[] input = {'B', 'A'};
        //char[] expected = {'A', 'B'};


        char[] output = MergeSort.mergeSort(input);
        if (expected.length != output.length) {
            Assert.fail("Expected and Output length mismatch");
        }
        Assert.assertTrue("Output is not as expected", Arrays.equals(output, expected));
    }
}
