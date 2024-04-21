package data.sorting.stable;

import java.util.List;

import data.utils.Clone;
import java.lang.reflect.InvocationTargetException;

public class InsertionSort {

    private <E extends Comparable<E>> boolean isLesser(E element1, E element2) {
        return element1.compareTo(element2) < 0;
    }

    public <S extends List<E>, E extends Comparable<E>> S sort(S iterable)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        int size = iterable.size();

        S newList = Clone.clone(iterable, true);

        E backwardElement, pivot;
        int backwardIndex;
        for (int forwardIndex = 1; forwardIndex < size; forwardIndex++) {
            backwardIndex = forwardIndex - 1;
            pivot = newList.get(forwardIndex);
            backwardElement = newList.get(backwardIndex);
            while (this.isLesser(pivot, backwardElement)) {
                // Desloca o elemento anterior uma posição para frente
                newList.set(backwardIndex + 1, backwardElement);
                backwardIndex -= 1;
                if (backwardIndex < 0) {
                    break;
                }
                backwardElement = newList.get(backwardIndex);
            }
            // Caso o pivot já seja maior que o elemento anterior na primeira passada
            // ele sobrescreve a si próprio. Um movimento redundante, mas sem um custo
            // considerável.
            newList.set(backwardIndex + 1, pivot);
        }

        return newList;
    }

}
