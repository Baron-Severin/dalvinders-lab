package dalvinlabs.com.androidlab.architecture.mvvm.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dalvinlabs.com.androidlab.R;
import dalvinlabs.com.androidlab.architecture.mvvm.viewmodel.RandomViewModel;
import dalvinlabs.com.androidlab.databinding.ActivityViewMvvmBinding;

/*
    1. It's part of the view in MVVM.
    2. Should only setup the view xml and give it ViewModel.
 */

public class ActivityView extends AppCompatActivity {

    private RandomViewModel viewModel = new RandomViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityViewMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_view_mvvm);
        viewModel = new RandomViewModel();
        viewModel.onCreate();
        binding.setViewModel(viewModel);

        // OR
        //binding.setVariable(BR.viewModel, new RandomViewModel());
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
