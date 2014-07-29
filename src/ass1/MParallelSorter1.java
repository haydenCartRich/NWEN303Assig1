package ass1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * By implementing merge sort using a cachedThreadPool I learned how to submit Callable tasks to a pool
 * and how to use the the Future type to get the result from a task when it is available (when the future is now).
 * Using a cachedThreadPool is advantageous over the sequential implementation of merge sort as it is able to
 * process the different branches of the algorithm at the same time (concurrently). The use of a ExecutorService
 * CachedThreadPool as opposed to just making a new thread means that the library can do all the 'hard stuff' for
 * so you don't create errors yourself by poor concurrent programming making threads. The only thing to check for
 * is that Future.get() actually gives you a value.
 */
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
