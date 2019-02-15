package com.example.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class JobViewModel extends ViewModel {
    private LiveData<ArrayList<Job>> jobLiveData;
    private JobService service = new JobService();

    public LiveData<ArrayList<Job>> getJobs() {
        if (jobLiveData == null) {
            jobLiveData = new MutableLiveData<>();
            loadJobs();
        }
        return jobLiveData;
    }

    private void loadJobs() {
        jobLiveData = service.getJobMutableLiveData();
    }

    public void sortListByPrice(int pref) {
        jobLiveData = service.sortByHigherPrice(pref);

    }

}
