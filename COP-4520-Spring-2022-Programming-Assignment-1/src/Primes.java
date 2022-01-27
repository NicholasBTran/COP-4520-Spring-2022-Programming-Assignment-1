import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

// source: https://www.geeksforgeeks.org/sieve-of-eratosthenes/
class SieveOfEratosthenes {
	static long sum = 0;
    void sieveOfEratosthenes(int n)
    {
        // Create a boolean array
        // "prime[0..n]" and
        // initialize all entries
        // it as true. A value in
        // prime[i] will finally be
        // false if i is Not a
        // prime, else true.
        boolean prime[] = new boolean[n + 1];
        for (int i = 0; i <= n; i++)
            prime[i] = true;
 
        for (int p = 2; p * p <= n; p++)
        {
            // If prime[p] is not changed, then it is a
            // prime
            if (prime[p] == true)
            {
                // Update all multiples of p
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }
 
        // Sum all prime numbers
        for (int i = 2; i <= n; i++)
        {
            if (prime[i] == true)
            	sum += i;
        }
    }
 
    // Driver Code
    public static void main(String args[])
    {
        int n = 100000000;
        SieveOfEratosthenes g = new SieveOfEratosthenes();
        g.sieveOfEratosthenes(n);
    }
}

class NewThread extends Thread {
	static AtomicInteger counter = new AtomicInteger();
	static AtomicInteger numPrime = new AtomicInteger();
	static AtomicLong sumPrime = new AtomicLong();

	public void run() {
		int i = counter.getAndIncrement();
		while (i < 100000000) {
			try {
				if (isPrime(i)) {
					numPrime.getAndIncrement();
					sumPrime.addAndGet(i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			i = counter.getAndIncrement();
		}
	}

	// Determine if a number is prime.
	static boolean isPrime(int n) {
		if (n < 2)
			return false;
		if (n == 2 || n == 3)
			return true;
		if (n % 2 == 0 || n % 3 == 0)
			return false;
		long sqrtN = (long) Math.sqrt(n) + 1;
		for (long i = 6L; i <= sqrtN; i += 6) {
			if (n % (i - 1) == 0 || n % (i + 1) == 0)
				return false;
		}
		return true;
	}
}

public class Primes {

	public static void main(String[] args) {
/*
		long startTime = System.currentTimeMillis();
		ArrayList<Thread> threads = new ArrayList<>();

		for (int i = 0; i < 16; i++) {
			NewThread t = new NewThread();
			t.setName("MyThread-" + i);
			t.start();
			threads.add(t);
		}
		
		for (Thread i : threads) {
			try {
				i.join(); // Wait for threads to finish
			} catch (Exception e) {
				System.out.println("[Exception]: " + e);
			}
		}

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Execution time = " + elapsedTime);
		System.out.println(NewThread.numPrime.get());
		System.out.println(NewThread.sumPrime.get());
		*/

		long startTime = System.currentTimeMillis();
		SieveOfEratosthenes.main(null);
		System.out.println(SieveOfEratosthenes.sum);
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Execution time = " + elapsedTime);
	}

}