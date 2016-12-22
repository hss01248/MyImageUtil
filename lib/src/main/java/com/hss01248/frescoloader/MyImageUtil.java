package com.hss01248.frescoloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class MyImageUtil {

    public static Context context;
    public static boolean isWWW;

    public static void init(final Context context, int cacheSizeInM,
                            String wwwBaseUrl,String test1BaseUrl,String test2BaseUrl,boolean iswww){
        FrescoUtil.init(context,cacheSizeInM);
        QiniuUtils.init(wwwBaseUrl,test1BaseUrl,test2BaseUrl,iswww);
        MyImageUtil.context = context;
        MyImageUtil.isWWW = iswww;
    }

    public static void showQiniuPic(View view, String url){
        FrescoUtil.measureView(view);

        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();

        //处理matchparent的情况:宽度设置为屏幕宽度减去两边的边距共30dp
        if (width < 5){//matchparent
            width = FrescoUtil.screenWidth;
        }

       String  newUrl = QiniuUtils.getSamllImage(url,width,height,QiniuUtils.isWWW,true);
        if (view instanceof  SimpleDraweeView){
            FrescoUtil.loadUrl(newUrl,(SimpleDraweeView)view,null,width,height,null);
        }

    }



    public static void show(View view, String url){
        FrescoUtil.measureView(view);
        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();

        //处理matchparent的情况:宽度设置为屏幕宽度减去两边的边距共30dp
        if (width < 5){//matchparent
            width = FrescoUtil.screenWidth;
        }

        if (view instanceof  SimpleDraweeView){
            FrescoUtil.loadUrl(url,(SimpleDraweeView)view,null,width,height,null);
        }
    }




    public static void getBitmapWithQiniu(@NonNull final String url,  @NonNull final int width, @NonNull final int height,
                                              @Nullable BasePostprocessor processor, @NonNull final FrescoUtil.BitmapListener listener){
        String  newUrl = QiniuUtils.getSamllImage(url,width,height,QiniuUtils.isWWW,true);
        FrescoUtil.getBitmapWithProcessor(newUrl,context,width,height,processor,listener);
    }

    public static void getBitmap(@NonNull final String url,  @NonNull final int width, @NonNull final int height,
                                          @Nullable BasePostprocessor processor, @NonNull final FrescoUtil.BitmapListener listener){
        FrescoUtil.getBitmapWithProcessor(url,context,width,height,processor,listener);
    }

    public static String  getQiniuOriginUrl(String url){
       return  QiniuUtils.getOriginUrl(url,isWWW);
    }



}
