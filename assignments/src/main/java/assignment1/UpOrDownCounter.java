package assignment1;

public class UpOrDownCounter {
	private int counter;
	private int end;

	public UpOrDownCounter(int start, int end) {
		if (start == end) {
			throw new IllegalArgumentException("Arguments 'start' and 'end' must be unique");
		}

		this.counter = start;
		this.end = end;
	}

	public int getCounter() {
		return this.counter;
	}

	public boolean count() {
		if (this.counter != this.end) {
			this.counter += this.counter > this.end ? -1 : 1;
		}

		return this.counter != this.end;
	}
}
