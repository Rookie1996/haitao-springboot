package com.xjr.mapper;

import com.xjr.pojo.SearchRecords;
import com.xjr.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRecordsMapper extends MyMapper<SearchRecords> {

    // 查询热搜词 可以包括地点和实体
    public List<String> getHotWords();
}