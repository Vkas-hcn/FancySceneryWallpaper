package com.android.local;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

/**
 * @author Create by l
 * Description:
 **/
@TargetApi(21)
public class IQK extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        ////Log.e("DaemonLog", "JobService onStartJob");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        ////Log.e("DaemonLog", "JobService onStopJob");
        return false;
    }
}
