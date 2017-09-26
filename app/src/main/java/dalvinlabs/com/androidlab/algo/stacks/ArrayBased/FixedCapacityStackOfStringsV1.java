package dalvinlabs.com.androidlab.algo.stacks.ArrayBased;

/**
 * 1. Stacks are implemented by using arrays.
 * <p>
 * Time complexity
 * Push : 1
 * Pop : 1
 * <p>
 * Drawbacks.
 * 1. Fixed capacity.
 * 2. Client needs to provide capacity.
 */

class FixedCapacityStackOfStringsV1 {

    private int mCapacity;
    private String[] mData = null;
    private int mPointer = -1;

    FixedCapacityStackOfStringsV1(int capacity) {
        mCapacity = capacity;
        mData = new String[capacity];
    }

    void push(String item) throws Exception {
        if (mPointer >= mCapacity - 1) throw new Exception("Stack overflow");
        mPointer += 1;
        mData[mPointer] = item;
    }

    String pop() throws Exception {
        if (mPointer <= -1) throw new Exception("Stack underflow");
        String popItem = mData[mPointer];
        mData[mPointer] = null;
        mPointer -= 1;
        return popItem;
    }

    void print() {
        System.out.println("[STACK] Capacity = " + mCapacity);
        for (int i = mData.length - 1; i >= 0; i--) {
            System.out.print(mData[i]);
            if (i == mPointer) {
                System.out.print(" <-- ");
            }
            System.out.println();
        }
    }
}
