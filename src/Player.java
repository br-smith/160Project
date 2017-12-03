import java.util.HashMap;

public class Player {
    private String name;
    private int playerID;
    private double rank;
    private int numTourneys;
    private HashMap<String, Integer> placements;

    public Player(){
        name = "";
        rank = 0;
        numTourneys = 0;
        playerID = -1;
        placements = new HashMap<String, Integer>();
    }

    public Player(String name, int playerID){
        this.name = name;
        rank = 0;
        numTourneys = 0;
        this.playerID = playerID;
        placements = new HashMap<String, Integer>();
    }

    public Player(String name, double rank){
        numTourneys = 0;
        placements = new HashMap<String, Integer>();
        this.name = name;
        this.rank = rank;
        playerID = -1;
    }

    public Player(String name, double rank, int playerID){
        this.name = name;
        this.rank = rank;
        this.playerID = playerID;
        placements = new HashMap<String, Integer>();
        numTourneys = 0;
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
}
