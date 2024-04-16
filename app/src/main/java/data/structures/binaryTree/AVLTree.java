package data.structures.binaryTree;

import data.structures.util.Node;

public class AVLTree<E extends Comparable<E>> implements IBinaryTree<E> {

    public static enum Rotation {
        LEFT,
        RIGHT
    }

    private Node<E> root = null;
    private int size = 0;

    public int getSize() {
        return this.size;
    }

    private int getBranchHeight(Node<E> branchRoot) {
        return branchRoot == null ? 0 : branchRoot.height;
    }

    private int getBranchBalance(Node<E> branchRoot) {
        return branchRoot == null ? 0 : branchRoot.balanceFactor;
    }

    private void updateBranch(Node<E> branchRoot) {
        int leftHeight = this.getBranchHeight(branchRoot.previous);
        int rightHeight = this.getBranchHeight(branchRoot.next);

        branchRoot.balanceFactor = leftHeight - rightHeight;
        branchRoot.height = 1 + Math.max(leftHeight, rightHeight);
    }

    private Node<E> rotate(Node<E> node, Rotation mode) {

        Node<E> leadingNode;
        if (mode == Rotation.LEFT) {
            leadingNode = node.next;
            node.next = leadingNode.previous;
            leadingNode.previous = node;
        } else {
            leadingNode = node.previous;
            node.previous = leadingNode.next;
            leadingNode.next = node;
        }
        this.updateBranch(node);
        this.updateBranch(leadingNode);
        return leadingNode;
    }

    private Node<E> balance(Node<E> node) {

        int currentBalance = this.getBranchBalance(node);

        if (currentBalance > 1) {
            // Tem mais nós à esquerda
            int leftBalance = this.getBranchBalance(node.previous);
            if (leftBalance < 0) {
                // O nó imediatamente à esquerda do nó atual está com mais nós à sua direita
                node.previous = this.rotate(node.previous, Rotation.LEFT);
            } else if (leftBalance > 0) {
                return this.rotate(node, Rotation.RIGHT);
            }
        } else if (currentBalance < -1) {
            // Tem mais nós à direita
            int rightBalance = this.getBranchBalance(node.next);
            if (rightBalance > 0) {
                // O nó imediatamente à direita do nó atual está com mais nós à sua esquerda
                node.next = this.rotate(node.next, Rotation.RIGHT);
            } else if (rightBalance < 0) {
                return this.rotate(node, Rotation.LEFT);
            }
        }

        return node;
    }

    private Node<E> insertRecursive(E value, Node<E> node) {
        if (node == null)
            return new Node<E>(value);

        if (value.compareTo(node.value) < 0) {
            node.previous = this.insertRecursive(value, node.previous);
        } else if (value.compareTo(node.value) > 0) {
            node.next = this.insertRecursive(value, node.next);
        }
        this.updateBranch(node);
        return this.balance(node);
    }

    @Override
    public boolean insert(E value) {
        if (this.contains(value)) {
            return false;
        }
        this.root = this.insertRecursive(value, this.root);
        this.size += 1;
        return true;
    }

    private void traverseRecursive(SearchMode mode, Node<E> node) {
        if (node == null) {
            return;
        }

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
        if (node == null) {
            return null;
        }

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

    @Override
    public boolean contains(E value) {
        E result = this.search(value);
        if (result == null) {
            return false;
        }

        return true;
    }

    private E findBranchSmallestValue(Node<E> node) {
        return node.previous == null ? node.value : this.findBranchSmallestValue(node.previous);
    }

    private Node<E> deleteRecursive(E value, Node<E> node) {
        if (node == null) {
            return null;
        }

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

        E smallest = this.findBranchSmallestValue(node.next);
        node.value = smallest;
        node.next = this.deleteRecursive(smallest, node.next);
        this.updateBranch(node);

        return this.balance(node);
    }

    @Override
    public boolean delete(E value) {
        if (this.contains(value)) {
            this.root = this.deleteRecursive(value, this.root);
            return true;
        }
        return false;
    }

}
