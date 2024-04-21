package data.sorting.stable;

import java.util.List;

import java.lang.reflect.InvocationTargetException;
import data.utils.Clone;
import data.utils.Validation;

public class BubbleSort {

    public <E extends Comparable<E>> List<E> sort(List<E> iterable)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        List<E> newList = Clone.clone(iterable, true);

        int size = newList.size();

        E currentElement;
        E nextElement;
        for (int index = 0; index < size; index++) {
            for (int i = 0; i < size - index - 1; i++) {
                currentElement = newList.get(i);
                nextElement = newList.get(i + 1);
                if (Validation.isGreater(currentElement, nextElement)) {
                    newList.set(i, nextElement);
                    newList.set(i + 1, currentElement);
                }
            }
        }

        return newList;
    }
}
