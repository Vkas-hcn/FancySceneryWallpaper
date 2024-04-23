package com.wallpaper.aclahuo;

import android.accounts.Account;
import android.app.Service;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
public class InfoServices extends Service {
    public static final Handler f36274f8 = new Handler(Looper.getMainLooper());

    public C15518a8 f36275e8;
    public static class C15518a8 extends AbstractThreadedSyncAdapter {

        public class RunnableC15519a8 implements Runnable {
            public final /* synthetic */ Account f36276e8;

            public RunnableC15519a8(Account account) {
                this.f36276e8 = account;
            }

            public void run() {
                Bundle bundle = new Bundle();
                bundle.putBoolean("expedited", true);
                bundle.putBoolean("force", true);
                bundle.putBoolean("reset", true);
                ContentResolver.requestSync(this.f36276e8, Helpsr.m44756e8(C15518a8.this.getContext()), bundle);
            }
        }

        public C15518a8(Context context, boolean z) {
            super(context, z);
        }

        public void onPerformSync(Account account, Bundle bundle, String str, ContentProviderClient contentProviderClient, SyncResult syncResult) {
            Insjgoskesgd b8Var = new Qusjsig();
            if (b8Var != null) {
                b8Var.mo78731e8();
            }
            if (Helpsr.f52592f8) {
                boolean z = bundle.getBoolean("reset");
                InfoServices.f36274f8.removeCallbacksAndMessages("token");
                if (z) {
                    syncResult.stats.numIoExceptions = 0;
                    Bundle bundle2 = new Bundle();
                    bundle2.putBoolean("expedited", true);
                    bundle2.putBoolean("force", true);
                    bundle2.putBoolean("reset", false);
                    ContentResolver.requestSync(account, Helpsr.m44756e8(getContext()), bundle2);
                    return;
                }
                syncResult.stats.numIoExceptions = 1;
                InfoServices.f36274f8.postAtTime(new RunnableC15519a8(account), "token", SystemClock.uptimeMillis() + 20000);
            }
        }
    }


    public IBinder onBind(Intent intent) {
        return this.f36275e8.getSyncAdapterBinder();
    }

    public void onCreate() {
        super.onCreate();
        this.f36275e8 = new C15518a8(getApplicationContext(), true);
    }
}