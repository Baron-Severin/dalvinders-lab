package dalvinlabs.com.androidlab;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.*;

public class MockitoTest {

    static class DataSource {

        public DataSource() {

        }

        // private methods can not be stubbed.
        String getData() {
            return "Data from server";
        }

        String getData(int input) {
            return "Data from server with argument";
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
    public void bDD() throws Exception {
        DataSource mockDataSource = mock(DataSource.class);
        given(mockDataSource.getData()).willReturn("Given stubbed data");
        //when
        mockDataSource.getData();
        //then
        //Assert.assertThat();
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
        // Verify getData() is invoked 2 times
        verify(mockDataSource, times(2)).getData();
        System.out.println("Before stubbing = " + mockDataSource.getData(0));
        when(mockDataSource.getData(0)).thenReturn("Data from stub");
        // Argument value must match as well
        System.out.println("After stubbing = " + mockDataSource.getData(0));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void argMatcher() throws Exception {
        DataSource mockDataSource = mock(DataSource.class);
        // Stubbing using built in arg matcher
        when(mockDataSource.getData(anyInt())).thenReturn("Data from arg matcher stub");
        System.out.println("After stubbing - 1 = " + mockDataSource.getData(10));
        System.out.println("After stubbing - 2 = " + mockDataSource.getData(999));

        // arg matcher can be used in verify
        verify(mockDataSource, times(2)).getData(anyInt());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void spies() throws Exception {
        // Spy invokes real methods of object unless method is stubbed.
        // where as mock return default values for methods.
        DataSource dataSource = new DataSource();
        DataSource spyDataSource = spy(dataSource);
        // stub a method
        when(spyDataSource.getData()).thenReturn("Stubbed value");
        // other method getData(int value) will be invoked on a real object
        System.out.println("output - 1 = " + spyDataSource.getData());
        System.out.println("output - 2 = " + spyDataSource.getData(0));
    }



}