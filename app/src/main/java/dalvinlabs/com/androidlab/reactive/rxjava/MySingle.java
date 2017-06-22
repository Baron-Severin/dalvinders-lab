package dalvinlabs.com.androidlab.reactive.rxjava;


import java.util.List;

import dalvinlabs.com.androidlab.reactive.rxjava.observers.MyObservers;

public class MySingle {
    /*
        Observables emits only once
     */
    public static void single() {
        io.reactivex.Single<List<String>> myObservable = io.reactivex.Single.just(Utils.getData());
        myObservable.subscribe(MyObservers.singleObserver);
    }
}
