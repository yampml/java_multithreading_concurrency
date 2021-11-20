package thread.creation.example2;

import java.util.Arrays;
import java.util.List;

public class MultiExecutor {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            Thread.currentThread().setName("Thread 1");
            System.out.println("Thread " + Thread.currentThread().getName() + " is running ");
        });

        MultiExecutor multiExecutor = new MultiExecutor(Arrays.asList(thread1));
        multiExecutor.executeAll();
    }

    private List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeAll() {
        for(Runnable r : tasks) {
            r.run();
        }
    }

}
