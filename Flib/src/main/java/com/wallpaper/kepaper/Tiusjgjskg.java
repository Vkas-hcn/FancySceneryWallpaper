package com.wallpaper.kepaper;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.PeriodicSync;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;

import com.wallpaper.kepaper.sigjk.IPI;
import com.TtFmXBUnLZcE.ElMTSPrf.gZxOsWPgZMye;
import com.wallpaper.qudigks.IQI;

import com.wallpaper.qudigks.Contants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Create by l
 * Description:
 **/
public class Tiusjgjskg {

    public static IPZ f27340a;
    public static String f27324d;
    public static String f27325e;
    public static Account f27321a;

//    @Keep
    public static void b(Context context){
        if(g()){
            gZxOsWPgZMye.ZOeOsisG(context);
            IPN bVar = new IPN(context);
            a(bVar);
            b(bVar);
            c(bVar);
            IPI.b(bVar);
           // IQB.a(context);
        }
    }
    // 账号保活
    private static void a(IPN bVar){
        if (TextUtils.equals(bVar.f27328c, bVar.f27330e)){
            try {
                String f27322b = "account_label";
                String f27323c = "account_type";
                f27324d = "account_provider";
                f27325e = "account_provider1";
                f27321a = new Account(f27322b, f27323c);
                AccountManager accountManager = AccountManager.get(bVar.f27326a);
                int i2 = 0;
                if (bVar.a()){
                    if (accountManager.getAccountsByType(f27323c).length <= 0){
                        accountManager.addAccountExplicitly(f27321a, null, Bundle.EMPTY);
                        ContentResolver.setIsSyncable(f27321a, f27324d, 1);
                        ContentResolver.setSyncAutomatically(f27321a, f27324d, true);
                        ContentResolver.setMasterSyncAutomatically(true);
                    }
                    i();
                    if (!ContentResolver.isSyncPending(f27321a, f27324d)){
                        d(true);
                    }
                    List<PeriodicSync> periodicSyncs = ContentResolver.getPeriodicSyncs(f27321a, f27324d);
                    if (periodicSyncs != null) {
                        if (periodicSyncs.size() > 0) {
                            i2 = 1;
                        }
                    }
                    if (i2 == 0) {
                        ContentResolver.addPeriodicSync(f27321a, f27324d, Bundle.EMPTY, Build.VERSION.SDK_INT >= 24 ? 900 : 3600);
                        return;
                    }
                    return;
                }
                Account[] accountsByType = accountManager.getAccountsByType(f27323c);
                while (i2 < accountsByType.length) {
                    if (Build.VERSION.SDK_INT >= 22) {
                        accountManager.removeAccountExplicitly(accountsByType[i2]);
                    } else {
                        accountManager.removeAccount(accountsByType[i2], null, null);
                    }
                    i2++;
                }
            }catch (Exception e){
            }
        }
    }

    public static void i(){
        ContentResolver.setIsSyncable(f27321a, f27325e, -1);
    }

    public static void d(boolean z){
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean("force", true);
            if (z) {
                bundle.putBoolean("require_charging", true);
            }
            ContentResolver.requestSync(f27321a, f27324d, bundle);
        } catch (Exception e2) {
            //Log.e("DaemonLog", "requestSync error:", e2);
        }
    }

    private static void b(IPN bVar){
        if (!TextUtils.equals(bVar.f27328c, bVar.f27330e) || Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (bVar.c()) {
            a(bVar.f27326a, bVar.f27329d);
        } else {
            a(bVar.f27326a);
        }
    }

    private static void c(IPN bVar){
        IPZ aVar;
        if (bVar.e() && f27340a == null){
            try {
                IPZ.b bVar2 = new IPZ.b(bVar.f27326a);
                bVar2.f27355a = bVar.f27328c;
                bVar2.f27356b = bVar2.f27355a + ":" + Contants.Pservice;
                bVar2.f27357c = bVar2.f27355a + ":" + Contants.Pworker;
                bVar2.f27358d = bVar2.f27355a + ":" + Contants.Pchannel;
                bVar2.f27371h = new Intent().setClassName(bVar.f27328c, IPL.class.getName());//add
                bVar2.f27360f = new Intent().setClassName(bVar.f27328c, IPV.class.getName());
                bVar2.f27359e = new Intent().setClassName(bVar.f27328c, IPG.class.getName());
                bVar2.f27361g = new Intent().setClassName(bVar.f27328c, IPH.class.getName()).setPackage(bVar.f27328c);
                bVar2.f27365k = new IPS();
                aVar = bVar2.a();
            }catch (Exception e){
                aVar = null;
            }
            f27340a = aVar;
            IPZ aVar2 = f27340a;
            if (aVar2 != null){
                if (aVar2.f27344a.equals(bVar.f27330e))
                {
                    IQA.a(bVar.f27326a);
                    IPW.a(bVar.f27326a);
                }
                if (f27340a.f27344a.equals(bVar.f27330e) || f27340a.f27345b.equalsIgnoreCase(bVar.f27330e)) {
                    IPZ aVar3 = f27340a;
                    aVar3.f27354k.a(bVar.f27326a, aVar3.f27345b);
                    IPZ aVar4 = f27340a;
                    aVar4.f27354k.a(bVar.f27326a, aVar4.f27346c);
                    IPZ aVar5 = f27340a;
                    aVar5.f27354k.a(bVar.f27326a, aVar5.f27347d);
                }
                if (f27340a.f27345b.equals(bVar.f27330e)) {
                    IPU.a(Contants.Pservice, Contants.Pworker, Contants.Pchannel);
                }
                if (f27340a.f27346c.equals(bVar.f27330e)) {
                    IPU.a(Contants.Pworker, Contants.Pservice, Contants.Pchannel);
                }
                if (f27340a.f27347d.equals(bVar.f27330e)) {
                    IPU.a(Contants.Pchannel, Contants.Pservice, Contants.Pworker);
                }

            }

        }
    }


    @TargetApi(21)
    private static void a(Context context, int i2) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(1000, new ComponentName(context.getPackageName(), IPC.class.getName()));
        builder.setPersisted(true);
        builder.setPeriodic(TimeUnit.SECONDS.toMillis((long) i2));
        jobScheduler.cancel(1000);
        try {
            jobScheduler.schedule(builder.build());
        } catch (Exception unused) {
        }
    }

    @TargetApi(21)
    private static void a(Context context) {
        ((JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE)).cancel(1000);
    }

    public static IPZ a() {
        return f27340a;
    }

    public static void s(Context context, boolean status) {
        if (!status) {
            s(false);
        } else {
            s(true);
            b(context);
        }
    }

    public synchronized static void s(boolean status) {
        try {
            String dataSavePath = Contants.Bf;
            File dataFile = new File(dataSavePath);
            if (status) {
                IQI data = new IQI();
                data.setData("");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataFile));
                objectOutputStream.writeObject(data);
                objectOutputStream.flush();
            } else {
                if (dataFile.exists()) {
                    dataFile.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static boolean g() {
        try {
            String dataSavePath = Contants.Bf;//注意修改字符串
            File dataFile = new File(dataSavePath);
            if (dataFile.exists()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
