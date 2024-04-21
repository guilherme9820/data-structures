package data.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Clone {

    @SuppressWarnings("unchecked")
    public static <S extends List<E>, E extends Comparable<E>> S clone(S cloneable, boolean fill)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        S cloned;
        try {
            cloned = (S) cloneable.getClass().getConstructor((Class<?>[]) null).newInstance((Object[]) null);
        } catch (Exception e) {
            throw e;
        }
        if (fill) {
            for (E element : cloneable) {
                cloned.add(element);
            }
        }

        return cloned;
    }
}
