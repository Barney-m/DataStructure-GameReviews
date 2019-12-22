import com.app.adt.IIterator;
import com.app.adt.list.ArrayList;
import com.app.adt.list.ILinkedList;
import com.app.adt.list.IList;
import com.app.adt.list.LinkedList;
import com.app.adt.map.HashMap;
import com.app.adt.map.IMap;
import com.app.adt.queue.ArrayQueue;
import com.app.adt.queue.IQueue;
import com.app.adt.set.HashSet;
import com.app.adt.set.ISet;
import com.app.adt.stack.ArrayStack;
import com.app.adt.stack.IStack;
import com.app.adt.tree.BinarySearchTree;
import com.app.adt.tree.ITree;

import com.app.model.Review;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.*;
import java.util.Arrays;
import java.util.Scanner;

public class TestEfficiency {

    static IList<Review> reviewList = new ArrayList<>();
    static Review[] r = new Review[100];
    static ITree<Review> search = new BinarySearchTree();
    static ILinkedList<String> link = new LinkedList<>();
    static IMap<Character,IList> categories = new HashMap<>();
    static Scanner keyboard = new Scanner(System.in);
    static IList<String> titleList = new ArrayList<>();
    static long totalTime;
    
    public static void init(){
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
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        init();
	
	try{
            //*********************************
            //*            Hashing            *
            //*********************************
            IIterator<Review> it = reviewList.iterator();
            
            
            while(it.hasNext()){
                titleList.add(it.next().getTitle());
            }
            Object[] arrTitle = titleList.toArray();
            titleList.clear();
            ISet<String> titleSet = new HashSet<>();
            for(int i = 0;i < arrTitle.length;i++){
                if(titleSet.add((String)arrTitle[i])){
                    titleList.add((String)arrTitle[i]);
                };
            }
            
            int k = 0;
            int j = 5;
            int i = 0;
            HashingTime(k,j,i);
            System.out.println("\n\n        The Average Time of 10 Times Loop Hashing is : " 
                    + (totalTime / 10) + " ns\n\n");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //**********************************
        //*             Filter             *
        //**********************************
        try{
            BufferedReader br = new BufferedReader(new FileReader("filter-word.txt"));
            String line = br.readLine();
            String[] unsignificantWord = line.split(" ");
            for(String s : unsignificantWord)
                link.add(s);
            int index = 0;
            int k = 5;
            int x = 0;
            for(int i = 0;i < reviewList.length();i++){
                if(index > 54)
                    break;
                
                String comment = reviewList.get(i).getReview();
                long startTime = System.nanoTime();
                comment = comment.replaceAll("[^a-zA-Z ]", " ");
                reviewList.get(index).setReview(comment);
                FilterTime(index,0);
                long endTime = System.nanoTime();
                if(index == k){
                    x++;
                    System.out.println("The " + x + " Loop Time for Filtering is : " 
                            + (endTime - startTime));
                    k += 5;
                }
                index++;
                totalTime += endTime - startTime;
            }
            
            System.out.println("\n      The Average Time of 10 Times Loop Filtering is : " + totalTime / 10 + " ns\n\n");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        totalTime = 0;
        for(int i = 0;i < 50;i++){
            String[] comments = reviewList.get(i).getReview().toLowerCase().split(" ");
            IList<String> value = new ArrayList<String>();
            for(String data : comments){
                if(!data.equals(""))
                    value.add(data);
            }

            String[] target = value.toArray(new String[value.length()]);
            long startTime = System.nanoTime();
            Object[] sortedComments = reviewList.sort(target);
            long endTime = System.nanoTime();
            totalTime += endTime - startTime;
            String strSortedComments = "";
            for(int j = 0;j < sortedComments.length;j++)
                strSortedComments = sortedComments[j] + " ";
            reviewList.get(i).setReview(Arrays.toString(sortedComments));
        }
        
        System.out.println("        The Average Time of 10 Times Loop Sorting is : " + totalTime / 10 + " ns\n\n");
        totalTime = 0;
        
        //*********************************
        //*        Duplicate Check        *
        //*********************************
        ISet<String> set = new HashSet<>();
        IIterator<Review> it = reviewList.iterator();
        IMap<String,Integer> count = new HashMap<>();
        int i = 0;
        int x = 0;

        while(it.hasNext()){
            if(x > 49)
                break;
            String[] comments = it.next().getReview().replaceAll("[^a-zA-Z,]", "").split(",");
            long startTime = System.nanoTime();
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
            long endTime = System.nanoTime();
            totalTime += endTime - startTime;
            x++;
        }
        System.out.println("The Average Time of 10 Times Loop Duplicate Check is : " + totalTime / 10 + " ns\n\n");
        
    }
    
    public static void HashingTime(int k,int j,int i){
        long startTime = System.nanoTime();
        IList<String> tagList = new ArrayList<>();
        
        while(k < j){
            tagList.clear();
            tagList.add(reviewList.get(k).getTitle());
            categories.put(reviewList.get(k).getTitle().charAt(0), tagList);
            k++;
        }
        long endTime = System.nanoTime();
        totalTime += endTime - startTime;
        j += 5;
        i++;
        if(j < 55)
            HashingTime(k,j,i);
        System.out.println("The " + i + " Loop Time for Hashing is : " + (endTime - startTime));
    }
    
    public static void FilterTime(int index,int start){
        if(start < link.size()){
            reviewList.get(index).setReview(reviewList.get(index).getReview()
                    .toLowerCase().replaceAll("\\b" + link.get(start + 1) + "\\b", ""));
            start++;
            FilterTime(index,start);
        }
    }
    
}
