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
    public void testJust() throws Exception {
        MyObservables.just();
    }

    @Test
    public void testFromCallable() throws Exception {
        MyObservables.fromCallable();
    }

    @Test
    public void testSingle() throws Exception {
        MySingle.single();
    }

    @Test
    public void testSubject() throws Exception {
        Hybrids.subject();
    }

    @Test
    public void testMap() throws Exception {
        //MyObservables.map();
    }
}
