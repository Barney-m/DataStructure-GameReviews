
public class filtertest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String digit = "";
        for(int i = 1;i <= 100;i++)
            digit += i;
        
        digit += "abc";
        digit += "!@##%$^-1       1ab";
        
        digit = digit.replaceAll("[^a-zA-Z ]", "");
        System.out.println(digit);
    }
    
}
