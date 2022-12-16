package com.aston.app;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Stanislau Trebnikau
 */
public class MyList<T> {

    /**
     * Number of elements of MyList
     */
    private int size;


    /**
     * The main array that stores the elements of the collection. It expands if necessary.
     */
    private T[] elementData;

    /**
     * Default initial capacity
     */
    private final int DEFAULT_CAPACITY = 10;

    /**
     * Constructs an empty list with default capacity equals 10
     */
    public MyList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initCapacity the initial capacity of the list
     * @throws IllegalArgumentException - if param initial capacity is negative
     */
    public MyList(int initCapacity) {
        if (initCapacity > 0) {
            this.elementData = (T[]) new Object[initCapacity];
        } else if (initCapacity == 0) {
            this.elementData = (T[]) new Object[]{};
        } else {
            throw new IllegalArgumentException(new StringBuilder("Wrong initCapacity: ")
                    .append(initCapacity).toString());
        }
    }

    /**
     * Trims the capacity of this ArrayList instance to be the list's current size. An application can use this operation to minimize the storage of an ArrayList instance.
     */
    public void trimToSize() {
        if (size < elementData.length) {
            elementData = Arrays.copyOf(elementData, size);
        }
    }

    /**
     * Returns the number of elements in this list
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains no elements
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return true if this list contains the specified element
     *
     * @param o search element
     * @return true if this list contains the specified element
     */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }


    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     *
     * @return an array containing all of the elements in this list in proper sequence
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * Add the specified element to the end of this list.
     *
     * @param t element to be appended to this list
     * @return true everything good
     */
    public boolean add(T t) {
        ensureCapacity(size + 1);
        elementData[size++] = t;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, T element) {
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - elementData.length > 0) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.
     *
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    removeWithoutCheckAndReturn(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elementData[i].equals(o)) {
                    removeWithoutCheckAndReturn(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException
     */
    public T remove(int index) {
        rangeCheck(index);
        T removed = elementData[index];
        int numberOfItemToCopy = size - index - 1;
        if (numberOfItemToCopy > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numberOfItemToCopy);
        }
        elementData[--size] = null;
        return removed;
    }

    private void removeWithoutCheckAndReturn(int index) {
        int numberOfItemToCopy = size - index - 1;
        if (numberOfItemToCopy > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numberOfItemToCopy);
        }
        elementData[--size] = null;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException(new StringBuilder("Entered index: ")
                    .append(index)
                    .append(", current Size: ")
                    .append(size).toString());
    }


    /**
     * Remove all elements from this list
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException
     */
    public T get(int index) {
        rangeCheck(index);
        return elementData[index];
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException
     */
    public T set(int index, T element) {
        rangeCheck(index);
        T oldElement = elementData[index];
        elementData[index] = element;
        return oldElement;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o search element
     * @return Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Sorts the collection by comparator
     *
     * @param c comparator
     */
    public void sort(Comparator<? super T> c) {
        Arrays.sort(elementData, 0, size, c);
    }

    /**
     * This method participate only in tests
     *
     * @return elementData length
     */
    private int getElementDataLength() {
        return elementData.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}
