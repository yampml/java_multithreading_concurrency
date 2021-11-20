package thread.interrupt.example;

import java.math.BigInteger;

public class JoiningThread2 {

    private static class ComplexCalculation {
        public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
            BigInteger result;

            PowerCalculatingThread t1 = new PowerCalculatingThread(base1, power1);
            PowerCalculatingThread t2 = new PowerCalculatingThread(base2, power2);

            t1.start();
            t1.join();
            result = t1.getResult();
            t2.start();
            t2.join();
            result.add(t2.getResult());


            return result;
        }

        private static class PowerCalculatingThread extends Thread {
            private BigInteger result = BigInteger.ONE;
            private BigInteger base;
            private BigInteger power;

            PowerCalculatingThread(BigInteger base, BigInteger power) {
                this.base = base;
                this.power = power;
            }

            @Override
            public void run() {
                for(BigInteger i=BigInteger.ONE; i.compareTo(power) < 0; i.add(BigInteger.ONE)) {
                    result = result.multiply(base);
                }
            }

            public BigInteger getResult() { return result; }
        }

    }

}
