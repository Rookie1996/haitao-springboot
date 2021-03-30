package com.xjr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xjr.mapper.*;
import com.xjr.pojo.*;
import com.xjr.pojo.vo.*;
import com.xjr.service.IndexService;
import com.xjr.utils.PagedResult;
import com.xjr.utils.SqlDate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {


    @Autowired
    private SearchRecordsMapper searchRecordsMapper;

    @Autowired
    private RateMapper rateMapper;

    @Autowired
    private UsersLikeSchedulesMapper usersLikeSchedulesMapper;

    // -----------------------------------
    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private  StockMapper stockMapper;

    @Autowired
    private ProductSpecMapper productSpecMapper;

    @Autowired
    private ProductImageMapper productImageMapper;

    @Autowired
    private ProductsMapper productsMapper;

    //-----------------------------------------------
    @Autowired
    private SkdDetailImageMapper skdDetailImageMapper;

    @Autowired
    private ScheduleDetailsMapper scheduleDetailsMapper;

    @Autowired
    private SkdImageMapper skdImageMapper;

    @Autowired
    private SchedulesMapper schedulesMapper;


    // 2021/3/30 该方法分页搜索schedules对象，封装成cardVO之后传给前端的！！！

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public PagedResult getSkdCards(Integer currentPage, Integer pageSize) {

        //开始分页操作
        // 该分页插件只会对一条sql加limit操作
        PageHelper.startPage(currentPage, pageSize);

        // 1、根据默认规则查询所有行程
        List<Schedules> skdList = schedulesMapper.selectAllSkdCards();

        // 2、对cardsList进行拼装
        List<cardVO> cardsList = new ArrayList<>();
        String week[] = {"日","一","二","三","四","五","六"};

        for (Schedules skd: skdList){
            cardVO cardVO = new cardVO();
            cardVO.setSkdId(skd.getSkdId());
            cardVO.setTitle(skd.getSkdTitle());
            cardVO.setDesc(skd.getSkdDesc());
            cardVO.setUrl(skd.getSkdImgUrl());
            cardVO.setStars(skd.getSkdStarlevel());
            cardVO.setLikes(skd.getSkdLikes());
            cardVO.setLocation(skd.getSkdDestination());
            cardVO.setUid(skd.getUid());
            cardVO.setStatus(skd.getSkdStatus());

            // 处理mysql时间段
            Date startTime = skd.getSkdStarttime();
            Date endTime = skd.getSkdEndtime();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(startTime);
            Integer start_year = calendar1.get(Calendar.YEAR);
            Integer start_month = calendar1.get(Calendar.MONTH)+1;
            Integer start_day = calendar1.get(Calendar.DAY_OF_MONTH);
            String start_week_day = week[calendar1.get(Calendar.DAY_OF_WEEK)-1];

            String sArr[] = SqlDate.convertStringFromDate(startTime).split(" ");
            String start_detail = sArr[1];

//            System.out.println("start_detail:"+start_detail);

            cardVO.setStart_year(start_year);
            cardVO.setStart_month(start_month);
            cardVO.setStart_day(start_day);
            cardVO.setStart_week_day(start_week_day);
            cardVO.setStart_detail(start_detail);

            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(endTime);
            Integer end_year = calendar2.get(Calendar.YEAR);
            Integer end_month = calendar2.get(Calendar.MONTH)+1;
            Integer end_day = calendar2.get(Calendar.DAY_OF_MONTH);
            String end_week_day = week[calendar2.get(Calendar.DAY_OF_WEEK)-1];
//            String end_detail = calendar1.get(Calendar.HOUR_OF_DAY)+":"+calendar1.get(Calendar.MINUTE);

            String eArr[] = SqlDate.convertStringFromDate(endTime).split(" ");
            String end_detail = eArr[1];

            cardVO.setEnd_year(end_year);
            cardVO.setEnd_month(end_month);
            cardVO.setEnd_day(end_day);
            cardVO.setEnd_week_day(end_week_day);
            cardVO.setEnd_detail(end_detail);

//            System.out.println("end_detail:"+end_detail);

            // 存入cardLists中
            cardsList.add(cardVO);
        }


        // 3、分页显示
        PageInfo<Schedules> pageList = new PageInfo<>(skdList);

        PagedResult pagedResult = new PagedResult();
        //当前页数
        pagedResult.setPage(currentPage);
        //总页数
        pagedResult.setTotal(pageList.getPages());
        //每行显示的内容
        pagedResult.setRows(cardsList);
        //总记录数
        pagedResult.setRecords(pageList.getTotal());

        return pagedResult;
    }

    @Override
    public List<String> getScheduleImages(String skdId) {

        List<String> urls = skdImageMapper.selectUrlsBySkdId(skdId);

        return urls;
    }

    @Override
    public List<skdDetailAndImgVO> getAllSkdDetails(String skdId) {

        List<ScheduleDetails> list = scheduleDetailsMapper.selectAllSkdDetailBySkdId(skdId);

        // 好像用schedulesDetail对象也可以
        List<skdDetailAndImgVO> result = new ArrayList<>();

        for(ScheduleDetails scheduleDetails: list){

            skdDetailVO skdDetailVO = new skdDetailVO();
            skdDetailVO.setSkdDetailId(scheduleDetails.getSkdDetailId());
            skdDetailVO.setArticle(scheduleDetails.getSkdDetailArticle());
            skdDetailVO.setTimepoint(SqlDate.convertStringFromDate(scheduleDetails.getSkdDetailTimepoint()));
            skdDetailVO.setStatus(scheduleDetails.getSkdDetailActive());
            skdDetailVO.setLoc(scheduleDetails.getSkdDetailPlace());
            skdDetailVO.setSkdId(scheduleDetails.getSkdId());

            // 塞入DetailAndImg对象中
            skdDetailAndImgVO skdDetailAndImgVO = new skdDetailAndImgVO();
            skdDetailAndImgVO.setSkdDetailVO(skdDetailVO);

            // 每个skdDetail都去查对应的skd_image的图片 urls
            List<String> urls = skdDetailImageMapper.selectAllBySkdDetailId(scheduleDetails.getSkdDetailId());
            skdDetailAndImgVO.setUrls(urls);

            result.add(skdDetailAndImgVO);
        }

        return result;
    }

    @Transactional
    @Override
    public List<productVO> getAllSkdProducts(String skdId) {

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
    public List<commentVO> getAllSkdComments(String skdId) {

        List<commentVO> result = commentsMapper.selectVOBySkdId(skdId);

        return result;
    }

    @Override
    public Integer getScheduleLikes(String skdId) {

        Integer res = usersLikeSchedulesMapper.selectNumsBySkdId(skdId);

        return res;
    }

    @Override
    public BigDecimal getScheduleStars(String skdId) {

        BigDecimal average = rateMapper.selectAverageStarsBySkdId(skdId);

        return average==null?new BigDecimal(0):average;
    }

    @Override
    public List<String> getHotWords() {

        List<String> hotwordsList = searchRecordsMapper.getHotWords();

        return hotwordsList;
    }

    @Override
    public void saveSearchRecords(String searchContent, String loc, String keywords, String userId) {
        if(StringUtils.isEmpty(searchContent)){
            return;
        }
        // 防止没有抽取信息的内容，进入数据库
        if(StringUtils.isEmpty(loc) && StringUtils.isEmpty(keywords)){
            return;
        }
        SearchRecords searchRecords = new SearchRecords();
        searchRecords.setSrContent(searchContent);
        searchRecords.setSrDestination(loc);
        searchRecords.setSrEntity(keywords);
        if(!StringUtils.isEmpty(userId)){
            searchRecords.setSrUid(userId);
        }
        searchRecordsMapper.insertSelective(searchRecords);
    }
}
