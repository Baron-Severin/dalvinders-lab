package dalvinlabs.com.androidlab.reactive.rxjava;

import java.util.concurrent.CountDownLatch;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class MyMaybe {

    static class MyMayBeObserver<T> implements MaybeObserver<T> {

        long startTime = 0L;
        String mName;
        CountDownLatch mLatch;

        MyMayBeObserver() {}

        MyMayBeObserver(String name) {
            mName = name;
        }

        MyMayBeObserver(CountDownLatch latch) {
            mLatch = latch;
        }

        @Override
        public void onSubscribe(Disposable d) {
            startTime = System.currentTimeMillis();
            if (mName != null) {
                System.out.println("MyObserver :: name = " + mName);
            }
            System.out.println("MyObserver :: onSubscribe");
            System.out.println("MyObserver :: disposable = " + d.toString());
            System.out.println("-----");
        }

        @Override
        public void onSuccess(T value) {
            if (mName != null) {
                System.out.println("MyObserver :: name = " + mName);
            }
            System.out.println("MyObserver :: onSuccess");
            System.out.println("T = " + value.getClass());
            System.out.println("thread = " + Thread.currentThread().getName());
            System.out.println("output = " + value);
            System.out.println("-----");
        }

        @Override
        public void onError(Throwable e) {
            if (mName != null) {
                System.out.println("MyObserver :: name = " + mName);
            }
            System.out.println("MyObserver :: onError = " + e);
            System.out.println("-----");
        }

        @Override
        public void onComplete() {
            if (mName != null) {
                System.out.println("MyObserver :: name = " + mName);
            }
            System.out.println("MyObserver :: onComplete");
            System.out.println("Time taken milliseconds = " + (System.currentTimeMillis() - startTime));
            System.out.println("-----");
            if (mLatch != null) {
                mLatch.countDown();
            }
        }
    }

    public static void just() {
        //Invokes observer's onSuccess
        //Maybe.just("abc")
        //        .subscribe(new MyMayBeObserver<>());

        //Invokes observer's onComplete
        Maybe.fromCallable(() -> null)
                .switchIfEmpty(observer -> observer.onSuccess("switchIfEmpty"))
                .subscribe(new MyMayBeObserver<>());

        //Invokes observer's onError
        //Maybe.error(new Throwable("Test exception"))
        //        .subscribe(new MyMayBeObserver<>());
    }

    public static void fromCallable() {
        // Won't invoke onSuccess of observer because it returns null
        Maybe.fromCallable(() -> null)
                .subscribe(new MyMayBeObserver<>());

        Maybe.fromCallable(() -> "abc")
                .subscribe(new MyMayBeObserver<>());
    }


}
