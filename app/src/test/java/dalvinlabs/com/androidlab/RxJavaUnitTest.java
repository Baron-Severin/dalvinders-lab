package dalvinlabs.com.androidlab;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import dalvinlabs.com.androidlab.reactive.rxjava.RxJava;

@RunWith(MockitoJUnitRunner.class)
public class RxJavaUnitTest {

    @Test
    public void testJust() throws Exception {
        RxJava.just();
    }

    @Test
    public void testFromCallable() throws Exception {
        RxJava.fromCallable();
    }

    @Test
    public void testSingle() throws Exception {
        RxJava.single();
    }

    @Test
    public void testSubject() throws Exception {
        RxJava.subject();
    }

    @Test
    public void testMap() throws Exception {
        RxJava.map();
    }
}
