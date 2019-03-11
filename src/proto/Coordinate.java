package proto;

public class Coordinate {

    private static int MAX_COORD;
    private int x;
    private int y;
    private String reference = "";

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

    public Coordinate(int[] coords, String reference) {
        this(coords);
        this.reference = reference;
    }

    public static void setMaxCoord(int maxCoord) {
        MAX_COORD = maxCoord;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        //return reference + "( " + (char) (x + 65) + ", " + (y) + " )";
        return reference + "( " + (char) (x + 65) + ", " + (y + 1) + " )";
    }


}
