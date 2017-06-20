package dalvinlabs.com.androidlab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dalvinlabs.com.androidlab.algo.search.BinarySearch;

public class AlgoUnitTest {
    int[] input = {4, 8, 12, 16, 20, 24, 28, 32, 36, 40};

    @Test
    public void testBinarySearchSuccess() {
        System.out.println("# # # # #");
        System.out.println("testBinarySearchSuccess()");
        System.out.println("isSearched = " + BinarySearch.search(input, 28));
        System.out.println("# # # # #");
    }

    @Test
    public void testBinarySearchFailed() {
        System.out.println("# # # # #");
        System.out.println("testBinarySearchFailed()");
        System.out.println("isSearched = " + BinarySearch.search(input, 27));
        System.out.println("# # # # #");
    }

    @Test
    public void testBinarySearchRecursionSuccess() {
        System.out.println("# # # # #");
        System.out.println("testBinarySearchRecursionSuccess()");
        System.out.println("isSearched = " + BinarySearch.searchRecursion(input, 28));
        System.out.println("# # # # #");
    }

    @Test
    public void testBinarySearchRecursionFailed() {
        System.out.println("# # # # #");
        System.out.println("testBinarySearchRecursionFailed()");
        System.out.println("isSearched = " + BinarySearch.searchRecursion(input, 27));
        System.out.println("# # # # #");
    }

    @Test
    public void testBinarySearchRecursionFinalSuccess() {
        System.out.println("# # # # #");
        System.out.println("testBinarySearchRecursionFinalSuccess()");
        System.out.println("isSearched = " + BinarySearch.searchRecursionFinal(input, 0, input.length-1, 28));
        System.out.println("# # # # #");
    }

    @Test
    public void testBinarySearchRecursionFinalFailed() {
        System.out.println("# # # # #");
        System.out.println("testBinarySearchRecursionFinalFailed()");
        System.out.println("isSearched = " + BinarySearch.searchRecursionFinal(input, 0, input.length-1, 27));
        System.out.println("# # # # #");
    }
}
