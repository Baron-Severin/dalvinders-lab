package dalvinlabs.com.androidlab.dagger;


import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

/*
    1. Regular class
    2. Nothing to do with dagger
    3. Dagger is creating instance of this in a module to satisfy dependencies.
    4. And dagger provides it context as well.
 */
class NetworkApiInjectionByProvidesWithContext {

    private static final String LOG_TAG = NetworkApiInjectionByProvidesWithContext.class.getSimpleName();

    Context context;

    NetworkApiInjectionByProvidesWithContext(Context context) {
        this.context = context;
    }

    void testDagger() {
        if (context == null) {
            Log.d(LOG_TAG,"Injection failed");
        } else {
            Log.d(LOG_TAG,"Injection passed");
        }
    }
}
