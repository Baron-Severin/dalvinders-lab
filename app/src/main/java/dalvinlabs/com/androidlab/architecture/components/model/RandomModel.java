package dalvinlabs.com.androidlab.architecture.components.model;


import java.util.Random;

public class RandomModel {

    private String random;

    public String getRandom() {
        return random;
    }

    public RandomModel generate() {
        Random random = new Random();
        this.random = Integer.toString(random.nextInt());
        return this;
    }
}
