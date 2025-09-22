//Juede

//In this class, you are to use three threads to output three types of characters to a text area for display.
//In the first thread, you are to output random letter characters such as a, b, c, d
//In the second thread, you are to output random number digits such as 0, 1, 2, 3, 4, 5, 6, 7, 8, 9.
//In the third thread, you are to output random characters such as !, @, #, $, %, &, *
//Display a minimum of 10,000 for each of the three sets.
//Write test code that ensures all methods function correctly.

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

public class JuedejaThreeThreads extends Application {

    private TextArea textArea;
//need a private final monitor object to coordinate objects between threads, other threads have to wait
//can only be acquired and released, not editable
    private final Object lock = new Object();

    @Override

    public void start(Stage primary_stage) {
//create text area on stage
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefRowCount(20);
        textArea.setPrefColumnCount(80);
//places children elements in a single, vertical column
        VBox root = new VBox(textArea);
//create a scene
        Scene scene = new Scene(root, 600,400);
//sets stage name, scene and display it
        primary_stage.setTitle("Three Threads Output");
        primary_stage.setScene(scene);
        primary_stage.show();
//call thread methods
        startLetterThread();
        startNumberThread();
        startSymbolThread();

    }

    private void startLetterThread() {

        Task<Void> letterTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
//random object
                Random random = new Random();
//for loop
                for(int i = 0; i < 10000; i++) {

                    char letter = (char) ('a' + random.nextInt(26));

                    Platform.runLater(() -> {

                        synchronized (lock) {

                            textArea.appendText(String.valueOf(letter));
                        }

                    });

                    Thread.sleep(1);

                }

                return null;


            }

        };

        Thread letterThread = new Thread(letterTask);
        letterThread.setDaemon(true);
        letterThread.start();

    }

    private void startNumberThread() {

        Task<Void> numberTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
//create random object for generating random numbers
                Random random = new Random();
//for loop
                for(int i = 0; i < 10000; i++) {
//creates random number between 0-9
                    int number = random.nextInt(10);

                    Platform.runLater(() -> {

                        synchronized (lock) {

                            textArea.appendText(String.valueOf(number));
                        }

                    });

                    Thread.sleep(1);

                }

                return null;


            }

        };

        Thread numberThread = new Thread(numberTask);
//daemon threads are low priority and service user threads
        numberThread.setDaemon(true);
//executes the thread with start
        numberThread.start();

    }

    private void startSymbolThread() {

        Task<Void> symbolTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
//create symbol array
                char[] symbols = {'!', '@','#', '$', '%', '&','*'};
                Random random = new Random();

//for loop for generating 10,000 symbols in text area
                for(int i = 0; i < 10000; i++) {

                    char symbol = symbols[random.nextInt(symbols.length)];
//runlater() will executes code after setting up Java scene and Stage to stop UI corruption/thread conflicts
                    Platform.runLater(() -> {
//locking the thread so they do not overwrite each other
                        synchronized(lock) {

                            textArea.appendText(String.valueOf(symbol));
                        }

                    });
//sleep lets us see the characters as they are being generated
                    Thread.sleep(1);

                }
//not returning any result in a Void method
                return null;


            }

        };

        Thread symbolThread = new Thread(symbolTask);
        symbolThread.setDaemon(true);
        symbolThread.start();

    }
//main method
    public static void main(String[] args) {
//instantiated class and calls start method on javafx application thread
        launch(args);

    }


}
