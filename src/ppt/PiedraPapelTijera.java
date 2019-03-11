package ppt;

import proto.Game;
import proto.GamePane;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class PiedraPapelTijera implements Game {

    static String[] msjRonda = {"Empate! " + "\n\n", "Ganaste la ronda!  " + "\n\n", "Perdiste la ronda! " + "\n\n"};
    Scanner scan = new Scanner(System.in);
    String[] jugadas = {"Piedra", "Papel", "Tijera"};
    int contRonda = 0;
    int win = 0;
    int lose = 0;
    int tie = 0;
    String marcador = " ";
    int contMarcador = 0;
    JFrame frame = new JFrame();
    private GamePane gamepane = null;

    @Override
    public void startGame() {
        while (contRonda < 3) {
            String jugada = preguntarJugadaU();
            String jugadaM = generarJugadaM();
            int r = compararJugadas(jugadaM, jugada);
            arbitro(r);
        }
        gameOver();
    }

    @Override
    public void setGamePane(GamePane gamePane) {
        this.gamepane = gamepane;
    }

    public String generarJugadaM() {
        contMarcador += 1;
        Random r = new Random();
        return jugadas[r.nextInt(3)];
    }

    public String preguntarJugadaU() {
        marcador = marcador + " Marcador: " + "\n\n" + "Rondas Ganadas: " + win + "\n" + "Rondas Perdidas: " + lose + "\n" + "Rondas Empatadas: " + tie;
        int jugadaU = JOptionPane.showOptionDialog(frame, marcador + "\n\n" + "Selecciona tu proxima jugada", "Ronda: " + contMarcador, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, jugadas, jugadas[0]);
        if (jugadaU == -1)
            System.exit(0);
        return jugadas[jugadaU];
    }

    public int compararJugadas(String jugadaM, String jugada) {

        String[] jugadas2 = {"Tijera", "Piedra", "Papel"};

        for (int i = 0; i < jugadas.length; i++) {

            if ((jugadas[i].equals(jugada)) && (jugadas2[i].equals(jugadaM))) {
                marcador += jugada + " vence a " + jugadaM + "\n";
                return 1;

            } else if ((jugadas[i].equals(jugadaM)) && (jugadas2[i].equals(jugada))) {
                marcador += jugada + " pierde contra " + jugadaM + "\n";
                return 2;

            } else if ((jugadas[i].equals(jugadaM)) && (jugadas[i].equals(jugada))) {
                marcador += jugada + " es igual a " + jugadaM + "\n";
                return 0;
            }
        }
        return 3;
    }

    public void arbitro(int resultado) {
        if (resultado == 0) {
            marcador = msjRonda[0];
            tie += 1;

        } else if (resultado == 1) {
            marcador = msjRonda[1];
            win += 1;
            contRonda += 1;

        } else if (resultado == 2) {
            marcador = msjRonda[2];
            lose += 1;
            contRonda += 1;

        } else {
            System.out.println("ERROR");
        }

    }

    public void gameOver() {
        if (win > lose) {
            JOptionPane.showMessageDialog(frame, "Felicidades Campeon! Ganaste la partida " + win + " a " + lose, "GAME OVER", JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showMessageDialog(frame, "Perdiste la partida! " + lose + " a " + win, "GAME OVER", JOptionPane.DEFAULT_OPTION);
        }
    }

}