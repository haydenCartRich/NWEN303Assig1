package ass1;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class TestString {

	final String ALPHANUM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


	String[][] dataset={
			{"a","ab","b","ba","bar"},
			{"foo","f00","fo0","fO0","0Oof"},
			{"123","321","164","015983","999101501"},
			{"a","b","c","d","e"},
			{},
		    manyOrdered(10000),
		    manyReverse(10000),
		    manyRandom(10000)
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
		for(String[] l:dataset){TestHelper.testData(l,s);}
	}
	@Test
	public void testMSequentialSorter() {
		Sorter s=new MSequentialSorter();
		for(String[] l:dataset){TestHelper.testData(l,s);}
	}
	@Test
	public void testMParallelSorter1() {
		Sorter s=new MParallelSorter1();
		for(String[] l:dataset){TestHelper.testData(l,s);}
	}
	@Test
	public void testMParallelSorter2() {
		Sorter s=new MParallelSorter2();
		for(String[] l:dataset){TestHelper.testData(l,s);}
	}

}
