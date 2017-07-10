package dalvinlabs.com.androidlab.architecture.mvc.model;

import java.util.Random;

/*
    Model
 */
public class MvcModel {
    private String random;

    public String getRandom() {
        return random;
    }

    public MvcModel generate() {
        Random random = new Random();
        this.random = Integer.toString(random.nextInt());
        return this;
    }
}
