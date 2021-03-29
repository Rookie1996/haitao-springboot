package com.xjr.mapper;

import com.xjr.pojo.Products;
import com.xjr.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsMapper extends MyMapper<Products> {

    // 根据skdId查询所有的product表信息
    public List<Products> selectProductsBySkdId(String skdId);

    // 根据pName查询表中记录数 （效率低待改进）
    public Integer selectPnameCount(String pName);

    // 根据pid主键设置p_is_del = 1
    public void deleteProductByPid(String pid);

    // 根据pid增加销量
    void increaseSalesByPid(String pid, Integer number);

}