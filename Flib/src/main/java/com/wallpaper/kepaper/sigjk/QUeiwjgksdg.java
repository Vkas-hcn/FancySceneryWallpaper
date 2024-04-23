package com.wallpaper.kepaper.sigjk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.wallpaper.kepaper.EWUij;

/**
 * @author Create by l
 * Description:
 **/
public class QUeiwjgksdg extends Service {

    public IQC f12210a;

    public static void b(EWUij bVar){
        if (TextUtils.equals(bVar.f27328c, bVar.f27330e)){
            Intent intent = new Intent(bVar.f27326a, QUeiwjgksdg.class);
            try {
//                if (bVar.d()){
                    bVar.f27326a.startService(intent);
//                }else {
//                    Log.i("DaemonLog","IPI stop");
//                    bVar.f27326a.stopService(intent);
//                }
            }catch (Exception e){
               // Log.i("DaemonLog","IPI begin exception");
            }
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        IQC cVar = this.f12210a;
        if (cVar != null){
            cVar.d(getApplicationContext());
            this.f12210a = null;
        }
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (this.f12210a == null){
            this.f12210a = new IQC();
            this.f12210a.a(getApplicationContext());
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
