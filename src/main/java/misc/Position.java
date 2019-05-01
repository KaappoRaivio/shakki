package misc;

import java.io.Serializable;

public class Position implements Serializable {
    private final int x;

    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
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
