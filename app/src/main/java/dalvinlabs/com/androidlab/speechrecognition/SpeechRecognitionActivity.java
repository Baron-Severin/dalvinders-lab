package dalvinlabs.com.androidlab.speechrecognition;


import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import dalvinlabs.com.androidlab.R;
import dalvinlabs.com.androidlab.databinding.ActivitySpeechRecognitionBinding;

/*
    Experiment speech recognition
     1. Edit text box -> Keyboard shows the mic button -> Tapping mic will present default android speech recognition behavior
     2. Use SpeechRecognizer class -> Use partial results to populate text view
        Limitation: Speech recognizer will stop listening once it detects that user has stopped speaking. As a result user Speech Recognition has to be started again explicitly.
     3.
 */

public class SpeechRecognitionActivity extends AppCompatActivity{
    private static final String LOG_TAG = SpeechRecognitionActivity.class.getSimpleName();
    private static final int REQUEST_CODE_AUDIO_RECORD_PERMISSION_ANDROID_RECOGNIZER = 1;
    private static final int REQUEST_CODE_AUDIO_RECORD_PERMISSION_CUSTOM_RECOGNIZER = 2;
    private SpeechRecognitionViewModel speechRecognitionViewModel = new SpeechRecognitionViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySpeechRecognitionBinding activitySpeechRecognitionBinding = DataBindingUtil.setContentView(this, R.layout.activity_speech_recognition);
        activitySpeechRecognitionBinding.setViewModel(speechRecognitionViewModel);
    }

    private RecognitionListener mRecognitionListener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {
            Log.d(LOG_TAG, "onReadyForSpeech");
        }

        @Override
        public void onBeginningOfSpeech() {
            Log.d(LOG_TAG, "onBeginningOfSpeech");
        }

        @Override
        public void onRmsChanged(float rmsdB) {
            Log.d(LOG_TAG, "onRmsChanged");
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            Log.d(LOG_TAG, "onBufferReceived");
        }

        @Override
        public void onEndOfSpeech() {
            Log.d(LOG_TAG, "onEndOfSpeech");
        }

        @Override
        public void onError(int error) {
            Log.d(LOG_TAG, "onError = " + error);
        }

        @Override
        public void onResults(Bundle results) {
            Log.d(LOG_TAG, "onResults");
            //processResults(results);
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
            Log.d(LOG_TAG, "onPartialResults");
            processResults(partialResults);
        }

        @Override
        public void onEvent(int eventType, Bundle params) {
            Log.d(LOG_TAG, "onEvent");
        }
    };

    private void processResults(Bundle bundle) {
        Log.d(LOG_TAG, "processResults");
        if (bundle != null) {
            List<String> results = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            if (results != null && !results.isEmpty()) {
                speechRecognitionViewModel.speechRecognizerResult.set(results.get(0));
            }
            Log.d(LOG_TAG, "Results = " + results.toString());
        } else {
            Log.d(LOG_TAG, "Results NULL");
        }
    }

    private boolean checkPermission(int requestCode) {
        Log.d(LOG_TAG, "checkPermission");
        boolean permission = true;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                // TODO: Show some explanation to user
                permission = false;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, requestCode);
            }
        }
        return permission;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_AUDIO_RECORD_PERMISSION_ANDROID_RECOGNIZER:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onClickAndroidSpeechRecognizer(null);
                } else {
                    // TODO: Permission denied
                }
                break;
            case REQUEST_CODE_AUDIO_RECORD_PERMISSION_CUSTOM_RECOGNIZER:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onClickCustomSpeechRecognizer(null);
                } else {
                    // TODO: Permission denied
                }
                break;
        }
    }

    public void onClickSpeechRecognizerIntent(View view) {
        Intent intent = RecognizerIntent.getVoiceDetailsIntent(this);
        intent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        List<ResolveInfo> infos = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_ALL);
        if (infos != null && !infos.isEmpty()) {
            startActivityForResult(intent, 2);
        } else {
            Toast.makeText(this, "No component can handle it", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickAndroidSpeechRecognizer(View view) {
        Log.d(LOG_TAG, "onClickSpeechRecognizer");
        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            if (checkPermission(REQUEST_CODE_AUDIO_RECORD_PERMISSION_ANDROID_RECOGNIZER)) {
                SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
                speechRecognizer.setRecognitionListener(mRecognitionListener);
                Intent intent = RecognizerIntent.getVoiceDetailsIntent(this);
                intent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
                speechRecognizer.startListening(intent);
            } else {
                //TODO: Handle it
            }
        } else {
            Log.d(LOG_TAG, "Speech recognition is NOT available");
        }
    }

    public void onClickCustomSpeechRecognizer(View view) {
        Log.d(LOG_TAG, "onClickSpeechRecognizer");
        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            if (checkPermission(REQUEST_CODE_AUDIO_RECORD_PERMISSION_CUSTOM_RECOGNIZER)) {
                SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this, new ComponentName(this, MySpeechRecognitionService.class));
                speechRecognizer.setRecognitionListener(mRecognitionListener);
                Intent intent = RecognizerIntent.getVoiceDetailsIntent(this);
                intent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
                speechRecognizer.startListening(intent);
            } else {
                //TODO: Handle it
            }
        } else {
            Log.d(LOG_TAG, "Speech recognition is NOT available");
        }
    }
}
