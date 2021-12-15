package com.wuhai.myframe2.ui.dkplayer;

import android.content.Context;

import xyz.doikki.videoplayer.player.PlayerFactory;

public class DkAliPlayerFactory extends PlayerFactory<DkAliPlayer> {

    public static DkAliPlayerFactory create() {
        return new DkAliPlayerFactory();
    }

    @Override
    public DkAliPlayer createPlayer(Context context) {
        return new DkAliPlayer(context);
    }
}
