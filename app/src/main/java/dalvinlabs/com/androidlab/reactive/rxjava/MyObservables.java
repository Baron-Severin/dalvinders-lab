package dalvinlabs.com.androidlab.reactive.rxjava;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MyObservables {

    // # # # OBSERVERS

    private static CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static class MyObserver<T> implements Observer<T> {

        MyObserver() {

        }

        @Override
        public void onSubscribe(Disposable d) {
            System.out.println("MyObserver :: onSubscribe");
        }

        @Override
        public void onNext(T value) {
            System.out.println("MyObserver :: onNext");
            System.out.println("T = " + value.getClass());
            System.out.println("thread = " + Thread.currentThread().getName());
            System.out.println("output = " + value);
            System.out.println("-----");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("MyObserver :: onError = " + e);
            System.out.println("-----");
        }

        @Override
        public void onComplete() {
            System.out.println("MyObserver :: onComplete");
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

        Observable.fromIterable(data).buffer(3).subscribe(new MyObserver<>());

        Observable.fromIterable(data).buffer(2, 3).subscribe(new MyObserver<>());

        Observable.fromIterable(data).buffer(2, 3, ArrayList::new).subscribe(new MyObserver<>());

        Observable.fromIterable(data).buffer(2, HashSet::new).subscribe(new MyObserver<>());

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
        }, HashSet::new).subscribe(new MyObserver<>());

        /*
            Buffer with boundary observable
         */
        Observable.fromIterable(data).buffer((Observer<? super Integer> observer) -> {
            System.out.println("onNext - 1");
            observer.onNext(1);
            observer.onNext(2);
        }).subscribe(new MyObserver<>());

        /*
            1. Buffer with boundary observable by providing some other in built observable
            2. Create a buffer of 100 Microseconds
         */
        Observable.fromIterable(data)
                .buffer(Observable.interval(100L, TimeUnit.MICROSECONDS))
                .subscribe(new MyObserver<>());


    }

    public static void merge() {
        Observable<String> a = Observable.just("abc");
        Observable<Integer> b = Observable.just(123);

        Observable.zip(a, b, (c, d) -> {
            return (String) c+d;
        }).subscribe(new MyObserver<>());
    }



}
