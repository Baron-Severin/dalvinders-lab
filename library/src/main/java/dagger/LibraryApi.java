package dagger;


import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

//@Singleton
public class LibraryApi {

    private static final String TAG = "LibraryApi";

//    @Inject
    public LibraryApi() {
        Log.d(TAG, "constructor");
    }
}
