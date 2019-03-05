package proto;

public enum Player {
    PLAYER0(' '),
    PLAYER1('X'),
    PLAYER2('O');

    char id;

    Player(char id) {
        this.id = id;
    }

    public char getId() {
        return id;
    }
}
