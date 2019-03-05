package treslinea;

import lib.IO;
import proto.Game;
import proto.Player;

public class TresEnLinea implements Game {

    TresEnLineaBoard game;

    @Override
    public void startGame() {
        game = new TresEnLineaBoard();
        Player p1 = Player.PLAYER1;
        Player p2 = Player.PLAYER2;
        int count = 0;
        while (game.isGameOver() == null) {
            game.printBoard();
            playerTurn((count++ % 2 == 1) ? p1 : p2);
        }
        game.printBoard();
        System.out.println("Game Over");
    }

    public void playerTurn(Player player) {
        int[] coords;
        boolean valid = false;
        do {
            coords = enterCoords(player);
            valid = game.validTurn(coords, player);
        } while (!valid);
        game.doTurn(coords, player);
    }

    public int[] enterCoords(Player player) {
        int x = 0, y = 0;
        do {
            System.out.println("Player " + player.getId() + " enter coords x , y: ");
            x = IO.scanInt();
            y = IO.scanInt();
        } while (x < 1 || x > 3 || y < 1 || y > 3);
        return new int[]{x, y};
    }
}
