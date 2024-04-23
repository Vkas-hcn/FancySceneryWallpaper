package com.android.internal.imp;

import android.media.MediaPlayer;
import android.util.Log;

/**
 * @author Create by l
 * Description:
 **/
public class IQD implements MediaPlayer.OnErrorListener {

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
       // Log.i("DaemonLog", "mediaPlayer onError: " + what + "," + extra);
        return false;
    }
}
