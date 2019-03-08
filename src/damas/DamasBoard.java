package damas;

import damas.misc.CoordinatesComparator;
import lib.Geometry.Line;
import lib.Geometry.Point;
import lib.Math.Algebra;
import proto.Board;
import proto.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import static lib.Misc.IO.println;


public class DamasBoard extends Board {
    private DamasPlayer p1 = DamasPlayer.PLAYER1;
    private DamasPlayer p2 = DamasPlayer.PLAYER2;

    private int rows;
    private int cols;
    private int fronts;

    char[][] table;

    public DamasBoard() {
        this(8,3);
    }

    public DamasBoard(int size, int fronts) {
        this.rows = size;
        this.cols = size;
        this.fronts = fronts;
        this.fronts = Algebra.min(fronts, (rows/2)-1);
        table = new char[rows][cols];
        clearBoard();
    }

    @Override
    public void clearBoard() {
        //fill with ' '
        for (char[] ccs : table) {
            Arrays.fill(ccs, ' ');
        }
        //fill p1
        for (int i = table.length - 1, h = 0; h < fronts; i--, h++) {
            for (int j = 0; j < table[i].length; j++) {
                if ((i % 2 != 0 && j % 2 == 0) || (i % 2 == 0 && j % 2 != 0))
                    table[i][j] = p1.getId();
            }
        }
        //fill p2
        for (int i = 0; i < fronts; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if ((i % 2 != 0 && j % 2 == 0) || (i % 2 == 0 && j % 2 != 0))
                    table[i][j] = p2.getId();
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

    public char[][] cloneBoard() {
        return table.clone();
    }

    public Character isGameOver() {
        int pp1 = 0, pp2 = 0;
        for (char[] chars : table) {
            for (char charac : chars) {
                if (charac == p1.getId() || charac == p1.getIdQ())
                    pp1++;
                else if (charac == p2.getId()|| charac == p2.getIdQ())
                    pp2++;
            }
        }
        if (pp1 == 0)//loser
            return p2.getIdQ();//winner
        else if (pp2 == 0)
            return p1.getIdQ();
        else return null;
    }

    @Override
    public boolean validTurn(int[] coords, Player player) {
        return false;
    }

    @Override
    public void doTurn(int[] coords, Player player) {

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

    public ArrayList<int[]> listOfAttackMoves(int[] coords, char[][] board) {
        int x = coords[0];
        int y = coords[1];
        ArrayList<int[]> moves = new ArrayList<>();
        if (canEatUpLeft(board, coords))
            moves.add(new int[]{x - 2, y - 2});
        if (canEatUpRight(board, coords))
            moves.add(new int[]{x - 2, y + 2});
        if (canEatDownLeft(board, coords))
            moves.add(new int[]{x + 2, y - 2});
        if (canEatDownRight(board, coords))
            moves.add(new int[]{x + 2, y + 2});
        return moves;
    }

    public ArrayList<int[]> listOfAttackMoves(int[] coords) {
        return listOfAttackMoves(coords, table);
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
            if (canEat(table, coords))
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
        if (x == 0 || table[x][y] == p2.getId())//top row cant go above / p2 pawn cant go above
            return false;
        if (y > 0) {
            return table[x - 1][y - 1] == ' ';//above left
        }
        return false;
    }

    public boolean canMoveUpRight(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x == 0 || table[x][y] == p2.getId())//top row cant go above / p2 pawn cant go above
            return false;
        if (y < table[x].length - 1) {
            return table[x - 1][y + 1] == ' ';//above left
        }
        return false;
    }

    public boolean canMoveDownLeft(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x == table.length - 1 || table[x][y] == p1.getId())//last row, cant go below / p1 pawn cant go below
            return false;
        if (y > 0) {
            return table[x + 1][y - 1] == ' ';//below left
        }
        return false;
    }

    public boolean canMoveDownRight(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x == table.length - 1 || table[x][y] == p1.getId())//last row, cant go below / p1 pawn cant go below
            return false;
        if (y < table[x].length - 1) {
            return table[x + 1][y + 1] == ' ';//below left
        }
        return false;
    }

    public void move(int[] piece, int[] moveTo) {
        int x = piece[0];
        int y = piece[1];
        int newX = moveTo[0];
        int newY = moveTo[1];
        table[newX][newY] = table[x][y];
        table[x][y] = ' ';
        checkQueenablePawn(newX, newY);
    }

    public boolean canEat(char[][] tab, int[] coords) {
        return canEatUpLeft(tab, coords) || canEatUpRight(tab, coords) || canEatDownLeft(tab, coords) || canEatDownRight(tab, coords);
    }

    public boolean canEatUpLeft(char[][] tab, int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x <= 1 || tab[x][y] == p2.getId())//top 2 rows cant eat above / p2 pawn cant eat above
            return false;
        char piece = tab[x][y];
        if (y > 1 && Character.isLetter(tab[x - 1][y - 1])) {
            return Character.toLowerCase(tab[x - 1][y - 1]) != Character.toLowerCase(piece) && tab[x - 2][y - 2] == ' ';
        }
        return false;
    }

    public boolean canEatUpRight(char[][] tab, int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x <= 1 || tab[x][y] == p2.getId())//top 2 rows cant eat above / p2 pawn cant eat above
            return false;
        char piece = tab[x][y];
        if (y < table[x].length - 2 && Character.isLetter(tab[x - 1][y + 1])) {
            return Character.toLowerCase(tab[x - 1][y + 1]) != Character.toLowerCase(piece) && tab[x - 2][y + 2] == ' ';
        }
        return false;
    }

    public boolean canEatDownLeft(char[][] tab, int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x >= table.length - 2 || tab[x][y] == p1.getId())//bot 2 rows cant eat below / p1 pawn cant eat below
            return false;
        char piece = tab[x][y];
        if (y > 1 && Character.isLetter(tab[x + 1][y - 1])) {
            return Character.toLowerCase(tab[x + 1][y - 1]) != Character.toLowerCase(piece) && tab[x + 2][y - 2] == ' ';
        }
        return false;
    }

    public boolean canEatDownRight(char[][] tab, int[] coords) {
        int x = coords[0];
        int y = coords[1];
        char piece = tab[x][y];
        if (x >= table.length - 2 || tab[x][y] == p1.getId())//bot 2 rows cant eat below / p1 pawn cant eat below
            return false;
        if (y < table[x].length - 2 && Character.isLetter(tab[x + 1][y + 1])) {
            return Character.toLowerCase(tab[x + 1][y + 1]) != Character.toLowerCase(piece) && tab[x + 2][y + 2] == ' ';
        }
        return false;
    }

    public char[][] eat(int[] piece, int[] moveTo) {

        char[][] tab = new char[table.length][table[0].length];
        System.arraycopy(table,0,tab,0,tab.length);
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

        checkQueenablePawn(newX, newY);
        return tab;
    }



    public void checkQueenablePawn(int x, int y) {
        if ((x == 0) && (table[x][y] == p1.getId()))
            table[x][y] = p1.getIdQ();
        else if (x == table.length - 1 && table[x][y] == p2.getId())
            table[x][y] = p2.getIdQ();
    }

}
