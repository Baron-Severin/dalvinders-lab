package dalvinlabs.com.androidlab.design.patterns.abstractfactory.concreteproducts;


import android.util.Log;

import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractCar;

public class AlphaCar extends AbstractCar {
    private static final String LOG_TAG = AlphaCar.class.getSimpleName();

    @Override
    public void build() {
        Log.d(LOG_TAG, "Building Alpha Car");
    }
}
