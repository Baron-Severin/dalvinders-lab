package dalvinlabs.com.androidlab.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/*
    1. Used by dagger
    2. Instances of dependencies are created here.
    3. Dagger generate code calls these methods to inject dependencies into other classes.
 */
@Module
class NetworkApiModule {

//    @NetworkScope
    @Provides
    /*
        1. Instance of NetworkApiInjectionByProvides dependency created here.
        2. method name does not matter.
        3. return type matters.
        4. It's an explicit way of creating instances of dependencies.
        5. This is used if some complex logic is required to create dependency instance.
        6. Otherwise constructor of dependency can be annotated with @inject. For e.g. in this
            case NetworkApiInjectionByProvides constructor could have been annotated with @inject.
     */
    NetworkApiInjectionByProvides methodNameDoesNotMatter() {
        return new NetworkApiInjectionByProvides();
    }
}
