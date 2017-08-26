package dalvinlabs.com.androidlab.architecture.mvvm.viewmodel;


public interface ViewModel {
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
}
