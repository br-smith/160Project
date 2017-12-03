import java.util.Map;

public class Ranking {
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
