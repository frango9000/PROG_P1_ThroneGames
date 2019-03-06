package damas;

import lib.Data.ListManip;
import proto.Board;
import proto.Player;

import java.util.ArrayList;
import java.util.Arrays;

import static lib.Misc.IO.println;
import static lib.Misc.IO.scanInt;


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

    private char[][] table;

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

    public ArrayList<int[]> listOfMovables(DamasPlayer player) {
        ArrayList<int[]> pieces = listOfPieces(player);
        ArrayList<int[]> movables = new ArrayList<>();

        for (int[] coords : pieces) {
            if (isMovable(coords))
                movables.add(coords);
        }
        return movables;
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
        int x = coords[0];
        int y = coords[1];
        if (table[x][y] == 'x') {
            if (canMoveUp(coords))
                return true;
        } else if (table[x][y] == 'o') {
            if (canMoveDown(coords))
                return true;
        } else if (table[x][y] == 'X' || table[x][y] == 'O') {
            if (canMoveUp(coords) || canMoveDown(coords))
                return true;
        }
        return false;
    }

    public boolean canMoveUp(int[] coords) {
        return canMoveUpLeft(coords) || canMoveUpRight(coords);
    }

    public boolean canMoveDown(int[] coords) {
        return (canMoveDownLeft(coords) || canMoveDownRight(coords));
    }

    public boolean canMoveUpLeft(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x == 0)//top row cant go above
            return false;
        if (y > 0) {
            return table[x - 1][y - 1] == ' ';//above left
        }
        return false;
    }

    public boolean canMoveUpRight(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x == 0)//top row cant go above
            return false;
        if (y < table[x].length - 1) {
            return table[x - 1][y + 1] == ' ';//above left
        }
        return false;
    }

    public boolean canMoveDownLeft(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x == table.length - 1)//last row, cant go below
            return false;
        if (y > 0) {
            return table[x + 1][y - 1] == ' ';//below left
        }
        return false;
    }

    public boolean canMoveDownRight(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if (x == table.length - 1)//last row, cant go below
            return false;
        if (y < table[x].length - 1) {
            return table[x + 1][y + 1] == ' ';//below left
        }
        return false;
    }

    public void move(int[] coords, int[] newcoords) {
        int x = coords[0];
        int y = coords[1];
        int nx = newcoords[0];
        int ny = newcoords[1];
        table[nx][ny] = table[x][y];
        table[x][y] = ' ';
    }

}
