package dalvinlabs.com.androidlab.dagger;


import android.app.Application;

import dagger.Module;
import dagger.Provides;

/*
    1. Used by dagger
    2. Instances of dependencies are created here.
    3. Dagger generate code calls these methods to inject dependencies into other classes.
    4. Constructor is provided to receive context, it's provided at the time of building a component
        in application onCreate()
 */
@Module
public class NetworkApiModuleWithContext {

    private Application application;

    public NetworkApiModuleWithContext(Application application) {
        this.application = application;
    }

    @Provides
    NetworkApiInjectionByProvidesWithContext whatsInName() {
        return new NetworkApiInjectionByProvidesWithContext(application.getApplicationContext());
    }
}
