package damas;

import lib.Data.ListManip;
import proto.Game;

import java.util.ArrayList;

import static lib.Misc.IO.println;
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
        ArrayList<int[]> movables = table.listOfActionables(player, false);
        ListManip.printList(movables, true,1);
        //pick piece
        int piece = 0;
        do {
            piece = scanInt("Move piece: ");
        }while( piece < 1 || piece > movables.size());
        int[] pieceCoords = movables.get(piece-1);

        ArrayList<int[]> moves = table.listOfMoves(pieceCoords);
        ArrayList<int[]> attacks = table.listOfAttackMoves(pieceCoords);
        ArrayList<int[]> movats = new ArrayList<>(moves);
        movats.addAll(attacks);

        if(moves.size() > 0) {
            println("Moves: ");
            ListManip.printList(moves, true, 1);
        }

        if(attacks.size() > 0){
            println("Attacks: ");
            ListManip.printList(attacks,true, moves.size()+1);
        }

        //pick position to move piece to
        int move = 0;
        do {
            move = scanInt("Pick a move:");
        }while(move < 1 || move >  movats.size() );
        int[] destination = movats.get(move-1);

        if(move < moves.size())
            table.move(pieceCoords, destination);
        else
            table.eat(table.table, pieceCoords, destination);
    }


}
