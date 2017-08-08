package dalvinlabs.com.androidlab.reactive.rxjava;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyObservables {

    // # # # OBSERVERS

    private static CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static Observer<String> genericObserver1 = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {
            System.out.println("genericObserver1 :: onSubscribe");
            compositeDisposable.add(d);
        }

        @Override
        public void onNext(String output) {
            System.out.println("-----");
            System.out.println("genericObserver1 :: onNext");
            System.out.println("thread = " + Thread.currentThread().getName());
            System.out.println("output = " + output);
            System.out.println("-----");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("genericObserver1 :: onError = " + e);
            e.printStackTrace();
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
        }

        @Override
        public void onNext(String output) {
            System.out.println("-----");
            System.out.println("genericObserver2 :: onNext");
            System.out.println("thread = " + Thread.currentThread().getName());
            System.out.println("output = " + output);
            System.out.println("-----");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("genericObserver2 :: onError = " + e.getMessage());
        }

        @Override
        public void onComplete() {
            System.out.println("genericObserver2 :: onComplete");
        }
    };

    // # # # # # CREATE
    private static Consumer<List<String>> consumerList = data ->
            System.out.println("consumerList :: accept = " + data.toString());


    // # # # OBSERVABLES
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
     */
    public static void create() {
        Observable<String> myObservable = Observable.create((emitter) -> {
            if (!emitter.isDisposed()) {
                System.out.println("got emitter = " + emitter);
                emitter.onNext("from create");
                emitter.onError(new Throwable("Just a test"));
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
        Generate observable
        TODO: more understanding required
     */
    public static void generate() {
        Observable<String> myObservable = Observable.generate( (emitter -> {
            System.out.println("got emitter = " + emitter);
            emitter.onNext("from generate");
            emitter.onError(new Throwable("Just a test"));
        }));
        myObservable.subscribe(genericObserver1);
        System.out.println("-----");
        myObservable.subscribe(genericObserver2);
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


}
