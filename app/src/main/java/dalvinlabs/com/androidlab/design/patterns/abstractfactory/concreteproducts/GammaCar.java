package dalvinlabs.com.androidlab.design.patterns.abstractfactory.concreteproducts;

import android.util.Log;

import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractCar;


public class GammaCar extends AbstractCar {
    private static final String LOG_TAG = GammaCar.class.getSimpleName();

    @Override
    public void build() {
        Log.d(LOG_TAG, "Building Gamma Car");
    }
}
