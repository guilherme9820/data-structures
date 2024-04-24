package data.sorting;

import org.junit.jupiter.api.Test;

import data.sorting.unstable.HeapSort;
import data.utils.Clone;
import data.utils.Random.RandomInt;

import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.time.Instant;
import java.time.Duration;

public class HeapSortTest {

    @Test
    void sort()
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        System.out.println(this.getClass().getSimpleName());
        HeapSort heapSort = new HeapSort();

        RandomInt generator = new RandomInt();

        List<Integer> testList = generator.generateRandomList(0, 100, 25);
        // List<Integer> testList = new ArrayList<>(Arrays.asList(93, 48, 1, 56, 6, 46,
        // 8, 64, 6, 22));
        // List<Integer> testList = new ArrayList<>(Arrays.asList(10, 20, 15, 12, 40,
        // 25, 18));

        // System.out.println("Original");
        // System.out.println(testList.toString());

        Instant start, finish;
        start = finish = null;
        String elapsedTime = "";
        List<Integer> newList = Clone.clone(testList, true);
        try {
            start = Instant.now();
            newList = heapSort.sort(testList);
            finish = Instant.now();
            elapsedTime = Duration.between(start, finish).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println("Sorted");
        // System.out.println(newList.toString());
        System.out.println(
                "Elapsed time: " + elapsedTime.substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase());

        Collections.sort(testList);

        assertIterableEquals(testList, newList, "The lists are not equal!");

    }
}
