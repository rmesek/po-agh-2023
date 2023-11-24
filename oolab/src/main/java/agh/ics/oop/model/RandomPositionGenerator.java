package agh.ics.oop.model;

import java.util.*;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final Iterator<Long> it;
    private final int maxWidth;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount) {
        this.maxWidth = maxWidth;
        long maxLength = (long) maxWidth * (long) maxHeight;
        if (grassCount > maxLength) {
            throw new UnsupportedOperationException("cannot generate more grasses than spaces");
        }
        if (grassCount > 0) {
            this.it = new Random().longs(0, maxLength).distinct().limit(grassCount).boxed().iterator();
        } else {
            this.it = Collections.emptyIterator();
        }
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new PositionIterator();
    }

    private class PositionIterator implements Iterator<Vector2d> {
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Vector2d next() {
            Long cursor1d = it.next();
            return new Vector2d((int) (cursor1d % maxWidth), (int) (cursor1d / maxWidth));
        }
    }
}
