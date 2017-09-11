package dalvinlabs.com.androidlab.dagger;


/*
    1. Regular class
    2. Nothing to do with dagger
    3. Dagger is creating instance of this in a module to satisfy dependencies.
 */
class NetworkApiInjectionByProvides {

    boolean validateUser(String name, String password) {
        return (name != null && password != null);
    }
}
