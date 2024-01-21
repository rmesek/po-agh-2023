package agh.ics.oop.presenter;

public class TextFieldData<T> {
    public T min;
    public T max;
    public String propertyName;

    public TextFieldData(T min, T max, String propertyName) {
        this.min = min;
        this.max = max;
        this.propertyName = propertyName;
    }
}
