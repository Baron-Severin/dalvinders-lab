package dalvinlabs.com.androidlab.reactive.rxjava.operators;

import java.util.List;

import dalvinlabs.com.androidlab.reactive.rxjava.MyObservables;
import dalvinlabs.com.androidlab.reactive.rxjava.Utils;
import io.reactivex.Single;

public class MyOperators {
    /*
        Map operator takes an arbitrary function, apply it before returning data to observer.
     */
//    public static void map() {
//        MySingle<List<String>> myObservable = MySingle.just(Utils.getData())
//                .map(MyObservables::transform);
//        myObservable.subscribe(mySingleObserver);
//    }
//
//    public static List<String> transform(List<String> data) {
//        System.out.println("transform()");
//        data.forEach((each) -> {
//            // All this is required because String i.e. each returned here is immutable.
//            data.set(data.indexOf(each), each + " transformed");
//        });
//        return data;
//    }
}
