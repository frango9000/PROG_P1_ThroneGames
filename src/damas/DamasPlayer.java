package damas;

public enum DamasPlayer implements Comparable<DamasPlayer> {
    PLAYER0(' '),
    PLAYER1('x'),
    PLAYER2('o');

    char id;
    char idQ;

    DamasPlayer(char id) {
        this.id = id;
        idQ = Character.toUpperCase(id);
    }

    public char getId() {
        return id;
    }

    public char getIdQ() {
        return idQ;
    }


}
