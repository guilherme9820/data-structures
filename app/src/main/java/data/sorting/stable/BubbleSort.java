package data.sorting.stable;

import java.util.List;

import java.lang.reflect.InvocationTargetException;
import data.utils.Clone;

public class BubbleSort {

    public <S extends List<E>, E extends Comparable<E>> S sort(S iterable)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        S newList = Clone.clone(iterable, true);

        int size = newList.size();

        E currentElement;
        E nextElement;
        for (int index = 0; index < size; index++) {
            for (int i = 0; i < size - index - 1; i++) {
                currentElement = newList.get(i);
                nextElement = newList.get(i + 1);
                if (currentElement.compareTo(nextElement) > 0) {
                    newList.set(i, nextElement);
                    newList.set(i + 1, currentElement);
                }
            }
        }

        return newList;
    }
}
