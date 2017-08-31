package dalvinlabs.com.androidlab;


import android.app.Application;

import dalvinlabs.com.androidlab.dagger.DaggerNetworkApiComponent;
import dalvinlabs.com.androidlab.dagger.NetworkApiComponent;

public class AndroidLabApplication extends Application {

    private NetworkApiComponent networkApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        networkApiComponent = DaggerNetworkApiComponent.builder().build();
    }

    public NetworkApiComponent getNetworkApiComponent() {
        return networkApiComponent;
    }
}
