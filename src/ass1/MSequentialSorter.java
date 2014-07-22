package ass1;

import java.util.ArrayList;
import java.util.List;

public class MSequentialSorter implements Sorter {

	@Override
	public <T extends Comparable<? super T>> List<T> sort(List<T> list) {
		if(list.size() <= 1){
			ArrayList<T> l = new ArrayList<T>();
			l.addAll(list);
			return l;
		}
		else {
			int middle = list.size() / 2;
			List<T> left = list.subList(0, middle);
			List<T> right = list.subList(middle, list.size());
			return merge(sort(left), sort(right));
		}
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
