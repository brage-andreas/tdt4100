package assignment1;

public class UpOrDownCounter {
    int counter;
    int end;

    UpOrDownCounter(int start, int end) {
        if (start == end) {
            throw new IllegalArgumentException("Arguments 'start' and 'end' must be unique");
        }

        this.counter = start;
        this.end = end;
    }

    int getCounter() {
        return counter;
    }    

    boolean count() {
        if (counter != end) {
            counter += counter > end ? -1 : 1;
        }

        return counter != end;
    }
}