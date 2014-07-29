package ass1;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

/**
 * ISequentialSorter on Floats took: 7786 milliseconds on average
 * MParallelSorter1 on Floats took: 177 milliseconds on average
 * MParallelSorter2 on Floats took: 139 milliseconds on average
 * MSequentialSorter on Floats took: 236 milliseconds on average
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

public class TestFloatSpeed {

	//ISequentialSorter test is currently commented out as will take a long time with these values..
	final int RUNS = 5; //number of runs to average result over
	final int SIZE = 100000; //size of test lists (I used 100,000 as gave better results for Parallel vs MSequential than 10,000.)

  Float[][] dataset={
    {1f,2f,3f,4f,5f,6f},
    {7f,6f,5f,4f,3f,2f},
    {-7f,6f,-5f,-4f,3f,-2f},
    {7f/0f,-6f/0f,5f,4f,3f,2f},
    {7f/0f,0f/0f,0f/0f,0f/0f,0f/0f,-5f/0f,4f,3f,2f},
    {},
    manyOrdered(SIZE),
    manyReverse(SIZE),
    manyRandom(SIZE)
  };
  private Float[] manyRandom(int size) {
    Random r=new Random(0);
    Float[] result=new Float[size];
    for(int i=0;i<size;i++){result[i]=r.nextFloat();}
    return result;
  }
  private Float[] manyReverse(int size) {
    Float[] result=new Float[size];
    for(int i=0;i<size;i++){result[i]=(size-i)+0.42f;}
    return result;
  }
  private Float[] manyOrdered(int size) {
    Float[] result=new Float[size];
    for(int i=0;i<size;i++){result[i]=i+0.42f;}
    return result;
  }

  @Test
  public void testISequentialSorter() {
    Sorter s=new ISequentialSorter();
	long startTime = System.currentTimeMillis();
	//for(int i = 0; i < RUNS; i++)
	//	for(Float[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("ISequentialSorter on Floats took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
  }

  @Test
  public void testMSequentialSorter() {
    Sorter s=new MSequentialSorter();
	long startTime = System.currentTimeMillis();
	for(int i = 0; i < RUNS; i++)
		for(Float[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MSequentialSorter on Floats took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
  }
  @Test
  public void testMParallelSorter1() {
    Sorter s=new MParallelSorter1();
	long startTime = System.currentTimeMillis();
	for(int i = 0; i < RUNS; i++)
		for(Float[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MParallelSorter1 on Floats took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
  }
  @Test
  public void testMParallelSorter2() {
    Sorter s=new MParallelSorter2();
	long startTime = System.currentTimeMillis();
	for(int i = 0; i < RUNS; i++)
		for(Float[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MParallelSorter2 on Floats took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
 }

}
