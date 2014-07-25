package ass1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 *For joins are good as when a process is done, it can give its resources to another thread.
 * */
public class MParallelSorter2 implements Sorter {

	static final ForkJoinPool mainPool = new ForkJoinPool();

	@Override
	public <T extends Comparable<? super T>> List<T> sort(List<T> list) {
		if(list.size() < 20) return new ISequentialSorter().sort(list);
		class Sort extends RecursiveTask<List<T>>{

			List<T> list;

			Sort(List<T> l){
				list = l;
			}

			@Override
			protected List<T> compute() {
				int middle = list.size() / 2;
				List<T> left = list.subList(0, middle);
				List<T> right = list.subList(middle, list.size());
				return merge(sort(left), sort(right));
			}}
		return mainPool.invoke(new Sort(list));



	}

	private <T extends Comparable<? super T>> List<T> merge(List<T> a, List<T> b) {
		List<T> merged = new ArrayList<T>();
		int leftPointer = 0, rightPointer = 0; //counters of items left in respective lists
		while(leftPointer < a.size() || rightPointer < b.size()) {
			if(leftPointer < a.size() && rightPointer < b.size()) {
				if(a.get(leftPointer).compareTo(b.get(rightPointer)) <= 0.0) {
					merged.add(a.get(leftPointer));
					leftPointer++;
				} else {
					merged.add(b.get(rightPointer));
					rightPointer++;
				}
			} else if(leftPointer+1 <= a.size()) {
				merged.add(a.get(leftPointer));
				leftPointer++;
			} else if(rightPointer+1 <= b.size()) {
				merged.add(b.get(rightPointer));
				rightPointer++;
			}
		}
		return merged;
	}

}
