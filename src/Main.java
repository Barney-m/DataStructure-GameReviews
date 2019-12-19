import com.app.adt.IIterator;
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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
            
            BufferedReader br = new BufferedReader(new FileReader("filter-word.txt"));
            String line = null;
            while((line = br.readLine()) != null){}
            String[] unsignificantWord = line.split(" ");
            
            IQueue<String> queue = new ArrayQueue<String>();
            
            for(int i = 0;i < reviewList.length();i++){
                    filter(unsignificantWord);
                    String[] comments = reviewList.get(i).getReview().toLowerCase().split(" ");
                    Object[] sortedComments = reviewList.sort(comments);
                    String strSortedComments = "";
                    for(int j = 0;j < sortedComments.length;j++)
                        strSortedComments = sortedComments[j] + " ";
                    reviewList.get(i).setReview(Arrays.toString(sortedComments));
            }
            
            
	}catch(Exception e) {
            e.printStackTrace();
	}
        
        try{
            BufferedReader br = new BufferedReader(new FileReader("filter-word.txt"));
            String line = null;
            while((line = br.readLine()) != null){}
            String[] unsignificantWord = line.split(" ");
            filter(unsignificantWord);
        }catch(IOException e){
            
        }
        
        
    }
    
    public static void filter(String[] unsignificantWord){
        LinkedList<String> word = new LinkedList<String>();
        
        for(int i = 0;i < unsignificantWord.length;i++)
            word.add(unsignificantWord[i]);
        
        IIterator<String> iterator = word.iterator();
        
        while(iterator.hasNext())
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
