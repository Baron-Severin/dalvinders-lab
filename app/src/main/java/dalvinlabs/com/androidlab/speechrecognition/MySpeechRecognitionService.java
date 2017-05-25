package dalvinlabs.com.androidlab.speechrecognition;


import android.app.Service;
import android.content.Intent;
import android.speech.RecognitionService;
import android.support.annotation.IntDef;
import android.util.Log;

public class MySpeechRecognitionService extends RecognitionService {
    private static final String LOG_TAG = MySpeechRecognitionService.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");
        return START_STICKY;
    }

    @Override
    protected void onStartListening(Intent recognizerIntent, Callback listener) {
        Log.d(LOG_TAG, "onStartListening");

        //TODO: Do something , explore google cloud speech recognition
    }

    @Override
    protected void onCancel(Callback listener) {
        Log.d(LOG_TAG, "onCancel");
    }

    @Override
    protected void onStopListening(Callback listener) {
        Log.d(LOG_TAG, "onStopListening");
    }


}
