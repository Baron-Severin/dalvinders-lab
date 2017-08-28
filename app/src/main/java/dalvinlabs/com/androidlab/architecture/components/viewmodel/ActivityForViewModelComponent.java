package dalvinlabs.com.androidlab.architecture.components.viewmodel;


import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import dalvinlabs.com.androidlab.R;
import dalvinlabs.com.androidlab.databinding.ActivityArchitectureComponentViewModelBinding;

public class ActivityForViewModelComponent extends LifecycleActivity {

    private static final String LOG_TAG = ActivityForViewModelComponent.class.getSimpleName();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(LOG_TAG, "onCreate");
        /*
            Get lifecycle aware view model. On screen rotation same view model instance is received.
         */
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        ActivityArchitectureComponentViewModelBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_architecture_component_view_model);
        binding.setViewModel(viewModel);
    }
}
