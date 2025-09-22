//JuedejaRichard_Module5_9-7-25
//Write a test program that reads words from a text file
//and displays all non-duplicate words in ascending order and then in descending order.


import java.io.*;
import java.util.*;

public class WordsCollection {
    public static void main(String[] args){
        try {
            //call write words method then pass to file
            write_words_to_file("collection_of_words.txt");

            //create set for collection and pass to file
            Set<String> words = read_words_from_file("collection_of_words.txt");

            System.out.println("Words without Duplicates: " + words);
        //made case-insensitive so that it does not matter whether upper or lower case
            Set<String> ascending = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
            ascending.addAll(words);

            System.out.println("Ascending order: " + ascending);

            Set<String> descending = new TreeSet<>(String.CASE_INSENSITIVE_ORDER.reversed());
            descending.addAll(words);

            System.out.println("Descending order: " + descending);

        } catch (IOException e) {

            System.err.println("Error: " + e.getMessage());

        }

    }
    public static Set<String> read_words_from_file(String filename) throws IOException{

     //sets do not allow duplicates and will remove them
        Set<String> words = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        //initialize arrayList
        List<String> original_words = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){

        String line;

     //while collection is not empty read each line
        while((line = reader.readLine()) != null) {
     // \\n separates by line, \\s separates by spaces, + is for more than 1 space
            String[] linewords = line.trim().split("\\s+");
     //adds word/line to set and ArrayList if not empty
           for(String pulled_word : linewords){
     //checking if the word/line is empty
               if(!pulled_word.isEmpty()){

                   words.add(pulled_word);
                   original_words.add(pulled_word);
               }
         }
        }
    }
        System.out.println("Words with Duplicates: " + original_words);
        return words;
   }
    //what in array that is written to the file
   public static void write_words_to_file(String filename) throws IOException{
        String[] test_words = {
                "apples bread strawberry programming is very cool",
                "I like programming",
                "I think programming is cool",
                "Apples are great",
                "bread is amazing",
                "Bread is very great for the health",
                "Food is amazing",
                "Don't you like these sentences"
        };
        //actually does the writing
        try(PrintWriter writer = new PrintWriter(new FileWriter(filename, false))){
            for(String line : test_words){
                writer.println(line);
            }
        }
   }
}
