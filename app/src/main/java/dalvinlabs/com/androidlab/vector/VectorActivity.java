package dalvinlabs.com.androidlab.vector;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dalvinlabs.com.androidlab.R;
import dalvinlabs.com.androidlab.databinding.ActivityVectorBinding;

/*
    App min sdk version < 21 -> App uses vector drawable -> Android automatically generates
    corresponding PNG files for those vectors in directory drawables-xxhdpi-v4, This is visible
    in apk not in project.
    1. It's recommended to use app:srcCompat in image view while using AppCompatActivity.
    2. Though android:src does work
    3. But if vector contains the bug https://issuetracker.google.com/issues/37085738
       then android:src will break as this is fixed in support library, hence app:srcCompat works
    4. android:src will user VectorDrawable adn app:srcCompat will user VectorDrawableCompat

 */

public class VectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_vector);
    }
}
