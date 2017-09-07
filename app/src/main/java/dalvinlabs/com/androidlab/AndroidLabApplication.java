package dalvinlabs.com.androidlab;


import android.app.Application;

//import dalvinlabs.com.androidlab.dagger.DaggerNetworkApiComponent;
//import dalvinlabs.com.androidlab.dagger.NetworkApiComponent;
//import dalvinlabs.com.androidlab.dagger.NetworkApiModuleWithContext;

public class AndroidLabApplication extends Application {

    //private NetworkApiComponent networkApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        /*
            Since we created a @Module i.e. NetworkApiModuleWithContext
            which expects context in constructor, hence we are creating an instance and passing here.
            There onwards that module can pass that context further while satisfying dependencies of
            other classes.
         */
        //networkApiComponent = DaggerNetworkApiComponent.builder().
        //        networkApiModuleWithContext(new NetworkApiModuleWithContext(this)).build();
    }

    //public NetworkApiComponent getNetworkApiComponent() {
    //    return networkApiComponent;
    //}
}
