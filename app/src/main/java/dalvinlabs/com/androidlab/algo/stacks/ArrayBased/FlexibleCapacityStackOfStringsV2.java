package dalvinlabs.com.androidlab.algo.stacks.ArrayBased;

/**
 * 1. Stack increases and shrinks the array based upon following criteria.
 * 2. Increase size of array by 1 on every push.
 * 3. Decrease size of array by 1 on every pop.
 * <p>
 * Time Complexity for N items
 * <p>
 * Push first item = 1 array access for push + 0 array access for resize
 * Push second item = 1 array access for resize + 1 array access for push
 * Push third item =  2 array access for resize + 1 array access for push
 * Push fourth item = 3 array access for push + 1 array access for push
 * <p>
 * Hence for Nth item
 * N-1 array access for push + 1 array access for push
 * <p>
 * So For all N items
 * <p>
 * 1 + 2 + 3 + 4 ... N-1 + N => N(N+1)/2 => N^2
 * <p>
 * Pop Nth item = 1 array access for pop + (N-1) array access for pop
 * Pop (N-1)th item = 1 array access for pop + (N-2) array access for pop
 * <p>
 * Hence for N Pop items
 * <p>
 * 1 + 2 + 3 + 4 ... N-1 + N => N(N+1)/2 => N^2
 */

class FlexibleCapacityStackOfStringsV2 {

    private String[] mData = {};
    private int mPointer = -1;

    private void expand() {
        System.out.println("resize");
        String[] resizedData = new String[mData.length + 1];
        for (int i = 0; i < mData.length; i++) {
            resizedData[i] = mData[i];
        }
        mData = resizedData;
    }

    private void shrink() {
        System.out.println("shrink");
        String[] resizedData = new String[mData.length - 1];
        for (int i = 0; i < resizedData.length; i++) {
            resizedData[i] = mData[i];
        }
        mData = resizedData;
    }

    void push(String item) throws Exception {
        expand();
        mPointer += 1;
        mData[mPointer] = item;
    }

    String pop() throws Exception {
        if (mPointer <= -1) throw new Exception("Stack underflow");
        String popItem = mData[mPointer];
        mData[mPointer] = null;
        mPointer -= 1;
        shrink();
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

}
