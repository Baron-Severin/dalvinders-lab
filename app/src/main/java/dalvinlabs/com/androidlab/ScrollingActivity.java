package dalvinlabs.com.androidlab;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dagger.LibraryActivity;
import dalvinlabs.com.androidlab.UX.UxActivity;
import dalvinlabs.com.androidlab.architecture.components.ActivityArchitectureComponents;
import dalvinlabs.com.androidlab.architecture.mvc.controller.ActivityController;
import dalvinlabs.com.androidlab.architecture.mvp.view.ActivityView;
import dalvinlabs.com.androidlab.dagger.DaggerActivity;
import dalvinlabs.com.androidlab.deeplink.DeepLinkOptionsActivity;
import dalvinlabs.com.androidlab.design.patterns.abstractfactory.AbstractFactory;
import dalvinlabs.com.androidlab.nearby.NearbyActivity;
import dalvinlabs.com.androidlab.network.okhttp.OkHttp;
import dalvinlabs.com.androidlab.speechrecognition.SpeechRecognitionActivity;
import dalvinlabs.com.androidlab.vector.VectorActivity;

//import java.util.Date;
//import java.util.TimeZone;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

public class ScrollingActivity extends AppCompatActivity {
    private static final String LOG_TAG = ScrollingActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //convertTime();
        //patterns();
        Long.compare(2, 3);
        testAbstractFactory();
        OkHttp.run();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*private void convertTime() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy:HH");
        try {
            for (String eachTimeZone : TimeZone.getAvailableIDs()) {
                Log.d(LOG_TAG, eachTimeZone);
            }

            TimeZone estTimeZone = TimeZone.getTimeZone("EST");
            format.setTimeZone(estTimeZone);
            Date date = format.parse("01/19/2017:12");
            Log.d(LOG_TAG, "Current Time EST To PST = " + date.toString());

            TimeZone pstTimeZone = TimeZone.getDefault();
            format.setTimeZone(pstTimeZone);
            date = format.parse("01/19/2017:12");
            Log.d(LOG_TAG, "Current Time PST To PST = " + date.toString());

        } catch (ParseException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }
    }*/

    /*private void patterns() {
        Pattern patternType = Pattern.compile("type=([^,]+)", Pattern.CASE_INSENSITIVE);
        Pattern patternTitle = Pattern.compile("title=([^,]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternType.matcher("Type=CDT,Title=Insider Sale Just for You!,Date=10/06/2016,Time=11-00,Timezone=EST,Details=Up to 75%,Code=POWER");
        boolean status = matcher.find();
        Log.d(LOG_TAG, "status = " + status);
        if (status) {
            Log.d(LOG_TAG, "group count = " + matcher.groupCount());
            Log.d(LOG_TAG, "group 0 = " + matcher.group(0));
            Log.d(LOG_TAG, "group 1 = " + matcher.group(1));
        }
        matcher = patternTitle.matcher("Type=CDT,Title=Insider Sale Just for You!,Date=10/06/2016,Time=11-00,Timezone=EST,Details=Up to 75%,Code=POWER");
        status = matcher.find();
        Log.d(LOG_TAG, "status = " + status);
        if (status) {
            Log.d(LOG_TAG, "group 0 = " + matcher.group(0));
            Log.d(LOG_TAG, "group 1 = " + matcher.group(1));
        }
    }*/

    private void testAbstractFactory() {
        AbstractFactory abstractFactory = AbstractFactory.createFactory(AbstractFactory.TYPE.ALPHA);
        abstractFactory.createCar().build();
        abstractFactory.createTruck().build();
        abstractFactory.createVan().build();
        abstractFactory = AbstractFactory.createFactory(AbstractFactory.TYPE.BETA);
        abstractFactory.createCar().build();
        abstractFactory.createTruck().build();
        abstractFactory.createVan().build();
        abstractFactory = AbstractFactory.createFactory(AbstractFactory.TYPE.GAMMA);
        abstractFactory.createCar().build();
        abstractFactory.createTruck().build();
        abstractFactory.createVan().build();
    }

    private void test() {
        List<Map<String, String>> data = new ArrayList<>();
        View view = new View(this);
    }

    public void onClickSpeechRecognition(View View) {
        Log.d(LOG_TAG, "onClickSpeechRecognition");
        startActivity(new Intent(this, SpeechRecognitionActivity.class));
    }

    public void onClickDeepLinks(View view) {
        Log.d(LOG_TAG, "onClickDeepLinks");
        startActivity(new Intent(this, DeepLinkOptionsActivity.class));
    }

    public void onClickVector(View view) {
        Log.d(LOG_TAG, "onClickDeepLinks");
        startActivity(new Intent(this, VectorActivity.class));
    }

    public void onClickMvc(View view) {
        Log.d(LOG_TAG, "onClickMvc");
        startActivity(new Intent(this, ActivityController.class));
    }

    public void onClickMvp(View view) {
        Log.d(LOG_TAG, "onClickMvp");
        startActivity(new Intent(this, ActivityView.class));
    }

    public void onClickMvvm(View view) {
        Log.d(LOG_TAG, "onClickMvp");
        startActivity(new Intent(this, dalvinlabs.com.androidlab.architecture.mvvm.view.ActivityView.class));
    }

    public void onClickAndroidArchitecture(View view) {
        Log.d(LOG_TAG, "onClickAndroidArchitecture");
        startActivity(new Intent(this, ActivityArchitectureComponents.class));
    }

    public void onClickDagger(View view) {
        Log.d(LOG_TAG, "onClickAndroidArchitecture");
        startActivity(new Intent(this, DaggerActivity.class));
    }

    public void onClickDaggerLibrary(View view) {
        Log.d(LOG_TAG, "onClickAndroidArchitecture");
        startActivity(new Intent(this, LibraryActivity.class));
    }

    public void onClickUX(View view) {
        Log.d(LOG_TAG, "onClickUX");
        startActivity(new Intent(this, UxActivity.class));
    }

    public void onClickNearby(View view) {
        Log.d(LOG_TAG, "onClickNearby");
        startActivity(new Intent(this, NearbyActivity.class));
    }

}
