package damas;

import proto.Player;

import java.util.ArrayList;

public class DamasPlayer extends Player {

    char id;
    char idQ;
    char utf;
    char utfQ;

    public final static int MAX_PLAYERS = 2;

    public static DamasPlayer[] players = new DamasPlayer[MAX_PLAYERS+1];//index 0 is player 0 for ties etc


    private final static char P0 = ' ';

    private final static char P1_ID = 'o';
    private final static char P1_IDQ = 'O';         //utf  = '\u25EF';//◯
    private final static char P1_UTF = '\u26AA';    //utfQ = '\u26E3';//⛣
    private final static char P1_UTFQ = '\u29F2';   //utfQ = '\u25CD';//◍

    private final static char P2_ID = 'x';
    private final static char P2_IDQ = 'X';         //utf  = '\u2B24';//⬤
    private final static char P2_UTF = '\u26AB';
    private final static char P2_UTFQ = '\u29F3';   //utfQ = '\u29ED';//⧭

    private DamasPlayer(int n) {
        if(players[n] != null ) {
            switch (n) {
                case 0:
                    id = P0;
                    idQ = P0;
                    utf = P0;
                    utfQ = P0;

                case 1:
                    id = P1_ID;     //o
                    idQ = P1_IDQ;    //o
                    utf = P1_UTF;  //⚪
                    utfQ = P1_UTFQ; //⧲
                    break;
                case 2:
                    id = P2_ID;
                    idQ = P2_IDQ;
                    utf = P2_UTF;  //⚫
                    utfQ = P2_UTFQ; //⧳
                    break;
            }
            players[n] = this;
        }
    }

    public static DamasPlayer newPlayer(){
        if(players[0] == null)
            players[0] = new DamasPlayer(0);
        for (int i = 1; i < players.length; i++) {
            if(players[i] == null)
                return players[i] = new DamasPlayer(i);

        }
        return null;
    }

    public static char getCaseSensitiveUTF(char cas) {
        switch (cas) {
            case P1_ID:
                return P1_UTF;
            case P1_IDQ:
                return P1_UTFQ;
            case P2_ID:
                return P2_UTF;
            case P2_IDQ:
                return P2_UTFQ;
            default:
                return ' ';
            }
    }


    public static DamasPlayer getPlayer(int i) {
        return players[--i];
    }

    public static DamasPlayer getPlayer(char c){
        switch (c){
            case P1_ID:
            case P1_IDQ:
            case P1_UTF:
            case P1_UTFQ:
                return players[1];

            case P2_ID:
            case P2_IDQ:
            case P2_UTF:
            case P2_UTFQ:
                return players[2];
            default:
                return players[0];
        }
    }

    public char getId() {
        return id;
    }

    public char getIdQ() {
        return idQ;
    }

    public char getUtf() {
        return utf;
    }

    public char getUtfQ() {
        return utfQ;
    }

}
