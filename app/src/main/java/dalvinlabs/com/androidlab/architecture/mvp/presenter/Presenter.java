package dalvinlabs.com.androidlab.architecture.mvp.presenter;


/*
    1. Defines interface needs to be implemented by every concrete Presenter.
    2. Android life cycle methods which view invokes to let Presenter know about current life cycle.
 */
public interface Presenter {

    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
}
