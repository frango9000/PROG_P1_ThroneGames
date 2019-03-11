package proto;

public class Coordinate {

    private int x;
    private int y;

    private String reference = "";

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

    public Coordinate(int[] coords, String reference) {
        this(coords);
        this.reference = reference;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static void setMaxCoord(int maxCoord) {
        MAX_COORD = maxCoord;
    }

    @Override
    public String toString() {
        //return reference + "( " + (char) (x + 65) + ", " + (y) + " )";
        return reference + "( " + (char) (x + 65) + ", " + (y+1) + " )";
    }


}
