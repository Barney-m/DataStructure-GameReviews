
import com.app.adt.map.HashMap;
import com.app.adt.map.IMap;

public class TestMap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        IMap<Character,String> map = new HashMap<>(); 
        
        map.put('a', "apple");
        map.put('a', "abc");
        map.put('a', "asd");
        map.put('a', "az");
        
        map.put('b', "blob");
        
        for(int i = 0;i < map.size();i++){
            System.out.println(map.get('b'));
        }
    }
    
}
