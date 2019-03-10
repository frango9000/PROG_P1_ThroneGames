package damas.misc;

import damas.DamasPlayer;

public interface Damable {

    void gameOver();

    int pickPiece(DamasPlayer player);

    int pickMove();


}
