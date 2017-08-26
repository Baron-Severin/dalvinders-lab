package dalvinlabs.com.androidlab.architecture.mvvm.model;


import java.util.Random;

/*
    Similar to MVC and MVP
 */

public class MvvmModel {
    private String random;

    public String getRandom() {
        return random;
    }

    public MvvmModel generate() {
        Random random = new Random();
        this.random = Integer.toString(random.nextInt());
        return this;
    }
}
