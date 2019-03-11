package proto;

public enum SimplePlayer implements IPlayer {
    PLAYER0(' '),
    PLAYER1('X'),
    PLAYER2('O');

    private char id;

    SimplePlayer(char id) {
        this.id = id;
    }

    public char getId() {
        return id;
    }
}
