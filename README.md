# COP-4520-Spring-2022-Programming-Assignment-1
Nicholas Tran
COP4520

To compile and run the program, use the usual java commands at the CLI:<br/>
`javac Primes.java` <br/>
`java Primes` <br/>

This program uses a multi-threaded implementation of the Sieve of Eratosthenes algorithm (from https://www.geeksforgeeks.org/sieve-of-eratosthenes/) to find prime numbers from 2 up to 10^8. The execution time, total number of primes found, and sum of those primes are outputted to a file called primes.txt in the format:<br/>
\<execution time>  \<total number of primes found>  \<sum of all primes found> <br/>
<top ten maximum primes, listed in order from lowest to highest>

During experimental testing, the program is able to calculate primes up to 10^8 about twice as fast with 8 threads when compared to a single thread.
