package com.android.internal;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public  class IPL extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //Log.d("IPL","bring up activity onCreate");
        setFinishOnTouchOutside(true);
        finish();
    }
}
