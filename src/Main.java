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

public class Main {
    static long totalTime = 0;
    static IList<Review> reviewList = new ArrayList<>();
    static Review[] r = new Review[100];
    static ITree<Review> search = new BinarySearchTree();
    static ILinkedList<String> link = new LinkedList<>();
    static IMap<Character,IList> categories = new HashMap<>();
    static Scanner keyboard = new Scanner(System.in);
    
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
            IList<String> titleList = new ArrayList<>();
            
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
            
            
            IIterator<String> tagIterator = titleList.iterator();
            long startTime = System.nanoTime();
            for(int i = 65;i < 91;i++){
                IList<String> tagList = new ArrayList<>();
                char ascii = (char) i;
                for(int k = 0;k < titleList.length();k++){
                    if(titleList.get(k).charAt(0) == ascii){
                        tagList.add(titleList.get(k));
                    }
                }
                if(!tagList.isEmpty())
                    categories.put(ascii, tagList);
            }
            long endTime = System.nanoTime();
            System.out.println("\nThe Total Time used for Indexing all data is : " 
                    + (endTime - startTime) + " ns");
            int l = 0;
            int input;
            System.out.println("\n\n\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~ Steam Game Review Checking System ~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
            do{
                System.out.println("**************************************");
                System.out.println("*                                    *");
                System.out.println("*  Choose a tag or exit the program  *");
                System.out.println("*                                    *");
                System.out.println("**************************************");
                char[] tag = new char[27];
                l = 0;
                for(int i = 65;i < 91;i++){
                    if(categories.containsKey((char)i)){
                        tag[l] = (char)i;
                        System.out.println(l + 1 + ". " + tag[l]);
                        l++;
                    }
                }
                System.out.println(l + 1 + ". Exit");
                System.out.print("Enter: ");
                input = keyboard.nextInt();
                
                DisplayTitle(tag[input - 1]);
                
            }while(input < (l + 1));
            
            
        }catch(Exception e) {
            e.printStackTrace();
	}
            
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
        
        
        
    }
    
    public static void DisplayTitle(char tag){
        try{
            String[] title = categories.get(tag).toString().split(",");
            int input;
            int i = 0;
            do{
                System.out.println("*************************************");
                System.out.println("*                                   *");
                System.out.println("*    Choose a game to see review    *");
                System.out.println("*                                   *");
                System.out.println("*************************************");
                i = 0;
                while(i < title.length){
                    System.out.println(i + 1 + ". " + title[i]);
                    i++;
                }
                System.out.println(i + 1 + ". Back");
                System.out.print("Enter: ");
                input = keyboard.nextInt();
                if(input < (i + 1))
                    ShowGameReview(title[input - 1]);
            }while(input < (i + 1));
        }catch(Exception e){
            
        }
    }
    
    public static void ShowGameReview(String title){
        IIterator<Review> iter = reviewList.iterator();
        IList<Review> processList = new ArrayList<>();
        while(iter.hasNext()){
            Review r;
            if((r = iter.next()).getTitle().equals(title))
                processList.add(r);
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
            IIterator<Review> iterator = processList.iterator();
            int index = 0;
            long startTime = System.nanoTime();
            while(iterator.hasNext()){
                String comment = iterator.next().getReview();
                comment = comment.replaceAll("[^a-zA-Z ]", " ");
                processList.get(index).setReview(comment);
                filter(index,0,processList);
                index++;
            }
            long endTime = System.nanoTime();
            System.out.println("\n\nThe Total Time used for Filter all data is : " + (endTime - startTime) + " ns\n\n");
            
        }catch(Exception e){
            e.printStackTrace();
        }

        //*********************************
        //*            Sorting            *
        //*********************************
        int x = 0;
        for(int i = 0;i < processList.length();i++){
            String[] comments = processList.get(i).getReview().toLowerCase().split(" ");
            IList<String> value = new ArrayList<String>();
            for(String data : comments){
                if(!data.equals(""))
                    value.add(data);
            }

            String[] target = value.toArray(new String[value.length()]);
            long startTime = System.nanoTime();
            Object[] sortedComments = processList.sort(target);
            long endTime = System.nanoTime();
            if(i < 10){
                totalTime += endTime - startTime;
                System.out.println("The Loop " + (i + 1) + " of Sorting Used Time is : " 
                        + (endTime - startTime) + " ns");
                x++;
            }
            String strSortedComments = "";
            for(int j = 0;j < sortedComments.length;j++)
                strSortedComments = sortedComments[j] + " ";
            processList.get(i).setReview(Arrays.toString(sortedComments));
        }
        
        System.out.println("\nThe Average of " + x + " Loop of Sorting is : " 
                + totalTime / x + " ns");
        //*********************************
        //*        Duplicate Check        *
        //*********************************
        ISet<String> set = new HashSet<>();
        IIterator<Review> it = processList.iterator();
        IMap<String,Integer> count = new HashMap<>();
        int i = 0;
        

        long startTime = System.nanoTime();
        while(it.hasNext()){
            String[] comments = it.next().getReview().replaceAll("[^a-zA-Z,]", "").split(",");
            
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
        long endTime = System.nanoTime();
        System.out.println("\n\nThe Total Time used for Remove all Duplicated data is : "
                + (endTime - startTime) + " ns");
        System.out.println("\n\n*************************");
        System.out.println(title + " - Game Review");
        System.out.println("*************************");
        try{
            BufferedReader br = new BufferedReader(new FileReader("recommendation-word.txt"));
            String[] positive = br.readLine().split(" ");
            String[] negative = br.readLine().split(" ");
            int positiveCount = 0;
            int negativeCount = 0;
            for(int j = 0;j < processList.length();j++){
                String[] words = processList.get(j).getReview().replaceAll("[^a-zA-Z,]", "").split(",");
                positiveCount += positiveCheck(positive,words);
                negativeCount += negativeCheck(negative,words);
            }
            
            double totalReview = positiveCount + negativeCount;
            double reviewScore = positiveCount / totalReview;
            double rating = reviewScore - (reviewScore - 0.5) * pow(2,-log10(totalReview + 1));
            
            if(positiveCount > negativeCount)
                System.out.println("The " + title + " game is Positive\n");
            else if(positiveCount < negativeCount)
                System.out.println("The " + title + " game is Negative\n");
            else
                System.out.println("The " + title + " has Mixed review\n");
            
            System.out.println("Total Reviews: " + String.format("%.0f", totalReview));
            System.out.println("Review Score: " + String.format("%.2f", reviewScore));
            
            
            System.out.println("Rating: " + String.format("%.2f", rating));
            
            System.out.println("\n****************************");
            System.out.println("         Top Review         ");
            System.out.println("****************************");
            Review review = topReview(title);
            System.out.println("Date Posted:\t" + review.getDatePosted());
            System.out.println("     Review:\t" + review.getReview());
            System.out.println("       Game:\t" + review.getTitle());
            System.out.print("Press any key to continue....");
            System.in.read();
        }catch(Exception e){
            
        }
    }
    
    public static void filter(int index,int start,IList<Review> processList){
        if(start < link.size()){
            processList.get(index).setReview(processList.get(index).getReview().toLowerCase().replaceAll("\\b" + link.get(start + 1) + "\\b", ""));
            start++;
            filter(index,start,processList);
        }
    }
    
    public static int positiveCheck(String[] positive,String[] words){
        int positiveCount = 0;
        for (String word : words) {
            for (String p : positive) {
                if (word.equals(p)) {
                    positiveCount++;
                    break;
                }
            }
        }
        return positiveCount;
    }
    
    public static int negativeCheck(String[] negative,String[] words){
        int negativeCount = 0;
        for (String word : words) {
            for (String n : negative) {
                if (word.equals(n)) {
                    negativeCount++;
                    break;
                }
            }
        }
        return negativeCount;
    }
    
    public static Review topReview(String title){
        
        IIterator<Review> it = reviewList.iterator();
        Review review = new Review();
        int max = 0;
        while(it.hasNext()){
            Review r = it.next();
            if(r.getTitle().equals(title))
                max = Math.max(max, r.getHelpful());
            
        }
        
        it = reviewList.iterator();
        while(it.hasNext()){
            Review r = it.next();
            if(max == r.getHelpful())
                review = r;
        }
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader("steam_reviews.csv"));
            String rowData = null;
            String[] dataByRow;
            reader.readLine();
            while((rowData = reader.readLine()).charAt(0) != ',') {
                dataByRow = rowData.split(",");
		Review r = new Review(dataByRow[0],Integer.parseInt(dataByRow[1]),Integer.parseInt(dataByRow[2]),Integer.parseInt(dataByRow[3]),Boolean.parseBoolean(dataByRow[4].toLowerCase()),dataByRow[5],dataByRow[6],dataByRow[7]);
		if(r.getHelpful() == max && r.getTitle().equals(title))
                    review = r;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return review;
    }
}