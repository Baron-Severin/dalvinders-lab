package dalvinlabs.com.androidlab.dagger;

import javax.inject.Singleton;

import dagger.Component;
import dagger.LibraryApi;
import dagger.LibraryComponent;

/*
    1. Dagger generates code for this component with class name DaggerNetworkApiComponent.
    2. Instance of DaggerNetworkApiComponent can be stored in Application class.
    3. Instance of DaggerNetworkApiComponent is used to inject dependencies into particular class(A).
    4. (A) needs to annotate fields with @Inject which it wants to satisfy with injections.
    5. We can enable modules via @Component annotation
*/
@NetworkScope
@Component(modules = {NetworkApiModule.class, NetworkApiModuleWithContext.class}
                , dependencies = LibraryComponent.class)
public interface NetworkApiComponent {

    // It essentially means inject dependencies into DaggerConsumer class if this method is invoked.
    void inject(DaggerConsumer consumer);
}
