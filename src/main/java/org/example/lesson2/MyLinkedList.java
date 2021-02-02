package org.example.lesson2;

public interface MyLinkedList<T> {

    void insertFirst(T value);
    T removeFirst();

    boolean remove(T value);

    boolean contains(T value);

    int size();

    boolean isEmpty();

    void display();

    T getFirst();

    Node<T> getFirstElement();


    class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
