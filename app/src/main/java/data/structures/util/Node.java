package data.structures.util;

public class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
    public Node<E> next;
    public Node<E> previous;
    public int height;
    public int balanceFactor;
    public E value;

    public Node() {
        this.value = null;
        this.initNode();
    }

    public Node(E value) {
        this.value = value;
        this.initNode();
    }

    private void initNode() {
        this.next = null;
        this.previous = null;
        this.height = 1;
        this.balanceFactor = 0;
    }

    @Override
    public int compareTo(Node<E> node) {
        return this.value.compareTo(node.value);
    }
}
