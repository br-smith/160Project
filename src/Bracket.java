import jdk.nashorn.internal.parser.JSONParser;
//import org.json.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;

public class Bracket {
    private ArrayList<Match> bracket;
    private HashMap<String, Integer> placements;
    private int playerCount;
    private String tournyName;
    int tournyParticipants;
    String address;

    public Bracket() {
        bracket = new ArrayList<Match>();
        placements = new HashMap<>();
    }

    public HashMap<String, Integer> getPlacements() {
        return placements;
    }

    //@SuppressWarnings("unchecked");
    public void makeBracket(String tournamentFileName, ArrayList<Player> players) throws IOException {
//        JSONParser parser = new JSONParser();
        // This will read the lines of the text file
        BufferedReader reader = new BufferedReader(new FileReader(tournamentFileName));
        // This code takes the first two lines, which give tourny info and address info, to the appropriate places
        String line;
        line = reader.readLine();
        System.out.println(line);
        String[] tournyInfo = line.split(",");
        tournyName = tournyInfo[0];
        tournyParticipants = Integer.valueOf(tournyInfo[1].replace(" ", ""));
        address = reader.readLine();

        // This code looks at the remaining lines to add matches to the bracket
        while ((line = reader.readLine()) != null) {
            String [] playerInfo = line.split(",");
            String player1 = playerInfo[0].replaceAll(" ","");
            String player2 = playerInfo[1].replaceAll(" ", "");
            boolean found1 = false;
            boolean found2 = false;
            for (Player p: players) {
                if (p.getName().equals(player1)) {
                    found1 = true;
                } else if (p.getName().equals(player2)){
                    found2 = true;
                }
            }
            // Need to add the players if we missed them
            if (!found1) {
                Player p1 = new Player(player1, ++playerCount);
                players.add(p1);
            } if (!found2) {
                Player p2 = new Player(player2, ++playerCount);
                players.add(p2);
            }

            bracket.add(new Match(playerInfo[0].replaceAll(" ", ""),
                    playerInfo[1].replaceAll(" ", ""),
                    playerInfo[2].replaceAll(" ", ""), players));
        }
    }

    //precondition: ArrayList<Match> bracket has been made using the previous function
    public void makePlacements() {
        for (Match m: bracket) {
            String winner = m.getWinner();
            String loser = (m.getPlayer1ID().equals(m.getWinner()) ? m.getPlayer2ID() : m.getPlayer1ID());
            if (!placements.containsKey(winner)){
                int place = (placements.size() < 4 ? placements.size() + 1 : (placements.size() >= 4 && placements.size() < 6) ? 5 : 7);
                placements.put(winner, place);
            }
            if (!placements.containsKey(loser)) {
                int place = (placements.size() < 4 ? placements.size() + 1 : (placements.size() >= 4 && placements.size() < 6) ? 5 : 7);
                placements.put(loser, place);
            }
        }
    }

//    public static void main(String[]args) throws IOException {
//        Bracket b = new Bracket();
////        b.makeBracket("./TournamentFiles/Tournament1.txt", );
//        b.makePlacements();
//        Iterator it = b.getPlacements().entrySet().iterator();
//        while (it.hasNext()) {
//            HashMap.Entry pair = (HashMap.Entry)it.next();
//            System.out.println(pair.getKey() + " = " + pair.getValue());
//            it.remove(); // avoids a ConcurrentModificationException
//        }
//
//    }

    public void setPlacements(HashMap<String, Integer> placements) {
        this.placements = placements;
    }

    public String getTournyName() {
        return tournyName;
    }

    public void setTournyName(String tournyName) {
        this.tournyName = tournyName;
    }
}
