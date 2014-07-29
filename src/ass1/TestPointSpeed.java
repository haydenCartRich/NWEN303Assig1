package ass1;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;
class PointCopy implements Comparable<PointCopy>{
  public PointCopy(long x, long y) {
    super();
    this.x = x;
    this.y = y;
  }
  long x;
  long y;
  @Override
  public int compareTo(PointCopy other) {
    return new Long(this.x*this.x+this.y*this.y).compareTo(other.x*other.x+other.y*other.y);
  }

  /**
   * ISequentialSorter on Points took: 34295 milliseconds on average
   * MParallelSorter1 on Points took: 196 milliseconds on average
   * MParallelSorter2 on Points took: 146 milliseconds on average
   * MSequentialSorter on Points took: 250 milliseconds on average
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

}
public class TestPointSpeed {

	//ISequentialSorter test is currently commented out as will take a long time with these values..
	final int RUNS = 5; //number of runs to average result over
	final int SIZE = 100000; //size of test lists (I used 100,000 as gave better results for Parallel vs MSequential than 10,000.)

  PointCopy[][] dataset={
    {new PointCopy(10,10),new PointCopy(20,30),new PointCopy(30,30),new PointCopy(40,40),new PointCopy(50,50),new PointCopy(60,60)},
    {new PointCopy(110,10),new PointCopy(120,3),new PointCopy(130,3),new PointCopy(140,140),new PointCopy(-50,150),new PointCopy(3260,-260)},
    {new PointCopy(0,0),new PointCopy(0,3),new PointCopy(-130,3),new PointCopy(-140,-140)},
    {},
    manyOrdered(SIZE),
    manyReverse(SIZE),
    manyRandom(SIZE)
  };
  private PointCopy[] manyRandom(int size) {
    Random r=new Random(0);
    PointCopy[] result=new PointCopy[size];
    for(int i=0;i<size;i++){result[i]=new PointCopy(r.nextLong(),r.nextLong());}
    return result;
  }
  private PointCopy[] manyReverse(int size) {
    PointCopy[] result=new PointCopy[size];
    for(int i=0;i<size;i++){result[i]=new PointCopy(size-i,size*3-i);}
    return result;
  }
  private PointCopy[] manyOrdered(int size) {
    PointCopy[] result=new PointCopy[size];
    for(int i=0;i<size;i++){result[i]=new PointCopy(i*3,i*2);}
    return result;
  }

  @Test
  public void testISequentialSorter() {
    Sorter s=new ISequentialSorter();
	long startTime = System.currentTimeMillis();
	//for(int i = 0; i < RUNS; i++)
	//	for(PointCopy[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("ISequentialSorter on Points took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
  }
  @Test
  public void testMSequentialSorter() {
    Sorter s=new MSequentialSorter();
	long startTime = System.currentTimeMillis();
	for(int i = 0; i < RUNS; i++)
		for(PointCopy[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MSequentialSorter on Points took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
  }
  @Test
  public void testMParallelSorter1() {
    Sorter s=new MParallelSorter1();
	long startTime = System.currentTimeMillis();
	for(int i = 0; i < RUNS; i++)
		for(PointCopy[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MParallelSorter1 on Points took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
  }
  @Test
  public void testMParallelSorter2() {
    Sorter s=new MParallelSorter2();
	long startTime = System.currentTimeMillis();
	for(int i = 0; i < RUNS; i++)
		for(PointCopy[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MParallelSorter2 on Points took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
  }

}
