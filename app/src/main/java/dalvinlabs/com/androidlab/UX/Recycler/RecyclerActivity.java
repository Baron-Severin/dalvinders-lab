package dalvinlabs.com.androidlab.UX.Recycler;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import dalvinlabs.com.androidlab.R;
import dalvinlabs.com.androidlab.databinding.ItemRecyclerBinding;

public class RecyclerActivity extends AppCompatActivity {

    private String[] titles = {"Dog", "Cat", "Lion", "Tiger", "Wolf"};
    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(new Adapter());

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.green);
        swipeRefreshLayout.setColorSchemeResources(R.color.red);
    }

    public SwipeRefreshLayout.OnRefreshListener onRefreshListener = () -> {
        handler.postDelayed(() -> swipeRefreshLayout.setRefreshing(false), 5000);
    };

    private class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecyclerBinding binding;

        ViewHolder(ItemRecyclerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ViewModel viewModel) {
            binding.setViewModel(viewModel);
        }

    }

    public class ViewModel {

        public String title;
        public String text;

        public ViewModel(String title, String text) {
            this.title = title;
            this.text = text;
        }
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(ItemRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(new ViewModel(titles[position], "Hello, What's up"));
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}
