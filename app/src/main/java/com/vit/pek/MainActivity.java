package com.vit.pek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.main_activity_rcv);
        recyclerView.addItemDecoration(new MarginItemDecoration((int) getResources().getDimension(R.dimen.item_space)));

        // TODO: Vitali 2019-08-03  to be continue
    }
}
