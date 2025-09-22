//JuedejaRichard_Module3_8-29-25
//Write a test program that contains a static method that returns a new ArrayList
//The new ArrayList returned will contain all original values with no duplicates from the original ArrayList. 
//Fill the Original ArrrayList with 50 random values from 1 to 20.

import java.lang.Math.*;
import java.util.*;

public class ArrayListProgram {

    public static void main(String[] args) {
    //sets ArrayList size
        int arrayListSize = 50;

        ArrayList<Integer> intList = new ArrayList<Integer>(arrayListSize);
    // initialize random object
        Random rand = new Random();
    //for loop to populate ArrayList
        for (int i = 0; i < arrayListSize; i++) {
            int random_value = rand.nextInt(20) + 1;
            intList.add(random_value);
        }
        System.out.println("Original ArrayList:"+ intList);

        //passing integerlist to method remove_duplicates then print
        ArrayList<Integer> no_duplicates = remove_duplicates(intList);
        System.out.println("New ArrayList:" + no_duplicates);
    }
    //method that removes duplicated values in the ArrayList
    public static <E> ArrayList<E> remove_duplicates(ArrayList<E> list) {
    Set<E> unique_elements = new LinkedHashSet<>(list);

    return new ArrayList<>(unique_elements);
    }

}
