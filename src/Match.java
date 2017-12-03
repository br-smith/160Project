import java.util.ArrayList;

public class Match {
    private String player1ID;
    private String player2ID;
    private String winner;
    private final static int K = 32; // The degree to which each match matters in terms of elo (32 is the option for chess)

    public Match(){
        player1ID = "";
        player2ID = "";
        winner = "";
    }

    // Each match will affect a player's elo
    public Match(String player1ID, String player2ID, String winner, ArrayList<Player> players) {
        Player p1 = null;
        Player p2 = null;
        for (Player p : players) {
            if (player1ID.equals(p.getName())) {
                p1 = p;
            } else if (player2ID.equals(p.getName())) {
                p2 = p;
            }
            if (p1 != null && p2 != null) {
                break;
            }
        }
        if (p1 == null){
            p1 = new Player(player1ID, players.size()+1);
            players.add(p1);
        }
        if (p2 == null) {
            p2 = new Player(player2ID, players.size() +1);
            players.add(p2);
        }

        double rating1 = p1.getElo();
        double rating2 = p2.getElo();

        // Get transformed ratings (evidently this makes the elo generation process easier)
        rating1 = Math.pow(10, rating1 / 400);
        rating2 = Math.pow(10, rating2 / 400);

        // Calculating expected scores:
        double expected1 = rating1 / (rating1 + rating2);
        double expected2 = rating2 / (rating1 + rating2);


        this.player1ID = player1ID;
        this.player2ID = player2ID;
        this.winner = winner;
        // each player gets a score as per actual result. 1 for win, 0 for loss
        int score1 = (player1ID.equals(winner) ? 1 : 0);
        int score2 = (player2ID.equals(winner) ? 1 : 0);

        int finalRating1 = (int)Math.round(p1.getElo() + K * (score1 - expected1));
        int finalRating2 = (int)Math.round(p2.getElo() + K * (score2 - expected2));

        p1.setElo(finalRating1);
        p2.setElo(finalRating2);
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
