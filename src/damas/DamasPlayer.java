package damas;

public enum DamasPlayer implements Comparable<DamasPlayer> {
    PLAYER0(' '),
    PLAYER1('o'),
    PLAYER2('x');

    char id;
    char idQ;
    char utf;
    char utfQ;

    DamasPlayer(char id) {
        this.id = id;
        idQ = Character.toUpperCase(id);
        if(this.id == 'o'){
            //utf  = '\u25EF';//◯
            utf  = '\u26AA';//⚪
            utfQ = '\u29F2';//⧲
            //utfQ = '\u26E3';//⛣
            //utfQ = '\u25CD';//◍
        }
        else if (this.id == 'x'){
            //utf  = '\u2B24';//⬤
            utf  = '\u26AB';//⚫
            utfQ = '\u29F3';//⧳
            //utfQ = '\u29ED';//⧭
        }
    }

    public static char getCaseSensitiveUTF(char cas) {
        switch (cas) {
            case 'o':
                return PLAYER1.utf;
            case 'O':
                return PLAYER1.utfQ;
            case 'x':
                return PLAYER2.utf;
            case 'X':
                return PLAYER2.utfQ;
            default:
                return ' ';
            }
    }



    public char getId() {
        return id;
    }

    public char getIdQ() {
        return idQ;
    }

    public char getUtf() {
        return utf;
    }

    public char getUtfQ() {
        return utfQ;
    }

}
