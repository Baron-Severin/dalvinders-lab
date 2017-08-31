package dalvinlabs.com.androidlab.dagger;

import javax.inject.Singleton;

import dagger.Component;

/*
    1. Dagger generates code for this component with class name DaggerNetworkApiComponent.
    2. Instance of DaggerNetworkApiComponent can be stored in Application class.
    3. Instance of DaggerNetworkApiComponent is used to inject dependencies into particular class(A).
    4. (A) needs to annotate fields with @Inject which it wants to satisfy with injections.
    5. We can enable modules via @Component annotation
*/
@Singleton
@Component(modules = {NetworkApiModule.class})
public interface NetworkApiComponent {
    void inject(DaggerConsumer consumer);
    void inject(DaggerConsumer2 consumer);
}
