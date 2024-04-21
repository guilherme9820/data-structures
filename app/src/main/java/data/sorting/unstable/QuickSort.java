package data.sorting.unstable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

import data.utils.Clone;
import data.utils.Validation;

public class QuickSort {

    private <E extends Comparable<E>> void swap(int index1, int index2, List<E> iterable) {

        E tempElement = iterable.get(index1);
        iterable.set(index1, iterable.get(index2));
        iterable.set(index2, tempElement);
    }

    private <E extends Comparable<E>> int partition(int lowerBound, int upperBound, int pivotIndex, List<E> iterable) {
        // System.out.println("INPUT: " + iterable.toString());
        // System.out.print("LOWERBOUND: " + lowerBound + " ");
        // System.out.println("UPPERBOUND: " + upperBound);

        E pivot = iterable.get(pivotIndex);
        this.swap(pivotIndex, upperBound, iterable);
        int leftPointer = lowerBound, rightPointer = upperBound;

        boolean foundGreater = false, foundLower = false;
        while (leftPointer != rightPointer) {

            foundGreater = Validation.isGreaterOrEqual(iterable.get(leftPointer), pivot);

            if (foundGreater) {

                foundLower = Validation.isLess(iterable.get(rightPointer), pivot);
                if (foundLower) {
                    this.swap(leftPointer, rightPointer, iterable);
                    foundLower = foundGreater = false;
                } else
                    rightPointer -= 1;
            } else
                leftPointer += 1;

        }
        // System.out.println("SPLIT: " + iterable.toString());

        this.swap(leftPointer, upperBound, iterable);

        return leftPointer;

    }

    private <E extends Comparable<E>> void execute(int lowerBound, int upperBound, List<E> iterable) {
        if (lowerBound >= upperBound) {
            return;
        }

        // int pivotIndex = upperBound;
        int pivotIndex = new Random().nextInt(upperBound - lowerBound) + lowerBound;

        int divisionIndex = this.partition(lowerBound, upperBound, pivotIndex, iterable);

        this.execute(lowerBound, divisionIndex - 1, iterable);
        this.execute(divisionIndex + 1, upperBound, iterable);

    }

    private <E extends Comparable<E>> void executeV2(int lowerBound, int upperBound, List<E> iterable) {
        if (lowerBound >= upperBound) {
            return;
        }

        E pivot = iterable.get(upperBound);

        int leftPointer = lowerBound, rightPointer = upperBound;

        while (leftPointer < rightPointer) {

            while (Validation.isLessOrEqual(iterable.get(leftPointer), pivot) && leftPointer < rightPointer) {
                leftPointer += 1;
            }

            while (Validation.isGreaterOrEqual(iterable.get(rightPointer), pivot) && leftPointer < rightPointer) {
                rightPointer -= 1;
            }
            this.swap(leftPointer, rightPointer, iterable);
        }
        this.swap(leftPointer, upperBound, iterable);

        this.executeV2(lowerBound, leftPointer - 1, iterable);
        this.executeV2(leftPointer + 1, upperBound, iterable);
    }

    public <E extends Comparable<E>> List<E> sort(List<E> iterable)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        List<E> newList = Clone.clone(iterable, true);

        this.execute(0, iterable.size() - 1, newList);

        return newList;
    }
}
