import com.app.adt.list.LinkedList;
import com.app.model.Review;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = null;
		
	try{
            reader = new BufferedReader(new FileReader("steam_reviews.csv"));
            String rowData = null;
            String[] dataByRow;
            reader.readLine();
            Review review;
			
            LinkedList<Review> reviewList = new LinkedList<>();
			
            while((rowData = reader.readLine()).charAt(0) != ',') {
                dataByRow = rowData.split(",");
				
		review = new Review(dataByRow[0],Integer.parseInt(dataByRow[1]),Integer.parseInt(dataByRow[2]),Integer.parseInt(dataByRow[3]),Boolean.parseBoolean(dataByRow[4].toLowerCase()),dataByRow[5],dataByRow[6],dataByRow[7]);
		reviewList.addLast(review);

            }
	}catch(Exception e) {
            e.printStackTrace();
	}
    }
    
}
