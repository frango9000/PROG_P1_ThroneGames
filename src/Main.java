import proto.Game;
        import treslinea.TresEnLinea;

        import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String[] games = {"3 en Linea", "Conecta 4"};
        String selection = (String)JOptionPane.showInputDialog(null, "Elige un juego", "Juegos Reunidos", 0, null, games, 0);

        Game game;

        switch (selection) {
            case "3 en Linea":
                game = new TresEnLinea();
                break;
            default:
                game = new TresEnLinea();
                break;
        }
        game.startGame();
    }
}
