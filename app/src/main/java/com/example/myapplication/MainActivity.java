package com.example.myapplication;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView listView = findViewById(R.id.lst_job);
        listView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ListAdapter adapter = new ListAdapter();
        listView.setAdapter(adapter);

        JobViewModel viewModel = ViewModelProviders.of(this).get(JobViewModel.class);
        viewModel.getJobs().observe(this, jobList -> {
            adapter.setJobs(jobList);
            adapter.notifyDataSetChanged();
        });

        ArrayList<String> sortOptions = new ArrayList<>();
        sortOptions.add("SORT");
        sortOptions.add("Sort by higher price");
        sortOptions.add("Sort by lower price");
        Spinner spinner = findViewById(R.id.spin_sort);
        ArrayAdapter<String> adapterDSP = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, sortOptions );
        adapterDSP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterDSP);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    viewModel.sortListByPrice(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}
