package dalvinlabs.com.androidlab.reactive.rxjava;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyObservables {

    private static final String LOG_TAG = MyObservables.class.getSimpleName();

    private static Observer<List<String>> genericObserver = new Observer<List<String>>() {
        @Override
        public void onSubscribe(Disposable d) {
            System.out.println("onSubscribe");
        }

        @Override
        public void onNext(List<String> output) {
            System.out.println("-----");
            System.out.println("onNext");
            System.out.println("thread = " + Thread.currentThread().getName());
            System.out.println("output = " + output.toString());
            System.out.println("-----");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("onError");
        }

        @Override
        public void onComplete() {
            System.out.println("onComplete");
        }
    };

    /*
        Observables and Observer runs on a same thread.
        just emits single item i.e. in this case whole list.
     */
    public static void just() {
        Observable<List<String>> myObservable = Observable.just(Utils.getData());
        myObservable.subscribe(genericObserver);
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
        myObservable.subscribe(value -> {System.out.println("output = " + value);});
        
    }

    public static void fromIterable() {
        //Observable<String> myObservable = Observable.fromIterable(Utils.getData()).subscribe(genericObserver);

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
                .subscribe(genericObserver);
    }




}
