package data.structures.util;

public class Node<E> {
    public Node<E> next;
    public Node<E> previous;
    public int height;
    public int balanceFactor;
    public E value;

    public Node(E value) {
        this.value = value;
        this.next = null;
        this.previous = null;
        this.height = 1;
        this.balanceFactor = 0;
    }
}
