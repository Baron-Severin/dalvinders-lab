package dalvinlabs.com.androidlab.design.patterns.abstractfactory.concreteproducts;

import android.util.Log;

import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractTruck;


public class GammaTruck extends AbstractTruck {
    private static final String LOG_TAG = GammaTruck.class.getSimpleName();

    @Override
    public void build() {
        Log.d(LOG_TAG, "Building Gamma Truck");
    }
}
