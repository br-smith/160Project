public class Match {
    private int player1ID;
    private int player2ID;
    private int winner;

    public Match(){
        player1ID = -1;
        player2ID = -1;
        winner = -1;
    }
    public Match(int player1ID, int player2ID, int winner){
        this.player1ID = player1ID;
        this.player2ID = player2ID;
        this.winner = winner;
    }

    public int getPlayer1ID() {
        return player1ID;
    }

    public void setPlayer1ID(int player1ID) {
        this.player1ID = player1ID;
    }

    public int getPlayer2ID() {
        return player2ID;
    }

    public void setPlayer2ID(int player2ID) {
        this.player2ID = player2ID;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
}
