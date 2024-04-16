package data.structures.binaryTree;

public interface IBinaryTree<E> {

    public static enum SearchMode {
        PREORDER,
        INORDER,
        POSTORDER
    }

    public boolean insert(E value);

    public boolean delete(E value);

    public void traverse(SearchMode mode);

    public E search(E value);

    public boolean contains(E value);

}
