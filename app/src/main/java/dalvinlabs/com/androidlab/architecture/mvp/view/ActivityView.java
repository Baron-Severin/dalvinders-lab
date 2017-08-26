package dalvinlabs.com.androidlab.architecture.mvp.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import dalvinlabs.com.androidlab.R;
import dalvinlabs.com.androidlab.architecture.mvp.presenter.ConcretePresenter;

/*
    1. In MVP activity is considered as view.
    2. Implements view interface so presenter receives non-android specific view.
    3. It creates instance of presenter and give it a view.

    TODO: Can presenter be injected through dagger.
 */

public class ActivityView extends AppCompatActivity implements LabView {

    private TextView textView = null;
    private ConcretePresenter presenter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ConcretePresenter(this);
        presenter.onCreate();
        setContentView(R.layout.activity_view);
        textView = findViewById(R.id.textViewRandom);

    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showRandom(String random) {
        textView.setText(random);
    }

    public void onClickRandom(View view) {
        presenter.onGenerateRandom();
    }
}
