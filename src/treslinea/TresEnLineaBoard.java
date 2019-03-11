package treslinea;

import proto.Board;
import proto.Coordinate;
import proto.SimplePlayer;

import java.util.ArrayList;
import java.util.Arrays;

public class TresEnLineaBoard extends Board {

    private int size;
    private int rows;
    private int cols;


    public TresEnLineaBoard() {
        this(3);
    }

    public TresEnLineaBoard(int size) {
        this.size = size;
        this.rows = size;
        this.cols = size;
        table = new char[rows][cols];
        clearBoard();
    }

    public int getSize() {
        return size;
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
        String line = "   -";
        for (int i = 0; i < table.length; i++) {
            line += "----";
        }
        System.out.printf("%4s", "");
        for (int col = 0; col < table.length; col++) {
            System.out.printf("%2d  ", col + 1);
        }
        System.out.println();
        System.out.println(line);
        for (int row = 0; row < table.length; row++) {
            System.out.printf(" %c ", ((char) (65 + row)));
            for (int cell = 0; cell < table[row].length; cell++) {
                System.out.printf("| %c ", table[row][cell]);
            }
            System.out.println("|\n" + line);
        }

    }

    public String toString() {
        StringBuilder str = new StringBuilder("<html>\n" +
                "<style>\n" +
                "table{border-collapse: collapse;\n" +
                "table-layout: fixed;\n" +
                "border-spacing: 0px;}\n" +
                "td{border:solid black 2px;\n" +
                "width: 70px;\n" +
                "height: 70px;\n" +
                "text-align: center;\n" +
                "font-size: 30px;}\n" +
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
            str.append("<th>" + (col + 1) + "</th>\n");
        }
        str.append("</tr>\n");

        //all rows of the table with an extra first column (number coords)
        for (int row = 0; row < table.length; row++) {
            str.append("<tr>\n");
            str.append("<th>" + ((char) (65 + row)) + "</th>\n");
            for (int cell = 0; cell < table[row].length; cell++) {//all cells on each row
                str.append("<td");
                if (row == 0)
                    str.append(" class=\"top\"");
                else if (row == table.length - 1)
                    str.append(" class=\"bot\"");

                if (cell == 0)
                    str.append(" style=\"border-left: none;\"");
                else if (cell == table[row].length - 1)
                    str.append(" style=\"border-right: none;\"");

                str.append(">");
                str.append(table[row][cell]);
                str.append("</td>\n");
            }
            str.append("</tr>\n");
        }

        //close html
        str.append("</table>\n");
        str.append("</html>");
        System.out.println(str.toString());
        return str.toString();
    }

    public ArrayList<int[]> moves() {
        ArrayList<int[]> moves = new ArrayList<>();
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (table[row][col] == ' ') {
                    moves.add(new int[]{row, col});
                }
            }
        }
        return moves;
    }

    public Coordinate[] movesArray() {
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

    @Override
    public boolean validTurn(int[] coords, SimplePlayer simplePlayer) {
        return false;
    }

    @Override
    public void doTurn(int[] coords, SimplePlayer simplePlayer) {

    }

    public boolean validTurn(Coordinate coord) {
        return (table[coord.getX()][coord.getY()] == ' ');
    }

    public void doTurn(Coordinate coord, SimplePlayer player) {
        table[coord.getX()][coord.getY()] = player.getId();
    }

}
