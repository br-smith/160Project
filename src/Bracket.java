import org.json.*;

import java.io.FileReader;
import java.io.InputStream;

public class Bracket {
    private Match[] bracket;
    private int[] placements;

    //@SuppressWarnings("unchecked");
    public int[] makeBracket(String tournament){
        JSONParser parser = new JSONParser();

        try{
            Object obj = parser.parse(new FileReader(tournament));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
