import com.app.adt.list.ArrayList;
import com.app.adt.list.ILinkedList;
import com.app.adt.list.IList;
import com.app.adt.list.LinkedList;
import com.app.adt.queue.ArrayQueue;
import com.app.adt.queue.IQueue;
import com.app.adt.stack.ArrayStack;
import com.app.adt.stack.IStack;

import com.app.model.Review;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Main {
    
    static IList<Review> reviewList = new ArrayList<>();
    public static void main(String[] args) {
        BufferedReader reader = null;
		
	try{
            reader = new BufferedReader(new FileReader("steam_reviews.csv"));
            String rowData = null;
            String[] dataByRow;
            reader.readLine();
            Review review;
			
            while((rowData = reader.readLine()).charAt(0) != ',') {
                dataByRow = rowData.split(",");
		review = new Review(dataByRow[0],Integer.parseInt(dataByRow[1]),Integer.parseInt(dataByRow[2]),Integer.parseInt(dataByRow[3]),Boolean.parseBoolean(dataByRow[4].toLowerCase()),dataByRow[5],dataByRow[6],dataByRow[7]);
		reviewList.add(review);
                
            }
            
            IQueue<String> queue = new ArrayQueue<String>();
            
            for(int i = 0;i < reviewList.length();i++){
                if(reviewList.get(i).getTitle().equals("Grand Theft Auto V")){
                    String[] comments = reviewList.get(i).getReview().toLowerCase().split(" ");
                    Object[] sortedComments = reviewList.sort(comments);
                    String strSortedComments = "";
                    for(int j = 0;j < sortedComments.length;j++)
                        strSortedComments = sortedComments[j] + " ";
                    reviewList.get(i).setReview(Arrays.toString(sortedComments));
                    System.out.println(strSortedComments);
                    queue.add(Arrays.toString(comments));
                }
                
            }
            System.out.print(reviewList.get(0).getReview());
            
	}catch(Exception e) {
            e.printStackTrace();
	}
        
        
    }
    
    public static void insertionSort(String[] data){
        int i;
        String key;
        for(int j = 1;j < data.length;j++){
            key = data[j];
            i = j - 1;
            while(i >= 0){
                if(key.compareTo(data[i]) > 0){
                    break;
                }
                data[i + 1] = data[i];
                i--;
            }
            data[i + 1] = key;
        }
    }
//    
//    public static void quickSort(String[] data,int start,int end){
//        int i = start;
//        int j = end;
//        
//        // only examine arrays of 2 or more elements.
//        if(end - start >= i){
//            // The pivot point of the sort method is arbitrarily set to the first element int the array.
//            String pivot = data[i];
//            
//            
//            while(j > i){
//                // from the left, if the current element is lexicographically less than the (original)
//                while(data[i].compareTo(pivot) <= 0 && i < end && j > i)
//                    i++;
//                // from the right, if the current element is lexicographically greater than the (original)
//                while(data[j].compareTo(pivot) >= 0 && j > start && j >= i)
//                    j--;
//                // check the two elements in the center, the last comparison before the scans cross.
//                if(j > i)
//                    swap(data,i,j);
//            }
//            // Swap the pivot point with the last element of the left partition.
//            swap(data,start,j);
//            // sort left partition
//            quickSort(data,start,j-1);
//            // sort right partition
//            quickSort(data,j+1,end);
//        }
//    }
//    
//    private static void swap(String[] data,int i,int j){
//        String tmp = data[i];
//        data[i] = data[j];
//        data[j] = tmp;
//    }
    
}
