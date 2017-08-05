package dalvinlabs.com.androidlab;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.Executors;

import dalvinlabs.com.androidlab.reactive.rxjava.Hybrids;
import dalvinlabs.com.androidlab.reactive.rxjava.MyObservables;
import dalvinlabs.com.androidlab.reactive.rxjava.MySingle;

//@RunWith(MockitoJUnitRunner.class)
public class MyObservablesTest {

    @Test
    public void testObservableJust() throws Exception {
        MyObservables.just();
    }

    @Test
    public void testObservableFromCallable() throws Exception {
        MyObservables.fromCallable();
    }

    @Test
    public void testObservableFromArray() throws Exception {
        MyObservables.fromArray();
    }

    @Test
    public void testObservableFromIterable() throws Exception {
        MyObservables.fromIterable();
    }

    @Test
    public void testObservableFromFuture() throws Exception {
        MyObservables.fromFuture();
    }

    @Test
    public void testObservableCreate() throws Exception {
        MyObservables.create();
    }

    @Test
    public void testObservableGenerate() throws Exception {
        MyObservables.generate();
    }

    @Test
    public void testObservableDefer() throws Exception {
        MyObservables.defer();
    }
}
