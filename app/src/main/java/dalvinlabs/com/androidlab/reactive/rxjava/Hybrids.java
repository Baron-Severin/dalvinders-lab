package dalvinlabs.com.androidlab.reactive.rxjava;

/*
    Place to subjects i.e. which is both an observable and an observer
 */

import java.util.List;

import dalvinlabs.com.androidlab.reactive.rxjava.observers.MyObservers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class Hybrids {
    /*
        Subject is both observable and observer
     */
    public static void subject() {
        Subject<List<String>> mySubject = PublishSubject.create();
        // Subject is a PIPE, takes data at one end and provides at the other end.
        mySubject.subscribe(MyObservers.genericObserver); // Subject is working as a observable, that's why it can be subscribed.
        System.out.println("Going to push data to subject");
        mySubject.onNext(Utils.getData()); // Subject is working as a observer, that's why it's taking data.
    }
}
