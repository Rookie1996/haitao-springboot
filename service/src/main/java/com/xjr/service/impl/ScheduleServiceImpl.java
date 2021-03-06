package com.xjr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xjr.mapper.CommentsMapper;
import com.xjr.mapper.RateMapper;
import com.xjr.mapper.SchedulesMapper;
import com.xjr.mapper.SkdImageMapper;
import com.xjr.pojo.Comments;
import com.xjr.pojo.Schedules;
import com.xjr.pojo.SkdImage;
import com.xjr.pojo.vo.selectCardVO;
import com.xjr.service.ScheduleService;
import com.xjr.utils.DateCrossUtils;
import com.xjr.utils.JSONResult;
import com.xjr.utils.PagedResult;
import com.xjr.utils.SqlDate;
import io.swagger.models.auth.In;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private RateMapper rateMapper;

    @Autowired
    private SchedulesMapper schedulesMapper;

    @Autowired
    private SkdImageMapper skdImageMapper;

    @Autowired(required = false)
    private Sid sid;

    @Override
    public PagedResult getDraftSkds(String userId, Integer page, Integer pageSize) {

        //开始分页操作
        PageHelper.startPage(page, pageSize);

        // 1、根据userId查询所有行程草稿
        List<Schedules> list_draft = schedulesMapper.selectSkdByUIdAndStatus(userId, (byte)2);

        // 2、分页显示
        PageInfo<Schedules> pageList = new PageInfo<>(list_draft);

        PagedResult pagedResult = new PagedResult();
        //当前页数
        pagedResult.setPage(page);
        //总页数
        pagedResult.setTotal(pageList.getPages());
        //每行显示的内容
        pagedResult.setRows(list_draft);
        //总记录数
        pagedResult.setRecords(pageList.getTotal());

        return pagedResult;
    }

    /**
     *
     * @param userId
     * @param page 当前页数
     * @param pageSize 每页显示数量
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public PagedResult getPublishedSkds(String userId, Integer page, Integer pageSize) {

        //开始分页操作
        PageHelper.startPage(page, pageSize);

        // 1、根据userId和skd_status查询所有行程
        List<Schedules> list_publish = schedulesMapper.selectPublishedSkdByUid(userId);

        // 该分页插件只会对一条sql加limit操作

//        // 未完成的行程在前面
//        list_doing.addAll(list_done);

        // 2、分页显示
        PageInfo<Schedules> pageList = new PageInfo<>(list_publish);

        PagedResult pagedResult = new PagedResult();
        //当前页数
        pagedResult.setPage(page);
        //总页数
        pagedResult.setTotal(pageList.getPages());
        //每行显示的内容
        pagedResult.setRows(list_publish);
        //总记录数
        pagedResult.setRecords(pageList.getTotal());

        return pagedResult;
    }

    @Override
    public List<Schedules> selectSkdByUIdAndStatus(String uid, Byte status) {

        List<Schedules> list = new ArrayList<>();
        list = schedulesMapper.selectSkdByUIdAndStatus(uid, status);

        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String submitSkd(Schedules schedules) {

        byte status = schedules.getSkdStatus();
        // 操作Schedules表
        // 1、 新增行程总览
        if(status == 0 && schedules.getSkdId() == null) {
            String skdId = sid.nextShort();
            schedules.setSkdId(skdId);
            schedulesMapper.insert(schedules);

        }else
        // 2、 将草稿发布出来，或是修改发布行程
        if(status == 0 && schedules.getSkdId() != null){
            schedulesMapper.updateByPrimaryKey(schedules);

        }else
        // 3、 保存为草稿
        if(status == 2 && schedules.getSkdId() == null){
            String skdId = sid.nextShort();
            schedules.setSkdId(skdId);
            schedulesMapper.insert(schedules);
        }else
        // 4、 继续保存为草稿
        if(status == 2 && schedules.getSkdId() != null){
            schedulesMapper.updateByPrimaryKey(schedules);

        }

        return schedules.getSkdId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteSkd(String skdId) {

        // 1、删除 设置schedules表中状态
        Schedules skd = new Schedules();
        skd.setSkdId(skdId);
        skd.setSkdStatus((byte)3);
        schedulesMapper.updateByPrimaryKeySelective(skd);

        // 2、设置所有的skd_image中图片状态
        skdImageMapper.deleteSkdImageBySkdId(skdId);

        // 3、删除虚拟目录中的图片，按skd_id删除文件夹即可
        // 删除方法从controller中抽离，编写删除util。/或者直接在controller层删除---选择在controller中删除

    }

    @Override
    public void finishSkd(String skdId) {

        Schedules skd = new Schedules();
        skd.setSkdId(skdId);
        // 1-已完成 2-草稿
        skd.setSkdStatus((byte)1);
        schedulesMapper.updateByPrimaryKeySelective(skd);
    }

    @Override
    public void submitMainUrl(String skdId, String uploadFilePath) {

        Schedules schedules = new Schedules();
        schedules.setSkdId(skdId);
        schedules.setSkdImgUrl(uploadFilePath);

        // 更新主图片url
        schedulesMapper.updateByPrimaryKeySelective(schedules);
    }

    @Override
    public Boolean isValidTimeInterval(String uid, String startTime, String endTime) {

        List<Schedules> list = schedulesMapper.selectSkdByUIdAndStatus(uid, (byte) 0);

        for(Schedules skd: list){
            String sTime = SqlDate.convertStringFromDate(skd.getSkdStarttime());
            String eTime = SqlDate.convertStringFromDate(skd.getSkdEndtime());
//            System.out.println("startTime:"+startTime);
//            System.out.println("endTime:"+endTime);
//            System.out.println("-------------------------");
//            System.out.println("sTime:"+sTime);
//            System.out.println("eTime:"+eTime);

            try {
                Boolean f = DateCrossUtils.isDateCross(startTime, endTime, sTime, eTime);
//                System.out.println(f);
                if(f){
                    return false;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public Schedules selectSkdByPrimaryKey(String skdId) {

        Schedules schedules = schedulesMapper.selectByPrimaryKey(skdId);

        return schedules;
    }

    @Override
    public List<String> selectAllImgBySkdId(String skdId) {

        List<String> list = skdImageMapper.selectUrlsBySkdId(skdId);

        return list;
    }

    @Override
    public void deleteUrlIfExist(String url) {
        // url ---> null
        schedulesMapper.updateNullByUrl(url);
    }

    @Transactional
    @Override
    public PagedResult getSkdsByCondition(List<String> keywords, Map<String, Integer> countMap, Integer currentPage,
                                          Integer pageSize, String skdTime, String likesOrSales, String status) {

        String time[] = {"全部行程","最新发布"};
        String likes[] = {"默认排序","点赞排序","好评排序"};
        String statusArr[] = {"未完成","已完成"};

        Integer condition1 = skdTime.equals("全部行程")?0: 1;

        Integer condition2 = 0;
        switch (likesOrSales){
            case "默认排序":
                condition2 = 0;
                break;
            case "点赞排序":
                condition2 = 1;
                break;
            case "好评排序":
                condition2 = 2;
                break;
            default: break;
        }

        Integer condition3 = -1;
        if(status.equals("未完成")){
            condition3 = 0;
        }else if(status.equals("已完成")){
            condition3 = 1;
        }


        // 1、关键词取前3个做模糊匹配
        // 暂时只取一个出现频率最高的关键词
        String keyword = "";
        if(keywords.size()>0){
            keyword = keywords.get(0);
        }

        // 2、地点取前3个做模糊匹配
        // 暂时只取一个出现频率最高的地点词
        String loc = "";
        Integer cnt = 0;
        if(countMap.size()>0){
            for(Map.Entry<String, Integer> entry : countMap.entrySet()){
                if(entry.getValue()>cnt){
                    loc = entry.getKey();
                    cnt = entry.getValue();
                }
            }
        }

        // 3、开始分页操作
        PageHelper.startPage(currentPage, pageSize);

        List<Schedules> list = new ArrayList<>();

        // 4、进行sql调用
        list = schedulesMapper.selectByCondition(keyword, loc, condition1, condition2, condition3);

        List<selectCardVO> cardList = new ArrayList<>();

        // 5、加载短评、rate信息
        for(Schedules schedules: list){
            String skdId = schedules.getSkdId();

            BigDecimal rate = rateMapper.selectAverageStarsBySkdId(skdId);

            List<Comments> comments = commentsMapper.selectCommentsBySkdId(skdId);

            selectCardVO selectCardVO = new selectCardVO();
            selectCardVO.setSchedules(schedules);
            selectCardVO.setRate(rate);
            selectCardVO.setCommentsList(comments);

            cardList.add(selectCardVO);
        }


        // 6、分页显示
        PageInfo<Schedules> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        //当前页数
        pagedResult.setPage(currentPage);
        //总页数
        pagedResult.setTotal(pageList.getPages());
        //每行显示的内容
        pagedResult.setRows(cardList);
        //总记录数
        pagedResult.setRecords(pageList.getTotal());

        return pagedResult;
    }


    // 服务器保存静态图片资源
//    public void uploadImages(Schedules schedules, String[] urls, String fileSpace) throws IOException {
//        // 保存到数据库中的相对路径
//        String uploadPathDB = "/" + schedules.getUid() + "/skdImg";
//
//        FileOutputStream fileOutputStream = null;
//
//        // 文件上传的最终保存路径
//        String finalImgPath = "";
//
//        for(int i=0; i<urls.length; i++){
//            try {
//                if (urls[i] != null) {
//
//                    String fileName = urls[i];
//                    if (StringUtils.isNotBlank(fileName)) {
//
//                        finalImgPath = fileSpace + uploadPathDB + "/" + fileName;
//                        // 设置数据库保存的路径
//                        uploadPathDB += ("/" + fileName);
//
//                        File outFile = new File(finalImgPath);
//
//                        if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
//                            // 创建父文件夹
//                            outFile.getParentFile().mkdirs();
//                        }
//
//                        fileOutputStream = new FileOutputStream(outFile);
//
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (fileOutputStream != null) {
//                    fileOutputStream.flush();
//                    fileOutputStream.close();
//                }
//            }
//        } // for end
//
//    }
}
