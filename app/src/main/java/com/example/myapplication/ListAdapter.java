package com.example.myapplication;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.ItemJobBinding;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ArrayList<Job> jobs;

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ItemJobBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_job, viewGroup, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binding.setJob(jobs.get(i));
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemJobBinding binding;

        ViewHolder(ItemJobBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
