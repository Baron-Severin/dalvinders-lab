package dalvinlabs.com.androidlab.reactive.rxjava;


import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MySingle {

    private static SingleObserver<List<String>> singleObserver = new SingleObserver<List<String>>() {
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
        1. Observables emits only once
        2. Map operator is applied which is like calling a function for each and every value emitted
            observable.
     */
    public static void single() {
        Single<List<String>> myObservable = Single.just(Utils.getData()).map(Utils::transform);
        myObservable.subscribe(singleObserver);
    }
}
