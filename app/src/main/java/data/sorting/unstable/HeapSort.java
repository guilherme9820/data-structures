package data.sorting.unstable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import data.utils.Clone;
import data.utils.Validation;

public class HeapSort {

    private <E extends Comparable<E>> void swap(int index1, int index2, List<E> iterable) {

        E tempElement = iterable.get(index1);
        iterable.set(index1, iterable.get(index2));
        iterable.set(index2, tempElement);
    }

    private boolean validBoundary(int index, int size) {
        if (Validation.isGreaterOrEqual(index, size) || Validation.isLess(index, 0)) {
            return false;
        }
        return true;
    }

    private <E extends Comparable<E>> void heapify(int rootIndex, int size, List<E> iterable) {

        int leftIndex = 2 * rootIndex + 1;
        int rightIndex = 2 * rootIndex + 2;
        int indexOfLargest = leftIndex;

        if (!this.validBoundary(leftIndex, size))
            // Chegou a um nó folha
            return;

        if (this.validBoundary(rightIndex, size))
            indexOfLargest = Validation.isGreaterOrEqual(iterable.get(leftIndex), iterable.get(rightIndex)) ? leftIndex
                    : rightIndex;

        if (Validation.isGreaterOrEqual(iterable.get(indexOfLargest), iterable.get(rootIndex)))
            this.swap(rootIndex, indexOfLargest, iterable);

        this.heapify(indexOfLargest, size, iterable);
    }

    private <E extends Comparable<E>> void heapify(List<E> iterable) {
        // System.out.println("INPUT: " + iterable.toString());
        int size = iterable.size();

        for (int index = (size / 2) - 1; index > -1; index--) {
            // Começa da metade do array, pois o restante são nós folhas,
            // ou seja, já são considerados heaps
            this.heapify(index, size, iterable);
        }
        // System.out.println("HEAPIFIED: " + iterable.toString());
    }

    private <E extends Comparable<E>> void sort(int size, List<E> iterable) {
        // System.out.println("INPUT: " + iterable.toString());
        this.heapify(iterable);
        for (int index = size - 1; index > 0; index--) {
            this.swap(0, index, iterable);
            this.heapify(0, index, iterable);
        }

        // System.out.println("DELETE: " + iterable.toString());
    }

    public <E extends Comparable<E>> List<E> sort(List<E> iterable)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        List<E> sortedList = Clone.clone(iterable, true);

        this.sort(sortedList.size(), sortedList);

        return sortedList;
    }

}
