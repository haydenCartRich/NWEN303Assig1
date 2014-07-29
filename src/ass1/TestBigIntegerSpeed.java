package ass1;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

/**
 * ISequentialSorter on BigIntegers took: 50850 milliseconds on average
 * MParallelSorter1 on BigIntegers took: 179 milliseconds on average
 * MParallelSorter2 on BigIntegers took: 170 milliseconds on average
 * MSequentialSorter on BigIntegers took: 268 milliseconds on average
 *
 * ForkJoin was the fastest followed by the CachedThreadPool. The Sequential Merge sort was a fair bit slower, and
 * Insertion sort was incredibly slow compared to the other three. Fork join should be the fastest as it is designed
 * for divide and conquer algorithms like merge sort. When a thread completes, it gives its resources to other threads
 * which gives it the edge over the CachedThreadPool.
 *
 * The differing speeds between data types is based only on their comparators. The comparators are called the same
 * number of times for each data type, however (for example) the float comparator is significantly faster than
 * that of Strings, Points, and BigIntegers, so the algorithms were quicker on Floats than all the others. This is why
 * the order of ranking between the different algorithms remains the same for each data type (Fastest to slowest is:
 * MParallelSorter2, MParallelSorter1, MSequentialSorter, then ISequentialSorter).
 */

public class TestBigIntegerSpeed {

	//ISequentialSorter test is currently commented out as will take a long time with these values..
	final int RUNS = 5; //number of runs to average result over
	final int SIZE = 100000; //size of test lists (I used 100,000 as gave better results for Parallel vs MSequential than 10,000.)

  BigInteger[][] dataset={
    {new BigInteger("999101101"),new BigInteger("999101201"),new BigInteger("999101301"),new BigInteger("999101401"),new BigInteger("999101501")},
    {new BigInteger("999191101"),new BigInteger("999181201"),new BigInteger("999171301"),new BigInteger("999161401"),new BigInteger("999151501")},
    {new BigInteger("-999101101"),new BigInteger("-999101201"),new BigInteger("999101301"),new BigInteger("-999101401"),new BigInteger("999101501")},
    {new BigInteger("0"),new BigInteger("-999101201"),new BigInteger("1"),new BigInteger("-999101401"),new BigInteger("999101501")},
    {},
    manyOrdered(SIZE),
    manyReverse(SIZE),
    manyRandom(SIZE)
  };
  private BigInteger[] manyRandom(int size) {
    Random r=new Random(0);
    BigInteger[] result=new BigInteger[size];
    for(int i=0;i<size;i++){result[i]=new BigInteger(250,r);}
    return result;
  }
  private BigInteger[] manyReverse(int size) {
    BigInteger[] result=new BigInteger[size];
    for(int i=0;i<size;i++){result[i]=new BigInteger("99999"+(size-i));}
    return result;
  }
  private BigInteger[] manyOrdered(int size) {
    BigInteger[] result=new BigInteger[size];
    for(int i=0;i<size;i++){result[i]=new BigInteger("99999"+i);}
    return result;
  }

  @Test
  public void testISequentialSorter() {
    Sorter s=new ISequentialSorter();
	long startTime = System.currentTimeMillis();
	//for(int i = 0; i < RUNS; i++)
	//	for(BigInteger[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("ISequentialSorter on BigIntegers took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
  }
  @Test
  public void testMSequentialSorter() {
    Sorter s=new MSequentialSorter();
	long startTime = System.currentTimeMillis();
	for(int i = 0; i < RUNS; i++)
		for(BigInteger[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MSequentialSorter on BigIntegers took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
  }
  @Test
  public void testMParallelSorter1() {
    Sorter s=new MParallelSorter1();
	long startTime = System.currentTimeMillis();
	for(int i = 0; i < RUNS; i++)
		for(BigInteger[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MParallelSorter1 on BigIntegers took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
  }
  @Test
  public void testMParallelSorter2() {
    Sorter s=new MParallelSorter2();
	long startTime = System.currentTimeMillis();
	for(int i = 0; i < RUNS; i++)
		for(BigInteger[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MParallelSorter2 on BigIntegers took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
  }
}
