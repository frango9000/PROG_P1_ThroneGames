import damas.Damas;
import proto.Game;
import proto.GamePane;
import treslinea.TresEnLinea;

import javax.swing.*;

public class Menu {

    private GamePane gamepane;

    private String[] games = {"Damas", "3 en linea"};

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
            default:
                game = new Damas();
                break;
        }
        gamepane.setTitle(selection);
        game.startGame();
    }
}
