package com.xjr.pojo;

import com.xjr.pojo.vo.productVO;

public class ObjConvertUtils {

    public static productVO convertProductVOFromProduct(Products products){

        productVO productVO = new productVO();

        productVO.setPid(products.getpId());
        productVO.setName(products.getpName());
        productVO.setBrand(products.getpBrand());
        productVO.setUnit(products.getpUnit());
        productVO.setOrigin(products.getpOrigin());
        productVO.setInfo(products.getpInfo());
        productVO.setDesc(products.getpDesc());
        productVO.setSales(products.getpSales());
        productVO.setPrice(products.getpPrice());
        productVO.setStatus(products.getpStatus());
        productVO.setMainImgUrl(products.getpMainImgUrl());
        productVO.setIsDel(products.getpIsDel());
        productVO.setSkdId(products.getSkdId());

        return productVO;
    }

    public static void main(String[] args) {
        Products products = new Products();
        products.setpStatus(1);
        products.setpOrigin("法国");

        productVO productVO = convertProductVOFromProduct(products);
        System.out.println(productVO.toString());
    }
}
