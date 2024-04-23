package com.wallpaper.aclahuo;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class QWeuServies extends Service {

    public static boolean f52592f8 = true;
    public C15520a8 f36278e8;
    public static class C15520a8 extends AbstractAccountAuthenticator {
        public final Context f36279a8;

        public C15520a8(Context context) {
            super(context);
            this.f36279a8 = context;
        }

        @Override
        public Bundle addAccount(AccountAuthenticatorResponse accountAuthenticatorResponse, String str, String str2, String[] strArr, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            Helpsr.m44681a8(this.f36279a8);
            Helpsr.m44734b8(this.f36279a8);
            return bundle2;
        }

        @Override
        public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, Bundle bundle) {
            return null;
        }

        public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String str) {
            return null;
        }

        @Override
        public Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str, Bundle bundle) {
            return null;
        }

        public String getAuthTokenLabel(String str) {
            return null;
        }

        @Override
        public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String[] strArr) {
            return null;
        }

        @Override
        public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str, Bundle bundle) {
            return null;
        }
    }


    public IBinder onBind(Intent intent) {
        return this.f36278e8.getIBinder();
    }

    public void onCreate() {
        super.onCreate();
        this.f36278e8 = new C15520a8(this);
    }
}