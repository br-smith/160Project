import java.util.HashMap;
import java.util.Map;

public class Ranking {
    private Player[] players;
    private int playerCount;
    public Ranking(){
        players = new Player[16];
        playerCount = 0;
    }
    public void getPlacements(String tournament){
        Bracket bracket = new Bracket();
        try{
            //bracket.makeBracket("src/TournamentFiles/Tournament1.txt");
            bracket.makeBracket(tournament);
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
                    players[playerCount] = new Player(entry.getKey(), playerCount);
                    players[playerCount].addPlacement(bracket.getTournyName(), entry.getValue());
                    playerCount++;
                }
                flag = 0;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public double[] getAvgRanking(Player[] players){
        int numPlayers = players.length;

        int[] total = new int[numPlayers];
        double[] average = new double[numPlayers];
        for(int i = 0; i < numPlayers; i++) {
            for (Map.Entry<String, Integer> entry : players[i].getPlacements().entrySet()){
                total[i] += entry.getValue();
            }
            average[i] = total[i] / players[i].getNumTourneys();
        }
        return average;
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
