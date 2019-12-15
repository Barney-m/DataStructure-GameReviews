import com.app.adt.list.ArrayList;
import com.app.adt.list.IList;
import com.app.adt.map.*;
public class TestADT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IMap<Character,IList<String>> categories = new HashMap<Character,IList<String>>();
        
        IList<String> game = new ArrayList<>();
        IList<String> tmp = new ArrayList<>();
        game.add("Apple");
        game.add("ABC");
        game.add("A123");
        game.add("Boyyy");
        game.add("Biii");
        for(int i = 0;i < game.length();i++){
            tmp.add(game.get(i));

                categories.put(tmp.get(i).charAt(0),tmp);
                

            
        }
        tmp.clear();
        
        
        System.out.println(categories);
        
        System.out.println(categories.get('A'));
    }
    
}
