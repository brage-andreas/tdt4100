package assignment5;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;

public class BinaryComputingIterator implements Iterator<Double> {
    public final Iterator<Double> firstIterator;
    public final Iterator<Double> secondIterator;
    public final BinaryOperator<Double> binaryOperator;
    public final Double firstDefault;
    public final Double secondDefault;

    public BinaryComputingIterator(Iterator<Double> firstIterator, Iterator<Double> secondIterator,
            BinaryOperator<Double> binaryOperator) {
        this.firstIterator = firstIterator;
        this.secondIterator = secondIterator;
        this.binaryOperator = binaryOperator;
        this.firstDefault = null;
        this.secondDefault = null;
    }

    public BinaryComputingIterator(Iterator<Double> firstIterator, Iterator<Double> secondIterator, Double firstDefault,
            Double secondDefault, BinaryOperator<Double> binaryOperator) {
        this.firstIterator = firstIterator;
        this.secondIterator = secondIterator;
        this.binaryOperator = binaryOperator;
        this.firstDefault = firstDefault;
        this.secondDefault = secondDefault;
    }

    public boolean hasNext() {
        if (!this.firstIterator.hasNext() && !this.secondIterator.hasNext()) {
            return false;
        }

        if (!this.firstIterator.hasNext() && this.firstDefault == null) {
            return false;
        }

        if (!this.secondIterator.hasNext() && this.secondDefault == null) {
            return false;
        }

        return true;
    }

    public Double next() {

        Double nextFromFirst = this.firstIterator.hasNext() ? this.firstIterator.next() : this.firstDefault;
        Double nextFromSecond = this.secondIterator.hasNext() ? this.secondIterator.next() : this.secondDefault;

        return this.binaryOperator.apply(nextFromFirst, nextFromSecond);
    }
}
