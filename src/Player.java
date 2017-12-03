import java.util.HashMap;

public class Player{
    private String name;
    private int playerID;
    private double rank;
    private int numTourneys;
    private HashMap<String, Integer> placements;
    private int elo;

    public Player(){
        name = "";
        rank = 0;
        numTourneys = 0;
        playerID = -1;
        placements = new HashMap<String, Integer>();
        elo = 1000;
    }

    public Player(String name, int playerID){
        this.name = name;
        rank = 0;
        numTourneys = 0;
        this.playerID = playerID;
        placements = new HashMap<String, Integer>();
        elo = 1000;

    }

    public Player(String name, double rank){
        numTourneys = 0;
        placements = new HashMap<String, Integer>();
        this.name = name;
        this.rank = rank;
        playerID = -1;
        elo = 1000;
    }

    public Player(String name, double rank, int playerID){
        this.name = name;
        this.rank = rank;
        this.playerID = playerID;
        placements = new HashMap<String, Integer>();
        numTourneys = 0;
        elo = 1000;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

    public void addPlacement(String tournament,int placement){
        placements.put(tournament, placement);
        numTourneys++;
    }
    public void removePlacement(String tournament){
        placements.remove(tournament);
        numTourneys--;
    }

    public HashMap<String, Integer> getPlacements() {
        return placements;
    }

    public int getNumTourneys() {
        return numTourneys;
    }

    public void setNumTourneys(int numTourneys) {
        this.numTourneys = numTourneys;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }
}
