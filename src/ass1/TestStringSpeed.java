package ass1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

/**
 * ISequentialSorter on Strings took: 47772 milliseconds on average
 * MParallelSorter1 on Strings took: 174 milliseconds on average
 * MParallelSorter2 on Strings took: 170 milliseconds on average
 * MSequentialSorter on Strings took: 265 milliseconds on average
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

public class TestStringSpeed {

	final String ALPHANUM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	//ISequentialSorter test is currently commented out as will take a long time with these values..
	final int RUNS = 5; //number of runs to average result over
	final int SIZE = 100000; //size of test lists (I used 100,000 as gave better results for Parallel vs MSequential than 10,000.)

	String[][] dataset={
			{"a","ab","b","ba","bar"},
			{"foo","f00","fo0","fO0","0Oof"},
			{"123","321","164","015983","999101501"},
			{"a","b","c","d","e"},
			{},
		    manyOrdered(SIZE),
		    manyReverse(SIZE),
		    manyRandom(SIZE)
	};

	private String[] manyOrdered(int size) {
		ArrayList<String> result= new ArrayList<String>();
		for(int i=0;i<size;i++){result.add(generateRandomString());}
		Collections.sort(result);
		return result.toArray(new String[result.size()]);
	}

	private String[] manyReverse(int size) {
		ArrayList<String> result= new ArrayList<String>();
		for(int i=0;i<size;i++){result.add(generateRandomString());}
		Collections.sort(result);
		Collections.reverse(result);
		return result.toArray(new String[result.size()]);
	}

	private String[] manyRandom(int size) {
		String[] resultArray = new String[size];
		for(int i=0;i<size;i++){resultArray[i] = generateRandomString();}
		return resultArray;
	}

	private String generateRandomString() {
		int stringLength = (int) (Math.random() * 51); //maximum string length of 50
		String ranString = "";
		for(int i = 0; i < stringLength; i++){
			ranString += ALPHANUM.charAt((int) (Math.random() * ALPHANUM.length()));
		}
		return ranString;
	}

	@Test
	public void testISequentialSorter() {
		Sorter s=new ISequentialSorter();
		long startTime = System.currentTimeMillis();
		//for(int i = 0; i < RUNS; i++)
		//	for(String[]l:dataset){s.sort(Arrays.asList(l));}
	    System.out.println("ISequentialSorter on Strings took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
	}
	@Test
	public void testMSequentialSorter() {
		Sorter s=new MSequentialSorter();
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < RUNS; i++)
			for(String[]l:dataset){s.sort(Arrays.asList(l));}
	    System.out.println("MSequentialSorter on Strings took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
	}
	@Test
	public void testMParallelSorter1() {
		Sorter s=new MParallelSorter1();
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < RUNS; i++)
			for(String[]l:dataset){s.sort(Arrays.asList(l));}
	    System.out.println("MParallelSorter1 on Strings took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
	}
	@Test
	public void testMParallelSorter2() {
		Sorter s=new MParallelSorter2();
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < RUNS; i++)
			for(String[]l:dataset){s.sort(Arrays.asList(l));}
	    System.out.println("MParallelSorter2 on Strings took: " + (System.currentTimeMillis() - startTime)/RUNS +" milliseconds on average");
	}

}
