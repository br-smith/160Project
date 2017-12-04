import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

public class ContentPanel extends JPanel {
    JTable display;
    JScrollPane scrolly;

    public void setDisplayToCurrentStandings(Ranking r) {
        TreeSet<Player> players = r.getPowerRankings();
        String [] cols = {"Player Name", "Average Rank", "ELO"};
        String [][] rows = new String[players.size()] [cols.length];
        int i = players.size()-1;
        for (Player p : players){
            rows[i][0] = p.getName();
            rows[i][1] = String.valueOf(p.getRank());
            rows[i][2] = String.valueOf(p.getElo());
            i--;
        }
        display = new JTable(rows, cols);
        redraw();
    }

    public void setDisplayToTournamentStandings(String tournament, Ranking r) {
        PlayerComparer pc = new PlayerComparer();
        TreeSet<Player> placements = new TreeSet<Player>(pc);
        String[] cols = {"Placement", "Player"};
        Bracket curr = null;
        for (Bracket b : r.getBrackets()) {
            if (b.getTournyName().equals(tournament)) {
                curr = b;
                break;
            }
        }
        assert curr != null;
        String [][]rows = new String[curr.getPlacements().size()][cols.length];
        int i = 0;
        for (Map.Entry<String, Integer> e : curr.getPlacements().entrySet()) {
            Player p = new Player(e.getKey(), i);
            p.setRank(e.getValue());
            placements.add(p);
            i++;
        }
        i = 0;
        for (Player p: placements) {
            rows[i][1] = String.valueOf(p.getName());
            rows[i][0] = String.valueOf(p.getRank());
            i++;
        }
        display = new JTable(rows, cols);
        redraw();
    }

    public void redraw() {
        removeAll();
        scrolly = new JScrollPane(display);
        scrolly.add(new JScrollBar());
        add(scrolly);
        revalidate();
        repaint();
    }


}
