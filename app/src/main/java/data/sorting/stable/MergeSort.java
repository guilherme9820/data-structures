package data.sorting.stable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import data.utils.Clone;
import data.utils.Validation;

public class MergeSort {

    private <E extends Comparable<E>> List<E> merge(List<E> leftList, List<E> rightList)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // System.out.print("LEFTLIST: " + leftList.toString() + " ");
        // System.out.print("RIGHTLIST: " + rightList.toString());
        // System.out.println();

        List<E> merged = Clone.clone(leftList, false);

        int leftIndex = 0, rightIndex = 0;
        while (leftIndex < leftList.size() && rightIndex < rightList.size()) {
            E leftElement = leftList.get(leftIndex);
            E rightElement = rightList.get(rightIndex);
            if (Validation.isLess(rightElement, leftElement)) {
                merged.add(rightElement);
                rightIndex += 1;
            } else {
                merged.add(leftElement);
                leftIndex += 1;
            }

        }

        // Append leftovers to the end
        while (leftIndex < leftList.size()) {
            merged.add(leftList.get(leftIndex));
            leftIndex += 1;
        }
        while (rightIndex < rightList.size()) {
            merged.add(rightList.get(rightIndex));
            rightIndex += 1;
        }
        // System.out.println("MERGED: " + merged.toString());
        return merged;
    }

    private <E extends Comparable<E>> List<E> split(int lowerBound, int upperBound, List<E> iterable)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // System.out.println("INDICES: " + lowerBound + " " + upperBound);
        int size = upperBound - lowerBound + 1;
        // System.out.println("INPUT: " + iterable.toString());
        if (size == 1) {
            List<E> newList = Clone.clone(iterable, false);
            newList.add(iterable.get(lowerBound));
            return newList;
        }
        // System.out.println("DIV: " + ((size - 1) / 2 + 1));
        // int border = (int) Math.ceil(size / 2);
        int border = (size - 1) / 2 + 1;
        // System.out.println("SIZE: " + size + " BORDER: " + border);

        List<E> leftPart = this.split(lowerBound, lowerBound + border - 1, iterable);
        List<E> rightPart = this.split(lowerBound + border, upperBound, iterable);

        return this.merge(leftPart, rightPart);

    }

    public <E extends Comparable<E>> List<E> sort(List<E> iterable)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        List<E> newList = Clone.clone(iterable, true);

        return this.split(0, iterable.size() - 1, newList);
    }

}
