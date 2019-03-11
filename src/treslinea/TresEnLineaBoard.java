package treslinea;

import damas.misc.Coordinate;
import lib.Math.Algebra;
import proto.Board;
import proto.SimplePlayer;

import java.util.ArrayList;
import java.util.Arrays;

public class TresEnLineaBoard extends Board {

    public TresEnLineaBoard() {
        this(3);
    }

    public TresEnLineaBoard(int size) {
        rows = size;
        cols = size;
        table = new char[rows][cols];
        clearBoard();
    }

    public void clearBoard() {
        for (char[] chars : table) {
            Arrays.fill(chars, ' ');
        }
    }

    public boolean isFull() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    public void printBoard() {
        String line = "-";
        for (int i = 0; i < table.length; i++) {
            line += "----";
        }
        System.out.println(line);
        for (char[] chars : table) {
            for (int i = 0; i < chars.length; i++) {
                System.out.printf("| %c ", chars[i]);

            }
            System.out.println(" |%n" + line);
        }

    }

    public String toString(){
        StringBuilder str = new StringBuilder("<html>\n" +
                "<style>\n" +
                "table{border-collapse: collapse;\n" +
                "table-layout: fixed;\n" +
                "border-spacing: 0px;}\n" +
                "td{border:solid black 2px;\n" +
                "width: 70px;\n" +
                "height: 70px;}\n" +
                "th{width: 30px}\n" +
                ".top{border-top: none;}\n" +
                ".bot{border-bottom: none;}\n" +
                "#left{border-left: none;}\n" +
                "#right{border-right: none;}\n" +
                "</style>\n" +
                "<table>\n");

        //first row of the table is a header (letter coords)
        str.append("<tr>\n");
        str.append("<th></th>\n");
        for (int col = 0; col < table.length; col++) {
            str.append("<th>" + (char) (65 + col) + "</th>\n");
        }
        str.append("</tr>\n");

        //all rows of the table with an extra first column (number coords)
        for (int row = 0; row < table.length; row++) {
            str.append("<tr>\n");
            str.append("<th>"+(row+1)+"</th>\n");
            for (int cell = 0; cell < table[row].length; cell++) {//all cells on each row
                str.append("<td");
                if(row == 0)
                    str.append(" class=\"top\"");
                else if(row == table.length-1)
                    str.append(" class=\"bot\"");

                if(cell == 0)
                    str.append(" style=\"border-left: none;\"");
                else if(cell == table[row].length-1)
                    str.append(" style=\"border-right: none;\"");

                str.append(">");
                //str.append((cell==-1)?"":table[row][cell]);
                str.append("</td>\n");
            }
            str.append("</tr>\n");
        }

        //close html
        str.append("</table>\n");
        str.append("</html>");

        return str.toString();
    }

    public ArrayList<int[]> moves(){
        ArrayList<int[]> moves = new ArrayList<>();
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if(table[row][col] == ' '){
                    moves.add( new int[] {row,col});
                }
            }
        }
        return moves;
    }

    public Coordinate[] movesArray(){
        ArrayList<int[]> moves = moves();
        Coordinate[] array = new Coordinate[moves.size()];

        for (int i = 0; i < moves.size(); i++) {
            array[i] = new Coordinate(moves.get(i));
        }
        return array;
    }


    public Character isGameOver() {
        for (int i = 0; i < table.length; i++) {
            if (table[i][0] != ' ' && (table[i][0] == table[i][1] && table[i][1] == table[i][2])) {
                return table[i][0];//row winner
            }
        }
        for (int i = 0; i < table.length; i++) {
            if (table[0][i] != ' ' && (table[0][i] == table[1][i] && table[1][i] == table[2][i])) {
                return table[0][i];//col winner
            }
        }
        if (table[0][0] != ' ' && (table[0][0] == table[1][1] && table[1][1] == table[2][2])) {
            return table[0][0];//main diag winner
        } else if (table[0][2] != ' ' && (table[0][2] == table[1][1] && table[1][1] == table[2][0])) {
            return table[0][2];//reverse diag winner
        } else if (isFull())
            return ' ';//game tied
        else return null;//game not over
    }

    public boolean validTurn(int[] coords, SimplePlayer simplePlayer) {
        return (table[coords[0] - 1][coords[1] - 1] == ' ');
    }

    public void doTurn(int[] coords, SimplePlayer simplePlayer) {
        table[coords[0] - 1][coords[1] - 1] = simplePlayer.getId();
    }

}
