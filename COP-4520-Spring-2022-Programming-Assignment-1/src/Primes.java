import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

// source: https://www.geeksforgeeks.org/sieve-of-eratosthenes/
class SieveOfEratosthenes {
	static long sum = 0;
	static long count = 0;

	void sieveOfEratosthenes(int n) {
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

		for (int p = 2; p * p <= n; p++) {
			// If prime[p] is not changed, then it is a
			// prime
			if (prime[p] == true) {
				// Update all multiples of p
				for (int i = p * p; i <= n; i += p)
					prime[i] = false;
			}
		}

		// Sum all prime numbers
		for (int i = 2; i <= n; i++) {
			if (prime[i] == true) {
				count++;
				sum += i;
			}
		}
	}

	// Driver Code
	public static void main(String args[]) {
		int n = 100000000;
		SieveOfEratosthenes g = new SieveOfEratosthenes();
		g.sieveOfEratosthenes(n);
	}
}

class NewThread extends Thread {
	static AtomicInteger counter = new AtomicInteger(2);
	static AtomicInteger counter2 = new AtomicInteger(2);
	static AtomicInteger numPrime = new AtomicInteger();
	static AtomicLong sumPrime = new AtomicLong();
	static final int n = 100000000;
	static boolean prime[] = new boolean[n + 1];

	public void run() {
		/*
		 * for (int p = 2; p * p <= n; p++) { // If prime[p] is not changed, then it is
		 * a // prime if (prime[p] == true) { // Update all multiples of p for (int i =
		 * p * p; i <= n; i += p) prime[i] = false; } }
		 */

		for (int p = counter.getAndIncrement(); p * p <= n; p = counter.getAndIncrement()) {
			// If prime[p] is not changed, then it is a
			// prime
			if (prime[p] == true) {
				// Update all multiples of p
				for (int i = p * p; i <= n; i += p)
					prime[i] = false;
			}
		}

		/*
		for (int i = counter2.getAndIncrement(); i <= n; i = counter2.getAndIncrement()) {
			if (prime[i] == true) {
				numPrime.incrementAndGet();
				sumPrime.addAndGet(i);
			}
		}
		*/

	}
}

public class Primes {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		long sum = 0;
		long count = 0;

		// SieveOfEratosthenes.main(null);
		// System.out.println(SieveOfEratosthenes.count);

		ArrayList<Thread> threads = new ArrayList<>();

		for (int i = 0; i <= NewThread.n; i++)
			NewThread.prime[i] = true;

		for (int i = 0; i < 8; i++) {
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

		
		for (int i = 2; i <= NewThread.n; i++) {
			if (NewThread.prime[i] == true) {
				count++;
				sum += i;
			}
		}
		System.out.println(count);
		

		//System.out.println(NewThread.numPrime);

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Execution time = " + elapsedTime);

	}

}