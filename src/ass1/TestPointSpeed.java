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

}
public class TestPointSpeed {

  PointCopy[][] dataset={
    {new PointCopy(10,10),new PointCopy(20,30),new PointCopy(30,30),new PointCopy(40,40),new PointCopy(50,50),new PointCopy(60,60)},
    {new PointCopy(110,10),new PointCopy(120,3),new PointCopy(130,3),new PointCopy(140,140),new PointCopy(-50,150),new PointCopy(3260,-260)},
    {new PointCopy(0,0),new PointCopy(0,3),new PointCopy(-130,3),new PointCopy(-140,-140)},
    {},
    manyOrdered(10000),
    manyReverse(10000),
    manyRandom(10000)
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
    for(PointCopy[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("ISequentialSorter on Points took: " + (System.currentTimeMillis() - startTime) +"milliseconds ");
  }
  @Test
  public void testMSequentialSorter() {
    Sorter s=new MSequentialSorter();
	long startTime = System.currentTimeMillis();
    for(PointCopy[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MSequentialSorter on Points took: " + (System.currentTimeMillis() - startTime) +"milliseconds ");
  }
  @Test
  public void testMParallelSorter1() {
    Sorter s=new MParallelSorter1();
	long startTime = System.currentTimeMillis();
    for(PointCopy[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MParallelSorter1 on Points took: " + (System.currentTimeMillis() - startTime) +"milliseconds ");
  }
  @Test
  public void testMParallelSorter2() {
    Sorter s=new MParallelSorter2();
	long startTime = System.currentTimeMillis();
    for(PointCopy[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MParallelSorter2 on Points took: " + (System.currentTimeMillis() - startTime) +"milliseconds ");
  }

}
