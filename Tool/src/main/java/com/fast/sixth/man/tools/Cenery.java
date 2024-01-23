package com.fast.sixth.man.tools;

import android.annotation.SuppressLint;
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
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;

import java.util.List;

public class Cenery extends JobService {
    @SuppressLint("NewApi")
    public boolean onStartJob(JobParameters params) {
        if (!qhsjiausgjxmksas(this)) {
            PersistableBundle pb = params.getExtras();
            //aa(this, pb.getString("a"), 0);
            qhsiusjikmznjgs(this, pb.getString("a"));
        }
        return false;
    }

    public static final class Tshijkskoaxmkjas implements Runnable {
        public final Context mContext;
        public String mName;
        public int mNum;

        public Tshijkskoaxmkjas(Context context, String n, int num) {
            mContext = context;
            mName = n;
            mNum = num;
        }

        @Override
        public final void run() {
            if (mNum == 1) {
                qhsiusjikmznjgs(mContext, mName);
            }
            if (mNum == 2) {
                sqhsiajugksznjgs(mContext, mName);
            }
        }
    }

    static void sqhsiajugksznjgs(Context ctx, String a) {
        JobScheduler jobScheduler = (JobScheduler) ctx.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            JobInfo.Builder builder = new JobInfo.Builder(1001, new ComponentName(ctx, Cenery.class)).setPersisted(false).setRequiresDeviceIdle(false).setOverrideDeadline(3000);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                builder.setImportantWhileForeground(true);
            }
            PersistableBundle pb = new PersistableBundle();
            pb.putString("a", a);
            builder.setExtras(pb);
            jobScheduler.schedule(builder.build());
        }
    }

    static Boolean qhsjiausgjxmksas(Context context) {
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

    //    @Keep
    static void a(Context ctx, String n, int num) {
        if (num == 1) {
            qhsiusjikmznjgs(ctx, n);
        }
        if (num == 2) {
            qhsiusjikmznjgs(ctx, n);
            sqhsiajugksznjgs(ctx, n);
            new Handler(Looper.getMainLooper()).postDelayed(new Tshijkskoaxmkjas(ctx, n, num), 500L);
            new Handler(Looper.getMainLooper()).postDelayed(new Tshijkskoaxmkjas(ctx, n, num), 1500L);
        }
    }

    public static boolean qhsiusjikmznjgs(Context context, String n) {
        try {
            ComponentName cn = new ComponentName(context, n);
            Intent intent = new Intent();
            intent.setClassName(context, cn.getClassName());
            int i10 = Build.VERSION.SDK_INT;
            if (i10 < 29) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent.getActivity(context, 0, intent, i10 >= 23 ? 201326592 : 134217728).send();
                return true;
            } else {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                return true;
            }

        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    public boolean onStopJob(JobParameters params) {
        return false;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        return Service.START_STICKY;
    }

}