
package data.sorting;

import org.junit.jupiter.api.Test;

import data.sorting.stable.MergeSortInplace;
import data.utils.Clone;
import data.utils.Random.RandomInt;

import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Collections;
import java.time.Instant;
import java.time.Duration;

public class MergeSortInplaceTest {

    @Test
    void sort()
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        System.out.println(this.getClass().getSimpleName());
        MergeSortInplace mergeSortInplace = new MergeSortInplace();

        RandomInt generator = new RandomInt();

        List<Integer> testList = generator.generateRandomList(0, 100, 25);

        // System.out.println("Original");
        // System.out.println(testList.toString());

        Instant start, finish;
        start = finish = null;
        String elapsedTime = "";
        List<Integer> newList = Clone.clone(testList, true);
        try {
            start = Instant.now();
            newList = mergeSortInplace.sort(testList);
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
