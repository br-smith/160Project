import java.util.HashMap;

public class Player {
    private String name;
    private int playerID;
    private double rank;
    private HashMap<String, Integer> placements;

    public Player(){
        name = "";
        rank = 0;
        playerID = -1;
        placements = new HashMap<String, Integer>();
    }

    public Player(String name, double rank){
        this.name = name;
        this.rank = rank;
        playerID = -1;
    }

    public Player(String name, double rank, int playerID){
        this.name = name;
        this.rank = rank;
        this.playerID = playerID;
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
    }
    public void removePlacement(String tournament){
        placements.remove(tournament);
    }
}
