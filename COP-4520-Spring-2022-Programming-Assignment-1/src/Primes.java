import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


// Adapted from SieveOfEratosthenes (source: https://www.geeksforgeeks.org/sieve-of-eratosthenes/)
// Uses the Sieve Of Eratosthenes algorithm to calculate primes from 2 to n.
class NewThread extends Thread {
	static AtomicInteger counter = new AtomicInteger(2);
	static final int n = (int)Math.pow(10, 8);
	static boolean prime[] = new boolean[n + 1];

	public void run() {
		for (int p = counter.getAndIncrement(); p * p <= n; p = counter.getAndIncrement()) {
			// If prime[p] is not changed, then it is a prime
			if (prime[p] == true) {
				// Update all multiples of p
				for (int i = p * p; i <= n; i += p)
					prime[i] = false;
			}
		}
	}
}

public class Primes {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		long sum = 0;
		long count = 0;
		int numThreads = 8;
		ArrayList<Thread> threads = new ArrayList<>();

		// Initialize array used for prime checking
		for (int i = 2; i <= NewThread.n; i++)
			NewThread.prime[i] = true;

		// Create and run threads
		for (int i = 0; i < numThreads; i++) {
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

		// Calculate sum and total count of primes
		for (int i = 2; i <= NewThread.n; i++) {
			if (NewThread.prime[i] == true) {
				count++;
				sum += i;
			}
		}

		// Find the top ten prime numbers
		int topTen[] = new int[10];
		int index = 0;
		for (int i = NewThread.n; i > 0 && index < 10; i-- ) {
			if (NewThread.prime[i] == true) {
				topTen[index++] = i;
			}
		}
		
		String lastTenPrimes = "";
		for (int i = 9; i >= 0; i--) {
			// System.out.print(topTen[i] + " ");
			lastTenPrimes += topTen[i] + " ";
		}
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;

		// Write output to file
		try {
			String str = "" + elapsedTime + " " + count + " " + sum + "\n" + lastTenPrimes;
			System.out.println(str);
		    FileOutputStream outputStream = new FileOutputStream("primes.txt");
		    byte[] strToBytes = str.getBytes();
		    outputStream.write(strToBytes);

		    outputStream.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

	}

}