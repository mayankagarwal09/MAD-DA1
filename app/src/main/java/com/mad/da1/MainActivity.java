package com.mad.da1;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerAdapter mAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startSplashActivity();

        recyclerView = findViewById(R.id.recycler_view);
        ArrayList<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("Mobile App Dev","Monday"));
        subjects.add(new Subject("HCI","Tuesday"));
        subjects.add(new Subject("Soft Skills","Wednesday"));
        subjects.add(new Subject("Disaster Management","Thursday"));
        subjects.add(new Subject("HPC","Friday"));
        mAdapter = new RecyclerAdapter(subjects);
        //intialize linear layout manager vertically
        LinearLayoutManager linearVertical = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearVertical);
        recyclerView.setAdapter(mAdapter);
        enableSwipeToDeleteAndUndo();

    }



    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final Subject item = mAdapter.getData().get(position);
                mAdapter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mAdapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

    public void startSplashActivity(){
        Intent intent = new Intent(this,SplashActivity.class);
        startActivity(intent);
    }
}
