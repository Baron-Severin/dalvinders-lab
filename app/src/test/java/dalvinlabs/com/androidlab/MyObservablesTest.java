package dalvinlabs.com.androidlab;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import dalvinlabs.com.androidlab.reactive.rxjava.Hybrids;
import dalvinlabs.com.androidlab.reactive.rxjava.MyObservables;
import dalvinlabs.com.androidlab.reactive.rxjava.MySingle;

//@RunWith(MockitoJUnitRunner.class)
public class MyObservablesTest {

    @Test
    public void testJust() throws Exception {
        MyObservables.just();
    }

    @Test
    public void testFromArray() throws Exception {
        MyObservables.fromArray();
    }

    @Test
    public void testFromCallable() throws Exception {
        MyObservables.fromCallable();
    }

    @Test
    public void testFromIterable() throws Exception {
        MyObservables.fromIterable();
    }

    @Test
    public void testFromFuture() throws Exception {
        MyObservables.fromFuture();
    }

    @Test
    public void testCreate() throws Exception {
        MyObservables.create();
    }

    @Test
    public void testGenerateStateless() throws Exception {
        MyObservables.generateStateless();
    }

    @Test
    public void testGenerateStateful() throws Exception {
        MyObservables.generateStateful();
    }

    @Test
    public void testDefer() throws Exception {
        MyObservables.defer();
    }

    @Test
    public void testStackTrace() throws Exception {
        MyObservables.stackTrace();
    }

    @Test
    public void testEmpty() throws Exception {
        MyObservables.empty();
    }

    @Test
    public void testNever() throws Exception {
        MyObservables.never();
    }

    @Test
    public void testError() throws Exception {
        MyObservables.error();
    }

    @Test
    public void testInterval() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        MyObservables.interval(latch);
        latch.await(10l, TimeUnit.SECONDS);
    }

    @Test
    public void testIntervalRange() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        MyObservables.intervalRange();
        latch.await(10l, TimeUnit.SECONDS);
    }

    @Test
    public void testRange() throws Exception {
        MyObservables.range();
    }

    @Test
    public void testRepeat() throws Exception {
        MyObservables.repeat();
    }

    @Test
    public void testRepeatUntil() throws Exception {
        MyObservables.repeatUntil();
    }

}
