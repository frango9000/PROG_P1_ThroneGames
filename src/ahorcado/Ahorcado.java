package ahorcado;

import proto.Game;
import proto.GamePane;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ahorcado implements Game {
    private static String[] palabras;
    private Frame frame = new Frame();
    private String palabra;
    private char[] palabraOculta;
    private int contador;
    private int TMAX = 7;

    private boolean fromFile = true;


    public Ahorcado() {
        if(fromFile){
            ArrayList<String> lista = new ArrayList<>();
            try {
                File file = new File("src/ahorcado/palabras.txt");
                Scanner scan = new Scanner(file);
                while(scan.hasNext()){
                    lista.add(scan.next());
                }
                palabras = new String[lista.size()];
                for (int i = 0; i < lista.size(); i++) {
                    palabras[i] = lista.get(i);

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }

        this.palabra = palabraAleatoria();
        this.palabraOculta = ocultarPalabra();
        contador = 0;
    }

    public static void main(String[] args) {
        Ahorcado a = new Ahorcado();
        a.startGame();
    }

    private static String palabraAleatoria() {
        int posicionRand = (int) Math.floor(Math.random() * palabras.length);
        return palabras[posicionRand];
    }

    private static String dibujar(int caso) {
        String[] muñeco = new String[17];
        muñeco[0] = "   --------------------------------" + "\n";
        muñeco[1] = "   | --          HORCA         -- |" + "\n";
        muñeco[2] = "   --------------------------------" + "\n";
        muñeco[3] = "   |                               |" + "\n";
        muñeco[4] = "   |                               |" + "\n";
        muñeco[5] = "   |                          ∧∧∧∧  " + "\n";
        muñeco[6] = "   |                          |  O  O  | " + "\n";
        muñeco[7] = "   |                          |   ___  |  " + "\n";
        muñeco[8] = "   |                          \\_____/  " + "\n";
        muñeco[9] = "   |                                ||" + "\n";
        muñeco[10] = "   |                                ||" + "\n";
        muñeco[11] = "   |                                ||" + "\n";
        muñeco[12] = "   |                                ||" + "\n";
        muñeco[13] = "   |                                ||" + "\n";
        muñeco[14] = "   |                            " + "\n";
        muñeco[15] = "   |                            " + "\n";
        muñeco[16] = "   |                           " + "\n";


        switch (caso) {
            case 0:
                StringBuilder orca = new StringBuilder("\n");

                for (int i = 0; i < 5; i++) {

                    orca.append(muñeco[i]);

                }
                return orca.toString();

            case 1:
                StringBuilder cabeza = new StringBuilder("\n");

                for (int i = 0; i < 9; i++) {

                    cabeza.append(muñeco[i]);

                }
                return cabeza.toString();

            case 2:
                StringBuilder cuerpo = new StringBuilder("\n");

                for (int i = 0; i < 14; i++) {

                    cuerpo.append(muñeco[i]);

                }
                return cuerpo.toString();

            case 3:
                StringBuilder brazoI = new StringBuilder("\n");
                muñeco[9] = "   |                              //||" + "\n";
                muñeco[10] = "   |                             // ||" + "\n";
                muñeco[11] = "   |                            //  ||" + "\n";
                muñeco[12] = "   |                           //   ||" + "\n";
                muñeco[13] = "   |                                ||" + "\n";

                for (int i = 0; i < 14; i++) {

                    brazoI.append(muñeco[i]);

                }
                return brazoI.toString();

            case 4:
                StringBuilder brazoD = new StringBuilder("\n");
                muñeco[9] = "   |                              //||\\\\" + "\n";
                muñeco[10] = "   |                             // || \\\\" + "\n";
                muñeco[11] = "   |                            //  ||  \\\\" + "\n";
                muñeco[12] = "   |                          //    ||   \\\\" + "\n";
                muñeco[13] = "   |                                ||   " + "\n";

                for (int i = 0; i < 14; i++) {

                    brazoD.append(muñeco[i]);

                }
                return brazoD.toString();

            case 5:
                StringBuilder piernaI = new StringBuilder("\n");
                muñeco[9] = "   |                              //||\\\\" + "\n";
                muñeco[10] = "   |                             // || \\\\" + "\n";
                muñeco[11] = "   |                           //   ||  \\\\" + "\n";
                muñeco[12] = "   |                          //    ||   \\\\" + "\n";
                muñeco[13] = "   |                                ||   " + "\n";
                muñeco[14] = "   |                              //" + "\n";
                muñeco[15] = "   |                             //" + "\n";
                muñeco[16] = "   |                            //" + "\n";

                for (int i = 0; i < 16; i++) {

                    piernaI.append(muñeco[i]);

                }
                return piernaI.toString();

            case 6:
                StringBuilder piernaD = new StringBuilder("\n");
                muñeco[9] = "   |                              //||\\\\" + "\n";
                muñeco[10] = "   |                             // || \\\\" + "\n";
                muñeco[11] = "   |                           //   ||  \\\\" + "\n";
                muñeco[12] = "   |                          //    ||   \\\\" + "\n";
                muñeco[13] = "   |                                ||   " + "\n";
                muñeco[14] = "   |                              //  \\\\" + "\n";
                muñeco[15] = "   |                             //    \\\\" + "\n";
                muñeco[16] = "   |                            //      \\\\" + "\n";

                for (int i = 0; i < 16; i++) {

                    piernaD.append(muñeco[i]);

                }
                return piernaD.toString();

            case 7:
                StringBuilder ko = new StringBuilder("\n");
                muñeco[6] = "   |                          |  X  X  |" + "\n";
                muñeco[7] = "   |                          |     o    |" + "\n";
                muñeco[9] = "   |                               //||\\\\" + "\n";
                muñeco[9] = "   |                             // || \\\\" + "\n";
                muñeco[10] = "   |                           //   ||  \\\\" + "\n";
                muñeco[11] = "   |                          //    ||   \\\\" + "\n";
                muñeco[12] = "   |                                ||   " + "\n";
                muñeco[13] = "   |                              //  \\\\" + "\n";
                muñeco[14] = "   |                             //    \\\\" + "\n";
                muñeco[15] = "   |                            //      \\\\" + "\n";

                for (int i = 0; i < 16; i++) {

                    ko.append(muñeco[i]);

                }
                return ko.toString();
        }
        return null;
    }

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

    private char[] ocultarPalabra() {

        char[] letras = palabra.toCharArray();
        char[] letrasOcultas = new char[letras.length];
        for (int i = 0; i < letras.length; i++) {
            letrasOcultas[i] = '_';
        }
        return letrasOcultas;
    }

    private char capturarLetra() {

        String txtVidas = " Te quedan: " + (TMAX - contador) + " vidas." + "\n";
        String txtIndicacion = "Intenta adivinar la siguiente palabra: " + "\n";
        String txtSolicitud = "Introduce una letra: " + "\n";
        String muñeco = dibujar(contador);
        char letraJugada;
        String[] opciones = {"Intentar", "Salir"};
        do {
            String t = JOptionPane.showInputDialog(frame, txtVidas + "\n\n" + txtIndicacion + Arrays.toString(palabraOculta) + "\n\n" + muñeco + "\n\n" + txtSolicitud, "AHORCADO", JOptionPane.PLAIN_MESSAGE);
            letraJugada = Character.toLowerCase(t.charAt(0));
        } while (!Character.isLetter(letraJugada));
        return letraJugada;
    }

    private boolean verificarExistencia(char letra) {

        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra) {
                return true;
            }
        }
        return false;
    }

    private void sustituirLetra(char letra) {
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra) {
                palabraOculta[i] = letra;
            }
        }
    }

    private int gameOver() {
        if (Arrays.equals(palabra.toCharArray(), palabraOculta)) {
            return 1;
        } else if (contador == TMAX) {
            return 2;
        } else {
            return 0;
        }
    }
}