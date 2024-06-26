import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MaxHeap.
 *
 * @author Jinseo Lee
 * @version 1.0
 * @userid jlee4223
 * @GTID 903950086
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MaxHeap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     *
     * The backingArray should have capacity 2n + 1 (including the empty 0
     * index) where n is the number of data in the passed in ArrayList (not
     * INITIAL_CAPACITY). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * Consider how to most efficiently determine if the list contains null data.
     * 
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Input");
        }
        backingArray = (T[]) new Comparable[(data.size() * 2) + 1];
        //copying data to the backingArray
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == null) {
                throw new IllegalArgumentException("Null Data");
            }
            backingArray[i + 1] = data.get(i);
        }
        size = data.size();
        for (int i = size / 2; i > 0; i--) {
            downHeap(i);
        }
    }

    /**
     *
     * @param idx the index to proceed downheap method
     */
    private void downHeap(int idx) {
        int left = 2 * idx;
        int right = (2 * idx) + 1;
        int largest = idx;

        if (left <= size && backingArray[left].compareTo(backingArray[largest]) > 0) {
            largest = left;
        }
        if (right <= size && backingArray[right].compareTo(backingArray[largest]) > 0) {
            largest = right;
        }

        if (largest != idx) {
            swapData(idx, largest);
            downHeap(largest);
        }
    }

    /**
     *
     * @param a the internal index of the backingArray to swap
     * @param b the second index to swap
     */
    private void swapData(int a, int b) {
        T temp = backingArray[b];
        backingArray[b] = backingArray[a];
        backingArray[a] = temp;
    }
    /**
     * Adds the data to the heap.
     *
     * If sufficient space is not available in the backing array (the backing
     * array is full except for index 0), resize it to double the current
     * length.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Input");
        }
        if (size + 1 == backingArray.length) {
            T[] newArr = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 1; i < size + 1; i++) {
                newArr[i] = backingArray[i];
            }
            backingArray = newArr;
        }
        backingArray[size + 1] = data;
        upHeap(size + 1);
        size++;
    }

    /**
     *
     * @param idx index of the data to perform upheap
     */
    private void upHeap(int idx) {
        while (idx > 1) {
            int parent = idx / 2;

            if (backingArray[idx].compareTo(backingArray[parent]) > 0) {
                swapData(idx, parent);
                idx = parent;
            } else {
                break;
            }
        }
    }


    /**
     * Removes and returns the root of the heap.
     *
     * Do not shrink the backing array.
     *
     * Replace any unused spots in the array with null.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("The heap is empty");
        }
        T out = backingArray[1];
        swapData(1, size);
        backingArray[size] = null;
        size--;
        downHeap(1);
        return out;
    }

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("The heap is empty");
        }
        return backingArray[1];
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
