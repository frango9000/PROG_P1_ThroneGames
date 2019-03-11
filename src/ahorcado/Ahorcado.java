package ahorcado;

import java.awt.Frame;
import java.util.Arrays;
import javax.swing.JOptionPane;
import proto.Game;
import proto.GamePane;

public class Ahorcado implements Game {

    Frame frame = new Frame();
    static String[] palabras = {"casa", "perro", "jungla", "proyecto", "escritorio", "lampara", "programacion", "java", "adivinar"};
    String palabra;
    char[] palabraOculta;
    int contador;
    int TMAX = 7;

    @Override
    public void startGame() {
        //System.out.println(palabra);
        while (gameOver() == 0) {
            char letraP = capturarLetra();
            if (verificarExistencia(letraP)) {
                sustituirLetra(letraP);
            } else {
                contador++;
            }
        }
        if (gameOver() == 2) {
            String muñeco = dibujar(contador);
            JOptionPane.showMessageDialog(frame, "PERDISTE!" + "\n" + muñeco + "\n" + "LA PALABRA OCULTA ERA: " + palabra, "GAME OVER!",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "FELICIDADES! HAS ADIVINADO LA PALABRA." + "\n" + palabra, "WIN!", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void setGamePane(GamePane gamePane) {

    }

    public Ahorcado() {
        this.palabra = palabraAleatoria();
        this.palabraOculta = ocultarPalabra();
        contador = 0;
    }

    public static String palabraAleatoria() {
        int posicionRand = (int) Math.floor(Math.random() * palabras.length);
        String palabraRand = palabras[posicionRand];
        return palabraRand;
    }

    public char[] ocultarPalabra() {

        char[] letras = palabra.toCharArray();
        char[] letrasOcultas = new char[letras.length];
        for (int i = 0; i < letras.length; i++) {
            letrasOcultas[i] = '_';
        }
        return letrasOcultas;
    }

    public char capturarLetra() {

        String txtVidas = " Te quedan: " + (TMAX - contador) + " vidas." + "\n";
        String txtIndicacion = "Intenta adivinar la siguiente palabra: " + "\n";
        String txtSolicitud = "Introduce una letra: " + "\n";
        String muñeco = dibujar(contador);
        char letraJugada;
        do {
            letraJugada = Character.toLowerCase(JOptionPane.showInputDialog(txtVidas + "\n\n" + txtIndicacion + Arrays.toString(palabraOculta) + "\n\n" + muñeco + "\n\n" + txtSolicitud).charAt(0));
        } while (!Character.isLetter(letraJugada));
        return letraJugada;
    }

    public boolean verificarExistencia(char letra) {

        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra) {
                return true;
            }
        }
        return false;
    }

    public void sustituirLetra(char letra) {
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra) {
                palabraOculta[i] = letra;
            }
        }
    }

    public int gameOver() {
        if (Arrays.equals(palabra.toCharArray(), palabraOculta)) {
            return 1;
        } else if (contador == TMAX) {
            return 2;
        } else {
            return 0;
        }
    }

    private static String dibujar(int caso) {
        String[] muñeco = new String[17];
        muñeco[0]  = "   --------------------------------" + "\n";
        muñeco[1]  = "   | --         HORCA        -- |" + "\n";
        muñeco[2]  = "   --------------------------------" + "\n";
        muñeco[3]  = "   |                               |" + "\n";
        muñeco[4]  = "   |                               |" + "\n";
        muñeco[5]  = "   |                          ∧∧∧∧   " + "\n";
        muñeco[6]  = "   |                           |  O O  |" + "\n";
        muñeco[7]  = "   |                           |  ___  |" + "\n";
        muñeco[8]  = "   |                           |_____|" + "\n";
        muñeco[9]  = "   |                                ||" + "\n";
        muñeco[10] = "   |                                ||" + "\n";
        muñeco[11] = "   |                                ||" + "\n";
        muñeco[12] = "   |                                ||" + "\n";
        muñeco[13] = "   |                                ||" + "\n";
        muñeco[14] = "   |                            " + "\n";
        muñeco[15] = "   |                            " + "\n";
        muñeco[16] = "   |                           " + "\n";


        switch (caso) {
            case 0:
                String orca = "\n";

                for (int i = 0; i < 5; i++) {

                    orca += muñeco[i];

                }
                return orca;

            case 1:
                String cabeza = "\n";

                for (int i = 0; i < 9; i++) {

                    cabeza += muñeco[i];

                }
                return cabeza;

            case 2:
                String cuerpo = "\n";

                for (int i = 0; i < 14; i++) {

                    cuerpo += muñeco[i];

                }
                return cuerpo;

            case 3:
                String brazoI = "\n";
                muñeco[9]  = "    |                              //||" + "\n";
                muñeco[10]  = "   |                             // ||" + "\n";
                muñeco[11] = "   |                            //  ||" + "\n";
                muñeco[12] = "   |                           //   ||" + "\n";
                muñeco[13] = "   |                                ||" + "\n";

                for (int i = 0; i < 14; i++) {

                    brazoI += muñeco[i];

                }
                return brazoI;

            case 4:
                String brazoD = "\n";
                muñeco[9]  = "   |                              //||\\\\" + "\n";
                muñeco[10] = "   |                             // || \\\\" + "\n";
                muñeco[11] = "   |                            //  ||  \\\\" + "\n";
                muñeco[12] = "   |                          //    ||   \\\\" + "\n";
                muñeco[13] = "   |                                ||   " + "\n";

                for (int i = 0; i < 14; i++) {

                    brazoD += muñeco[i];

                }
                return brazoD;

            case 5:
                String piernaI = "\n";
                muñeco[9]  = "   |                              //||\\\\" + "\n";
                muñeco[10] = "   |                             // || \\\\" + "\n";
                muñeco[11] = "   |                           //   ||  \\\\" + "\n";
                muñeco[12] = "   |                          //    ||   \\\\" + "\n";
                muñeco[13] = "   |                                ||   " + "\n";
                muñeco[14] = "   |                              //" + "\n";
                muñeco[15] = "   |                             //" + "\n";
                muñeco[16] = "   |                            //" + "\n";

                for (int i = 0; i < 16; i++) {

                    piernaI += muñeco[i];

                }
                return piernaI;

            case 6:
                String piernaD = "\n";
                muñeco[9]  = "   |                              //||\\\\" + "\n";
                muñeco[10] = "   |                             // || \\\\" + "\n";
                muñeco[11] = "   |                           //   ||  \\\\" + "\n";
                muñeco[12] = "   |                          //    ||   \\\\" + "\n";
                muñeco[13] = "   |                                ||   " + "\n";
                muñeco[14] = "   |                              //  \\\\" + "\n";
                muñeco[15] = "   |                             //    \\\\" + "\n";
                muñeco[16] = "   |                            //      \\\\" + "\n";

                for (int i = 0; i < 16; i++) {

                    piernaD += muñeco[i];

                }
                return piernaD;

            case 7:
                String ko = "\n";
                muñeco[6]  = "   |                           |  X X  |" + "\n";
                muñeco[7]  = "   |                           |    o    |" + "\n";
                muñeco[9]  = "   |                               //||\\\\" + "\n";
                muñeco[9]  = "   |                             // || \\\\" + "\n";
                muñeco[10] = "   |                           //   ||  \\\\" + "\n";
                muñeco[11] = "   |                          //    ||   \\\\" + "\n";
                muñeco[12] = "   |                                ||   " + "\n";
                muñeco[13] = "   |                              //  \\\\" + "\n";
                muñeco[14] = "   |                             //    \\\\" + "\n";
                muñeco[15] = "   |                            //      \\\\" + "\n";

                for (int i = 0; i < 16; i++) {

                    ko += muñeco[i];

                }
                return ko;
        }
        return null;
    }
}