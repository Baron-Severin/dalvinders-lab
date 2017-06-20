package dalvinlabs.com.androidlab.reactive.rxjava;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxJava {

    private static final String LOG_TAG = RxJava.class.getSimpleName();

    private static List<String> getData() {
        String[] data = {"ABC", "XYZ"};
        return Arrays.asList(data);
    }

    private static Observer<List<String>> myObserver = new Observer<List<String>>() {
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
        Observable and Observer runs on a same thread.
        just emits single item i.e. in this case whole list.
     */
    public static void just() {
        Observable<List<String>> myObservable = Observable.just(getData());
        myObservable.subscribe(myObserver);
    };

    /*
        Observable and Observer runs on a separate threads.
     */
    public static void fromCallable() {
        Observable<List<String>> myObservable = Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                System.out.println("call, thread = " + Thread.currentThread().getName());
                return getData();
            }
        });

        myObservable.subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread()) For ANDROID use Android Schedulers.
                .observeOn(Schedulers.newThread()) // For ease of unit test, this is used
                .subscribe(myObserver);
    }

    private static SingleObserver<List<String>> mySingleObserver = new SingleObserver<List<String>>() {
        @Override
        public void onSubscribe(Disposable d) {
            System.out.println("onSubscribe");
        }

        @Override
        public void onSuccess(List<String> value) {
            System.out.println("onSuccess data = " + value.toString());
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("onError = " + e.getMessage());
        }
    };

    /*
        Observable emits only once
     */
    public static void single() {
        Single<List<String>> myObservable = Single.just(getData());
        myObservable.subscribe(mySingleObserver);
    }

    /*
        Subject is both observable and observer
     */
    public static void subject() {
        Subject<List<String>> mySubject = PublishSubject.create();
        // Subject is a PIPE, takes data at one end and provides at the other end.
        mySubject.subscribe(myObserver); // Subject is working as a observable, that's why it can be subscribed.
        System.out.println("Going to push data to subject");
        mySubject.onNext(getData()); // Subject is working as a observer, that's why it's taking data.
    }

    /*
        Map operator takes an arbitrary function, apply it before returning data to observer.
     */
    public static void map() {
        Single<List<String>> myObservable = Single.just(getData())
                .map(RxJava::transform);
        myObservable.subscribe(mySingleObserver);
    }

    public static List<String> transform(List<String> data) {
        System.out.println("transform()");
        data.forEach((each) -> {
            // All this is required because String i.e. each returned here is immutable.
            data.set(data.indexOf(each), each + " transformed");
        });
        return data;
    }


    /*

     */
}
