package damas;

import damas.misc.Damable;
import lib.Data.ListManip;
import proto.Game;
import proto.GamePane;

import java.util.ArrayList;

import static lib.Misc.IO.println;
import static lib.Misc.IO.scanInt;

public class Damas implements Game {


    private DamasBoard table;
    private DamasPlayer PLAYER_1;
    private DamasPlayer PLAYER_2;

    private DamasPlayer ACTIVE_PLAYER;

    private GamePane gamepane;

    private Damable menu;

    private ArrayList<int[]> movables;
    private ArrayList<int[]> moves;
    private ArrayList<int[]> attacks;
    private ArrayList<int[]> movats;
    public Damas() {
        PLAYER_1 = DamasPlayer.newPlayer();
        PLAYER_2 = DamasPlayer.newPlayer();
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
            ACTIVE_PLAYER = (count++ % 2 == 1) ? PLAYER_1 : PLAYER_2;
            nextTurn();
        }
        menu.gameOver();
    }

    private void nextTurn() {
        //Pick piece to move
        movables = table.listOfActionables(ACTIVE_PLAYER);
        int piece = menu.pickPiece();
        int[] pieceCoords = movables.get(piece);

        //Pick Move
        moves = table.listOfMoves(pieceCoords);
        attacks = table.listOfAttackMoves(pieceCoords);
        movats = new ArrayList<>();
        movats.addAll(moves);
        movats.addAll(attacks);

        do {
            int move = menu.pickMove();
            int[] destination = movats.get(move);

            if (move <= moves.size()) {
                table.moveTo(pieceCoords, destination);
                break;
            }
            table.eatOverTo(pieceCoords, destination);
            pieceCoords = destination;

            attacks = movats = table.listOfAttackMoves(pieceCoords);
            moves.clear();
            if (attacks.size() > 0 )
                table.printBoard();
        } while (movats.size() > 0);
    }

    public class damasConsole implements Damable{

        @Override
        public int pickPiece() {
            table.printBoard();
            println("Player " + ACTIVE_PLAYER.getIdQ() + " Turn.");
            if(movables.size() == 0) {
                println("Player " + ACTIVE_PLAYER.getIdQ() + " has no available moves. Check Bugs!");
                //return;  //catch bugs
            }
            ListManip.printList(movables, true, 1);

            //pick piece
            int piece = 0;
            do {
                piece = scanInt("Move piece: ");
            } while (piece < 1 || piece > movables.size());
            return piece - 1;
        }

        @Override
        public int pickMove() {

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
            return move-1;
        }

        @Override
        public void gameOver() {
            table.printBoard();
            System.out.println("Game Over\nWinner is: " + table.isGameOver() );
        }
    }

    public class damasWindow implements Damable{

        @Override
        public int pickPiece() {
            return 0;
        }

        @Override
        public int pickMove() {//DamasPlayer player, int[] coords+
            return 0;
        }

        @Override
        public void gameOver() {

        }
    }


}
