package org.example.lesson2;

public interface MyArrayList<T> {
        void add(T value);

        void insert(T value, int index);

        T get(int index);

        boolean remove(T value);
        T remove(int index);

        int indexOf(T value);

        default boolean contains(T value) {
            return indexOf(value) != -1;
        }

        int size();

        default boolean isEmpty() {
            return size() == 0;
        }

        void display();
}
