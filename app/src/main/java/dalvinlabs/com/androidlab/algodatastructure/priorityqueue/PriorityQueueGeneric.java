package dalvinlabs.com.androidlab.algodatastructure.priorityqueue;

/**
    Minimum value have higher priority in this implementation.
 */
public class PriorityQueueGeneric<T> {

    private Object[] array;
    private int n;

    public PriorityQueueGeneric(int size) {
        array = new Object[size];
    }

    /**
     * New item data starts from the end of loop and keep traversing to find a spot
     * Logic is written in a way the (n-1) always be the front of queue
     * This avoids the shuffling of elements in array during deletion.
     * Time = O(N)
     */
    public void insert(Comparable<T> data) {
        if (n == 0) {
            // Queue is empty
            array[n++] = data;
        } else if (n == array.length){
            // Queue is full
            System.out.println("Queue is full");
        } else {
            // Loop to shift the array data 1 slot towards empty side
            int i = 0;
            T t;
            for (i = n - 1; i >= 0; i--) {
                t = (T)array[i];
                if (data.compareTo(t) > 0) {
                    // if data needs to further find the spot , move the one cell towards empty direction.
                    array[i + 1] = array[i];
                } else break;
            }
            // Set the data to vacant cell.
            array[i + 1] = data;
            n++;
        }
    }

    /**
     * Time = O(1)
     */
    public T remove() {
        if (n == 0) {
            System.out.println("Queue is empty");
            return null;
        }
        return (T)array[--n];
    }

    public int size() {
        return n;
    }

    public void print() {
        System.out.println("Priority Queue");
        for (int i = array.length - 1; i >= 0 ; i--) {
            System.out.println(array[i]);
            System.out.println("--");
        }
    }


}
