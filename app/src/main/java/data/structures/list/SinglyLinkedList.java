package data.structures.list;

import javax.xml.bind.PropertyException;

import data.structures.util.Node;

public class SinglyLinkedList<E extends Comparable<E>> implements IList<E> {

    private Node<E> head = null;
    private Node<E> tail = null;
    public int size = 0;

    public void setSize(E value) throws PropertyException {
        throw new PropertyException("The list size cannot be modified externally.");
    }

    @Override
    public E getHead() {
        return this.head.value;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void prepend(E value) {
        Node<E> new_node = new Node<E>(value);

        new_node.next = this.head;

        if (this.tail == null)
            this.tail = new_node;
        this.head = new_node;
        this.size++;
    }

    @Override
    public void append(E value) {
        Node<E> new_node = new Node<E>(value);

        if (this.head == null)
            this.head = new_node;

        if (this.tail != null)
            this.tail.next = new_node;
        this.tail = new_node;
        this.size++;
    }

    @Override
    public E pop(int index) {
        Node<E> current = this.head;
        Node<E> previous = this.head;
        int currentIndex = 0;

        while (current != null) {

            if (currentIndex == index) {
                if (current == this.head) {
                    this.head = current.next;
                } else {
                    previous.next = current.next;
                }
                this.size--;
                return current.value;
            }
            previous = current;
            current = current.next;
            currentIndex++;
        }
        return null;
    }

    public void printList() {
        Node<E> current = this.head;

        System.out.print("[ ");
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println("]");
    }

    public SinglyLinkedList<E> reverse() {
        Node<E> current = this.head;
        SinglyLinkedList<E> reversedList = new SinglyLinkedList<>();

        while (current != null) {
            reversedList.prepend(current.value);
            current = current.next;
        }

        return reversedList;
    }

}