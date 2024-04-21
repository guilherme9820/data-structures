package data.utils;

import java.util.ArrayList;
import java.util.List;

public class Random {

    public static class RandomInt {
        public int generate(int min, int max) {
            return (int) ((Math.random() * (max - min)) + min);
        }

        public List<Integer> generateRandomList(int min, int max, int size) {

            List<Integer> list = new ArrayList<>();

            for (int index = 0; index < size; index++) {
                list.add(this.generate(min, max));
            }

            return list;
        }
    }
}
