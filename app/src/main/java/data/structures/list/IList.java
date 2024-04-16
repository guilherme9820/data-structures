package data.structures.list;

public interface IList<E> {
    public int size = 0;

    public void prepend(E value);

    public void append(E value);

    public IList<E> reverse();

    public boolean isEmpty();

    public E getHead();

    public E pop(int index);
}
