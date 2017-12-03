public class TestMain {
    public static void main(String[]args) {
        Ranking r = new Ranking();
        r.getPlacements("./src/TournamentFiles/tournament1.txt");
        r.getAvgRanking(r.getPlayers());
        r.generatePowerRankings(r.getPlayers());
        for (Player p: r.getPowerRankings()) {
            System.out.println(p.getName() + " " +p.getElo());
            System.out.println(r.getPowerRankings().size());
        }
    }
}
