package com.wallpaper.aclahuo;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
public class Helpsr {
    public static boolean f52592f8 = true;

    public static String m39785a8(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static String m44741c8(Context context) {
        return m39785a8(new StringBuilder(), context.getApplicationInfo().packageName, ".accountType");
    }

    public static String m44750d8(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
        } catch (PackageManager.NameNotFoundException unused) {
            return context.getPackageName();
        }
    }
    public static String m44756e8(Context context) {
        return m39785a8(new StringBuilder(), context.getApplicationInfo().packageName, ".provider");
    }

    public static void m44734b8(Context context) {
        try {
            if (!ContentResolver.getMasterSyncAutomatically()) {
                ContentResolver.setMasterSyncAutomatically(true);
            }
        } catch (Throwable unused) {
        }
        Account account = new Account(m44750d8(context), m44741c8(context));
        try {
            if (ContentResolver.getIsSyncable(account, m44756e8(context)) <= 0) {
                ContentResolver.setIsSyncable(account, m44756e8(context), 1);
            }
        } catch (Throwable unused2) {
        }
        ContentResolver.setSyncAutomatically(account, m44756e8(context), true);
        ContentResolver.addPeriodicSync(account, m44756e8(context), new Bundle(), 1);
        if (f52592f8) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("expedited", true);
            bundle.putBoolean("force", true);
            bundle.putBoolean("reset", true);
            ContentResolver.requestSync(account, m44756e8(context), bundle);
        }
    }

    public static void m44681a8(Context context) {
        AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        try {
            if (accountManager.getAccountsByType(m44741c8(context)).length <= 0) {
                accountManager.addAccountExplicitly(new Account(m44750d8(context), m44741c8(context)), null, new Bundle());
            }
        } catch (Exception unused) {
        }
    }

    public static void a(Context context) {

        m44681a8(context);
        m44734b8(context);
    }

}
