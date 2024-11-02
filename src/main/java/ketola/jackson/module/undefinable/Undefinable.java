package ketola.jackson.module.undefinable;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Undefinable<T> {

    private final T value;

    public Undefinable(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @JsonCreator
    public static <T> Undefinable<T> create(T value) {
        return new Undefinable<>(value);
    }

    public static class Undefined extends Undefinable<Void> {
        public static final Undefined UNDEFINED = new Undefined();

        private Undefined() {
            super(null);
        }

        @Override
        public String toString() {
            return "Undefined";
        }
    }

    @Override
    public String toString() {
        return "Undefinable{" +
            "value=" + value +
            '}';
    }
}
