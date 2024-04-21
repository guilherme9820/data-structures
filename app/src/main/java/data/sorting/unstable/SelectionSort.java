package data.sorting.unstable;

import java.lang.reflect.InvocationTargetException;
import data.utils.Clone;
import data.utils.Validation;

import java.util.List;

public class SelectionSort {

    public <E extends Comparable<E>> List<E> sort(List<E> iterable)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        List<E> newList = Clone.clone(iterable, true);

        int size = newList.size();

        E currentElement;
        E smallestElement;
        for (int outer = 0; outer < size; outer++) {
            smallestElement = newList.get(outer);
            for (int inner = outer + 1; inner < size; inner++) {
                currentElement = newList.get(inner);
                if (Validation.isGreater(smallestElement, currentElement)) {
                    newList.set(outer, currentElement);
                    newList.set(inner, smallestElement);
                    smallestElement = currentElement;
                }
            }
        }

        return newList;

    }
}
