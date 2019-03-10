package damas.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Coordinate implements Comparable<Coordinate> {
    private int x;
    private int y;

    public static final int PIECE = 0;
    public static final int MOVE = 1;
    public static final int EAT = 2;

    private static String action = "";

    private static int MAX_COORD;

    public Coordinate() {
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(int[] coords) {
        this.x = coords[0];
        this.y = coords[1];
    }

    public static void setAction(int i) {
        switch (i) {
            case 0:
                action = "Piece @ ";
                break;
            case 1:
                action = "Move to";
                break;
            case 2:
                action = "Eat";
                break;
        }
    }

    public static void setMaxCoord(int maxCoord) {
        MAX_COORD = maxCoord;
    }

    @Override
    public String toString() {
        return action + "( " + (char) (y + 65) + ", " + (MAX_COORD-x) + " )";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Coordinate o) {
        return Arrays.compare(new int[]{this.x, this.y}, new int[]{o.x, o.y});
    }

    public static Coordinate[] toArray(ArrayList<int[]> list) {
        Coordinate[] array = new Coordinate[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = new Coordinate(list.get(i));
        }
        return array;

    }
}
