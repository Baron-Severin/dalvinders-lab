package dalvinlabs.com.androidlab.algodatastructure.stacks.ArrayBased;

/**
 * 1. Stack increase and shrink the array based upon following criteria.
 * 2. Double the size of array when it's FULL
 * 3. Halves the size of array when it's 25% FULL
 * 4. It solves the drawback for V3 rest of the logic is same.
 * <p>
 * Time complexity for N items
 * <p>
 * Push first item = 0 array access for resize + 1 array access for push, capacity = 1
 * Push second item = 1 array access for resize + 1 array access for push, capacity = 2
 * Push third item =  2 array access for resize + 1 array access for push, capacity = 4
 * Push fourth item = 0 array access for push + 1 array access for push, capacity = 4
 * Push fifth item = 4 array access for push + 1 array access for push, capacity = 8
 * <p>
 * Hence array access for resize
 * 1 + 2 + 4 + 8 ... K => 2^0 + 2^1 + 2^2 + 2^3 ... 2^K => 2^(K+1) - 1 => 2^K
 * <p>
 * Array access to push N items = N (1 per push)
 * <p>
 * So therefore total = N + 2^K => N (ignoring K i.e. cost of resizing the array).
 * <p>
 * Similarly time complexity to pop N items => N + 2^K => N (ignoring K i.e. cost of resizing the array).
 * <p>
 * Drawbacks
 * 1. Memory is allocated to array before it's being used.
 */

public class FlexibleCapacityStackOfStringsV4 {

    private String[] mData = new String[1];
    private int mPointer = -1;

    private void expand() {
        System.out.println("resize");
        // Double the array size
        String[] resizedData = new String[2 * mData.length];
        for (int i = 0; i < mData.length; i++) {
            resizedData[i] = mData[i];
        }
        mData = resizedData;
    }

    private void shrink() {
        System.out.println("shrink");
        // Halves the array size
        String[] resizedData = new String[mData.length / 2];
        for (int i = 0; i < resizedData.length; i++) {
            resizedData[i] = mData[i];
        }
        mData = resizedData;
    }

    public void push(String item) throws Exception {
        mPointer += 1;
        if (mPointer >= mData.length) {
            // Array is FULL
            expand();
        }
        mData[mPointer] = item;
    }

    public String pop() throws Exception {
        if (mPointer <= -1) throw new Exception("Stack underflow");
        String popItem = mData[mPointer];
        mData[mPointer] = null;
        if (mData.length > 1 && mPointer == mData.length / 4) {
            // Array is 25% FULL
            shrink();
        }
        mPointer -= 1;
        return popItem;
    }

    void print() {
        System.out.println("[STACK] Capacity = " + mData.length);
        for (int i = mData.length - 1; i >= 0; i--) {
            System.out.print(mData[i]);
            if (i == mPointer) {
                System.out.print(" <-- ");
            }
            System.out.println();
        }
    }

    int capacity() {
        return mData.length;
    }

}
