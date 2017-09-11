package dalvinlabs.com.androidlab.dagger;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dalvinlabs.com.androidlab.R;

public class DaggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        DaggerConsumer consumer = new DaggerConsumer(this);
        consumer.testDagger();
    }
}
