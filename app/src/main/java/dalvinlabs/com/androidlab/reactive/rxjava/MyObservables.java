package dalvinlabs.com.androidlab.reactive.rxjava;


import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyObservables {

    private static List<String> sData = Utils.getData();

    // # # # OBSERVERS

    private static CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static class MyObserver<T> implements Observer<T> {

        CountDownLatch mLatch;
        String mName;
        long startTime = 0L;

        MyObserver() {

        }

        MyObserver(String name) {
            mName = name;
        }

        MyObserver(CountDownLatch latch) {
            mLatch = latch;
        }

        @Override
        public void onSubscribe(Disposable d) {
            startTime = System.currentTimeMillis();
            if (mName != null) {
                System.out.println("MyObserver :: name = " + mName);
            }
            System.out.println("MyObserver :: onSubscribe");
            System.out.println("MyObserver :: disposable = " + d.toString());
            System.out.println("-----");
        }

        @Override
        public void onNext(T value) {
            if (mName != null) {
                System.out.println("MyObserver :: name = " + mName);
            }
            System.out.println("MyObserver :: onNext");
            System.out.println("T = " + value.getClass());
            System.out.println("thread = " + Thread.currentThread().getName());
            System.out.println("output = " + value);
            System.out.println("-----");
        }

        @Override
        public void onError(Throwable e) {
            if (mName != null) {
                System.out.println("MyObserver :: name = " + mName);
            }
            System.out.println("MyObserver :: onError = " + e);
            System.out.println("-----");
        }

        @Override
        public void onComplete() {
            if (mName != null) {
                System.out.println("MyObserver :: name = " + mName);
            }
            System.out.println("MyObserver :: onComplete");
            System.out.println("Time taken milliseconds = " + (System.currentTimeMillis() - startTime));
            System.out.println("-----");
            if (mLatch != null) {
                mLatch.countDown();
            }
        }
    }

    public static class MyMaybeObserver<T> implements MaybeObserver<T> {

        long startTime = 0L;

        @Override
        public void onSubscribe(Disposable d) {
            startTime = System.currentTimeMillis();
            System.out.println("MyMaybeObserver :: onSubscribe");
            System.out.println("-----");
        }

        @Override
        public void onSuccess(T value) {
            System.out.println("MyMaybeObserver :: onSuccess");
            System.out.println("T = " + value.getClass());
            System.out.println("thread = " + Thread.currentThread().getName());
            System.out.println("output = " + value);
            System.out.println("-----");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("MyMaybeObserver :: onError = " + e);
            System.out.println("-----");
        }

        @Override
        public void onComplete() {
            System.out.println("MyMaybeObserver :: onComplete");
            System.out.println("Time taken milliseconds = " + (System.currentTimeMillis() - startTime));
            System.out.println("-----");
        }
    }

    public static class MySingleObserver<T> implements SingleObserver<T> {

        @Override
        public void onSubscribe(Disposable d) {
            System.out.println("MySingleObserver :: onSubscribe");
            System.out.println("-----");
        }

        @Override
        public void onSuccess(T value) {
            System.out.println("MySingleObserver :: onSuccess");
            System.out.println("T = " + value.getClass());
            System.out.println("thread = " + Thread.currentThread().getName());
            System.out.println("output = " + value);
            System.out.println("-----");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("MySingleObserver :: onError = " + e);
            System.out.println("-----");
        }
    }

    public static class MyCompletableObserver implements CompletableObserver {
        @Override
        public void onSubscribe(Disposable d) {
            System.out.println("MyCompletableObserver :: onSubscribe");
            System.out.println("-----");
        }

        @Override
        public void onComplete() {
            System.out.println("MyCompletableObserver :: onComplete");
            System.out.println("thread = " + Thread.currentThread().getName());
            System.out.println("-----");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("MyCompletableObserver :: onError = " + e);
            System.out.println("-----");
        }
    }

    private static Observer<String> genericObserver1 = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {
            System.out.println("genericObserver1 :: onSubscribe");
            compositeDisposable.add(d);
            System.out.println("-----");
        }

        @Override
        public void onNext(String output) {
            System.out.println("genericObserver1 :: onNext");
            System.out.println("thread = " + Thread.currentThread().getName());
            System.out.println("output = " + output);
            System.out.println("-----");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("genericObserver1 :: onError = " + e);
            //e.printStackTrace();
            System.out.println("-----");
        }

        @Override
        public void onComplete() {
            System.out.println("genericObserver1 :: onComplete");
            System.out.println("-----");
        }
    };

    private static Observer<String> genericObserver2 = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {
            System.out.println("genericObserver2 :: onSubscribe");
            compositeDisposable.add(d);
            System.out.println("-----");
        }

        @Override
        public void onNext(String output) {
            System.out.println("genericObserver2 :: onNext");
            System.out.println("thread = " + Thread.currentThread().getName());
            System.out.println("output = " + output);
            System.out.println("-----");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("genericObserver2 :: onError = " + e.getMessage());
            System.out.println("-----");
        }

        @Override
        public void onComplete() {
            System.out.println("genericObserver2 :: onComplete");
            System.out.println("-----");
        }
    };

    private static Consumer<List<String>> consumerList = data ->
            System.out.println("consumerList :: accept = " + data.toString());


    /*
         ##########################
         # # # OBSERVABLES : CREATE
         ##########################
    */

    /*
        Observables and Observer runs on a same thread.
        just emits single item i.e. in this case whole list.
     */
    public static void just() {
        Observable<List<String>> myObservable = Observable.just(Utils.getData());
        myObservable.subscribe(consumerList);
    }

    /*
        Emits every item of an array
     */
    public static void fromArray() {
        Observable<String> myObservable = Observable.fromArray(Utils.getData().toArray(new String[0]));
        /*
          1. Instead of providing a subscriber, single method can be provided just to get onNext().
          2. Similarly 3 methods can be provided one for each onNext, onError, onCompleted.
         */
        myObservable.subscribe(value -> System.out.println("output = " + value));
    }

    /*
        Observables and Observer runs on a separate threads.
     */
    public static void fromCallable() {
        Observable<List<String>> myObservable = Observable.fromCallable(() -> {
            System.out.println("call, thread = " + Thread.currentThread().getName());
            return Utils.getData();
        });
        myObservable.subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread()) For ANDROID use Android Schedulers.
                .observeOn(Schedulers.newThread()) // For ease of unit test, this is used
                .subscribe(consumerList);
    }

    public static void fromIterable() {
        Observable<String> myObservable = Observable.fromIterable(Utils.getData());
        myObservable.subscribe(value -> System.out.println("output = " + value));
    }


    /*
        Observable from Future
     */
    public static void fromFuture() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> "Response from future");
        Observable<String> myObservable = Observable.fromFuture(future);
        myObservable.subscribe(genericObserver1);
    }



    /*
        1. Create custom observable, code is responsible for calling onNext, onComplete|onError.
        2. Observable is NOT created until subscription similar to "defer"
        3. create can emit multiple values.
     */
    public static void create() {
        Observable<String> myObservable = Observable.create((emitter) -> {
            try {
                if (!emitter.isDisposed()) {
                    System.out.println("got emitter = " + emitter);
                    emitter.onNext("from create - 1");
                    emitter.onNext("from create - 2");
                    emitter.onComplete();
                }
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
        myObservable.subscribe(genericObserver1);
        System.out.println("-----");
        myObservable.subscribe(genericObserver2);
    }

    /*
        1. Defer the creation of observable until subscription.
        2. Better than "create" because it uses built in operators.
     */
    public static void defer() {
        class Data {
            private String value = "abc";
        }

        final Data data = new Data();

        // Other observables gets created immediately e.g.
        Observable<String> myObservable = Observable.just(data.value);
        // Modify the data
        data.value = "xyz";
        // Even though data is modified, observer will get old data "abc"
        myObservable.subscribe(genericObserver1);

        data.value = "abc";
        myObservable = Observable.defer(() -> {
            System.out.println("defer supplier going to create observable");
            return Observable.just(data.value);
        });
        // modify data
        data.value = "xyz";
        // Because of defer, observable will be created upon subscription, hence it will get the
        // modified data
        myObservable.subscribe(genericObserver1);
    }

    /*
        1. Similar to create
        2. Stateless
    */
    public static void generateStateless() {
        Observable<String> myObservable = Observable.generate((emitter -> {
            System.out.println("got emitter = " + emitter);
            try {
                emitter.onNext("generate - 1");
                emitter.onNext("generate - 2 ");
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        }));
        myObservable.subscribe(genericObserver1);
    }

    /*
        1. Stateful generate
        2. Gets initial state
        3. Generate data and return new state
        4. After terminal operation consumer with dispose state gets called.
     */
    public static void generateStateful() {
        Observable<String> statefulObservable = Observable.generate(
                () -> {
                    int initialState = 1;
                    System.out.println("initial state = " + initialState);
                    return initialState;
                },
                (initialState, stringEmitter) -> {
                    try {
                        if (initialState <= 3) {
                            System.out.println("emitter = " + stringEmitter);
                            stringEmitter.onNext("generate - " + initialState);
                            initialState += 1;
                            System.out.println("update state = " + initialState);
                        } else {
                            stringEmitter.onComplete();
                        }
                    } catch (Exception e) {
                        stringEmitter.onError(e);
                    }
                    return initialState;
                }, (disposeState -> System.out.println("dispose state = " + disposeState)));
        statefulObservable.subscribe(genericObserver1);
    }

    /*
        Stack trace test
        It does print stack trace for this simple observable.
        TODO: Try this with multiple observables and operators
     */
    public static void stackTrace() {
        Observable<String> myObservable = Observable.fromCallable(() -> {
            Object value = null;
            return value.toString();
        });
        myObservable.subscribe(item -> System.out.println("output = " + item));
    }

    /*
        Return nothing but completes
     */
    public static void empty() {
        Observable<String> myObservable = Observable.empty();
        myObservable.subscribe(genericObserver1);
    }

    /*
        Neither return nor completes
     */
    public static void never() {
        Observable<String> myObservable = Observable.never();
        myObservable.subscribe(genericObserver1);
    }

    /*
        Return nothing but terminates with error.
     */
    public static void error() {
        Observable<String> myObservable = Observable.error(
                () -> new Exception("Exception from error observable"));
        myObservable.subscribe(genericObserver1);
    }

    /*
        By default runs on computation thread.
     */
    public static void interval(final CountDownLatch latch) {
        Observable<Long> myObservable = Observable.interval(0L, 30L,
                TimeUnit.MILLISECONDS);
        myObservable.subscribe(item -> System.out.println("output = " + item),
                throwable -> {
                    System.out.println("error = " + throwable);
                    latch.countDown();
                },
                () -> {
                    System.out.println("onComplete");
                    latch.countDown();
                });
    }

    /*
        By default runs on computation thread.
     */
    public static void intervalRange() {
        Observable.intervalRange(0L, 10L, 0L, 10L, TimeUnit.MILLISECONDS).
                subscribe(new MyObserver<>());
    }

    public static void range() {
        Observable.range(0, 10).subscribe(new MyObserver<>());
    }

    public static void repeat() {
        Observable.just("abc").repeat(3).subscribe(new MyObserver<>());
    }

    public static void repeatUntil() {

        class Data {
            private int value = 0;
        }

        final Data data = new Data();

        Observable.just("abc").repeatUntil(() -> {
            data.value += 1;
            return (data.value == 5);
        }).subscribe(new MyObserver<>());
    }

    /*
        1. Repeat until terminal conditions i.e. onComplete or onError
        2. In this case 3 tries with 1 second delay.
     */
    public static void repeatWhen() {
        Observable.fromArray("abc", "def", "ghi").
                repeatWhen((objectObservable -> {
                    System.out.println("repeatWhen delay");
                    return objectObservable.delay(1L, TimeUnit.SECONDS).take(3);
                })).subscribeOn(Schedulers.io()).subscribe(new MyObserver<>());

    }


    /*
         ##########################
         # # # OBSERVABLES : TRANSFORM
         ##########################
    */

    public static void buffer() {

        List<String> data = Utils.getData();

        int numberOfBuffers = 11;

        CountDownLatch latch = new CountDownLatch(numberOfBuffers);


        Observable.fromIterable(data).buffer(3).subscribe(new MyObserver<>(latch));

        Observable.fromIterable(data).buffer(2, 3).subscribe(new MyObserver<>(latch));

        Observable.fromIterable(data).buffer(2, 3, ArrayList::new).subscribe(new MyObserver<>(latch));

        Observable.fromIterable(data).buffer(2, HashSet::new).subscribe(new MyObserver<>(latch));

        /*
            Opening and closing windows defined
         */
        Observable.fromIterable(data).buffer((observer) -> {
            System.out.println("opening");
            // Starts buffer collection window
            // For every onNext a buffer window is created.
            observer.onNext("opening-1");
            observer.onNext("opening-2");
        }, (value) -> {
            System.out.println("closing, value = " + value);
            // Closes buffer collection window
            // Gets invoked for each onNext in opening indicator
            return Observable.just(value).delay(100L, TimeUnit.MICROSECONDS);
        }, HashSet::new).subscribe(new MyObserver<>(latch));

        /*
            Buffer with boundary observable
         */
        Observable.fromIterable(data).buffer((Observer<? super Integer> observer) -> {
            System.out.println("onNext - 1");
            observer.onNext(1);
            observer.onNext(2);
        }).subscribe(new MyObserver<>(latch));

        /*
            1. Buffer with boundary observable by providing some other in built observable
            2. Create a buffer of 100 Microseconds
         */
        Observable.fromIterable(data)
                .buffer(Observable.interval(100L, TimeUnit.MICROSECONDS))
                .subscribe(new MyObserver<>(latch));

        Observable.fromIterable(data)
                .buffer(Observable.interval(100L, TimeUnit.MICROSECONDS), 4)
                .subscribe(new MyObserver<>(latch));

        Observable.fromIterable(data)
                .buffer(Observable.interval(100L, TimeUnit.MICROSECONDS), () -> new HashSet<>())
                .subscribe(new MyObserver<>(latch));


        /*
            Boundary supplier is being passed from callable.
         */
        Observable.fromIterable(data)
                .buffer(() -> Observable.interval(100L, TimeUnit.MICROSECONDS))
                .subscribe(new MyObserver<>(latch));


        Observable.fromIterable(data)
                .buffer(() -> Observable.interval(100L, TimeUnit.MICROSECONDS), HashSet::new)
                .subscribe(new MyObserver<>(latch));

        try {
            latch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void flatMap() {
        /*
            1. Function is applied on each item of data.
            2. Function returns an observable which emits 2 modified items.
            3. Emissions of all observables of all modified items are combined into an observable.
         */
        List<String> data = Utils.getData();
        Observable.fromIterable(data)
                .flatMap((item) -> Observable.just(item + " flat mapped").repeat(2)
                ).subscribe(new MyObserver<>());
    }

    public static void flatMapDelayError(boolean isDelayError) {
        List<String> data = Utils.getData();
        Observable.fromIterable(data)
                .flatMap((item) -> {
                            if (item.equalsIgnoreCase("def")) {
                                return Observable.error(new Throwable("Just for test item = " + item));
                            } else {
                                return Observable.just(item + " flat mapped").repeat(2);
                            }
                        }, isDelayError
                ).subscribe(new MyObserver<>());
    }


    public static void flatMapMaxConcurrency() {
        List<String> data = Utils.getData();
        Observable.fromIterable(data)
                .flatMap((item) -> Observable.just(item + " flat mapped").repeat(10000), 100)
                .doOnNext((item) -> System.out.println("doOnNext Thread = " + Thread.currentThread().getName()))
                .subscribe(new MyObserver<>());
    }

    public static void flatMapBufferSize() {
        List<String> data = Utils.getData();
        Observable.fromIterable(data)
                .flatMap((item) -> Observable.just(item + " flat mapped")
                        .repeat(10000), true, 100, 100)
                .subscribe(new MyObserver<>());
    }

    public static void flatMapNotificationMappers() {
        /*
            1. Provides functions to emit observables for onError and onComplete also
            2. Since source is emitting an error for item "def"
            3. Flat map's errorMapper will emit observable
         */
        List<String> data = Utils.getData();
        Observable.create((stringEmitter) -> {
            for (String each : data) {
                if (!stringEmitter.isDisposed()) {
                    if (each.equalsIgnoreCase("def")) {
                        stringEmitter.onError(new Throwable("Just for test item = " + each));
                    } else {
                        stringEmitter.onNext(each);
                    }
                }
            }
            stringEmitter.onComplete();
        })
        .flatMap((item) -> Observable.just(item + " flat mapped").repeat(2)
                , (error) -> Observable.just("error flat mapped = " + error).repeat(2)
                , () -> Observable.just("completed flat mapped").repeat(2))
                .subscribe(new MyObserver<>());


        /*
            1. Since here source is not emitting any error
            2. Flat map's completeMapper will emit observable
         */
        Observable.fromIterable(data)
                .flatMap((item) -> Observable.just(item + " flat mapped").repeat(2)
                , (error) -> Observable.just("error flat mapped = " + error).repeat(2)
                , () -> Observable.just("completed flat mapped").repeat(2))
                .subscribe(new MyObserver<>());
    }

    public static void flatMapWithResultSelector() {
        List<String> data = Utils.getData();
        /*
            Combining actual emit item with transformed emit item
         */
        Observable.fromIterable(data)
                .flatMap((item) -> Observable.just(item + " from inner").repeat(2)
                        , (source, inner) -> "Combining source & inner = " + source + " : " + inner)
                .subscribe(new MyObserver<>());
    }

    public static void flatMapIterable() {
        List<String> data = Utils.getData();
        /*
            Returns iterable instead of inner observable
         */
        Observable.fromIterable(data)
                .flatMapIterable((item) -> {
                    List<String> iterable = new ArrayList<>();
                    iterable.add(item + "-1");
                    iterable.add(item + "-2");
                    return iterable;
                }).subscribe(new MyObserver<>());
    }

    public static void concatMap() {
        /*
            Similar to flat map but preserve the order
         */
        List<String> data = Utils.getData();
        Observable.fromIterable(data)
                .concatMap((item) -> Observable.just(item + " concat mapped").repeat(2)
                ).subscribe(new MyObserver<>());
    }

    public static void concatMapDelayError(boolean isDelayError) {
        List<String> data = Utils.getData();
        /*
            Delay any error till the end
         */
        Observable.fromIterable(data)
                .concatMapDelayError((item) -> {
                            if (item.equalsIgnoreCase("def")) {
                                return Observable.error(new Throwable("Just for test item = " + item));
                            } else {
                                return Observable.just(item + " flat mapped").repeat(2);
                            }
                        }, 1,isDelayError)
                .subscribe(new MyObserver<>());
    }

    public static void switchMap() {
        Observable<String> firstSource = Observable.create((emitter) -> {
            try {
                if (emitter.isDisposed()) {
                    emitter.onComplete();
                } else {
                    emitter.onNext("First Observable - 1");
                    emitter.onNext("First Observable - 2");
                }
            } catch (Exception e) {
                emitter.onError(e);
            }
            emitter.onComplete();
        });

        Observable<String> secondSource = Observable.create((emitter) -> {
            try {
                if (emitter.isDisposed()) {
                    emitter.onComplete();
                } else {
                    emitter.onNext("Second Observable - 1");
                    emitter.onNext("Second Observable - 2");
                }
            } catch (Exception e) {
                emitter.onError(e);
            }
            emitter.onComplete();
        });

        Observable<String> thirdSource = Observable.create((emitter) -> {
            try {
                if (emitter.isDisposed()) {
                    emitter.onComplete();
                } else {
                    emitter.onNext("third Observable - 1");
                    emitter.onNext("third Observable - 2");
                }
            } catch (Exception e) {
                emitter.onError(e);
            }
            emitter.onComplete();
        });

        List<Observable> apis = new ArrayList<>();
        apis.add(firstSource);
        apis.add(secondSource);
        apis.add(thirdSource);

        class Data {
            private int value = -1;
        }

        Data data = new Data();

        /*
            1. outer observable only emits items from most recent inner observable.
            2. In this case "second" observable got delayed to emit items and "third" starting
            emitting items so therefore items from "second" got ignored.
         */

        System.out.println("# 1");

        Observable.fromIterable(apis)
                .switchMap((item) -> {
            data.value ++ ;
            if (data.value == 1) {
                System.out.println("Delaying observable = " + item);
                return item.delay(1000L, TimeUnit.MICROSECONDS);
            } else {
                return item;
            }
        }).subscribe(new MyObserver<>());

        System.out.println("# 2");

        /*
            Same as above but error is delayed until most recent observable completes.
         */

        data.value = 0;

        Observable.fromIterable(apis)
                .switchMapDelayError((item) -> {
                    data.value ++ ;
                    if (data.value == 1) {
                        System.out.println("observable error = " + item.getClass().getSimpleName());
                        return Observable.error(new Throwable("Just for test, item = "
                                + item.getClass().getSimpleName()));
                    } else {
                        return item;
                    }
                }).subscribe(new MyObserver<>());

        System.out.println("# 3");

        /*
            Same as above but instead of observable it returns single
         */

        Observable.fromIterable(Utils.getData())
                .switchMapSingle(Single::just)
                .subscribe(new MyObserver<>());

        System.out.println("# 4");

        /*
            Same as above but delay the error until most recent inner observable completes
         */

        Observable.fromIterable(Utils.getData())
                .switchMapSingleDelayError((item) -> {
                    if (item.equalsIgnoreCase("def")) {
                        return Single.error(new Throwable("Just for test, item = " + item));
                    } else {
                        return Single.just(item);
                    }
                }).subscribe(new MyObserver<>());

    }

    public static void groupBy() {
        List<String> data = Utils.getAlphaNumericData();
        Observable.fromIterable(data).groupBy((item) -> {
            try {
                int a = Integer.parseInt(item);
                System.out.println(a);
                return 1000;
            } catch (NumberFormatException e) {
                return 2000;
            }
        }).subscribe((groupedObservable) ->
                groupedObservable
                        .subscribe(new MyObserver<>(groupedObservable.getKey().toString())));


        System.out.println("# 2");

        /*
            Same as above but second function is provided to modify the item values as well.
         */

        Observable.fromIterable(data).groupBy((item) -> {
            try {
                int a = Integer.parseInt(item);
                System.out.println(a);
                return 1000;
            } catch (NumberFormatException e) {
                return 2000;
            }
        }, (item) -> "Modified = " + item)
                .subscribe((groupedObservable) ->
                groupedObservable
                        .subscribe(new MyObserver<>(groupedObservable.getKey().toString())));
    }

    public static void map() {
        Observable.fromIterable(Utils.getData()).map((item) -> "Modified by map = " + item)
                .subscribe(new MyObserver<>());
    }

    public static void cast() {

        class Parent {
            @Override
            public String toString() {
                return "Parent Value";
            }
        }

        class Source extends Parent{
            @Override
            public String toString() {
                return "Source Value";
            }
        }

        List<Source> data = new ArrayList<>();
        data.add(new Source());

        /*
            Type casting a child object to it's parent class object
         */

        Observable.fromIterable(data)
                .cast(Parent.class)
                .subscribe(new MyObserver<>());
    }


    public static void scan() {
        /*
            Same as map but scan function receives most recently emitted value as an argument also.
         */
        Observable.range(1, 10)
                .scan((previous, current) -> {
                    System.out.println("previous = " + previous);
                    System.out.println("current = " + current);
                    return previous + current;
                }).subscribe(new MyObserver<>());
    }

    public static void scanWithInitialValue() {
        /*
            Same as scan but it emits initial value to observers first and pass it to function as
            a previous value.
         */
        Observable.range(1, 10)
                .scan(100, (previous, current) -> {
                    System.out.println("previous = " + previous);
                    System.out.println("current = " + current);
                    return previous + current;
                }).subscribe(new MyObserver<>());

        System.out.println("# 2");

        /*
            Same as above but initial value is returned via callable.
         */

        Observable.range(1, 10)
                .scanWith(() -> 1000, (previous, current) -> {
                    System.out.println("previous = " + previous);
                    System.out.println("current = " + current);
                    return previous + current;
                }).subscribe(new MyObserver<>());

    }

    public static void compose() {
        /*
            1. Transform the source observable to some other type
            2. Works on the original source observable
            3. Different from flatMap because flatMap receives individual items from source
            and then return observable instances.
         */
        Observable.fromIterable(Utils.getData())
                .compose((source) -> {
                    source.subscribe(new MyObserver<>());
                    return source;
                });

        System.out.println("# 2");

        Observable.fromIterable(Utils.getData())
                .compose((source) -> {
                    Observable<String> observable = Observable.just("composed");
                    observable.subscribe(new MyObserver<>());
                    return observable;
                });
    }

    public static void window() {
        /*
            Same as buffer but window creates inner observable per item and buffer collects items
            from source observable in buffer.
         */

        System.out.println("# 1");

        List<String> data = Utils.getData();
        Observable.fromIterable(data)
                .window(2)
                .subscribe((windowStream) -> windowStream.subscribe(new MyObserver<>()));

        System.out.println("# 2");

        Observable.fromIterable(data)
                .window(2, 3)
                .subscribe((windowStream) -> windowStream.subscribe(new MyObserver<>()));

        System.out.println("# 3");

        Observable.fromIterable(data)
                .window(2, 3, 1)
                .subscribe((windowStream) -> windowStream.subscribe(new MyObserver<>()));

        System.out.println("# 4");

        Observable.fromIterable(data)
                .window(100, 100, TimeUnit.MICROSECONDS)
                .subscribe((windowStream) -> windowStream.subscribe(new MyObserver<>()));

        System.out.println("# 5");

        Observable.fromIterable(data)
                .window((emitter) -> emitter.onNext(1))
                .subscribe((windowStream) -> windowStream.subscribe(new MyObserver<>()));

        /*
            Mose of the window operators are similar to buffer.
         */
    }

    /*
         ##########################
         # # # OBSERVABLES : FILTER
         ##########################
    */

    public static void debounce() {
        List<String> data = Utils.getData();

        System.out.println("# 1");

        /*
            An item needs to be most recent for at least 1 microseconds to be considered for
            transmission.
         */
        Observable.fromIterable(data)
                .debounce(1, TimeUnit.MICROSECONDS)
                .subscribe(new MyObserver<>());

        System.out.println("# 2");

        /*
            Item "def" is being intentionally delayed, so it will not most recent and "ghi" will
            emitted before it, Hence "def" will not be emitted.
         */
        Observable.fromIterable(data)
                .debounce((item) -> {
                    if (item.equalsIgnoreCase("def")) {
                        return Observable.just(item).delay(1000L, TimeUnit.MICROSECONDS);
                    } else {
                        return Observable.just(item);
                    }
                }).subscribe(new MyObserver<>());
    }

    public static void distinct() {

        List<String> data = Utils.getDuplicateData();

        /*
            Won't emit duplicate items
         */
        System.out.println("# 1");
        Observable.fromIterable(data)
                .distinct()
                .subscribe(new MyObserver<>());

        System.out.println("# 2");
        /*
            Distinct are decided based upon function provided.
         */
        Observable.fromIterable(data)
                .distinct((item) -> item)
                .subscribe(new MyObserver<>());

        System.out.println("# 3");

        Integer[] counts = {1, 2, 3, 4, 5, 6, 7, 8, 9 };
        /*
            Same as above but takes collection supplier, not sure about the purpose of
            collection supplier.
         */
        Observable.fromIterable(Arrays.asList(counts))
                .distinct((item) -> item % 5, HashSet::new)
                .subscribe(new MyObserver<>());

        System.out.println("# 4");
        /*
            Compare distinctiveness with 1 most recently value only
         */
        Observable.fromIterable(Utils.getDuplicateData())
                .distinctUntilChanged()
                .subscribe(new MyObserver<>());

        /*
            Same as above but key selector is provided
         */
        System.out.println("# 5");
        counts = new Integer[]{1, 2, 3, 4, 5, 5, 5, 5, 5 };
        Observable.fromIterable(Arrays.asList(counts))
                .distinctUntilChanged((item) -> item % 5)
                .subscribe(new MyObserver<>());

        /*
            Same as above but comparator is provider here.
            NOTE : method reference is being user in comparator
            prev.equals(curr) can be written as Integer::equals
         */
        System.out.println("# 6");
        counts = new Integer[]{1, 2, 2, 4, 4, 4, 4};
        Observable.fromIterable(Arrays.asList(counts))
                .distinctUntilChanged(Integer::equals)
                .subscribe(new MyObserver<>());
    }

    public static void elementAt() {
        List<String> data = Utils.getData();

        /*
            Receives item at a particular index only
         */
        Observable.fromIterable(data)
                .elementAt(1)
                .subscribe(new MyMaybeObserver<>());

        Observable.fromIterable(data)
                .elementAt(10)
                .subscribe(new MyMaybeObserver<>());

        Observable.fromIterable(data)
                .elementAt(10, "Default Item")
                .subscribe(new MySingleObserver<>());

        /*
            Throws exception if index < 0
         */
        Observable.fromIterable(data)
                .elementAtOrError(-1)
                .subscribe(new MySingleObserver<>());

    }

    public static void filter() {
        List<String> data = Utils.getData();
        Observable.fromIterable(data)
                .filter((item) -> item.equalsIgnoreCase("def"))
                .subscribe(new MyObserver<>());
    }

    public static void ofType() {
        List<Number> numbers = new ArrayList<>();
        numbers.add(123);
        numbers.add(456L);
        numbers.add(7.89F);

        Observable.fromIterable(numbers)
                .ofType(Integer.class)
                .subscribe(new MyObserver<>());
    }

    public static void first() {
        System.out.println("# 1");
        Observable.fromIterable(Utils.getData())
                .firstElement()
                .subscribe(new MyMaybeObserver<>());

        System.out.println("# 2");
        Observable.fromIterable(new ArrayList<>())
                .firstElement()
                .subscribe(new MyMaybeObserver<>());

        /*
            First element or default element
         */
        System.out.println("# 3");
        Observable.fromIterable(new ArrayList<>())
                .first("Default")
                .subscribe(new MySingleObserver<>());

        /*
            First element or error
         */
        System.out.println("# 4");
        Observable.fromIterable(new ArrayList<>())
                .firstOrError()
                .subscribe(new MySingleObserver<>());

        /*
            Blocking first, not recommended, can be used for testing purpose.
         */
        System.out.println("# 5");
        String output = Observable.fromIterable(Utils.getData())
                .blockingFirst();
        System.out.println(output);

        /*
            Blocking first or default, not recommended, can be used for testing purpose.
         */
        System.out.println("# 6");
        output = Observable.fromIterable(new ArrayList<String>())
                .blockingFirst("Default");
        System.out.println(output);

        /*
            Blocking each item
         */
        System.out.println("# 7");
        Observable.fromIterable(Utils.getData())
                .blockingForEach((item) -> System.out.println("output = " + item));

        /*
            Returns iterable
         */
        System.out.println("# 8");
        Iterable<String> iterable = Observable.fromIterable(Utils.getData())
                .blockingIterable();
        Iterator<String> iterator = iterable.iterator();
        iterator.forEachRemaining((item) -> System.out.println("output = " + item));

        /*
            Returns item if observable emits only 1 item else error
         */
        System.out.println("# 9");
        Observable.just("123")
                .singleElement()
                .subscribe(new MyMaybeObserver<>());

        System.out.println("# 10");
        Observable.fromIterable(Utils.getData())
                .singleElement()
                .subscribe(new MyMaybeObserver<>());

        /*
            Returns Single observer
         */
        System.out.println("# 11");
        Observable.just("123")
                .single("Default")
                .subscribe(new MySingleObserver<>());

        /*
            Same as above with default value
         */
        System.out.println("# 12");
        Observable.empty()
                .single("Default")
                .subscribe(new MySingleObserver<>());

        /*
            Exception if observable emits more than 1 item
         */
        System.out.println("# 13");
        Observable.fromIterable(Utils.getData())
                .singleOrError()
                .subscribe(new MySingleObserver<>());

        System.out.println("# 14");
        Observable.fromIterable(Utils.getData())
                .take(2)
                .subscribe(new MyObserver<>());

        System.out.println("# 15");
        Observable.fromIterable(Utils.getData())
                .take(10)
                .subscribe(new MyObserver<>());

    }

    public static void ignoreElements() {
        /*
            Ignore all emission and complets
         */
        Observable.fromIterable(Utils.getData())
                .ignoreElements()
                .subscribe(new MyCompletableObserver());
    }

    public static void last() {
        List<String> data = Utils.getData();

        System.out.println("# 1");
        Observable.fromIterable(data)
                .last("Default")
                .subscribe(new MySingleObserver<>());

        System.out.println("# 2");
        Observable.empty()
                .last("Default")
                .subscribe(new MySingleObserver<>());

        System.out.println("# 3");
        Observable.fromIterable(data)
                .lastElement()
                .subscribe(new MyMaybeObserver<>());

        System.out.println("# 4");
        Observable.fromIterable(data)
                .lastOrError()
                .subscribe(new MySingleObserver<>());

        System.out.println("# 5");
        Observable.empty()
                .lastOrError()
                .subscribe(new MySingleObserver<>());
    }

    public static void sample() {
        /*
            Most recent item in last 1000 microseconds window
         */
        System.out.println("# 1");
        Observable.fromIterable(sData)
                .timestamp()
                .sample(1000, TimeUnit.MICROSECONDS)
                .subscribe(new MyObserver<>());

        /*
            Emission controlled by sampler inner observable
         */
        System.out.println("# 2");
        Observable.fromIterable(sData)
                .sample((observer) -> {
                    System.out.println("emitter");
                    Observable.just("123").subscribe(observer);
                }, true).subscribe(new MyObserver<>());
    }

    public static void skip(CountDownLatch latch) {
        System.out.println("# 1");
        Observable.fromIterable(sData)
                .skip(2)
                .subscribe(new MyObserver<>());

        System.out.println("# 2");
        Observable.fromIterable(sData)
                .skipLast(4)
                .subscribe(new MyObserver<>());

        System.out.println("# 3");
        /*
            1. Source emits 10 items, each every second.
            2. Skip observable will delay it's broadcast by 5 seconds.
            3. Hence items emitted by source in initial 5 seconds will be ignored and not received
            by observer.
         */
        Observable.interval(1L, TimeUnit.SECONDS)
                .take(10)
                .skipUntil((observer) -> {
                    System.out.println("Skip observable");
                    Observable.just("123")
                            .delay(5L, TimeUnit.SECONDS).subscribe(observer);
                }).subscribeOn(Schedulers.computation()).subscribe(new MyObserver<>(latch));
    }

    public static void skipWhile() {
        /*
            Skips until predicate returns true, once it returns false, it's not checked again,
            all subsequent emissions are made.
         */
        Observable.fromIterable(sData)
                .skipWhile((item) -> {
                    System.out.println("item = " + item);
                    return item.equalsIgnoreCase("abc");
                }).subscribe(new MyObserver<>());
    }

    public static void takeLast() {
        Observable.fromIterable(sData)
                .takeLast(3)
                .subscribe(new MyObserver<>());
    }

    /*
         ##########################
         # # # OBSERVABLES : COMBINE
         ##########################
    */

    public static void combineLatest() {

        System.out.println("# 1");

        /*
            Emits item every second
         */
        Observable sourceInterval = Observable.interval(1L, TimeUnit.MILLISECONDS).take(10);

        /*
            Emits string items
         */
        Observable sourceString = Observable.fromIterable(sData);

        Observable<?> combined = Observable.combineLatest((objects) -> {
            StringBuilder stringBuilder = new StringBuilder();
            Stream.of(objects).forEach(stringBuilder::append);
            return stringBuilder.toString();
        }, 1, sourceInterval, sourceString);

        combined.subscribe(new MyObserver<>());

        System.out.println("# 2");

        /*
            Because empty observable immediately terminates hence whole sequence terminates.
         */
        combined = Observable.combineLatest((objects) -> {
            StringBuilder stringBuilder = new StringBuilder();
            Stream.of(objects).forEach(stringBuilder::append);
            return stringBuilder.toString();
        }, 1, sourceInterval, sourceString, Observable.empty());
        combined.subscribe(new MyObserver<>());

        System.out.println("# 3");
        /*
            Same as above but instead collection of sources are used.
         */
        List<Observable<?>> collection = new ArrayList<>();
        collection.add(sourceInterval);
        collection.add(sourceString.repeat(1));
        combined = Observable.combineLatest(collection, (objects) -> {
            StringBuilder stringBuilder = new StringBuilder();
            Stream.of(objects).forEach(stringBuilder::append);
            return stringBuilder.toString();
        });
        combined.subscribe(new MyObserver<>());

        System.out.println("# 4");
        /*
            Explicitly combine 2 sources, similarly it can be done up to 10 sources
         */
        combined = Observable.combineLatest(sourceInterval, sourceString.repeat(1),
                (first, second) -> first + "-" + second);
        combined.subscribe(new MyObserver<>());
    }

    public static void combineLatestDelayError() {
        Observable sourceInterval = Observable.interval(1L, TimeUnit.MILLISECONDS).take(10);
        Observable sourceString = Observable.fromIterable(sData);
        String[] array = {"foo", "bar"};
        Observable sourceError = Observable.fromArray(array).map((item) -> {
           if (item.equalsIgnoreCase("bar")) {
               throw new Exception("Just for test");
           } else return item;
        });

        /*
            Throws the error at the end.
         */
        Observable<?> combined = Observable.combineLatestDelayError((objects) -> {
            StringBuilder stringBuilder = new StringBuilder();
            Stream.of(objects).forEach(stringBuilder::append);
            return stringBuilder.toString();
        }, 1, sourceInterval, sourceString, sourceError);
        combined.subscribe(new MyObserver<>());
    }

    public static void merge(CountDownLatch latch) {
        System.out.println("# 1");

        Integer[] array = {1, 2, 3, 4, 5};
        /*
            Merge 2 observables
         */
        Observable.merge(Observable.fromArray(array), Observable.fromIterable(sData))
                .subscribe(new MyObserver<>());

        System.out.println("# 2");

        /*
            Merge list of observables
         */
        Observable<String> stringStream = Observable.fromIterable(sData);
        Observable<String> alphaNumericStream = Observable.fromIterable(Utils.getAlphaNumericData());
        List<Observable<String>> collection = new ArrayList<>();
        collection.add(stringStream);
        collection.add(alphaNumericStream);
        Observable.merge(collection).subscribe(new MyObserver<>());
        /*
            Merge observable sources emitted by an observable
         */
        System.out.println("# 3");

        class Data {
            private int value = 1;
        }
        Data data = new Data();

        /*
            1. Observable.fromCallable gets called 5 times
            2. This invokes callable 5 times
            3. Make it emit 5 observables
            4. Hence we receive an observable that emits 5 observables.
         */
        Observable<Observable<Integer>> source = Observable
                .fromCallable(() -> Observable.just(data.value ++)).repeat(5);

        /*
            Merge the items of all the observables emitted by a single observable
         */
        Observable.merge(source).subscribe(new MyObserver<>());

        /*
            Merge an array of observables
         */
        System.out.println("# 4");
        String[] temp = {"123", "456"};
        Observable<?>[] sourcesArray = new Observable[2];
        sourcesArray[0] = Observable.fromIterable(sData);
        sourcesArray[1] = Observable.fromArray(temp);
        Observable.mergeArray(sourcesArray).subscribe(new MyObserver<>(latch));
    }

    public static void startWith() {
        System.out.println("# 1");
        List<String> collection = new ArrayList<>();
        collection.add("123");
        collection.add("456");
        Observable.fromIterable(sData).startWith(collection).subscribe(new MyObserver<>());

        System.out.println("# 2");
        Observable.fromIterable(sData).startWith(Observable.just("START WITH"))
                .subscribe(new MyObserver<>());

        System.out.println("# 3");
        String[] array = {"111", "222"};
        Observable.fromIterable(sData).startWithArray(array)
                .subscribe(new MyObserver<>());
    }

    /*
        FIXME : Not working as expected, revisit later on.
     */
    public static void switchOnNext(CountDownLatch latch) {

        List<Observable<Long>> collectionOfObservable = new ArrayList<>();

        Observable<Long> first = Observable.interval(1L, TimeUnit.SECONDS).take(5)
                .doOnNext((item) -> System.out.println("first doOnNext item = " + item));

        Observable<Long> second = Observable.interval(1L, TimeUnit.SECONDS)
                .map(item -> item + 100).take(10);

        collectionOfObservable.add(first);
        collectionOfObservable.add(second);

        Observable.switchOnNext(Observable.fromIterable(collectionOfObservable))
                .subscribeOn(Schedulers.io()).subscribe(new MyObserver<>(latch));
    }

    public static void zipIterable(CountDownLatch latch){
        List<Observable<Long>> collectionOfObservables = new ArrayList<>();
        Observable<Long> first = Observable.interval(1L, TimeUnit.SECONDS);
        Long[] array = {100L, 200L, 300L};
        Observable<Long> second = Observable.fromArray(array);
        collectionOfObservables.add(first);
        collectionOfObservables.add(second);

        /*
            1. Collect an emission from each observable in iterable
            and provides to a function to apply zip logic
         */
        Observable.zip(collectionOfObservables, items -> (Long)items[0] + (Long)items[1])
                .subscribe(new MyObserver<>(latch));
    }

    public static void zipObservables(CountDownLatch latch) {

        /*
            1. source observable is being created through create which emits 3 observables
            2. 3rd observable emits 3 items but others emit 2 items.
            3. Hence we got an observable i.e. source which is observable that further emits
            observables.
            4. Zip operator will combine each item out of these 3 observables via combiner function.
         */

        Observable<Observable<String>> source = Observable.create(observableEmitter -> {
            System.out.println("create");
            observableEmitter.onNext(Observable.just("abc", "def"));
            observableEmitter.onNext(Observable.just("123", "456"));
            observableEmitter.onNext(Observable.just("alpha", "beta", "gama"));
            observableEmitter.onComplete();
        });

        source.doOnSubscribe(d -> System.out.println("source doOnSubscribe"));
        source.doOnNext(i -> System.out.println("source doOnNext = " + i.toString()));
        source.doOnComplete(() -> System.out.println("source doOnComplete"));


        Observable.zip(source, items -> items[0] + "-" + items[1] + "-" + items[2])
                .doOnSubscribe(d -> System.out.println("zip doOnSubscribe"))
                .doOnNext(i -> System.out.println("zip doOnNext"))
                .subscribe(new MyObserver<>(latch));
    }

    public static Observable<?> zipIndividual() {

        Observable<String> first = Observable.just("abc", "def", "ghi");
        Observable<Integer> second = Observable.just(1, 2, 3);

        Observable<String> zip = Observable.zip(first, second, (a, b) -> a + "-" + b);
        zip.subscribe(new MyObserver<>());
        return zip;
    }

    public static void join() {
        //TODO: More clarification required
        Observable<Long> left = Observable.just(1L, 2L, 3L);
        Observable<Long> right = Observable.just(100L, 200L, 300L);

        left.join(right,
                leftValue -> Observable.interval(1L, TimeUnit.MICROSECONDS),
                rightValue -> Observable.interval(1L, TimeUnit.MICROSECONDS),
                (a, b) -> a + b).subscribe(new MyObserver<>());
    }

}
