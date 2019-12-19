
import com.app.adt.list.ArrayList;
import com.app.adt.list.IList;
import com.app.adt.map.HashMap;
import com.app.adt.map.IMap;

public class TestMap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        IList<String> list = new ArrayList<String>();
        
        IMap<Character,IList> map = new HashMap<>(); 
        list.add("apple");
        list.add("apple1");
        list.add("apple2");
        map.put('a', list);

        
        for(int i = 0;i < map.length();i++){
            System.out.println(map.get('a'));
        }
    }
    
}
