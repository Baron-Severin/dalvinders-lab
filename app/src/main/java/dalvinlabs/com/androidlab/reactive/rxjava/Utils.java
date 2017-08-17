package dalvinlabs.com.androidlab.reactive.rxjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {

    public static List<String> getData() {
        String[] data = {"ABC", "DEF", "GHI", "JKL", "MNO", "PQR"};
        return Arrays.asList(data);
    }

    public static List<String> getAlphaNumericData() {
        String[] data = {"ABC", "123", "DEF", "456", "GHI", "789", "JKL", "MNO", "PQR"};
        return Arrays.asList(data);
    }

    public static List<String> transform(List<String> data) {
        System.out.println("transform()");
        data.forEach((each) -> {
            // All this is required because String i.e. each returned here is immutable.
            data.set(data.indexOf(each), each + " transformed");
        });
        return data;
    }

    public static List<String> getLargeData() {
        List<String> data = new ArrayList<>();
        Random random = new Random(1);
        for (int i = 0; i < 1000; i++) {
            data.add(Integer.toString(random.nextInt()));
        }
        return data;
    }





}
