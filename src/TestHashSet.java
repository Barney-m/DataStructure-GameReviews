
import com.app.adt.set.HashSet;
import com.app.adt.set.ISet;


public class TestHashSet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ISet<String> set = new HashSet<String>();
        
        set.add("love");
        set.add("lovey");
        set.add("lover");
        set.add("loving");
        set.add("loved");
        set.add("love");
        
        System.out.println(set.toString());
    }
    
}
