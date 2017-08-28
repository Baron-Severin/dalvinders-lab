package dalvinlabs.com.androidlab.architecture.components.viewmodel;


import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;

import dalvinlabs.com.androidlab.architecture.components.model.RandomModel;

/*
    1. Extends android ViewModel which make it survive activity recreation during configuration
        changes. But if activity finishes or process gets killed, it gets cleared.
 */

public class MyViewModel extends ViewModel {

    private static final String LOG_TAG = MyViewModel.class.getSimpleName();
    // TODO: check if model can be injected through dagger.
    private RandomModel model = null;

    public final ObservableField<String> random = new ObservableField<>("Default 123");

    /*
        Gets invoked upon activity finish.
        Back press on activity also results in activity finish.
     */
    @Override
    protected void onCleared() {
        Log.d(LOG_TAG, "onCleared");
        super.onCleared();
    }

    public MyViewModel() {
        Log.d(LOG_TAG, "constructor");
        model = new RandomModel();
    }

    public void onGenerateRandom() {
        Log.d(LOG_TAG, "generateRandom");
        random.set(model.generate().getRandom());
    }

}
