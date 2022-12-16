package com.aston.app;

import java.util.Comparator;

public interface MyList<T> {

    /**
     * Trims the capacity of this ArrayList instance to be the list's current size. An application can use this operation to minimize the storage of an ArrayList instance.
     */
    void trimToSize();

    /**
     * Returns the number of elements in this list
     *
     * @return the number of elements in this list
     */
    int size();

    /**
     * Returns true if this list contains no elements
     *
     * @return true if this list contains no elements
     */
    boolean isEmpty();

    /**
     * Return true if this list contains the specified element
     *
     * @param o search element
     * @return true if this list contains the specified element
     */
    boolean contains(Object o);


    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     *
     * @return an array containing all of the elements in this list in proper sequence
     */
    Object[] toArray();

    /**
     * Add the specified element to the end of this list.
     *
     * @param t element to be appended to this list
     * @return true everything good
     */
    boolean add(T t);

    /**
     * Inserts the specified element at the specified position in this list
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException
     */
    void add(int index, T element);


    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.
     *
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    boolean remove(Object o);

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException
     */
    T remove(int index);


    /**
     * Remove all elements from this list
     */
    void clear();

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException
     */
    T get(int index);

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException
     */
    T set(int index, T element);

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
    int indexOf(Object o);

    /**
     * Sorts the collection by comparator
     *
     * @param c comparator
     */
    void sort(Comparator<? super T> c);

    String toString();
}

