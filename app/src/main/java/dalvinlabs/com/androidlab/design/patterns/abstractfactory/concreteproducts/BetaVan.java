package dalvinlabs.com.androidlab.design.patterns.abstractfactory.concreteproducts;


import android.util.Log;

import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractVan;

public class BetaVan extends AbstractVan {
    private static final String LOG_TAG = BetaVan.class.getSimpleName();

    @Override
    public void build() {
        Log.d(LOG_TAG, "Building Beta Van");
    }
}
