package dalvinlabs.com.androidlab;


import org.junit.Test;

import dalvinlabs.com.androidlab.reactive.rxjava.MyMaybe;

public class MyMaybeTest {

    @Test
    public void testJust() {
        MyMaybe.just();
    }

    @Test
    public void testFromCallable() {
        MyMaybe.fromCallable();
    }
}
