package dalvinlabs.com.androidlab.network.okhttp;


import android.util.Log;
import android.webkit.WebChromeClient;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {
    private static final String LOG_TAG = OkHttp.class.getSimpleName();
    private static final String URL = "https://emails.macys.com/pub/cc?_ri_=X0Gzc2X%3DYQpglLjHJlTQGmYsBL10czf0gazfvHjPsCzdzdrhHnzedCOH95tDrh0JdKP07LrqkXkeJNfVXtpKX%3DSSCDYAT&_ei_=Eq2tf9zs59idfPO1Sc_9BblUuTnuyWYObGRH7Cv_wUkqhCXzLQwXRJtazlz7mKHfc_c4oolJUOfFp_eMLtsgBOLMJs1UBTFo-vGsqmYQC0o0vZvKDz6j4q7HYltnu9Ollg";
    private static final String URL_JS = "https://storage.googleapis.com/dalvin/test_js_redirect_to_http.html";

    public static void run() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL_JS)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.e(LOG_TAG, "Response Failed");
                    return;
                }

                Headers responseHeaders = response.headers();
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    Log.d(LOG_TAG, responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                Log.d(LOG_TAG, "Final URL = " + response.request().url());

            }
        });
    }
}
