package org.example.lesson2;

public class MyMyLinkedListImpl<T> implements MyLinkedList<T> {
    protected int size;
    protected Node<T> firstElement;

    @Override
    public void insertFirst(T value) {
        Node<T> newNode = new Node<>(value, firstElement);
        firstElement = newNode;
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Node<T> removedElement = firstElement;
        T data = removedElement.item;

        firstElement = firstElement.next;
        removedElement.next = null;
        removedElement.item = null;

        size--;
        return data;
    }

    @Override
    public boolean remove(T value) {
        Node<T> current = firstElement;
        Node<T> previous = null;
        while (current != null) {
            if (current.item.equals(value)) {
                break;
            }
            previous = current;
            current = current.next;
        }

        if (current == null) {
            return false;
        }

        if (current == firstElement) {
            firstElement = firstElement.next;
        }
        else {
            previous.next = current.next;
        }

        current.next = null;
        current.item = null;

        size--;
        return true;
    }

    @Override
    public boolean contains(T value) {
        Node<T> current = firstElement;
        while (current != null) {
            if (current.item.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void display() {
        System.out.println("-----------");
        Node<T> current = firstElement;
        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }
        System.out.println("-----------");
    }

    @Override
    public T getFirst() {
        return firstElement.item;
    }

    @Override
    public Node<T> getFirstElement() {
        return firstElement;
    }
}
