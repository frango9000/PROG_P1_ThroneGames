package damas.misc;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

public class CoordinatesComparator implements Comparator<int[]>, Serializable {

    @Override
    public int compare(int[] o1, int[] o2) {
        return Arrays.compare(o1, o2);
    }

}
