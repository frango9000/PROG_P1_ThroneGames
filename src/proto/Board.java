package proto;

public abstract class Board {
    protected int rows;
    protected int cols;
    protected char[][] table;

    public abstract void clearBoard();

    public abstract boolean isFull();

    public abstract void printBoard();

    public abstract Character isGameOver();

    public abstract boolean validTurn();

}
