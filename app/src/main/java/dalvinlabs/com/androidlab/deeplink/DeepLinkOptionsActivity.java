package dalvinlabs.com.androidlab.deeplink;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import dalvinlabs.com.androidlab.R;
import dalvinlabs.com.androidlab.databinding.ItemLinkBinding;

public class DeepLinkOptionsActivity extends AppCompatActivity {
    private static final String LOG_TAG = DeepLinkOptionsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link_options);
        LinkData linkData = getLinks();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new LinksAdapter(linkData));
    }

    private LinkData getLinks() {
        LinkData linkData = null;
        try {
            InputStream inputStream = getAssets().open("links.json");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            Gson gson = new GsonBuilder().create();
            linkData = gson.fromJson(inputStreamReader, LinkData.class);
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }
        return linkData;
    }

    private class LinksViewHolder extends RecyclerView.ViewHolder {
        ItemLinkBinding mItemLinkBinding;

        LinksViewHolder(ItemLinkBinding itemLinkBinding) {
            super(itemLinkBinding.getRoot());
            mItemLinkBinding = itemLinkBinding;
        }

        void bind(String link) {
            mItemLinkBinding.setLink(link);
        }
    }

    private class LinksAdapter extends RecyclerView.Adapter<LinksViewHolder> {
        LinkData mLinkData;

        public LinksAdapter(LinkData linkData) {
            mLinkData = linkData;
        }

        @Override
        public LinksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new LinksViewHolder(ItemLinkBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }

        @Override
        public void onBindViewHolder(LinksViewHolder holder, int position) {
            holder.bind(mLinkData.getLinks().get(position));
        }

        @Override
        public int getItemCount() {
            return mLinkData != null ? mLinkData.getLinks().size() : 0;
        }
    }

    public void onDeepLinkEnabled(View view) {
        Snackbar.make(view, "Deep link activity enabled", Snackbar.LENGTH_LONG).show();
        ComponentName componentName = new ComponentName(getPackageName(), DeepLinkInvokeActivity.class.getName());
        getPackageManager().setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    public void onDeepLinkDisabled(View view) {
        Snackbar.make(view, "Deep link activity disabled", Snackbar.LENGTH_LONG).show();
        ComponentName componentName = new ComponentName(getPackageName(), DeepLinkInvokeActivity.class.getName());
        getPackageManager().setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }


}
