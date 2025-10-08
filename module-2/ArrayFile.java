//Juedeja Richard_Module2_8-29-25
//Write a program that stores:
//An array of five random integers.
//An array of five random double values.
//Write a second program that will read the file and display the data.
import java.io.*;
import java.util.Random;

//public class
public class ArrayFile{
//main method for writing and reading arrays to a file
    public static void main(String[] args) {
//set array size dynamically instead of hardcoded
        int array_size = 5;
//initialized integers and doubles array
        int[] integers_array = new int[array_size];
        double[] doubles_array = new double[array_size];
//create random object
        Random rand = new Random();
//for loop for creating 5 random integers and 5 doubles in arrays
        for(int i = 0; i < array_size; i++) {

            integers_array[i] = rand.nextInt(1000); // Random Integers from 0 to 999
            doubles_array[i] = rand.nextDouble() * 1000; // Random doubles 0.0-1000.0

        }

        String file_name = "JuedejaRichard_datafile.dat";
//calls read and write methods created below with parameters
        writeArraysToFile(integers_array, doubles_array, file_name);

        readArraysFromFile(file_name, array_size);

    }

    private static void writeArraysToFile(int[] int_array, double[] double_array, String file_name) {
// true makes filewriter append data while false would erase existing data
        try(PrintWriter writer = new PrintWriter(new FileWriter(file_name, true))) {

// Write the integers
            writer.println("Integers:");

            for(int i = 0; i < int_array.length; i++) {
                writer.println(int_array[i]);
            }

// Write the doubles
            writer.println("Doubles:");
            for(int i = 0; i < double_array.length; i++) {

                writer.println(double_array[i]);
            }

            System.out.println("Wrote successfully to " + file_name);

        } catch (IOException e) {

            System.err.println("We have an error writing to the file, error message is: " + e.getMessage());

        }
    }

    private static void readArraysFromFile(String file_name, int array_size) {
//try-catch block for exception where might get wrong number type when reading the file
        try(BufferedReader reader = new BufferedReader(new FileReader(file_name))) {

            int[] loadedIntegers = new int[array_size];
            double[] loadedDoubles = new double[array_size];

            String line;
            boolean readingInt = false;
            boolean readingDouble = false;
            int intIndex = 0;
            int doubleIndex = 0;
//checks if file is empty
            while((line = reader.readLine()) != null) {
//if line numbers are integers set boolean value to true and double to false
                if(line.equals("Integers:")) {
                    readingInt = true;
                    readingDouble = false;
                }
//if line numbers are doubles set boolean value to true and integers to false
                else if (line.equals("Doubles:")) {
                    readingInt = false;
                    readingDouble = true;
                }
//if there are unread integers or double in the file, read the next line
                else if (readingInt && intIndex < array_size) {
//increment integers index
                    loadedIntegers[intIndex++] = Integer.parseInt(line);
                }

                else if (readingDouble && doubleIndex < array_size) {
//increment doubles index
                    loadedDoubles[doubleIndex++] = Double.parseDouble(line);

                }

            }


            System.out.println("Integers: ");
//for loop for displaying integer array
            for(int i = 0; i < loadedIntegers.length; i++) {
                System.out.println(loadedIntegers[i]);
            }

            System.out.println("Doubles: ");
//for loop for displaying doubles array
            for(int i = 0; i < loadedDoubles.length; i++) {
                System.out.printf("%.2f%n", loadedDoubles[i]);
            }
//error with reading or writing to file
        } catch (IOException e) {

            System.err.println("Error Reading the file, error message is: " + e.getMessage());
//error where you may have an integer instead of a double and vice versa
        } catch (NumberFormatException e) {

            System.err.println("Error parsing the numbers, error message is: " + e.getMessage());
        }

    }

}
