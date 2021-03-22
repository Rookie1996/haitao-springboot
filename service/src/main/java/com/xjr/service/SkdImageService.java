package com.xjr.service;

import com.xjr.pojo.SkdImage;

public interface SkdImageService {

    public void insertSkdImg(SkdImage skdImage);

    public void updateSkdImg(SkdImage skdImage);

    public void deleteImgByUrl(String url);

}
