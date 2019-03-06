package damas;

import lib.Data.ListManip;
import proto.Game;

import java.util.ArrayList;

import static lib.Misc.IO.scanInt;

public class Damas implements Game {


    DamasBoard table;
    DamasPlayer p1 = DamasPlayer.PLAYER1;
    DamasPlayer p2 = DamasPlayer.PLAYER2;


    @Override
    public void startGame() {
        table = new DamasBoard();
        int count = 0;
        while (table.isGameOver() == null) {
            table.printBoard();
            playerTurn((count++ % 2 == 1) ? p1 : p2);
        }
        table.printBoard();
        System.out.println("Game Over");
    }

    private void playerTurn(DamasPlayer player) {
        ArrayList<int[]> movables = table.listOfMovables(player);
        ListManip.printList(movables, true);
        //pick piece
        int piece = -1;
        do {
            piece = scanInt("Move piece: ");
        }while( piece >= movables.size() || piece < 0);
        int[] pieceCoords = movables.get(piece);

        //pick position to move piece to
        ArrayList<int[]> moves = table.listOfMoves(pieceCoords);
        ListManip.printList(moves,true);

        int move = -1;
        do {
            move = scanInt("Pick a move:");
        }while( move >= moves.size() || move < 0);
        int[] destination = moves.get(move);

        table.move(pieceCoords, destination);
    }


}
