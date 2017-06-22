package dalvinlabs.com.androidlab.reactive.rxjava;


import java.util.List;
import java.util.concurrent.Callable;

import dalvinlabs.com.androidlab.reactive.rxjava.observers.MyObservers;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class MyObservables {

    private static final String LOG_TAG = MyObservables.class.getSimpleName();

    /*
        MyObservables and Observer runs on a same thread.
        just emits single item i.e. in this case whole list.
     */
    public static void just() {
        io.reactivex.Observable<List<String>> myObservable = io.reactivex.Observable.just(Utils.getData());
        myObservable.subscribe(MyObservers.genericObserver);
    }

    ;

    /*
        MyObservables and Observer runs on a separate threads.
     */
    public static void fromCallable() {
        io.reactivex.Observable<List<String>> myObservable = io.reactivex.Observable.
                fromCallable(new Callable<List<String>>() {
                    @Override
                    public List<String> call() throws Exception {
                        System.out.println("call, thread = " + Thread.currentThread().getName());
                        return Utils.getData();
                    }
                });

        myObservable.subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread()) For ANDROID use Android Schedulers.
                .observeOn(Schedulers.newThread()) // For ease of unit test, this is used
                .subscribe(MyObservers.genericObserver);
    }
}
