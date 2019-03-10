package damas;

import damas.misc.Damable;
import lib.Data.ListManip;
import proto.Game;
import proto.GamePane;

import java.util.ArrayList;

import static lib.Misc.IO.println;
import static lib.Misc.IO.scanInt;

public class Damas implements Game {


    DamasBoard table;
    private DamasPlayer PLAYER_1 = DamasPlayer.newPlayer();
    private DamasPlayer PLAYER_2 = DamasPlayer.newPlayer();

    GamePane gamepane;

    Damable menu;

    public Damas() {
    }

    public void setGamepane(GamePane gamepane) {
        this.gamepane = gamepane;
    }

    @Override
    public void startGame() {
        if(gamepane == null)
            menu = new damasConsole();
        else menu = new damasWindow();

        table = new DamasBoard();

        int count = 1;
        while (table.isGameOver() == null) {
            playerTurn((count++ % 2 == 1) ? PLAYER_1 : PLAYER_2);
        }
        menu.gameOver();
    }

    private void playerTurn(DamasPlayer player) {

        ArrayList<int[]> movables = table.listOfActionables(player);

        int[] pieceCoords = movables.get(menu.pickPiece(player) - 1);
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
                move = scanInt("Pick a moveTo:");
            } while (move < 1 || move > movats.size());
            int[] destination = movats.get(move - 1);

            if (move <= moves.size()) {
                table.moveTo(pieceCoords, destination);
                break;
            }
            table.eatOverTo(pieceCoords, destination);
            pieceCoords = destination;

            attacks = table.listOfAttackMoves(pieceCoords);
            moves.clear();
            movats.clear();
            movats.addAll(attacks);

            if (attacks.size() > 0 )
                table.printBoard();
        } while (movats.size() > 0);
    }

    public class damasConsole implements Damable{

        @Override
        public void gameOver() {
            table.printBoard();
            System.out.println("Game Over\nWinner is: " + table.isGameOver() );
        }

        @Override
        public int pickPiece(DamasPlayer player) {
            table.printBoard();
            println("SimplePlayer " + player.getIdQ() + " Turn.");
            ArrayList<int[]> movables = table.listOfActionables(player);
            if(movables.size() == 0) {
                println("SimplePlayer " + player.getIdQ() + " has no available moves. Check Bugs!");
                //return;  //catch bugs
            }
            ListManip.printList(movables, true, 1);

            //pick piece
            int piece = 0;
            do {
                piece = scanInt("Move piece: ");
            } while (piece < 1 || piece > movables.size());
            return piece;
        }

        @Override
        public int pickMove() {
            return 0;
        }
    }

    public class damasWindow implements Damable{

        @Override
        public void gameOver() {

        }

        @Override
        public int pickPiece(DamasPlayer player) {
            return 0;
        }

        @Override
        public int pickMove() {//DamasPlayer player, int[] coords+
            return 0;
        }
    }


}
