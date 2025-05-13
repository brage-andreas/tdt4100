package assignment5.debugging;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StringMergingIterator implements Iterator<String> {

	private Iterator<String> first;
	private Iterator<String> second;
	private boolean useFirstIterator;

	public StringMergingIterator(Iterator<String> first, Iterator<String> second) {
		this.first = first;
		this.second = second;
		this.useFirstIterator = true;
	}

	@Override
	public boolean hasNext() {
		return first.hasNext() || second.hasNext();
	}

	@Override
	public String next() {

		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		String result = null;

		if (first.hasNext() && (useFirstIterator || !second.hasNext())) {
			result = first.next();
			useFirstIterator = false;
		} else if (second.hasNext()) {
			result = second.next();
			useFirstIterator = true;
		}

		return result;
	}

}
