package com.xjr.mapper;

import com.xjr.pojo.ProductSpec;
import com.xjr.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSpecMapper extends MyMapper<ProductSpec> {

    /**
     * 根据pid删除所有的规格，重新插入规格（待改进）
     * @param pid
     */
    void deleteAllSpecByPid(String pid);

    /**
     * 根据pid查询所有种类的颜色
     * @param pid
     * @return
     */
    List<String> selectUniqueColorByPid(String pid);

    /**
     * 根据pid查询所有种类的尺寸
     * @param pid
     * @return
     */
    List<String> selectUniqueSizeByPid(String pid);

}