package dalvinlabs.com.androidlab.design.patterns.abstractfactory.concreteproducts;

import android.util.Log;

import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractVan;


public class GammaVan extends AbstractVan {
    private static final String LOG_TAG = GammaVan.class.getSimpleName();

    @Override
    public void build() {
        Log.d(LOG_TAG, "Building Gamma Van");
    }
}
