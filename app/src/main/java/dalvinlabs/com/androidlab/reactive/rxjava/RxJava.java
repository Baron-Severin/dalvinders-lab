package dalvinlabs.com.androidlab.reactive.rxjava;


import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

public class RxJava {
    private static final String LOG_TAG = RxJava.class.getSimpleName();

    public void simpleObservable() {
        Observable<String> observable = Observable.create(
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {


                    }
                }
        );
    }

}
