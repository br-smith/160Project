import java.io.File;

public class TestMain {
    public static void main(String[]args) {
        Ranking r = new Ranking();
        File dir = new File("./src/TournamentFiles/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                r.getPlacements(dir.getAbsolutePath() + "/" + child.getName());
            }
        }
//        r.getPlacements("./src/TournamentFiles/tournament1.txt");
        r.getAvgRanking();
        r.generatePowerRankings(r.getPlayers());
        for (Player p: r.getPowerRankings()) {
            System.out.println(p.getName() + " " +p.getElo() + " " + p.getRank() + " Num of Tournaments: " + p.getNumTourneys());
        }
        for (Bracket b : r.getBrackets()) {
            System.out.println("Tournament: " + b.getTournyName() + " City: " + b.getCityName());
        }
        new RankingWindow(r);
    }
}
