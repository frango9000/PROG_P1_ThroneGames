import damas.Damas;
import ppt.PiedraPapelTijera;
import proto.Game;
import proto.GamePane;
import treslinea.TresEnLinea;

import java.io.File;


public class Menu {

    private GamePane gamepane;

    private String[] games = {"3 en linea", "Ahorcado", "Piedra Papel o Tijera","Damas"};

    public Menu() {
        gamepane = new GamePane();
    }

    public void gamesMenu() {

        String selection = (String) gamepane.showInputDialog("Elige un juego", games);

        Game game;

        switch (selection) {
            case "Damas":
                game = new Damas();
                break;
            case "3 en Linea":
                game = new TresEnLinea();
                break;
            case "Ahorcado":
                game = new Damas();
                break;
            case "Piedra Papel o Tijera":
                game = new PiedraPapelTijera();
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
