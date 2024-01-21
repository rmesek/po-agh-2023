package agh.ics.oop.presenter;

public class ConfigLimit<T> {
    public T min;
    public T max;

    public ConfigLimit(T min, T max) {
        this.min = min;
        this.max = max;
    }
}
