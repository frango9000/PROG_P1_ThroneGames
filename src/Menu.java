import ahorcado.Ahorcado;
import damas.Damas;
import ppt.PiedraPapelTijera;
import proto.Game;
import proto.GamePane;
import treslinea.TresEnLinea;


public class Menu {

    private GamePane gamepane;

    private String[] games = {"3 en Linea", "Ahorcado", "Piedra Papel o Tijera", "Damas"};

    public Menu() {
        gamepane = new GamePane();
    }

    public void gamesMenu() {

        String selection = (String) gamepane.showInputDialog("Elige un juego", games);

        Game game;

        switch (selection) {
            case "3 en Linea":
                game = new TresEnLinea();
                break;
            case "Ahorcado":
                game = new Ahorcado();
                break;
            case "Piedra Papel o Tijera":
                game = new PiedraPapelTijera();
                break;
            case "Damas":
                game = new Damas();
                break;
            default:
                game = new Damas();
                break;
        }
        gamepane.setTitle(selection);
        game.setGamePane(gamepane);
        game.startGame();
    }
}
