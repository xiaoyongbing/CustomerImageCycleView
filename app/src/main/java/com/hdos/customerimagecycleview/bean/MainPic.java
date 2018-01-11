package com.hdos.customerimagecycleview.bean;

/**
 * Created by xiaoyongbing on 2017/8/3.
 * 首页图片的原型
 */

public class MainPic {
    private String imgtitle;//图片标题
    private String imgUrl;//图片的url
    private String imgtype;//图片类型
    private String action;//跳转的地址

    public String getImgtitle() {
        return imgtitle;
    }

    public void setImgtitle(String imgtitle) {
        this.imgtitle = imgtitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgtype() {
        return imgtype;
    }

    public void setImgtype(String imgtype) {
        this.imgtype = imgtype;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
