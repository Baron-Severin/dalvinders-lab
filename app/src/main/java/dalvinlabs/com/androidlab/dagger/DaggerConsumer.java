package dalvinlabs.com.androidlab.dagger;


import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import dalvinlabs.com.androidlab.AndroidLabApplication;

public class DaggerConsumer {

    private static final String LOG_TAG = DaggerConsumer.class.getSimpleName();

    // Dagger can not work with private fields.
    // This gets injected because NetworkApiModule has a provide method to create this instance.
    @SuppressWarnings("WeakerAccess")
    @Inject
    NetworkApiInjectionByProvides networkApiInjectionByProvides;

    // This gets injected because constructor of class is annotated with @Inject, there's no provides method.
    @Inject
    NetworkApiInjectionByConstructor networkApiInjectionByConstructor;

    DaggerConsumer(Context context) {
        AndroidLabApplication androidLabApplication = (AndroidLabApplication) context.getApplicationContext();
        androidLabApplication.getNetworkApiComponent().inject(this);
    }

    void testDagger() {
        if (networkApiInjectionByProvides == null) {
            Log.d(LOG_TAG, "Injection through annotation @Provides failed");
        } else {
            Log.d(LOG_TAG, "Injection through annotation @Provides passed");
            System.out.println("Validate user = " + networkApiInjectionByProvides.validateUser("abc", "123"));
        }

        if (networkApiInjectionByConstructor == null) {
            Log.d(LOG_TAG, "Injection through annotation @Provides failed");
        } else {
            Log.d(LOG_TAG, "Injection through annotation @Provides passed");
            System.out.println("Validate user = " + networkApiInjectionByProvides.validateUser("abc", "123"));
        }
    }
}
