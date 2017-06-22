package dalvinlabs.com.androidlab.reactive.rxjava.observers;

/*
    Place to keep all observers.
 */

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MyObservers {

    public static Observer<List<String>> genericObserver = new Observer<List<String>>() {
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

    public static SingleObserver<List<String>> singleObserver = new SingleObserver<List<String>>() {
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
}
