package damas;

import damas.misc.CoordinatesComparator;
import lib.Geometry.Line;
import lib.Geometry.Point;
import lib.Math.Algebra;
import proto.Board;
import proto.SimplePlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import static lib.Misc.IO.println;


public class DamasBoard extends Board {
    private DamasPlayer PLAYER_1 = DamasPlayer.getPlayer(1);
    private DamasPlayer PLAYER_2 = DamasPlayer.getPlayer(2);

    private int rows;
    private int cols;
    private int fronts;

    private char[][] table;

    public DamasBoard() {
        this(8, 3);
    }

    public DamasBoard(int size, int fronts) {
        this.rows = size;
        this.cols = size;
        this.fronts = fronts;
        this.fronts = Algebra.min(fronts, (rows / 2) - 1);
        table = new char[rows][cols];
        clearBoard();
    }

    @Override
    public void clearBoard() {
        //fill with ' '
        for (char[] ccs : table) {
            Arrays.fill(ccs, ' ');
        }
        //fill PLAYER_1
        for (int row = table.length - 1, h = 0; h < fronts; row--, h++) {
            for (int col = 0; col < table[row].length; col++) {
                if ((row % 2 != 0 && col % 2 == 0) || (row % 2 == 0 && col % 2 != 0))
                    table[row][col] = PLAYER_1.getId();
            }
        }
        //fill PLAYER_2
        for (int row = 0; row < fronts; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if ((row % 2 != 0 && col % 2 == 0) || (row % 2 == 0 && col % 2 != 0))
                    table[row][col] = PLAYER_2.getId();
            }
        }

    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void printBoard() {
        //line

        String line = "-";
        for (int i = 0; i < cols; i++) {
            line += "----";
        }

        System.out.println(line);
        for (char[] chars : table) {
            for (char c : chars) {
                System.out.printf("| %c ", c);
            }
            System.out.println("|\n" + line);
        }

    }

    @Override
    public String toString() {
        int outBorder = 3;
        int cellSpace = 1;
        int cellBorder = 0;

        int pieceSize = 8 * 32 / rows;
        int fontSize = 8 * 24 / rows;

        String cellBlack = "aaaaaa";
        String cellWhite = "dddddd";
        String cellHighlight = "";

        int totalWidth = 400;
        int cellSize = totalWidth / rows;

        StringBuilder board = new StringBuilder();
        // html header
        board.append("<html>\n" +
                "<style>\n" +
                "table{border:solid " + outBorder + "px black;\n" +
                "border-collapse: collapse;\n" +
                "table-layout: fixed;\n" +
                "border-spacing: " + cellSpace + "px;}\n" +
                "td {border:" + cellBorder + "px solid black;\n" +
                "width: " + cellSize + "px;\n" +
                "height: " + cellSize + "px;\n" +
                "text-align: center;\n" +
                "font-size: " + pieceSize + "px;}\n" +
                "td.w{background-color: #" + cellWhite + ";}\n" +
                "td.b{background-color: #" + cellBlack + ";}\n" +
                "th{width: 18px; font-size:" + fontSize + "px}\n" +
                "</style>\n" +
                "<body>\n");

        //table and head row with letters
        board.append("<table>\n" +
                "<tr class=\"colheader\">\n" +
                "<th> \n" +
                "</th>\n");

        for (int col = 0; col < table[0].length; col++) {
            board.append("<th>" + (char) (65 + col) + "</th>\n");
        }

        //all rows with number
        for (int row = 0; row < table.length; row++) {
            board.append("</tr>\n" +
                    "<tr>\n" +
                    "<th>" + (table.length - row) + "</th>\n");
            for (int col = 0; col < table[row].length; col++) {
                board.append("<td class=\"");
                board.append(((row % 2 != 0 && col % 2 == 0) || (row % 2 == 0 && col % 2 != 0)) ? 'b' : 'w');
                board.append("\">" + DamasPlayer.getCaseSensitiveUTF(table[row][col]) + "</td>\n");
            }

        }
        //tail
        board.append("</tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>");


        return board.toString();
    }

    public Character isGameOver() {
        int pp1 = 0, pp2 = 0;
        for (char[] chars : table) {
            for (char charac : chars) {
                if (charac == PLAYER_1.getId() || charac == PLAYER_1.getIdQ())
                    pp1++;
                else if (charac == PLAYER_2.getId() || charac == PLAYER_2.getIdQ())
                    pp2++;
            }
        }
        if (pp1 == 0)//loser
            return PLAYER_2.getIdQ();//winner
        else if (pp2 == 0)
            return PLAYER_1.getIdQ();
        else return null;
    }

    @Override
    public boolean validTurn(int[] coords, SimplePlayer player) {
        return false;
    }

    @Override
    public void doTurn(int[] coords, SimplePlayer player) {

    }

    public ArrayList<int[]> listOfMoves(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        ArrayList<int[]> moves = new ArrayList<>();
        if (canMoveUpLeft(coords))
            moves.add(new int[]{x - 1, y - 1});
        if (canMoveUpRight(coords))
            moves.add(new int[]{x - 1, y + 1});
        if (canMoveDownLeft(coords))
            moves.add(new int[]{x + 1, y - 1});
        if (canMoveDownRight(coords))
            moves.add(new int[]{x + 1, y + 1});
        return moves;
    }

    public ArrayList<int[]> listOfAttackMoves(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        ArrayList<int[]> moves = new ArrayList<>();
        if (canEatUpLeft(coords))
            moves.add(new int[]{x - 2, y - 2});
        if (canEatUpRight(coords))
            moves.add(new int[]{x - 2, y + 2});
        if (canEatDownLeft(coords))
            moves.add(new int[]{x + 2, y - 2});
        if (canEatDownRight(coords))
            moves.add(new int[]{x + 2, y + 2});
        return moves;
    }

    public ArrayList<int[]> listOfMovables(DamasPlayer player) {
        ArrayList<int[]> pieces = listOfPieces(player);
        ArrayList<int[]> movables = new ArrayList<>();

        for (int[] coords : pieces) {
            if (isMovable(coords))
                movables.add(coords);
        }
        return movables;
    }

    public ArrayList<int[]> listOfAttackers(DamasPlayer player) {
        ArrayList<int[]> pieces = listOfPieces(player);
        ArrayList<int[]> attackers = new ArrayList<>();

        for (int[] coords : pieces) {
            if (canEat(coords))
                attackers.add(coords);
        }
        return attackers;
    }

    public ArrayList<int[]> listOfActionables(DamasPlayer player) {
        TreeSet<int[]> actionables = new TreeSet<>(new CoordinatesComparator());

        ArrayList<int[]> movables = listOfMovables(player);
        actionables.addAll(movables);

        ArrayList<int[]> attackers = listOfAttackers(player);
        actionables.addAll(attackers);

        return new ArrayList<>(actionables);
    }


    public ArrayList<int[]> listOfPieces(DamasPlayer player) {
        ArrayList<int[]> lista = new ArrayList<>();
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (table[row][col] == player.getId() || table[row][col] == player.getIdQ())
                    lista.add(new int[]{row, col});
            }
        }
        return lista;
    }

    public boolean isMovable(int[] coords) {
        return canMoveUpLeft(coords) || canMoveUpRight(coords) || canMoveDownLeft(coords) || canMoveDownRight(coords);
    }

    public boolean canMoveUpLeft(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x == 0 || table[x][y] == PLAYER_2.getId())//top row cant go above / PLAYER_2 pawn cant go above
            return false;
        if (y > 0) {
            return table[x - 1][y - 1] == ' ';//above left
        }
        return false;
    }

    public boolean canMoveUpRight(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x == 0 || table[x][y] == PLAYER_2.getId())//top row cant go above / PLAYER_2 pawn cant go above
            return false;
        if (y < table[x].length - 1) {
            return table[x - 1][y + 1] == ' ';//above left
        }
        return false;
    }

    public boolean canMoveDownLeft(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x == table.length - 1 || table[x][y] == PLAYER_1.getId())//last row, cant go below / PLAYER_1 pawn cant go below
            return false;
        if (y > 0) {
            return table[x + 1][y - 1] == ' ';//below left
        }
        return false;
    }

    public boolean canMoveDownRight(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x == table.length - 1 || table[x][y] == PLAYER_1.getId())//last row, cant go below / PLAYER_1 pawn cant go below
            return false;
        if (y < table[x].length - 1) {
            return table[x + 1][y + 1] == ' ';//below left
        }
        return false;
    }

    public void moveTo(int[] piece, int[] moveTo) {
        int x = piece[0];
        int y = piece[1];
        int newX = moveTo[0];
        int newY = moveTo[1];
        table[newX][newY] = table[x][y];
        table[x][y] = ' ';
        queenPawnIfRoyal(newX, newY);
    }

    public boolean canEat(int[] coords) {
        return canEatUpLeft(coords) || canEatUpRight(coords) || canEatDownLeft(coords) || canEatDownRight(coords);
    }

    public boolean canEatUpLeft(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x <= 1 || table[x][y] == PLAYER_2.getId())//top 2 rows cant eatOverTo above / PLAYER_2 pawn cant eatOverTo above
            return false;
        char piece = table[x][y];
        if (y > 1 && Character.isLetter(table[x - 1][y - 1])) {
            return Character.toLowerCase(table[x - 1][y - 1]) != Character.toLowerCase(piece) && table[x - 2][y - 2] == ' ';
        }
        return false;
    }

    public boolean canEatUpRight(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x <= 1 || table[x][y] == PLAYER_2.getId())//top 2 rows cant eatOverTo above / PLAYER_2 pawn cant eatOverTo above
            return false;
        char piece = table[x][y];
        if (y < this.table[x].length - 2 && Character.isLetter(table[x - 1][y + 1])) {
            return Character.toLowerCase(table[x - 1][y + 1]) != Character.toLowerCase(piece) && table[x - 2][y + 2] == ' ';
        }
        return false;
    }

    public boolean canEatDownLeft(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x >= this.table.length - 2 || table[x][y] == PLAYER_1.getId())//bot 2 rows cant eatOverTo below / PLAYER_1 pawn cant eatOverTo below
            return false;
        char piece = table[x][y];
        if (y > 1 && Character.isLetter(table[x + 1][y - 1])) {
            return Character.toLowerCase(table[x + 1][y - 1]) != Character.toLowerCase(piece) && table[x + 2][y - 2] == ' ';
        }
        return false;
    }

    public boolean canEatDownRight(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        char piece = table[x][y];
        if (x >= table.length - 2 || table[x][y] == PLAYER_1.getId())//bot 2 rows cant eatOverTo below / PLAYER_1 pawn cant eatOverTo below
            return false;
        if (y < table[x].length - 2 && Character.isLetter(table[x + 1][y + 1])) {
            return Character.toLowerCase(table[x + 1][y + 1]) != Character.toLowerCase(piece) && table[x + 2][y + 2] == ' ';
        }
        return false;
    }

    public char[][] eatOverTo(int[] piece, int[] moveTo) {

        char[][] tab = new char[table.length][table[0].length];
        System.arraycopy(table, 0, tab, 0, tab.length);
        int x = piece[0];
        int y = piece[1];
        int newX = moveTo[0];
        int newY = moveTo[1];

        Line line = new Line(new Point(x, y), new Point(newX, newY));
        Point eatPoint = line.middlePoint();
        println(eatPoint.toString());
        int eatX = (int) eatPoint.x;
        int eatY = (int) eatPoint.y;

        tab[newX][newY] = tab[x][y];
        tab[x][y] = tab[eatX][eatY] = ' ';

        queenPawnIfRoyal(newX, newY);
        return tab;
    }


    public void queenPawnIfRoyal(int x, int y) {
        if ((x == 0) && (table[x][y] == PLAYER_1.getId()))
            table[x][y] = PLAYER_1.getIdQ();
        else if (x == table.length - 1 && table[x][y] == PLAYER_2.getId())
            table[x][y] = PLAYER_2.getIdQ();
    }

}
