package dalvinlabs.com.androidlab.dagger;


import android.util.Log;

import javax.inject.Inject;

/*
    Since caller of this i.e. DaggerConsumer in already calling DaggerComponent's method to trigger
    injection, this doesn't have to explicitly trigger injection. Here networkApi1 and networkApi2
    will have valid references.
 */
class DaggerConsumer2 {

    private static final String LOG_TAG = DaggerConsumer2.class.getSimpleName();

    // This gets injected because NetworkApiModule has a provide method to create this instance.
    @SuppressWarnings("WeakerAccess")
    @Inject
    NetworkApiInjectionByProvides networkApi1;

    // This gets injected because constructor of class NetworkApiInjectionByConstructor
    // is annotated with @Inject, there's no provides method.
    @SuppressWarnings("WeakerAccess")
    @Inject
    NetworkApiInjectionByConstructor networkApi2;

    // This gets injected because NetworkApiModule has a provide method to create this instance.
    @SuppressWarnings("WeakerAccess")
    @Inject
    NetworkApiInjectionByProvidesWithContext networkApi3;

    @Inject
    DaggerConsumer2() {

    }

    void testDagger() {
        if (networkApi1 != null) {
            Log.d(LOG_TAG, "NetworkApiInjectionByProvides Injection passed");
        } else {
            Log.d(LOG_TAG, "NetworkApiInjectionByProvides Injection failed");
        }

        if (networkApi2 != null) {
            Log.d(LOG_TAG, "NetworkApiInjectionByConstructor Injection passed");
        } else {
            Log.d(LOG_TAG, "NetworkApiInjectionByConstructor Injection failed");
        }

        if (networkApi3 != null) {
            networkApi3.testDagger();
        } else {
            Log.d(LOG_TAG, "NetworkApiInjectionByProvidesWithContext Injection failed");
        }
    }

}
