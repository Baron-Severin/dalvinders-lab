package dalvinlabs.com.androidlab.reactive.rxjava;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;

public class MyObservables {

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


    // # # # OBSERVABLES : CREATE

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
        Observable<Long> myObservable = Observable.interval(0l, 30l,
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
        Observable.intervalRange(0l, 10l, 0l, 10l, TimeUnit.MILLISECONDS).
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
                    return objectObservable.delay(1l, TimeUnit.SECONDS).take(3);
                })).subscribeOn(Schedulers.io()).subscribe(new MyObserver<>());

    }


    // # # # OBSERVABLES : TRANSFORMING

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
            return Observable.just(value).delay(100l, TimeUnit.MICROSECONDS);
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
                .buffer(Observable.interval(100L, TimeUnit.MICROSECONDS), () -> {
                    return new HashSet<>();
                }).subscribe(new MyObserver<>(latch));


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











}
