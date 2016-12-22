package com.hss01248.myimageutil;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hss01248.frescoloader.FrescoUtil;


public class MainActivity extends AppCompatActivity {
    SimpleDraweeView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         view = (SimpleDraweeView) findViewById(R.id.my_image_view);

    }


    @Override
    protected void onResume() {
        super.onResume();
        //可溯源
        final String url = "https://www.daodianwang.com/Public/upload/business/122/business_logo1482387877.jpg";
        //可溯源
       final  String url2 = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
        //12306,不可溯源
        final  String url3 = "https://travel.12306.cn/imgs/resources/uploadfiles/images/fed7d5b4-37d3-4f32-bacc-e9b942cb721d_product_W572_H370.jpg";
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FrescoUtil.loadUrl(url3,view,null,260,260,null);
            }
        },3000);

    }
}
