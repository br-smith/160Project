public class Player {
    private String name;
    private int playerID;
    private double rank;
    private int[] placements;

    public Player(){
        name = "";
        rank = 0;
        playerID = -1;
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
}
