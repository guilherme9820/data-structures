package data.structures.binaryTree;

import data.structures.util.Node;

public class BinaryTree<E extends Comparable<E>> implements IBinaryTree<E> {

    private Node<E> root = null;

    public Node<E> getRoot() {
        return this.root;
    }

    private Node<E> insertRecursive(E value, Node<E> node) {

        if (node == null) {
            node = new Node<E>(value);
        } else if (value.compareTo(node.value) > 0) {
            node.next = this.insertRecursive(value, node.next);
        } else if (value.compareTo(node.value) < 0) {
            node.previous = this.insertRecursive(value, node.previous);
        }

        return node;

    }

    @Override
    public boolean insert(E value) {
        if (this.contains(value)) {
            return false;
        }
        this.root = this.insertRecursive(value, this.root);
        return true;
    }

    private E findSmallestValue(Node<E> node) {
        return node.previous == null ? node.value : this.findSmallestValue(node.previous);
    }

    private Node<E> deleteRecursive(E value, Node<E> node) {
        if (node == null)
            return null;

        if (value.compareTo(node.value) < 0) {
            node.previous = this.deleteRecursive(value, node.previous);
            return node;
        } else if (value.compareTo(node.value) > 0) {
            node.next = this.deleteRecursive(value, node.next);
            return node;
        }

        if (node.next == null && node.previous == null) {
            return null;
        }

        if (node.next == null) {
            return node.previous;
        }
        if (node.previous == null) {
            return node.next;
        }

        E smallest = this.findSmallestValue(node.next);
        node.value = smallest;
        node.next = this.deleteRecursive(smallest, node.next);

        return node;
    }

    @Override
    public boolean delete(E value) {
        if (this.contains(value)) {
            this.root = this.deleteRecursive(value, this.root);
            return true;
        }
        return false;
    }

    private void traverseRecursive(SearchMode mode, Node<E> node) {

        if (node == null)
            return;

        switch (mode) {
            case PREORDER:
                System.out.println(node.value);
                this.traverseRecursive(mode, node.previous);
                this.traverseRecursive(mode, node.next);
                break;
            case INORDER:
                this.traverseRecursive(mode, node.previous);
                System.out.println(node.value);
                this.traverseRecursive(mode, node.next);
                break;
            default:
                this.traverseRecursive(mode, node.previous);
                this.traverseRecursive(mode, node.next);
                System.out.println(node.value);
                break;
        }

    }

    @Override
    public void traverse(SearchMode mode) {
        this.traverseRecursive(mode, this.root);
    }

    private E searchRecursive(E value, Node<E> node) {
        if (node == null)
            return null;

        if (value.compareTo(node.value) < 0) {
            return this.searchRecursive(value, node.previous);
        } else if (value.compareTo(node.value) > 0) {
            return this.searchRecursive(value, node.next);
        }

        return node.value;
    }

    @Override
    public E search(E value) {
        return this.searchRecursive(value, this.root);
    }

    public boolean contains(E value) {
        E result = this.search(value);

        if (result == null) {
            return false;
        }
        return true;
    }

}
