import ahorcado.Ahorcado;
import damas.Damas;
import ppt.PiedraPapelTijera;
import proto.Game;
import proto.GamePane;
import treslinea.TresEnLinea;


class Menu {

    private GamePane gamepane;

    private String[] games = {"3 en Linea", "Ahorcado", "Piedra Papel o Tijera", "Damas", "Damas Intl", "Damas Canada", "Damas OP", "Damas Light"};

    public Menu() {
        gamepane = new GamePane();
    }

    public void gamesMenu() {
        Game game;
        while (true) {

            game = null;
            String selection = (String) gamepane.showGameMenu(games);

            try {
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
                    case "Damas Intl":
                        game = new Damas(10);
                        break;
                    case "Damas Canada":
                        game = new Damas(12);
                        break;
                    case "Damas OP":
                        game = new Damas(20);
                        break;
                    case "Damas Light":
                        game = new Damas(4);
                        break;
                    default:
                        System.exit(0);
                }

            } catch (NullPointerException e) {

                gamepane.showMessageDialog("Gracias por jugar.");
                System.exit(1);
            }
            try {
                gamepane.setTitle(selection);
                game.setGamePane(gamepane);
                game.startGame();
            } catch (Exception e) {

            }

        }

    }
}