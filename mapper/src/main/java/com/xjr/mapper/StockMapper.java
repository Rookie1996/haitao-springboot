package com.xjr.mapper;

import com.xjr.pojo.Stock;
import com.xjr.utils.MyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMapper extends MyMapper<Stock> {

    /**
     * 根据pid更新stock
     * @param pid
     * @param stock
     */
    void updateStockByPid(String pid, Integer stock);

    /**
     * 根据pid查询库存
     * @param pid
     * @return
     */
    Integer selectStockByPid(String pid);

    /**
     * 根据pid删除库存
     * @param pid
     */
    void deleteStockByPid(String pid);

    /**
     * 根据pid扣减库存
     * @param pid
     * @param number
     */
    Integer decreaseStockByPid(String pid, Integer number);

    // 回补库存
    void increaseStockByPid(String pid, Integer number);
}