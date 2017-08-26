package dalvinlabs.com.androidlab.architecture.mvp.model;

import java.util.Random;

public class MvpModel {
    private String random;

    public String getRandom() {
        return random;
    }

    public MvpModel generate() {
        Random random = new Random();
        this.random = Integer.toString(random.nextInt());
        return this;
    }
}
