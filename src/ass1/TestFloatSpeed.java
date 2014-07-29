package ass1;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class TestFloatSpeed {

  Float[][] dataset={
    {1f,2f,3f,4f,5f,6f},
    {7f,6f,5f,4f,3f,2f},
    {-7f,6f,-5f,-4f,3f,-2f},
    {7f/0f,-6f/0f,5f,4f,3f,2f},
    {7f/0f,0f/0f,0f/0f,0f/0f,0f/0f,-5f/0f,4f,3f,2f},
    {},
    manyOrdered(10000),
    manyReverse(10000),
    manyRandom(10000)
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
    for(Float[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("ISequentialSorter on Floats took: " + (System.currentTimeMillis() - startTime) +"milliseconds ");
  }

  @Test
  public void testMSequentialSorter() {
    Sorter s=new MSequentialSorter();
	long startTime = System.currentTimeMillis();
    for(Float[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MSequentialSorter on Floats took: " + (System.currentTimeMillis() - startTime) +"milliseconds ");
  }
  @Test
  public void testMParallelSorter1() {
    Sorter s=new MParallelSorter1();
	long startTime = System.currentTimeMillis();
    for(Float[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MParallelSorter1 on Floats took: " + (System.currentTimeMillis() - startTime) +"milliseconds ");
  }
  @Test
  public void testMParallelSorter2() {
    Sorter s=new MParallelSorter2();
	long startTime = System.currentTimeMillis();
    for(Float[]l:dataset){s.sort(Arrays.asList(l));}
    System.out.println("MParallelSorter2 on Floats took: " + (System.currentTimeMillis() - startTime) +"milliseconds ");
 }

}
