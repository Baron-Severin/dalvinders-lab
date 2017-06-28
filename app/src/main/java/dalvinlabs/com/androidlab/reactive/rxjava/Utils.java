package dalvinlabs.com.androidlab.reactive.rxjava;

import java.util.Arrays;
import java.util.List;

public class Utils {

    public static List<String> getData() {
        String[] data = {"ABC", "XYZ"};
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
}
