package com.xjr.mapper;

import com.xjr.pojo.Comments;
import com.xjr.pojo.vo.commentVO;
import com.xjr.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsMapper extends MyMapper<Comments> {

    // 根据skdId查询所有的短评信息
    public List<Comments> selectCommentsBySkdId(String skdId);

    // 根据skdId查询commentVO
    public  List<commentVO> selectVOBySkdId(String skdId);

}