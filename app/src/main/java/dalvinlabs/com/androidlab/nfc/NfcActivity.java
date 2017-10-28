package dalvinlabs.com.androidlab.nfc;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import dalvinlabs.com.androidlab.R;
import dalvinlabs.com.androidlab.databinding.ActivityNfcBinding;

public class NfcActivity extends AppCompatActivity {

    private static final String LOG_TAG = NfcActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNfcBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_nfc);
        Intent intent = getIntent();
        Log.d(LOG_TAG, "action = " + intent.getAction());
        Log.d(LOG_TAG, "data = " + intent.getDataString());
        Log.d(LOG_TAG, "data URI = " + intent.getData());
        binding.nfcData.setText(intent.toString());
    }
}
