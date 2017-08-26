package dalvinlabs.com.androidlab.architecture.mvp.presenter;

/*
    1. Must not have any reference to android components.
    2. Should receive View interface to apply actions
 */

import android.util.Log;

import dalvinlabs.com.androidlab.architecture.mvp.model.MvpModel;
import dalvinlabs.com.androidlab.architecture.mvp.view.LabView;

public class ConcretePresenter implements Presenter {

    private static final String LOG_TAG = ConcretePresenter.class.getSimpleName();

    private LabView view = null;
    private MvpModel model = null;

    public ConcretePresenter(LabView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "onCreate");
        model = new MvpModel();
    }

    @Override
    public void onPause() {
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onResume() {
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "onDestroy");
        view = null;
    }

    public void onGenerateRandom() {
        String random = model.generate().getRandom();
        /*
            Above model call can be asynchronous, so therefore check if view exists before
            invoking it.
         */
        if (view != null) {
            view.showRandom(random);
        }
    }


}
