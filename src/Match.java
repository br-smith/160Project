public class Match {
    private String player1ID;
    private String player2ID;
    private String winner;

    public Match(){
        player1ID = "";
        player2ID = "";
        winner = "";
    }
    public Match(String player1ID, String player2ID, String winner){
        this.player1ID = player1ID;
        this.player2ID = player2ID;
        this.winner = winner;
    }

    public String getPlayer1ID() {
        return player1ID;
    }

    public void setPlayer1ID(String player1ID) {
        this.player1ID = player1ID;
    }

    public String getPlayer2ID() {
        return player2ID;
    }

    public void setPlayer2ID(String player2ID) {
        this.player2ID = player2ID;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
