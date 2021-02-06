package org.example.lesson2;

import java.util.Arrays;

public class MyArrayListImpl<T extends Comparable<? super T>> implements Cloneable, MyArrayList<T> {
    private int size;
    private T[] arr;

    public MyArrayListImpl(int capacity) {
        this.arr = (T[]) new Comparable[capacity];
    }

    @Override
    public void add(T value) {
        checkAndIncreasing();
        arr[size++] = value;
    }

    private void checkAndIncreasing() {
        if (arr.length == size)
            arr = Arrays.copyOf(arr, newLength());
    }

    private int newLength() {
        return size > 0 ? size * 2 : 1;
    }

    @Override
    public void insert(T value, int index) {
        doInsert(value, index);
    }

    protected void doInsert(T value, int index) {
        checkAndIncreasing();
        if (index == size) {
            add(value);
        }
        else {
            checkIndex(index);
            if (size - index >= 0)  {
                System.arraycopy(arr, index, arr, index + 1, size - index);
            }
            arr[index] = value;
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex (index);
        return arr[index];
    }

    private void checkIndex(int index) {
        if (index>size || index<0) throw new ArrayIndexOutOfBoundsException();

    }

    @Override
    public boolean remove(T value) {
        return false;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = arr[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(arr, index + 1, arr, index, size - 1 - index);
        }
        arr[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(arr[i]);
            sb.append(", ");
        }
        if (size > 0) {
            sb.append(arr[size - 1]);
        }
        sb.append("]");
        return sb.toString();
    }
}
