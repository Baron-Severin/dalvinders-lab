package dalvinlabs.com.androidlab.receivers;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NfcReceiver extends BroadcastReceiver {

    private static final String LOG_TAG = NfcReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG, "onReceive");
    }
}
