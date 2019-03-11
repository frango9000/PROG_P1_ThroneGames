package damas.misc;

import proto.Coordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class CoordinateDamas extends Coordinate implements Comparable<CoordinateDamas> {
    private static int MAX_COORD;
    private int x;
    private int y;
    private String reference = "";

    public CoordinateDamas() {
    }

    public CoordinateDamas(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CoordinateDamas(int[] coords) {
        this.x = coords[0];
        this.y = coords[1];
    }

    public CoordinateDamas(int[] coords, String reference) {
        this(coords);
        this.reference = reference;
    }

    public static void setMaxCoord(int maxCoord) {
        MAX_COORD = maxCoord;
    }

    public static CoordinateDamas[] pickAPiece(ArrayList<int[]> pieces) {
        CoordinateDamas[] array = new CoordinateDamas[pieces.size()];
        for (int i = 0; i < pieces.size(); i++) {
            array[i] = new CoordinateDamas(pieces.get(i), "Piece @ ");
        }
        return array;
    }

    public static CoordinateDamas[] pickAMove(ArrayList<int[]> moves, ArrayList<int[]> attacks) {
        CoordinateDamas[] array = new CoordinateDamas[moves.size() + attacks.size()];
        if (moves.size() > 0)
            for (int i = 0; i < moves.size(); i++) {
                array[i] = new CoordinateDamas(moves.get(i), "Move @ ");
            }
        if (attacks.size() > 0)
            for (int i = moves.size(); i < array.length; i++) {
                array[i] = new CoordinateDamas(attacks.get(i - moves.size()), "Attack @ ");
            }
        return array;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return reference + "( " + (char) (y + 65) + ", " + (MAX_COORD - x) + " )";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateDamas that = (CoordinateDamas) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(CoordinateDamas o) {
        return Arrays.compare(new int[]{this.x, this.y}, new int[]{o.x, o.y});
    }

    public int getIndexOf(CoordinateDamas[] coords) {
        for (int i = 0; i < coords.length; i++) {
            if (coords[i] == this)
                return i;
        }
        return -1;
    }


}
