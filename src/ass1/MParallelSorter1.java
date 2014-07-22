package ass1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MParallelSorter1 implements Sorter {

	ExecutorService pool = Executors.newCachedThreadPool();

	@Override
	public <T extends Comparable<? super T>> List<T> sort(final List<T> list) {
		if(list.size() < 20) return new ISequentialSorter().sort(list);
		final int middle = list.size() / 2;
		Future<List<T>> leftFuture = pool.submit(new Callable<List<T>>() {
			@Override
			public List<T> call() throws Exception {
				return sort(list.subList(0, middle));
			}
		});
		Future<List<T>> rightFuture = pool.submit(new Callable<List<T>>() {
			@Override
			public List<T> call() throws Exception {
				return sort(list.subList(middle, list.size()));
			}
		});

		List<T> left;
		List<T> right;

		try {
			left = leftFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			left = sort(list.subList(0, middle)); //sequential
		}

		try {
			right = rightFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			right = sort(list.subList(middle, list.size())); //sequential
		}

		return merge(left, right);
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
