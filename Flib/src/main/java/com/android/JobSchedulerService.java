package com.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Keep;


import java.util.List;

public class JobSchedulerService extends JobService {
    @SuppressLint("NewApi")
    public boolean onStartJob(JobParameters params) {
        if(!i(this)) {
            PersistableBundle pb = params.getExtras();
            //aa(this, pb.getString("a"), 0);
            aa(this, pb.getString("a"));
        }
        return false;
    }
    public static void m(Context context) throws PendingIntent.CanceledException {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent.getActivity(
                context,
                0x2300,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE).send();
    }

    public static final class Rc implements Runnable {
        public final Context mContext;
        public  String mName;
        public  int  mNum;
        public Rc(Context context,String n, int num) {
            mContext = context;
            mName=n;
            mNum=num;
        }
        @Override
        public final void run() {
            if(mNum==1) {
                aa(mContext, mName);
            }
            if(mNum==2){
                b(mContext,mName);
            }
        }
    }

    static void b(Context ctx, String a) {
        JobScheduler jobScheduler = (JobScheduler) ctx.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            JobInfo.Builder builder = new JobInfo.Builder(1001, new ComponentName(ctx, JobSchedulerService.class))
                    .setPersisted(false)
                    .setRequiresDeviceIdle(false)
                    .setOverrideDeadline(3000);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                builder.setImportantWhileForeground(true);
            }
            PersistableBundle pb = new PersistableBundle();
            pb.putString("a", a);
            builder.setExtras(pb);
            jobScheduler.schedule(builder.build());
        }
    }

    static Boolean i(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list0 = am.getRunningAppProcesses();
        if (list0 != null) {
            for (ActivityManager.RunningAppProcessInfo info : list0) {
                if (!info.processName.equals(context.getApplicationInfo().processName) || info.importance != 100) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    @Keep
    static void a(Context ctx, String n, int num) {
        if(num==1) {
            aa(ctx, n);
        }
        if(num==2){
            aa(ctx, n);
            b(ctx,n);
            new Handler(Looper.getMainLooper()).postDelayed(new Rc(ctx, n, num), 500L);
            new Handler(Looper.getMainLooper()).postDelayed(new Rc(ctx, n, num), 1500L);
        }
    }

    public static boolean aa(Context context,String n) {
        try {
            ComponentName cn = new ComponentName(context, n);
            Intent intent = new Intent();
            intent.setClassName(context, cn.getClassName());
            int i10 = Build.VERSION.SDK_INT;
            if (i10 < 29){
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent.getActivity(context, 0, intent, i10 >= 23 ? 201326592 : 134217728).send();
                return true;
            }else{
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                return true;
            }

        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }
    public boolean onStopJob(JobParameters params ) {
        return false;
    }
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        return Service.START_STICKY;
    }

}