package dalvinlabs.com.androidlab.architecture.components.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import dalvinlabs.com.androidlab.R;

/*
    Support lib 26.1.0 brings life cycle feature in regular activities and fragments.
 */
public class MyLifeCycleAwareActivity extends AppCompatActivity {

    private static final String LOG_TAG = MyLifeCycleAwareActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_aware);
        // Passing life cycle to another component
        new CustomComponent(getLifecycle());
        // Alternate way
        //getLifecycle().addObserver(getInstanceOfComponent);
    }

    @Override
    protected void onStart() {
        Log.d(LOG_TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(LOG_TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(LOG_TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(LOG_TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(LOG_TAG, "onDestroy");
        super.onDestroy();
    }
}