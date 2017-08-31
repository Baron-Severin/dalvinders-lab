package dalvinlabs.com.androidlab.architecture.mvvm.viewmodel;

/*
    1. Contains observable data.
    2. View xml observe this data through android data binding.
    3. Less glue code as presenter is not required to update data in view.
    4. It should not talk to view, instead view talk to it via data binding.
    5. It implements ViewModel interface just in case it needs life cycle states from view.
 */

import android.databinding.ObservableField;
import android.util.Log;

import dalvinlabs.com.androidlab.architecture.mvvm.model.MvvmModel;

public class RandomViewModel implements ViewModel {

    private static final String LOG_TAG = RandomViewModel.class.getSimpleName();

    public final ObservableField<String> random = new ObservableField<>("Default 123");

    private MvvmModel model;

    public RandomViewModel() {
        model = new MvvmModel();
    }

    @Override
    public void onCreate() {
        Log.d(LOG_TAG,"onCreate");
    }

    @Override
    public void onPause() {
        Log.d(LOG_TAG,"onPause");
    }

    @Override
    public void onResume() {
        Log.d(LOG_TAG,"onResume");
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG,"onDestroy");
    }

    public void onGenerateRandom() {
        String data = model.generate().getRandom();
        random.set(data);
    }

}
