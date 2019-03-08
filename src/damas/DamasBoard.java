package damas;

import damas.misc.CoordinatesComparator;
import lib.Data.ArrayManip;
import lib.Data.ListManip;
import lib.Data.MatrixManip;
import lib.Geometry.Line;
import lib.Geometry.Point;
import proto.Board;
import proto.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import static lib.Misc.IO.*;


public class DamasBoard extends Board {
    public static void main(String[] args) {
        DamasBoard db = new DamasBoard();
        db.printBoard();
        ArrayList<int[]> pieces = db.listOfPieces(DamasPlayer.PLAYER2);
        ListManip.printList(pieces);
        println("");

        ArrayList<int[]> movables = db.listOfMovables(DamasPlayer.PLAYER2);
        ListManip.printList(movables);
        int i = scanInt("Move piece:");

        int[] c1 = movables.get(i);
        ArrayList<int[]> moves = db.listOfMoves(c1);
        ListManip.printList(moves);

        println("");


        int j = scanInt("to pos:");
        int[] ncoords = moves.get(j);
        db.move(c1, ncoords);
        db.printBoard();
        ArrayList<int[]> pieces2 = db.listOfPieces(DamasPlayer.PLAYER2);
        ListManip.printList(pieces2);
        println("");

        ArrayList<int[]> movables2 = db.listOfMovables(DamasPlayer.PLAYER2);
        ListManip.printList(movables2);

    }

    private DamasPlayer p1 = DamasPlayer.PLAYER1;
    private DamasPlayer p2 = DamasPlayer.PLAYER2;

    private int rows;
    private int cols;
    private int fronts;

    char[][] table;

    public DamasBoard() {
        rows = 8;
        cols = 8;
        fronts = 3;
        table = new char[rows][cols];
        clearBoard();
    }

    public DamasBoard(int rows, int cols, int fronts) {
        this.rows = rows;
        this.cols = cols;
        this.fronts = fronts;
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

    public Character isGameOver() {
        int pp1 = 0, pp2 = 0;
        for (char[] chars : table) {
            for (char charac : chars) {
                if (charac == p1.getId())
                    pp1++;
                else if (charac == p2.getId())
                    pp2++;
            }
        }
        if (pp1 == 0)
            return p1.getId();
        else if (pp2 == 0)
            return p2.getId();
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

    public ArrayList<int[]> listOfAttackMoves(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        ArrayList<int[]> moves = new ArrayList<>();
        if (canEatUpLeft(table,coords))
            moves.add(new int[]{x - 2, y - 2});
        if (canEatUpRight(table,coords))
            moves.add(new int[]{x - 2, y + 2});
        if (canEatDownLeft(table,coords))
            moves.add(new int[]{x + 2, y - 2});
        if (canEatDownRight(table,coords))
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
            if (canEat(table,coords))
                attackers.add(coords);
        }
        return attackers;
    }

    public ArrayList<int[]> listOfActionables(DamasPlayer player, boolean isEating){
        TreeSet<int[]> actionables = new TreeSet<>(new CoordinatesComparator());
        if(!isEating) {
            ArrayList<int[]> movables = listOfMovables(player);
            actionables.addAll(movables);
        }
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
        if (x == 0 || table[x][y] == 'o')//top row cant go above / p2 pawn cant go above
            return false;
        if (y > 0) {
            return table[x - 1][y - 1] == ' ';//above left
        }
        return false;
    }

    public boolean canMoveUpRight(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x == 0 || table[x][y] == 'o')//top row cant go above / p2 pawn cant go above
            return false;
        if (y < table[x].length - 1) {
            return table[x - 1][y + 1] == ' ';//above left
        }
        return false;
    }

    public boolean canMoveDownLeft(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x == table.length - 1 || table[x][y] == 'x')//last row, cant go below / p1 pawn cant go below
            return false;
        if (y > 0) {
            return table[x + 1][y - 1] == ' ';//below left
        }
        return false;
    }

    public boolean canMoveDownRight(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x == table.length - 1 || table[x][y] == 'x')//last row, cant go below / p1 pawn cant go below
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
    }

    public boolean canEat(char[][] tab, int[] coords){
        return canEatUpLeft(tab, coords) || canEatUpRight(tab, coords) ||canEatDownLeft(tab, coords) ||canEatDownRight(tab, coords);
    }
    public boolean canEatUpLeft(char[][] tab, int[] coords){
        int x = coords[0];
        int y = coords[1];
        if (x <= 1 || tab[x][y] == 'o')//top 2 rows cant eat above / p2 pawn cant eat above
            return false;
        char piece = tab[x][y];
        if (y > 1 && Character.isLetter(tab[x-1][y-1]) ) {
            return Character.toLowerCase(tab[x - 1][y - 1]) != Character.toLowerCase(piece) && tab[x - 2][y - 2] == ' ';
        }
        return false;
    }

    public boolean canEatUpRight(char[][] tab, int[] coords){
        int x = coords[0];
        int y = coords[1];
        if (x <= 1 || tab[x][y] == 'o')//top 2 rows cant eat above / p2 pawn cant eat above
            return false;
        char piece = tab[x][y];
        if (y < table[x].length - 2 && Character.isLetter(tab[x-1][y+1]) ) {
            return Character.toLowerCase(tab[x - 1][y + 1]) != Character.toLowerCase(piece) && tab[x - 2][y + 2] == ' ';
        }
        return false;
    }

    public boolean canEatDownLeft(char[][] tab, int[] coords){
        int x = coords[0];
        int y = coords[1];
        if (x >= table.length - 2 || tab[x][y] == 'x')//bot 2 rows cant eat below / p1 pawn cant eat below
            return false;
        char piece = tab[x][y];
        if (y > 1 && Character.isLetter(tab[x+1][y-1]) ) {
            return Character.toLowerCase(tab[x + 1][y - 1]) != Character.toLowerCase(piece) && tab[x + 2][y - 2] == ' ';
        }
        return false;
    }

    public boolean canEatDownRight(char[][] tab, int[] coords){
        int x = coords[0];
        int y = coords[1];
        char piece = tab[x][y];
        if (x >= table.length - 2 || tab[x][y] == 'x')//bot 2 rows cant eat below / p1 pawn cant eat below
            return false;
        if (y < table[x].length - 2 && Character.isLetter(tab[x+1][y+1]) ) {
            return Character.toLowerCase(tab[x + 1][y + 1]) != Character.toLowerCase(piece) && tab[x + 2][y + 2] == ' ';
        }
        return false;
    }

    public char[][] eat(char[][] tab, int[] piece, int[] moveTo){
        char[][] temp  = tab.clone();
        int x = piece[0];
        int y = piece[1];
        int newX = moveTo[0];
        int newY = moveTo[1];

        Line line = new Line(new Point(x,y), new Point(newX, newY));
        Point eatPoint = line.middlePoint();
        print(eatPoint.toString());
        int eatX = (int)eatPoint.x;
        int eatY = (int)eatPoint.y;

        temp[newX][newY] = temp[x][y];
        temp[x][y] = temp[eatX][eatY] = ' ';
        return temp;
    }

}
