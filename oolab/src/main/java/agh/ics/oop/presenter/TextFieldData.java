package agh.ics.oop.presenter;

public class TextFieldData<T> {
    public final T min;
    public final T max;
    public final String propertyName;

    public TextFieldData(T min, T max, String propertyName) {
        this.min = min;
        this.max = max;
        this.propertyName = propertyName;
    }
}
