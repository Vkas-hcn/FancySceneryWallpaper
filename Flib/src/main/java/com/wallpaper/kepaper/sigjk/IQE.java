package com.wallpaper.kepaper.sigjk;

import android.media.MediaPlayer;

/**
 * @author Create by l
 * Description:
 **/
public class IQE implements MediaPlayer.OnPreparedListener {

    public final IQC f27332a;

    public IQE(IQC cVar){
        this.f27332a = cVar;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        this.f27332a.f27337d = true;
        if (this.f27332a.f27338e){
            this.f27332a.b();
        }
    }
}
