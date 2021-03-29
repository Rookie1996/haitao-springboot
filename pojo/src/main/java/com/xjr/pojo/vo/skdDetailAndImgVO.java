package com.xjr.pojo.vo;

import java.io.Serializable;
import java.util.List;

public class skdDetailAndImgVO implements Serializable {

    public skdDetailVO skdDetailVO;

    public List<String> urls;

    public com.xjr.pojo.vo.skdDetailVO getSkdDetailVO() {
        return skdDetailVO;
    }

    public void setSkdDetailVO(com.xjr.pojo.vo.skdDetailVO skdDetailVO) {
        this.skdDetailVO = skdDetailVO;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "skdDetailAndImgVO{" +
                "skdDetailVO=" + skdDetailVO +
                ", urls=" + urls +
                '}';
    }
}
