package com.xjr.service.impl;

import com.xjr.mapper.ProductImageMapper;
import com.xjr.mapper.ProductSpecMapper;
import com.xjr.mapper.ProductsMapper;
import com.xjr.mapper.StockMapper;
import com.xjr.pojo.*;
import com.xjr.pojo.vo.productVO;
import com.xjr.service.ScheduleProductService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleProductServiceImpl implements ScheduleProductService {

    @Autowired
    private ProductImageMapper productImageMapper;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ProductSpecMapper productSpecMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired(required = false)
    private Sid sid;

    @Transactional
    @Override
    public List<productVO> getAllProductVOBySkdId(String skdId) {

        // 按照该列表顺序拼接  搜索的商品is_del为0
        List<Products> productsList = productsMapper.selectProductsBySkdId(skdId);
        List<productVO> result = new ArrayList<>();

        for (Products pro: productsList){
            String pid = pro.getpId();

            List<String> color = productSpecMapper.selectUniqueColorByPid(pid);
            List<String> size = productSpecMapper.selectUniqueSizeByPid(pid);
            List<String> urls = productImageMapper.selectUrlsByPid(pid);
            Integer stock = stockMapper.selectStockByPid(pid);

            productVO productVO = ObjConvertUtils.convertProductVOFromProduct(pro);
            productVO.setColor(color);
            productVO.setSize(size);
            productVO.setUrls(urls);
            productVO.setStock(stock);

            result.add(productVO);
        }


        return result;
    }

    @Override
    public void setProductStatusByPidList(List<String> pidList, Integer status) {

        for(String pid: pidList){
            Products products = new Products();
            products.setpId(pid);
            products.setpStatus(status);

            productsMapper.updateByPrimaryKeySelective(products);
        }
    }

    @Transactional
    @Override
    public String submitProducts(productVO productVO) {

        String pid = productVO.getPid();

        // 修改商品信息
        if(pid !=null && pid != ""){

            // 1、product
            Products products = new Products();
            products.setpId(pid); // 主键不需要修改，作为查询键

            products.setpName(productVO.getName());
            products.setpBrand(productVO.getBrand());
            products.setpUnit(productVO.getUnit());
            products.setpOrigin(productVO.getOrigin());
            products.setpInfo(productVO.getInfo());
            products.setpDesc(productVO.getDesc());
            products.setpPrice(productVO.getPrice());

            // 已上架的商品不提供修改，或者修改过后需要重新上架
            products.setpStatus(0);
            products.setSkdId(productVO.getSkdId());

            productsMapper.updateByPrimaryKeySelective(products);

            // 2、productSpec
            // 先删除，再插入吧
            productSpecMapper.deleteAllSpecByPid(pid);

            List<String> color = productVO.getColor();
            List<String> size = productVO.getSize();

            for(String _color: color){ //a x b种
                ProductSpec productSpec = new ProductSpec();
                productSpec.setpId(pid);
                productSpec.setColor(_color);

                for(String _size: size){
                    productSpec.setSize(_size);
                    productSpecMapper.insertSelective(productSpec);
                }
            }

            // 3、stock
            Integer stock = productVO.getStock();
            stockMapper.updateStockByPid(pid, stock);

        }else{
            pid = sid.nextShort();
            // 1、product
            Products products = new Products();
            products.setpId(pid);
            products.setpName(productVO.getName());
            products.setpBrand(productVO.getBrand());
            products.setpUnit(productVO.getUnit());
            products.setpOrigin(productVO.getOrigin());
            products.setpInfo(productVO.getInfo());
            products.setpDesc(productVO.getDesc());
            products.setpPrice(productVO.getPrice());
            // 新增的是未上架状态
            products.setpStatus(0);
            products.setSkdId(productVO.getSkdId());

            productsMapper.insertSelective(products);

            // 2、productSpec
            List<String> color = productVO.getColor();
            List<String> size = productVO.getSize();

            for(String _color: color){ //a x b种
                ProductSpec productSpec = new ProductSpec();
                productSpec.setpId(pid);
                productSpec.setColor(_color);

                for(String _size: size){
                    productSpec.setSize(_size);
                    productSpecMapper.insertSelective(productSpec);
                }
            }

            // 3、stock
            Integer stock = productVO.getStock();
            Stock stock1 = new Stock();
            stock1.setProductId(pid);
            stock1.setStock(stock);
            stockMapper.insertSelective(stock1);

        }

        return pid;
    }

    @Override
    public Boolean isProductNameValid(String pName) {

        Integer cnt = productsMapper.selectPnameCount(pName);

        return cnt==0;
    }

    @Override
    public void insertSkdProductImg(ProductImage productImage) {
        if(productImage == null){
            return;
        }
        int cnt = productImageMapper.countSkdProductImage(productImage.getUrl());
        if(cnt != 0){
            return;
        }
        // 直接插入即可id自增
        productImageMapper.insertSelective(productImage);
    }

    @Override
    public void deleteProductImageByUrlAndPid(String url, String pid) {

        productImageMapper.deleteImageByUrlAndPid(url, pid);
    }

    @Override
    public void deleteSkdProduct(String pid) {

        productsMapper.deleteProductByPid(pid);
    }

    @Override
    public void deleteProductImageByPid(String pid) {

        productImageMapper.deleteImageByPid(pid);
    }

    @Override
    public void deleteProductSpecByPid(String pid) {

        productSpecMapper.deleteAllSpecByPid(pid);
    }

    @Override
    public void deleteProductStockByPid(String pid) {

        stockMapper.deleteStockByPid(pid);
    }

    @Override
    public productVO getProductVOByPid(String pid) {

        Products pro = productsMapper.selectByPrimaryKey(pid);

        List<String> color = productSpecMapper.selectUniqueColorByPid(pid);
        List<String> size = productSpecMapper.selectUniqueSizeByPid(pid);
        List<String> urls = productImageMapper.selectUrlsByPid(pid);
        Integer stock = stockMapper.selectStockByPid(pid);

        productVO productVO = ObjConvertUtils.convertProductVOFromProduct(pro);
        productVO.setColor(color);
        productVO.setSize(size);
        productVO.setUrls(urls);
        productVO.setStock(stock);

        return productVO;
    }
}
