package com.hss01248.myimageutil;

import android.app.Application;
import android.content.Context;

import com.hss01248.frescoloader.FrescoUtil;

/**
 * Created by Administrator on 2016/12/22 0022.
 */

public class BaseApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        final Context  context= this;

        new Thread(new Runnable() {
            @Override
            public void run() {
                FrescoUtil.init(context,50);
            }
        }).run();

    }
}
