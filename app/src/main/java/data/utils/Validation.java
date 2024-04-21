package data.utils;

public class Validation {

    public static <E extends Comparable<E>> boolean isLess(E element1, E element2) {
        return element1.compareTo(element2) < 0;
    }

    public static <E extends Comparable<E>> boolean isGreater(E element1, E element2) {
        return element1.compareTo(element2) > 0;
    }

    public static <E extends Comparable<E>> boolean isEqual(E element1, E element2) {
        return element1.compareTo(element2) == 0;
    }

    public static <E extends Comparable<E>> boolean isLessOrEqual(E element1, E element2) {
        return isLess(element1, element2) || isEqual(element1, element2);
    }

    public static <E extends Comparable<E>> boolean isGreaterOrEqual(E element1, E element2) {
        return isGreater(element1, element2) || isEqual(element1, element2);
    }

}
