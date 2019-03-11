package treslinea;

import proto.Coordinate;
import proto.Game;
import proto.GamePane;
import proto.SimplePlayer;

import java.util.InputMismatchException;


public class TresEnLinea implements Game {

    public static boolean CONSOLE = true;
    TresEnLineaBoard game;

    GamePane gamepane = null;

    public static void main(String[] args) {
        TresEnLinea tnl = new TresEnLinea();
        tnl.setGamePane(new GamePane());
        tnl.startGame();
    }

    @Override
    public void startGame() {
        game = new TresEnLineaBoard();
        SimplePlayer p1 = SimplePlayer.PLAYER1;
        SimplePlayer p2 = SimplePlayer.PLAYER2;
        int count = 0;
        while (game.isGameOver() == null) {
            playerTurn((count++ % 2 == 0) ? p1 : p2);
        }
        game.printBoard();
        System.out.println("Game Over");
    }

    @Override
    public void setGamePane(GamePane gamepane) {
        this.gamepane = gamepane;
        CONSOLE = false;
    }

    public void playerTurn(SimplePlayer player) {
        Coordinate pick;
        boolean valid = false;
        do {
            if (CONSOLE)
                pick = enterCoordsC(player);
            else
                pick = enterCoords(player);
            valid = game.validTurn(pick);
        } while (!valid);
        game.doTurn(pick, player);
    }

    public Coordinate enterCoords(SimplePlayer player) {
        Coordinate pick;
        String board = game.toString();
        //board += "\n<html>Player's " + player.getId() + " turn\n";

        Coordinate[] moves = game.movesArray();
        pick = (Coordinate) gamepane.showInputDialog(board, moves);
        return pick;
    }

    public void gameOver() {
        gamepane.showMessageDialog(game.toString());
    }

    public Coordinate enterCoordsC(SimplePlayer simplePlayer) {
        game.printBoard();
        int x = 0, y = 0;
        do {
            System.out.println("Player " + simplePlayer.getId() + " turn");
            try {
                x = lib.Misc.IO.scanInt("Enter coord digit: ");
                y = lib.Misc.IO.scanChar("Enter coord letter: ");
            } catch (InputMismatchException e) {
                continue;
            }
        } while (x < 1
                || x > game.getSize()
                || Character.toLowerCase(y) < 'a'
                || Character.toLowerCase(y) > (char) game.getSize() - 1 + 'a');
        return new Coordinate(x - 1, y - 97);
    }

}
