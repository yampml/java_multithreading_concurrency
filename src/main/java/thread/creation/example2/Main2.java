package thread.creation.example2;

import java.util.Random;

public class Main2 {
    public static final int MAX_PASSWORD = 9999;

    public static void main(String[] args) {
        Random random = new Random();

        Vault vault = new Vault(random.nextInt(9999));
        System.out.println(vault.getPassword());
        HackerThread hacker1 = new AscendingHackerThread(vault);
        HackerThread hacker2 = new DescendingHackerThread(vault);
        PoliceThread police = new PoliceThread();

        hacker1.start();
        hacker2.start();
        police.start();
    }

    private static class Vault {
        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public int getPassword() { return this.password; }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
            }
            return this.password == guess;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread {
        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess=0; guess<MAX_PASSWORD; guess++) {
                if(vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {

        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess=MAX_PASSWORD; guess>=0; guess--) {
                if(vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {

            @Override
            public void start() {
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println(this.getName() + " is counting " + i);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Game over");
                System.exit(0);
            }
    }
}
