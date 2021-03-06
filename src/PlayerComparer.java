import java.util.Comparator;

public class PlayerComparer implements Comparator<Player> {
    public int compare(Player p1, Player p2){
        if (p1.getElo() < p2.getElo()) return -1;
        else if (p1.getElo() > p2.getElo()) return 1;
        else { // in the case where their elos are the same (Probably when it starts off)
            if (p1.getRank() < p2.getRank()) {
                return -1;
            } else if (p1.getRank() > p2.getRank()) {
                return 1;
            } else {
                if (p1.getPlayerID() < p2.getPlayerID()) {
                    return -1;
                } else if (p1.getPlayerID() > p2.getPlayerID()){
                    return 1;
                }
                return 0;
            }
        }
    }
}
