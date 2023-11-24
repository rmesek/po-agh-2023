package agh.ics.oop.model;

import java.util.*;
import java.util.stream.LongStream;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final List<Long> order;
    private final int limit;
    private final int maxWidth;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount) {
        this.limit = grassCount;
        this.maxWidth = maxWidth;
        long maxLength = (long) maxWidth * (long) maxHeight;
        if (grassCount > maxLength) {
            throw new IllegalArgumentException("cannot generate more grasses than spaces");
        }
        this.order = new ArrayList<>(LongStream.rangeClosed(0, maxLength - 1).boxed().toList());
        Collections.shuffle(this.order);
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new PositionIterator();
    }

    private class PositionIterator implements Iterator<Vector2d> {
        private int index = 0;
        @Override
        public boolean hasNext() {
            return index < limit;
        }

        @Override
        public Vector2d next() {
            Vector2d vector2d = new Vector2d((int) (order.get(index) % maxWidth), (int) (order.get(index) / maxWidth));
            index++;
            return vector2d;
        }
    }
}
