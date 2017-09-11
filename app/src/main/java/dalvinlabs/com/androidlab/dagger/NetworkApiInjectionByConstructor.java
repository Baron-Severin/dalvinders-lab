package dalvinlabs.com.androidlab.dagger;


import javax.inject.Inject;

/*
    1. Regular class
    2. Nothing to do with dagger
    3. Dagger is creating instance of this via @Inject annotation
 */
class NetworkApiInjectionByConstructor {

    @Inject
    NetworkApiInjectionByConstructor() {

    }

    boolean validateUser(String name, String password) {
        return (name != null && password != null);
    }
}
