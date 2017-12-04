import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class RankingWindow {
    private JFileChooser chooser;
    private JFrame window;
    private JPanel masterPanel;
    private ContentPanel contentPanel;

    public RankingWindow(Ranking r) {
        window = new JFrame();
        masterPanel = new JPanel(new BorderLayout());

        masterPanel.add(createTopButtonPanel(r), BorderLayout.NORTH);

        contentPanel = new ContentPanel();
        contentPanel.setDisplayToCurrentStandings(r);
        masterPanel.add(contentPanel, BorderLayout.CENTER);

        window.add(masterPanel);
        window.setSize(500, 500);
        window.setVisible(true);
    }

    public JPanel createTopButtonPanel(Ranking r) {
        JPanel topButtons = new JPanel();
        JButton addFile = new JButton("Add Tournament File");
        topButtons.add(addFile);

        addFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFile(r);
            }
        });

        JButton tournyStandings = new JButton("Get a specific Tournament's standings");
        tournyStandings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Bracket> brackets = r.getBrackets();
                String[] names = new String[brackets.size()];
                int i = 0;
                for (Bracket b: brackets) {
                    names[i] = b.getTournyName();
                    i ++;
                }
                JComboBox<String> options = new JComboBox<>(names);
                JOptionPane.showOptionDialog(window, options, "Select Tournament", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,null);
                String name = (String) options.getSelectedItem();
                contentPanel.setDisplayToTournamentStandings(name, r);
            }
        });

        topButtons.add(tournyStandings);
        return topButtons;
    }

    public void selectFile(Ranking r) {
        chooser = new JFileChooser();

        // This file filter allows the user to select JPEG files only
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tournament files", "to", "to");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(window);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //open a dialog box to select files
            File file = chooser.getSelectedFile();
            System.out.println(file.getPath());
            int ch;
            try {
                BufferedReader read = new BufferedReader(new FileReader(file));
                BufferedWriter w = new BufferedWriter(new FileWriter("./src/TournamentFiles/" + file.getName()));
            while ( (ch=read.read()) != -1) {
                w.write((char) ch);
            }
            read.close();
            w.flush();
            w.close();
            } catch (IOException e ) {
                e.printStackTrace();
            }
            Ranking curr = new Ranking();
            File dir = new File("./src/TournamentFiles/");
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    curr.getPlacements(dir.getAbsolutePath() + "/" + child.getName());
                }
            }
            curr.getAvgRanking();
            curr.generatePowerRankings(curr.getPlayers());
            r = curr;
            contentPanel.setDisplayToCurrentStandings(r);
        }
    }

}
