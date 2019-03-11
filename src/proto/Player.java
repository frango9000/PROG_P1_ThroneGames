package proto;

public class Player implements IPlayer,
        Comparable<Player> {

    private char id;

    public Player() {
        this(1);
    }

    public Player(int n) {
        switch (n) {
            case 1:
                id = 'o';
                break;
            case 2:
                id = 'x';
                break;
        }
    }

    public char getId() {
        return id;
    }

    @Override
    public int compareTo(Player o) {
        return this.id - o.getId();
    }
}
