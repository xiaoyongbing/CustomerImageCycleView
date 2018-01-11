package com.hdos.customerimagecycleview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hdos.customerimagecycleview.bean.MainPic;
import com.hdos.customerimagecycleview.util.StringUtils;
import com.hdos.customerimagecycleview.view.CycleViewPager;
import com.hdos.customerimagecycleview.view.ImageCycleView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageCycleView cycleViewPager;//滚动控件
    private List<MainPic> advertisements;//广告集合
    private Context context;//上下文


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        cycleViewPager = (ImageCycleView)findViewById(R.id.cycle_view);
        initData();
    }

    private void initData() {
        advertisements = new ArrayList<>();
        for(int i=0;i<5;i++){
            MainPic mainPic = new MainPic();
            mainPic.setImgUrl("http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg");
            advertisements.add(mainPic);
        }
        cycleViewPager.setImageResources((ArrayList<MainPic>) advertisements,mAdCycleViewListener);

    }

    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {

        @Override
        public void onImageClick(MainPic info, int position, View imageView) {
            //Toast.makeText(context, "content->"+info.getAd_id(), Toast.LENGTH_SHORT).show();
            //showAdvertisement(info.getUrl_type(), info.getUrl(), info.getAd_tite());
        }

        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            //从缓存中获取图片 如果缓存不存在就从网络上获取
            if(!StringUtils.isEmpty(imageURL)){
                Picasso.with(context).load(imageURL).into(imageView);
            }
            /*String[] fileNames = imageURL.split("/");
            String fileName = fileNames[fileNames.length-1];
            fileName = fileName.substring(0,fileName.length()-4);*/
           /* Bitmap bitmap = mCache.getAsBitmap(fileName);
            if(bitmap!=null) {
                imageView.setImageBitmap(bitmap);
            }else {
                ImageLoader.getInstance().displayImage(imageURL, imageView);// 使用ImageLoader对图片进行加装！
            }*/

        }
    };
}
