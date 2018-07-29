package me.destro.android.gitfav.utilities;

public class Algorithms {
    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T var1);

        default Consumer<T> andThen(Consumer<? super T> after) {
            throw new RuntimeException("Stub!");
        }
    }

    public static <T> void foreach(T[] array, Consumer<T> c) {
        if (array == null)
            return;

        for (T element : array) {
            c.accept(element);
        }
    }

}
