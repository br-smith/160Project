import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Ranking {
//    private Player[] players;
    private double[] averages;
    private ArrayList<Bracket> brackets;
    private PlayerComparer pc;
    private ArrayList<Player> players;
    private TreeSet<Player> PowerRankings;
    private int playerCount;


    public Ranking(){
        players = new ArrayList<Player>();
        playerCount = 0;
        pc = new PlayerComparer();
        PowerRankings = new TreeSet<Player>(pc);
        averages = new double[16];
        brackets = new ArrayList<>();
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public TreeSet<Player> getPowerRankings() {
        return PowerRankings;
    }

    public ArrayList<Bracket> getBrackets() {
        return brackets;
    }

    // Update the whole rankings object after we get a new tournament file
    public void updateRankings(String tournament) {
        getPlacements(tournament);
        getAvgRanking();
        generatePowerRankings(players);
    }

    public void getPlacements(String tournament){
        Bracket bracket = new Bracket();
        try{
            //bracket.makeBracket("src/TournamentFiles/Tournament1.to");
            bracket.makeBracket(tournament, players);
            bracket.makePlacements();
            int flag = 0;
            HashMap<String, Integer> placements = bracket.getPlacements(); //This returns a HashMap with player's placements for a certain tourney
            for (Map.Entry<String, Integer> entry : placements.entrySet()) { // For each entry in the HashMap...
                for (Player player : players) { // ...see if the player is already in the master list
                    if (player.getName().equals(entry.getKey())) { // if so, add his placement to his Player object
                        player.addPlacement(bracket.getTournyName(), entry.getValue());
                        flag = 1;
                    }
                }
                if (flag == 0) { // if player not found, add him to the master list and add this placement
                    players.add(new Player(entry.getKey(), playerCount));
                    players.get(playerCount).addPlacement(bracket.getTournyName(), entry.getValue());
//                    players[playerCount] = new Player(entry.getKey(), playerCount);
//                    players[playerCount].addPlacement(bracket.getTournyName(), entry.getValue());
                    playerCount++;
                }
                flag = 0;
            }
            brackets.add(bracket);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public double[] getAvgRanking(){
        int numPlayers = players.size();

        int[] total = new int[numPlayers];
        double[] average = new double[numPlayers];
        for(int i = 0; i < numPlayers; i++) {
//            for (Map.Entry<String, Integer> entry : players[i].getPlacements().entrySet()){
            for (Map.Entry<String, Integer> entry : players.get(i).getPlacements().entrySet()) {
                total[i] += entry.getValue();
            }

            average[i] = total[i] / players.get(i).getNumTourneys();
            players.get(i).setRank(average[i]);
        }
        averages = average;
        return average;
    }

    // Needs to have run getAvgRanking already
    public TreeSet<Player> generatePowerRankings (ArrayList<Player> players) {
        TreeSet<Player> rankings = new TreeSet<Player>(pc);
        for (Player p: players) {
            p.setElo((int) Math.abs(p.getElo() + 100 * (Math.log(1/p.getRank()))  - 50 * brackets.size() / p.getNumTourneys()));
            rankings.add(p);
        }
        PowerRankings = rankings;
        return PowerRankings;
    }

    public void setPowerRankings(TreeSet<Player> powerRankings) {
        PowerRankings = powerRankings;
    }

    public void setBrackets(ArrayList<Bracket> brackets) {
        this.brackets = brackets;
    }

    public double[] getAverages() {
        return averages;
    }

    public void setAverages(double[] averages) {
        this.averages = averages;
    }
//    public double[] getAvgRanking(int[][] tourneys){
//        int numPlayers = tourneys.length;
//
//        int[] total = new int[numPlayers];
//        double[] average = new double[numPlayers];
//        for(int i = 0; i < numPlayers; i++){
//            for(int j = 0; j < tourneys[i].length; j++){
//                total[i] += tourneys[i][j];
//            }
//            average[i] = total[i] / tourneys[i].length;
//        }
//        return average;
//    }
    /* JUST A PRACTICE MAIN METHOD TO ENSURE FUNCTIONALITY
    public static void main(String[] args){
        Ranking r = new Ranking();
        int[][] rankings = new int[][]{
                {1, 2, 3, 4, 5},
                {2, 4, 6, 8, 10},
                {3, 6, 9, 12, 15}
        };
        double[] avgRank;

        avgRank = r.getAvgRanking(rankings);
        for(int i = 0; i < avgRank.length; i++) {
            System.out.println(String.valueOf(avgRank[i]));
        }
        System.out.println("Damn you're fine");
    }
    */
}
