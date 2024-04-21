package data.sorting.unstable;

import java.lang.reflect.InvocationTargetException;
import data.utils.Clone;
import java.util.List;

public class SelectionSort {

    public <S extends List<E>, E extends Comparable<E>> S sort(S iterable)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        S newList = Clone.clone(iterable, true);

        int size = newList.size();

        E currentElement;
        E smallestElement;
        for (int outer = 0; outer < size; outer++) {
            smallestElement = newList.get(outer);
            for (int inner = outer + 1; inner < size; inner++) {
                currentElement = newList.get(inner);
                if ((smallestElement.compareTo(currentElement)) > 0) {
                    newList.set(outer, currentElement);
                    newList.set(inner, smallestElement);
                    smallestElement = currentElement;
                }
            }
        }

        return newList;

    }
}
