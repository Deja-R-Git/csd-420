//JuedejaRichard_Module4_9-02-25
//Write a test program that stores 50,000 integers in LinkedList
//and test the time to traverse the list using an iterator vs. using the get(index) method

import java.util.*;
public class LinkedListProgram {
    public static void main(String[] args) {
        //call performance test method and pass size variables to populate list
        // underscore is recognized as a comma for numbers in Java
        performance_test(50_000);
        performance_test(500_000);

    }

    public static void testIteratorTraversal(LinkedList<Integer> list) {
//long is needed to handle integer overflow (more than 2 billion numbers)
        long start_time = System.nanoTime();
//iterator object
        Iterator<Integer> iter = list.iterator();

        long sum = 0;

        while(iter.hasNext()) {

            sum+= iter.next();

        }

        long end_time = System.nanoTime();

        long duration = end_time - start_time;

        System.out.println("Iterator Traversal completed. Sum is:" + sum);
        System.out.println("Time Taken: " + duration + " nanoseconds (" + String.format("%.2f", duration/1_000_000.0) +
                " milliseconds)");

    }

    public static void testGetMethod(LinkedList<Integer> list) {
//start time
        long start_time = System.nanoTime();

        long sum = 0;

       for( int i = 0; i< list.size(); i++) {
           sum+=list.get(i);
        }
//end time
        long end_time = System.nanoTime();
//subtracts both times to get duration
        long duration = end_time - start_time;

        System.out.println("Get method Traversal completed. Sum is:" + sum);

        //1 milisecond equal 1million nanoseconds so we divide by that
        System.out.println("Time Taken: " + duration + " nanoseconds (" + String.format("%.2f", duration/1_000_000.0) +
                " milliseconds)");

    }

    public static void performance_test(int size){
        //creates actual list
        LinkedList<Integer> list = new LinkedList<>();

        System.out.println("Testing performance of LinkedList for size " + size);
//for loop for adding integers to list
        for(int i = 0; i < size; i++){
            list.add(i);
        }
//calling test methods
        System.out.println("Testing Iterator");
                testIteratorTraversal(list);

        System.out.println("Testing GetMethod");
                testGetMethod(list);
    }
}

//Measured the amount of time to add all the integers in the List with 50,000 and 500,000 integers
//With Iterator it took 4.39 miliseconds  for 50,000 and 7.63 miliseconds for 500,000
//With getMethod it took 838.62 miliseconds for 50,000 and 87670.50 milliseconds for 500,000
//For 50,000 Iterator was 210x Faster than getMethod
//For 500,000 Iterator is much faster than the getMethod by 12,000x