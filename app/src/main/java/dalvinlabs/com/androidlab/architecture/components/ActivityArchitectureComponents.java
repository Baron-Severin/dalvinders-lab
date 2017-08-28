package dalvinlabs.com.androidlab.architecture.components;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import dalvinlabs.com.androidlab.R;
import dalvinlabs.com.androidlab.architecture.components.lifecycle.MyLifeCycleAwareActivity;
import dalvinlabs.com.androidlab.architecture.components.viewmodel.ActivityForViewModelComponent;

public class ActivityArchitectureComponents extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architecture_components);
    }

    public void onClickLifeCycleAware(View view) {
        startActivity(new Intent(this, MyLifeCycleAwareActivity.class));
    }

    public void onClickViewModelComponent(View view) {
        startActivity(new Intent(this, ActivityForViewModelComponent.class));
    }
}
