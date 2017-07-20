package dalvinlabs.com.androidlab.reactive.rxjava;

/*
    Place to subjects i.e. which is both an observable and an observer
 */

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class Hybrids {

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
        Subject is both observable and observer
     */
    public static void subject() {
        Subject<List<String>> mySubject = PublishSubject.create();
        // Subject is a PIPE, takes data at one end and provides at the other end.
        mySubject.subscribe(genericObserver); // Subject is working as a observable, that's why it can be subscribed.
        System.out.println("Going to push data to subject");
        mySubject.onNext(Utils.getData()); // Subject is working as a observer, that's why it's taking data.
    }
}
