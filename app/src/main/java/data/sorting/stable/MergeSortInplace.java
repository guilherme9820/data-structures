package data.sorting.stable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import data.utils.Clone;
import data.utils.Validation;

public class MergeSortInplace {

    private <E extends Comparable<E>> void merge(int startingLeftIndex, int startingRightIndex, int size,
            List<E> iterable) {
        // System.out.println("ITERABLE: " + iterable.toString());

        int leftIndex = startingLeftIndex;
        int rightIndex = startingRightIndex;

        E element;
        while (rightIndex < (startingLeftIndex + size) && leftIndex < rightIndex) {
            element = iterable.remove(rightIndex);
            if (Validation.isLess(element, iterable.get(leftIndex))) {
                iterable.add(leftIndex, element);
                // leftIndex += 1;
                rightIndex += 1;
            } else
                iterable.add(rightIndex, element);
            leftIndex += 1;
        }
        // System.out.println("MERGED: " + iterable.toString());
    }

    private <E extends Comparable<E>> void split(int lowerBound, int upperBound, List<E> iterable) {
        // System.out.println("INDICES: " + lowerBound + " " + upperBound);
        int size = upperBound - lowerBound + 1;
        // System.out.println("INPUT: " + iterable.toString());
        if (size == 1) {
            return;
        }

        int borderIndex = (upperBound + lowerBound) / 2;

        this.split(lowerBound, borderIndex, iterable);
        this.split(borderIndex + 1, upperBound, iterable);

        this.merge(lowerBound, borderIndex + 1, size, iterable);

    }

    public <E extends Comparable<E>> List<E> sort(List<E> iterable)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        List<E> sortedList = Clone.clone(iterable, true);

        this.split(0, iterable.size() - 1, sortedList);

        return sortedList;
    }

}
