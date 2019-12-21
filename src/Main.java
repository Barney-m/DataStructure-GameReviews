import com.app.adt.IIterator;
import com.app.adt.list.ArrayList;
import com.app.adt.list.IList;
import com.app.adt.map.HashMap;
import com.app.adt.map.IMap;
import com.app.adt.queue.ArrayQueue;
import com.app.adt.queue.IQueue;
import com.app.adt.set.HashSet;
import com.app.adt.set.ISet;
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
            
            IIterator<Review> it = reviewList.iterator();
            IList<String> titleList = new ArrayList<>();
            IMap<Character,IList> categories = new HashMap<>();
            while(it.hasNext()){
                titleList.add(it.next().getTitle());
            }
            System.out.println(titleList);
            Object[] arrTitle = titleList.toArray();
            
            for(int i = 0;i < titleList.length();i++){
                for(int k = i + 1;k < titleList.length();k++){
                    if(titleList.get(i).equals(titleList.get(k)))
                        titleList.remove(k);
                }
            }
            
            System.out.println(titleList);
            
            //**********************************
            //*             Filter             *
            //**********************************
            BufferedReader br = new BufferedReader(new FileReader("filter-word.txt"));
            String line = br.readLine();
            String[] unsignificantWord = line.split(" ");
            
            IIterator<Review> iterator = reviewList.iterator();
            int index = 0;
            while(iterator.hasNext()){
                String comment = iterator.next().getReview();
                comment = comment.replaceAll("[^a-zA-Z ]", " ");
                reviewList.get(index).setReview(comment);
                filter(unsignificantWord,index,0);
                index++;
            }
            
            //*********************************
            //*            Sorting            *
            //*********************************
            for(int i = 0;i < reviewList.length();i++){
                String[] comments = reviewList.get(i).getReview().toLowerCase().split(" ");
                IList<String> value = new ArrayList<String>();
                for(String data : comments){
                    if(!data.equals(""))
                        value.add(data);
                }

                String[] target = value.toArray(new String[value.length()]);
                Object[] sortedComments = reviewList.sort(target);
                String strSortedComments = "";
                for(int j = 0;j < sortedComments.length;j++)
                    strSortedComments = sortedComments[j] + " ";
                reviewList.get(i).setReview(Arrays.toString(sortedComments));
            }
            
	}catch(Exception e) {
            e.printStackTrace();
	}
        
        
        //*********************************
        //*        Duplicate Check        *
        //*********************************
        ISet<String> set = new HashSet<>();
        IIterator<Review> it = reviewList.iterator();
        IMap<String,Integer> count = new HashMap<>();
//        while(it.hasNext()){
//            String[] comments = it.next().getReview().replaceAll("[^a-zA-Z,]", "").split(",");
//            int duplicate = 0;
//            for(int i = 0;i < comments.length;i++){
//                if(count.containsKey(comments[i]))
//                    duplicate = count.get(comments[i]);
//                for(int k = i + 1;k < comments.length;k++){
//                    if(!set.add(comments[i])){
//                        duplicate++;
//                    }
//                    else if(comments[i].length() >= 5){
//                        if(comments[k].length() >= 5){
//                            if(comments[i].substring(0,5).compareTo(comments[k].substring(0,5)) == 0)
//                                duplicate++;
//                        }
//                    }
//                    else if(comments[i].length() == 4){
//                        if(comments[k].length() >= 4){
//                            if(comments[i].substring(0,4).compareTo(comments[k].substring(0,4)) == 0)
//                                duplicate++;
//                        }
//                    }
//                    else if(comments[i].length() == 3){
//                        if(comments[k].length() >= 3){
//                            if(comments[i].substring(0,3).compareTo(comments[k].substring(0,3)) == 0)
//                                duplicate++;
//                        }
//                    }
//                    else if(comments[i].length() == 2){
//                        if(comments[k].length() >= 2){
//                            if(comments[i].substring(0,2).compareTo(comments[k].substring(0,2)) == 0)
//                                duplicate++;
//                        }
//                    }
//                }
//                count.put(comments[i], duplicate);
//                duplicate = 0;
//            }
//        }
int i = 0;
int x = 0;
//        while(it.hasNext()){
//            String[] comments = it.next().getReview().replaceAll("[^a-zA-Z,]", "").split(",");
//            System.out.println(reviewList.get(x).getReview());
//            for(String c : comments){
//                if(c.equals("game"))
//                    i++;
//                
//            }
//            x++;
//            
//            for (String comment : comments) {
//                if (comment.length() == 5) {
//                    if (!set.add(comment)) {
//                        duplicate = count.get(comment);
//                        duplicate++;
//                        count.put(comment, duplicate);
//                    }
//                    else {
//                        count.put(comment, 1);
//                    }
//                } else if (comment.length() == 4) {
//                    if (!set.add(comment.substring(0, 4))) {
//                       duplicate = count.get(comment);
//                       duplicate++;
//                       count.put(comment, duplicate);
//                    }
//                    else {
//                        count.put(comment, 1);
//                    }
//                } else if (comment.length() == 3) {
//                    if (!set.add(comment.substring(0, 3))) {
//                        duplicate++;
//                        count.put(comment, duplicate);
//                    }
//                    else {
//                        count.put(comment, 1);
//                    }
//                } else if (comment.length() == 2) {
//                    if (!set.add(comment.substring(0, 2))) {
//                        duplicate = count.get(comment);
//                        duplicate++;
//                        count.put(comment, duplicate);
//                    }
//                    else {
//                        count.put(comment, 1);
//                    }
//                }
//            }
//        }

        while(it.hasNext()){
            String[] comments = it.next().getReview().replaceAll("[^a-zA-Z,]", "").split(",");
            
            for(String game : comments){
                
                if(game.equals("gameplay")){
                    i++;
                }
            }
            for(String c : comments){
                if(!set.add(c)){
                    int duplicate = count.get(c);
                    duplicate++;
                    count.put(c, duplicate);
                }
                else{
                    count.put(c, 1);
                }
            }
        }
        
        System.out.println("Duplicate: " + count.get("gameplay"));
        System.out.println("Actual Number: " + i);
    }
    
    public static void filter(String[] unsignificantWord,int index,int start){
        if(start < unsignificantWord.length){
            reviewList.get(index).setReview(reviewList.get(index).getReview().toLowerCase().replaceAll("\\b" + unsignificantWord[start] + "\\b", ""));
            start++;
            filter(unsignificantWord,index,start);
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
