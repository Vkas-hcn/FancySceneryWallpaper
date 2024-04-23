package com.wallpaper.kepaper;
import android.app.Activity;
import android.os.Bundle;

public  class IPL extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //Log.d("IPL","bring up activity onCreate");
        setFinishOnTouchOutside(true);
        finish();
    }
}
