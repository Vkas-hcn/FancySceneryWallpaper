package com.wallpaper.kepaper.sigjk;

import android.media.MediaPlayer;

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
