package dalvinlabs.com.androidlab;


import android.app.Application;

import com.example.interfaces.Component;
import com.example.interfaces.ComponentProvider;

import dagger.DaggerLibraryComponent;
//import dagger.LibraryApplication;
import dagger.LibraryComponent;
import dalvinlabs.com.androidlab.dagger.DaggerNetworkApiComponent;
import dalvinlabs.com.androidlab.dagger.NetworkApiComponent;
import dalvinlabs.com.androidlab.dagger.NetworkApiModuleWithContext;

public class AndroidLabApplication extends Application implements ComponentProvider {

    private NetworkApiComponent networkApiComponent;
    private LibraryComponent libraryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        /*
            Since we created a @Module i.e. NetworkApiModuleWithContext
            which expects context in constructor, hence we are creating an instance and passing here.
            There onwards that module can pass that context further while satisfying dependencies of
            other classes.
         */
        libraryComponent = DaggerLibraryComponent.builder().build();

        networkApiComponent = DaggerNetworkApiComponent.builder().
                networkApiModuleWithContext(new NetworkApiModuleWithContext(this))
                .libraryComponent(libraryComponent)
                .build();

//        LibraryApplication.libraryComponent = libraryComponent;
    }

    public NetworkApiComponent getNetworkApiComponent() {
        return networkApiComponent;
    }


    @Override
    public Component get() {
        return libraryComponent;
    }
}
