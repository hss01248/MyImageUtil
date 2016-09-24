package com.hss01248.frescoloader;

import android.text.TextUtils;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class QiniuUtils {
    public  static String QINIU_TEST2 = "";//测试服
    public  static String QINIU_TEST = "";
    public  static String QINIU_WWW ="";
    public static  boolean isWWW;//是否为正式服


    public static boolean isQiniu(String url){
        boolean isQiniu= false;
        if (TextUtils.isEmpty(url)){
            return  false;
        }
        isQiniu = url.contains(QINIU_WWW) || url.contains(QINIU_TEST) || url.contains(QINIU_TEST2)
                || url.contains("?imageMogr2") || url.contains("?imageView2") ||url.contains("?imageView");
        return isQiniu;

    }


    public static String getOriginUrl(String url,boolean isWWW){
        String newUrl = url;
       // Logger.e("getUrl:"+ newUrl);
        if (TextUtils.isEmpty(url)){
            return "";
        }

        boolean hasHost = url.contains("http:" ) || url.contains("https:" )  ;
        if (!hasHost){
            if (isWWW){
                newUrl = QINIU_WWW + url;
            }else {
                newUrl = QINIU_TEST2 + url;
            }
        }

        if (newUrl.contains("?") ){
            newUrl = newUrl.substring(0,newUrl.indexOf("?"));
        }

        return newUrl;
    }

    public static String getSamllImage(String url,int width,int height,boolean isWWW,boolean isToJPG){
        if (TextUtils.isEmpty(url) ){
            return "";
        }
        String newUrl = url;
        StringBuilder sb = new StringBuilder(200);

        String originUrl = getOriginUrl(newUrl,isWWW);//将之前可能带有的参数拿掉，获取原图url
        sb.append(originUrl);

        if (width ==0 || height ==0){
            if (isToJPG ){
                sb.append("?imageMogr2/format/jpg");
            }
        }else {

            if (isToJPG ){//&& !url.contains(".gif")： gif也能转jpg
                sb.append("?imageMogr2/format/jpg");
            }
            // 限定短边，生成不小于200x200的缩略图
            //  http://78re52.com1.z0.glb.clouddn.com/resource/gogopher.jpg?imageMogr2/thumbnail/!200x200r
            // String imageEnd = "?imageMogr2/thumbnail/!"+"!"+width + "x"+ height +"r";
            sb.append(isToJPG ? "/thumbnail/!" : "?imageMogr2/thumbnail/!");
            sb.append(width+"");
            sb.append("x");
            sb.append(height+"");
            sb.append("r");


            ///gravity/Center/crop/300x300  锚点在正中（Center），生成300x300裁剪图

            sb.append("/gravity/Center/crop/");
            sb.append(width+"");
            sb.append("x");
            sb.append(height+"");

        }

        newUrl = sb.toString();
       // Logger.e("getUrlsmall:"+ newUrl);
        return newUrl;
    }

    /**
     * 先生成缩略图，然后将缩略图模糊成基本色块，达到的效果是完全看不清图像
     * @param url
     * @param isWww
     * @return
     */
    public static String highBlur(String url,boolean isWww){
        String newUrl = getOriginUrl(url,isWww);
        return newUrl+"?imageMogr2/thumbnail/!300x200r/blur/50x99";
    }


    public static void init(String wwwBaseUrl, String test1BaseUrl, String test2BaseUrl, boolean iswww) {
            QINIU_WWW = wwwBaseUrl;
        QINIU_TEST = test1BaseUrl;
        QINIU_TEST2 = test2BaseUrl;
        isWWW = iswww;
    }
}
