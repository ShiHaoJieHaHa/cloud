package own.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private static final AtomicCounter atomicCounter = new AtomicCounter();
    private AtomicCounter() {

    }
    public static AtomicCounter getInstance() {
        return atomicCounter;
    }

    private static AtomicInteger counter = new AtomicInteger();

    public int getValue() {
        return counter.get();
    }

    public int increase() {
        return counter.incrementAndGet();
    }

    public int decrease() {
        return counter.decrementAndGet();
    }
}
