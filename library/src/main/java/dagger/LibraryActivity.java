package dagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.interfaces.*;
import com.example.library.R;

import javax.inject.Inject;

public class LibraryActivity extends AppCompatActivity {

    private static final String TAG = "LibraryActivity";

    @Inject
    LibraryApi libraryApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

//        LibraryApplication.libraryComponent.inject(this);


        ComponentProvider componentProvider = (ComponentProvider) getApplication();
        LibraryComponent component = (LibraryComponent) componentProvider.get();
        component.inject(this);

        Log.d(TAG, "libraryApi = " + libraryApi);
        System.out.println("");
    }
}
