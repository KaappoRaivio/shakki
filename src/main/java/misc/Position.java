package misc;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {
    private final int x;

    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return getX() == position.getX() &&
                getY() == position.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    public double getAverage () {
        return (x + y) / 2.0f;
    }

    @Override
    public String toString() {
        return "Position(" +
                "x=" + x +
                ", y=" + y +
                ')';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
