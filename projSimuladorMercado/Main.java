package pct;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        ExecutorService application = Executors.newFixedThreadPool(2);

        //
        Buffer 

        System.out.println("Action\t\tValue\tProduced\tConsumed");
        System.out.println("------\t\t-----\t--------\t--------\n");

        // try to start producer and consumer giving each of them access
        // to sharedLocation
        try {
            application.execute(new Producer(sharedLocation));
            application.execute(new Consumer(sharedLocation));

        } // end try
        catch (Exception exception) {
            exception.printStackTrace();
        } // end catch

        application.shutdown(); // terminate application when threads end
    }
}
