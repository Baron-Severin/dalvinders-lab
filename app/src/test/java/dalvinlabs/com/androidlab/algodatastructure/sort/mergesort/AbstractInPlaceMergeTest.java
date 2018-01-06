package dalvinlabs.com.androidlab.algodatastructure.sort.mergesort;


import org.junit.Assert;
import org.junit.Test;

public class AbstractInPlaceMergeTest {

    private int[] data = {5,25,45,65,85, 15,35,55,75,95};

    @Test
    public void given_sortedHalves_whenSort_thenReceiveSortedResult() {
        int[] result = AbstractInPlaceMerge.merge(data, 0, 4, 9);
        System.out.println();
        System.out.println("Result");
        for (int i = 0; i < result.length; i ++) {
            System.out.print(result[i] + ",");
        }
        Assert.assertTrue(AbstractInPlaceMerge.isSorted(result, 0, result.length - 1));
        System.out.println();
        System.out.println("--- END ---");
    }

    @Test
    public void given_sortedHalves_whenSortWithSubsetIndices_1_thenReceiveSortedResult() {
        int[] result = AbstractInPlaceMerge.merge(data, 1, 4, 8);
        System.out.println();
        System.out.println("Result");
        for (int i = 0; i < result.length; i ++) {
            System.out.print(result[i] + ",");
        }
        Assert.assertTrue(AbstractInPlaceMerge.isSorted(result, 0,result.length - 1));
        System.out.println();
        System.out.println("--- END ---");
    }

    @Test
    public void given_sortedHalves_whenSortWithSubsetIndices_2_thenReceiveSortedResult() {
        int[] result = AbstractInPlaceMerge.merge(data, 3, 4, 6);
        System.out.println();
        System.out.println("Result");
        for (int i = 0; i < result.length; i ++) {
            System.out.print(result[i] + ",");
        }
        Assert.assertTrue(AbstractInPlaceMerge.isSorted(result, 0,result.length - 1));
        System.out.println("--- END ---");
        System.out.println();
    }
}
