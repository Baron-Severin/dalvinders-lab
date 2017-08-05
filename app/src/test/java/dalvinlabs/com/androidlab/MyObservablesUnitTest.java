package dalvinlabs.com.androidlab;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import dalvinlabs.com.androidlab.reactive.rxjava.Hybrids;
import dalvinlabs.com.androidlab.reactive.rxjava.MyObservables;
import dalvinlabs.com.androidlab.reactive.rxjava.MySingle;

@RunWith(MockitoJUnitRunner.class)
public class MyObservablesUnitTest {

    @Test
    public void testObservableJust() throws Exception {
        MyObservables.just();
    }

    @Test
    public void testObservableFromArray() throws Exception {
        MyObservables.fromArray();
    }

    @Test
    public void testObservableFromCallable() throws Exception {
        MyObservables.fromCallable();
    }

    @Test
    public void testObservableFromIterable() throws Exception {
        MyObservables.fromIterable();
    }



    @Test
    public void testSingle() throws Exception {
        MySingle.single();
    }

    @Test
    public void testSubject() throws Exception {
        Hybrids.subject();
    }


}
