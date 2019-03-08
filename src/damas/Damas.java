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
        int count = 1;
        while (table.isGameOver() == null) {
            playerTurn((count++ % 2 == 1) ? p1 : p2);
        }
        table.printBoard();
        System.out.println("Game Over\nWinner is: " + table.isGameOver() );
    }

    private void playerTurn(DamasPlayer player) {
        table.printBoard();
        println("Player " + player.getIdQ() + " Turn.");
        ArrayList<int[]> movables = table.listOfActionables(player);
        if(movables.size() == 0) {
            println("Player " + player.getIdQ() + " has no available moves. Check Bugs!");
            return;
        }
        ListManip.printList(movables, true, 1);
        //pick piece
        int piece = 0;
        do {
            piece = scanInt("Move piece: ");
        } while (piece < 1 || piece > movables.size());
        int[] pieceCoords = movables.get(piece - 1);

        ArrayList<int[]> moves = table.listOfMoves(pieceCoords);
        ArrayList<int[]> attacks = table.listOfAttackMoves(pieceCoords);
        ArrayList<int[]> movats = new ArrayList<>();
        movats.addAll(moves);
        movats.addAll(attacks);
        do {

            if (moves.size() > 0) {
                println("Moves: ");
                ListManip.printList(moves, true, 1);
            }

            if (attacks.size() > 0) {
                println("Attacks: ");
                ListManip.printList(attacks, true, moves.size() + 1);
            }

            int move = 0;
            do {
                move = scanInt("Pick a move:");
            } while (move < 1 || move > movats.size());
            int[] destination = movats.get(move - 1);

            if (move <= moves.size()) {
                table.move(pieceCoords, destination);
                break;
            }
            table.eat(pieceCoords, destination);
            pieceCoords = destination;

            attacks = table.listOfAttackMoves(pieceCoords);
            moves.clear();
            movats.clear();
            movats.addAll(attacks);

            if (attacks.size() > 0 )
                table.printBoard();
        } while (movats.size() > 0);


    }


}
