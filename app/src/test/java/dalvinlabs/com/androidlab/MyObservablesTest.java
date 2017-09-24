package dalvinlabs.com.androidlab;


import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import dalvinlabs.com.androidlab.reactive.rxjava.MyObservables;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

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

    @Test
    public void testRepeatWhen() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        MyObservables.repeatWhen();
        latch.await(1l, TimeUnit.MINUTES);
    }

    @Test
    public void testBuffer() throws Exception {
        MyObservables.buffer();
    }

    @Test
    public void testFlatMap() throws Exception {
        MyObservables.flatMap();
    }

    @Test
    public void testFlatMapDelayError() throws Exception {
        MyObservables.flatMapDelayError(true);
    }

    @Test
    public void testFlatMapDontDelayError() throws Exception {
        MyObservables.flatMapDelayError(false);
    }

    @Test
    public void testFlatMapMaxConcurrency() throws Exception {
        MyObservables.flatMapMaxConcurrency();
    }

    @Test
    public void testFlatMapBufferSize() throws Exception {
        MyObservables.flatMapBufferSize();
    }

    @Test
    public void testFlatMapNotificationMappers() throws Exception {
        MyObservables.flatMapNotificationMappers();
    }

    @Test
    public void testFlatMapWithResultSelector() throws Exception {
        MyObservables.flatMapWithResultSelector();
    }

    @Test
    public void testFlatMapIterable() throws Exception {
        MyObservables.flatMapIterable();
    }

    @Test
    public void testConcatMap() throws Exception {
        MyObservables.concatMap();
    }

    @Test
    public void testConcatMapDelayError() throws Exception {
        MyObservables.concatMapDelayError(true);
    }

    @Test
    public void testConcatMapDontDelayError() throws Exception {
        MyObservables.concatMapDelayError(false);
    }

    @Test
    public void testSwitchMap() throws Exception {
        MyObservables.switchMap();
    }

    @Test
    public void testGroupBy() throws Exception {
        MyObservables.groupBy();
    }

    @Test
    public void testMap() throws Exception {
        MyObservables.map();
    }

    @Test
    public void testCast() throws Exception {
        MyObservables.cast();
    }

    @Test
    public void testScan() throws Exception {
        MyObservables.scan();
    }

    @Test
    public void testWithInitialValue() throws Exception {
        MyObservables.scanWithInitialValue();
    }

    @Test
    public void testCompose() throws Exception {
        MyObservables.compose();
    }

    @Test
    public void testWindow() throws Exception {
        MyObservables.window();
    }

    @Test
    public void testDebounce() throws Exception {
        MyObservables.debounce();
    }

    @Test
    public void testDistinct() throws Exception {
        MyObservables.distinct();
    }

    @Test
    public void testElementAt() throws Exception {
        MyObservables.elementAt();
    }

    @Test
    public void testFilter() throws Exception {
        MyObservables.filter();
    }

    @Test
    public void testOfType() throws Exception {
        MyObservables.ofType();
    }

    @Test
    public void testFirst() throws Exception {
        MyObservables.first();
    }

    @Test
    public void testIgnoreElements() throws Exception {
        MyObservables.ignoreElements();
    }

    @Test
    public void testLast() throws Exception {
        MyObservables.last();
    }

    @Test
    public void testSample() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        MyObservables.sample();
        latch.await(10L, TimeUnit.SECONDS);
    }

    @Test
    public void testSkip() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        MyObservables.skip(latch);
        latch.await();
    }

    @Test
    public void testSkipWhile() throws Exception {
        MyObservables.skipWhile();
    }

    @Test
    public void testTakeLast() throws Exception {
        MyObservables.takeLast();
    }

    @Test
    public void testCombineLatest() throws Exception {
        MyObservables.combineLatest();
    }

    @Test
    public void testCombineLatestDelayError() throws Exception {
        MyObservables.combineLatestDelayError();
    }

    @Test
    public void testMerge() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        MyObservables.merge(latch);
        latch.await();
    }

    @Test
    public void testStartWith() throws Exception {
        MyObservables.startWith();
    }

    @Test
    public void testSwitchOnNext() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        MyObservables.switchOnNext(latch);
        latch.await();
    }

    @Test
    public void testZipIterable() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        MyObservables.zipIterable(latch);
        latch.await();
    }

    @Test
    public void testZipObservables() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        MyObservables.zipObservables(latch);
        latch.await();
    }

    @Test
    public void testZipIndividual() throws Exception {
        MyObservables.zipIndividual();
    }

    @Test
    public void testZipIndividualWithTestObserver() throws Exception {
        Observable observable = MyObservables.zipIndividual();
        TestObserver testObserver = observable.test();
        testObserver.assertSubscribed();
        testObserver.assertSubscribed();
        testObserver.assertResult("abc-1", "def-2", "ghi-3");
        List<List> events = testObserver.getEvents();
        testObserver.assertValueCount(3);
        testObserver.assertNoErrors();
        testObserver.assertComplete();
    }

    @Test
    public void testJoin() throws Exception {
        MyObservables.join();
    }

    @Test
    public void testConcat() throws Exception {
        MyObservables.concat();
    }

    @Test
    public void test() throws Exception {

    }

}
