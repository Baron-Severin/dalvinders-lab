package dalvinlabs.com.androidlab.algodatastructure.priorityqueue;

/**
    Minimum value have higher priority in this implementation.
 */
public class PriorityQueue {

    int[] array;
    private int n;

    PriorityQueue(int size) {
        array = new int[size];
    }

    /**
     * New item data starts from the end of loop and keep traversing to find a spot
     * Logic is written in a way the (n-1) always be the front of queue
     * This avoids the shuffling of elements in array during deletion.
     * Time = O(N)
     */
    void insert(int data) {
        if (n == 0) {
            // Queue is empty
            array[n++] = data;
        } else if (n == array.length){
            // Queue is full
            System.out.println("Queue is full");
        } else {
            // Loop to shift the array data 1 slot towards empty side
            int i = 0;
            for (i = n - 1; i >= 0; i--) {
                if (data > array[i]) {
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
    int remove() {
        if (n == 0) {
            System.out.println("Queue is empty");
            return -1;
        }
        return array[--n];
    }

    /**
     * Time = O(1)
     */
    void insertAlternate(int data) {
        if (n == array.length) {
            System.out.println("Queue is full");
            return;
        }
        array[n++] = data;
    }

    /**
     * Time = O(N)
     */
    int removeAlternate() {
        if (n == 0) {
            System.out.println("Queue is empty");
            return - 1;
        }
        int priorityItem = array[n-1];
        int priorityIndex = n-1;
        for (int i = n - 2; i >= 0; i--) {
            if (priorityItem > array[i]) {
                priorityItem = array[i];
                priorityIndex = i;
            }
        }
        if (priorityIndex == n - 1 ) {
            return array[--n];
        } else {
            array[priorityIndex] = array[--n];
            return priorityItem;
        }
    }

    void print() {
        System.out.println("Priority Queue");
        for (int i = array.length - 1; i >= 0 ; i--) {
            System.out.println(array[i]);
            System.out.println("--");
        }
    }


}
