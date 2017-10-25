package dalvinlabs.com.androidlab.UX;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import dalvinlabs.com.androidlab.R;
import dalvinlabs.com.androidlab.UX.Recycler.RecyclerActivity;

public class UxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ux);
    }

    public void onClickRecycler(View viwe) {
        startActivity(new Intent(this, RecyclerActivity.class));
    }
}
