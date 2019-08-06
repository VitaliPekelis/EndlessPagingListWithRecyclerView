package com.vit.pek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final int PAGE_SIZE = 20;

    private PagedListAdapter<DataItem, MyAdapter.DataModelViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.main_activity_rcv);

        int spaceInt = (int) (/*(PX)*/ getResources().getDisplayMetrics().density *
                /*(DP)*/ getResources().getDimension(R.dimen.item_space));

        recyclerView.addItemDecoration(new MarginItemDecoration(spaceInt));
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        MyDataSourceFactory factory = new MyDataSourceFactory(new DataRepository());

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();

        LiveData<PagedList<DataItem>> pagedListLiveData = new LivePagedListBuilder<>(factory, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build();

        pagedListLiveData.observe(this, (PagedList<DataItem> pagedList) ->
            adapter.submitList(pagedList)
        );

    }
}
