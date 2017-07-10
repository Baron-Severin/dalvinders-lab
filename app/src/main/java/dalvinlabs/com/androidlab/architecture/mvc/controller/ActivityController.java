package dalvinlabs.com.androidlab.architecture.mvc.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import dalvinlabs.com.androidlab.R;
import dalvinlabs.com.androidlab.architecture.mvc.model.MvcModel;

public class ActivityController extends AppCompatActivity {

    private MvcModel model = new MvcModel();
    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        textView = (TextView) findViewById(R.id.textViewRandom);
    }

    public void onClickRandom(View view) {
        textView.setText(model.generate().getRandom());
    }
}
