package com.hss01248.frescoloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class MyImageUtil {

    public static Context context;
    public static boolean isWWW;

    public static void init(final Context context, int cacheSizeInM,
                            int padding,String www,String test1,String test2,boolean iswww){
        FrescoUtilsForQiniu.init(context,cacheSizeInM,padding);
        QiniuUtils.init(www,test1,test2,iswww);
        MyImageUtil.context = context;
        MyImageUtil.isWWW = iswww;
    }

    public static void showQiniuPic(SimpleDraweeView view, String url){
        FrescoUtilsForQiniu.measureView(view);

        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();

        //处理matchparent的情况:宽度设置为屏幕宽度减去两边的边距共30dp
        if (width < 5){//matchparent
            width = FrescoUtilsForQiniu.screenWidth;
        }

       String  newUrl = QiniuUtils.getSamllImage(url,width,height,QiniuUtils.isWWW,true);
        FrescoUtilsForQiniu.loadUrl(newUrl,view,null,width,height,null);
    }



    public static void show(SimpleDraweeView view, String url){
        FrescoUtilsForQiniu.measureView(view);
        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();

        //处理matchparent的情况:宽度设置为屏幕宽度减去两边的边距共30dp
        if (width < 5){//matchparent
            width = FrescoUtilsForQiniu.screenWidth;
        }

        FrescoUtilsForQiniu.loadUrl(url,view,null,width,height,null);
    }




    public static void getBitmapWithQiniu(@NonNull final String url,  @NonNull final int width, @NonNull final int height,
                                              @Nullable BasePostprocessor processor, @NonNull final FrescoUtilsForQiniu.BitmapListener listener){
        String  newUrl = QiniuUtils.getSamllImage(url,width,height,QiniuUtils.isWWW,true);
        FrescoUtilsForQiniu.getBitmapWithProcessor(newUrl,context,width,height,processor,listener);
    }

    public static void getBitmap(@NonNull final String url,  @NonNull final int width, @NonNull final int height,
                                          @Nullable BasePostprocessor processor, @NonNull final FrescoUtilsForQiniu.BitmapListener listener){
        FrescoUtilsForQiniu.getBitmapWithProcessor(url,context,width,height,processor,listener);
    }

    public static String  getQiniuOriginUrl(String url){
       return  QiniuUtils.getOriginUrl(url,isWWW);
    }



}
