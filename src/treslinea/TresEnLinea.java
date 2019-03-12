package treslinea;

import proto.Coordinate;
import proto.Game;
import proto.GamePane;
import proto.SimplePlayer;

import java.util.InputMismatchException;


public class TresEnLinea implements Game {

    private static boolean CONSOLE = true;
    private TresEnLineaBoard board;

    private GamePane gamepane = null;

    SimplePlayer activePlayer;

    public static void main(String[] args) {
        TresEnLinea tnl = new TresEnLinea();
        tnl.setGamePane(new GamePane());
        tnl.startGame();
    }

    @Override
    public void startGame() {
        board = new TresEnLineaBoard();
        int count = 0;
        while (board.isGameOver() == null) {
            activePlayer = (count++ % 2 == 0) ? SimplePlayer.PLAYER1 : SimplePlayer.PLAYER2;
            playerTurn();
        }
        gameOver();
    }

    @Override
    public void setGamePane(GamePane gamepane) {
        this.gamepane = gamepane;
        CONSOLE = false;
    }

    private void playerTurn() {
        Coordinate pick;
        boolean valid;
        do {
            if (CONSOLE)
                pick = enterCoordsC();
            else
                pick = enterCoords();
            valid = board.validTurn(pick);
        } while (!valid);
        board.doTurn(pick, activePlayer);
    }

    private Coordinate enterCoords() {
        Coordinate pick;
        String turn = board.toString() +
                "<table><tr><td width=\"240px\" style=\"border: none;font-size:20px\">" +
                "Player's " + activePlayer.getId() + " turn" +
                "</td></tr></table></html>";
        Coordinate[] moves = board.movesArray();
        pick = (Coordinate) gamepane.showInputDialog(turn, moves);
        return pick;
    }

    public void gameOver() {
        String gameOver = board.toString() +
                "<table><tr><td width=\"240px\" style=\"border: none;\">" +
                "Player " + activePlayer.getId() + " wins" +
                "</td></tr></table></html>";
        gamepane.showMessageDialog(gameOver);
        board.printBoard();
        System.out.println("Game Over");
    }

    private Coordinate enterCoordsC() {
        board.printBoard();
        int x = 0, y = 0;
        do {
            System.out.println("Player " + activePlayer.getId() + " turn");
            try {
                x = lib.Misc.IO.scanInt("Enter coord digit: ");
                y = lib.Misc.IO.scanChar("Enter coord letter: ");
            } catch (InputMismatchException e) {
                continue;
            }
        } while (x < 1
                || x > board.getSize()
                || Character.toLowerCase(y) < 'a'
                || Character.toLowerCase(y) > (char) board.getSize() - 1 + 'a');
        return new Coordinate(x - 1, y - 97);
    }

}
