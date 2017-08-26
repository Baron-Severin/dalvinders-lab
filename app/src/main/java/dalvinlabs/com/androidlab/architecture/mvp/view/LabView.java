package dalvinlabs.com.androidlab.architecture.mvp.view;

/*
    1. Used to decouple android view from presenter.
    2. Presenter gets reference of this view instead of android specific view.
 */

public interface LabView {

    void showRandom(String random);
}
