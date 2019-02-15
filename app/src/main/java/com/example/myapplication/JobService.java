package com.example.myapplication;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class JobService {
    private MutableLiveData<ArrayList<Job>> jobMutableLiveData = new MutableLiveData<>();

    MutableLiveData<ArrayList<Job>> getJobMutableLiveData(){
        ArrayList<Job> jobs = new ArrayList<>();
        for(int i=0; i<10; i++) {
            Job job= new Job("id_"+i, (i+1)*100000, Integer.toString(i));
            jobs.add(job);
        }
        jobMutableLiveData.setValue(jobs);

        return jobMutableLiveData;
    }

    MutableLiveData<ArrayList<Job>> sortByHigherPrice(int pref) {
        if(jobMutableLiveData.getValue() != null ) {
            
            ArrayList<Job> jobs = jobMutableLiveData.getValue();
            Collections.sort(jobs, (o1, o2) -> {
                if(pref==1) {
                    return Integer.compare(o2.getPrice(), o1.getPrice());
                }
                return Integer.compare(o1.getPrice(), o2.getPrice());
            });

            jobMutableLiveData.setValue(jobs);
        }
        return jobMutableLiveData;
    }
}
