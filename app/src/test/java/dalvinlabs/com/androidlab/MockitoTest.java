package dalvinlabs.com.androidlab;

import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MockitoTest {

    private static class DataSource {

        // private methods can not be stubbed.
        String getData() {
            return "Data from server";
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void basic() throws Exception {
        // mock creation
        ArrayList<String> mockedList = mock(ArrayList.class);

        // using mocked object
        mockedList.add("first");
        mockedList.clear();

        // verify behavior on a mocked object happened once
        verify(mockedList).add("first");
        verify(mockedList).clear();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void stubbing() throws Exception {
        // mock creation
        DataSource mockDataSource = mock(DataSource.class);

        // Subbing
        // By default if mocked object returns null if stubbing is not provided.
        System.out.println("Before stubbing = " + mockDataSource.getData());
        when(mockDataSource.getData()).thenReturn("Data from stub");
        System.out.println("After stubbing = " + mockDataSource.getData());
    }

}