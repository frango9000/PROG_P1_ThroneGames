package treslinea;

import proto.Game;
import proto.GamePane;
import proto.SimplePlayer;

import javax.swing.*;


public class TresEnLinea implements Game {

    TresEnLineaBoard game;

    @Override
    public void startGame() {
        game = new TresEnLineaBoard();
        SimplePlayer p1 = SimplePlayer.PLAYER1;
        SimplePlayer p2 = SimplePlayer.PLAYER2;
        int count = 0;
        while (game.isGameOver() == null) {
            game.printBoard();
            playerTurn((count++ % 2 == 1) ? p1 : p2);
        }
        game.printBoard();
        System.out.println("Game Over");
    }

    @Override
    public void setGamePane(GamePane gamePane) {

    }

    public void playerTurn(SimplePlayer simplePlayer) {
        int[] coords;
        boolean valid = false;
        do {
            coords = enterCoords(simplePlayer);
            valid = game.validTurn(coords, simplePlayer);
        } while (!valid);
        game.doTurn(coords, simplePlayer);
    }

    public int[] enterCoords(SimplePlayer simplePlayer) {
        int x = 0, y = 0;
        do {


        } while (x < 1 || x > 3 || y < 1 || y > 3);
        return new int[]{x, y};
    }
/* consola
    public void playerTurn(SimplePlayer simplePlayer) {
        int[] coords;
        boolean valid = false;
        do {
            coords = enterCoords(simplePlayer);
            valid = game.validTurn(coords, simplePlayer);
        } while (!valid);
        game.doTurn(coords, simplePlayer);
    }

    public int[] enterCoords(SimplePlayer simplePlayer) {
        int x = 0, y = 0;
        do {
            System.out.println("SimplePlayer " + simplePlayer.getId() + " enter coords x , y: ");
            x = lib.Misc.IO.scanInt();
            y = lib.Misc.IO.scanInt();
        } while (x < 1 || x > 3 || y < 1 || y > 3);
        return new int[]{x, y};
    }
    */
}
