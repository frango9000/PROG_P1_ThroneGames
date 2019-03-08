import damas.Damas;
import proto.Game;
        import treslinea.TresEnLinea;

        import javax.swing.*;
//import lib.*;
public class Main {
    public static void main(String[] args) {
        String[] games = {"Damas", "3 en linea"};
        String selection = (String)JOptionPane.showInputDialog(null, "Elige un juego", "Juegos Reunidos", 0, null, games, 0);

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
        game.startGame();
    }
}
