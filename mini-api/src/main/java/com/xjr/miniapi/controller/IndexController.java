package com.xjr.miniapi.controller;

import com.xjr.jieba.extractWords;
import com.xjr.pojo.Comments;
import com.xjr.pojo.vo.commentVO;
import com.xjr.pojo.vo.productVO;
import com.xjr.pojo.vo.skdDetailAndImgVO;
import com.xjr.pojo.vo.skdDetailVO;
import com.xjr.service.IndexService;
import com.xjr.service.ScheduleService;
import com.xjr.utils.JSONResult;
import com.xjr.utils.PagedResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "首页卡片推荐接口", tags = {"首页卡片推荐的controller"})
@RequestMapping("/index")
public class IndexController extends BaseController{

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private IndexService indexService;

    // 日志对象
    private static final Logger logger = Logger.getLogger(IndexController.class);


    /**
     * 1、获取用户已发布的行程，并封装成卡片组件使用的cardVO对象
     * status：0表示进行中的行程，1表示已完成的行程
     *
     * 显示规则：
     * （1）默认：按点赞数、评分数、短评热度降序排序显示；（2）进行行程先显示，已完成行程后显示；（3）分页6张卡片；
     * （2）按某些规则推荐显示行程。
     *
     */
    @ApiOperation(value = "获取首页卡片推荐信息", httpMethod = "GET", notes = "获取首页卡片推荐信息")
    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    public JSONResult getSkdCard(Integer currentPage, Integer pageSize)
    {
        logger.info("获取首页卡片推荐信息接口：currentPage============================="+currentPage);
        logger.info("获取首页卡片推荐信息接口：pageSize============================="+pageSize);

        //没有显示显示的页码就显示第一页
        if (currentPage == null) {
            //当前页数
            currentPage = 1;
        }

        if (pageSize == null) {
            // 分页的记录个数
            pageSize = PAGE_SIZE;
        }

        PagedResult result = indexService.getSkdCards(currentPage, pageSize);

        logger.info("获取首页卡片推荐信息接口：PagedResult============================="+result.toString());


        return JSONResult.ok(result);
    }

    /**
     * 获取行程中行程图片。scheduleImages
     * @param skdId
     * @return
     */
    @ApiOperation(value = "获取行程中行程图片接口", httpMethod = "GET", notes = "获取行程中行程图片接口")
    @RequestMapping(value = "/scheduleImg", method = RequestMethod.GET)
    public JSONResult getScheduleImages(@ApiParam(required = true, value = "行程id", name = "skdId")
                                      @RequestParam(value = "skdId", required = true) String skdId)
    {
        logger.info("获取行程中行程图片接口：skdId============================="+skdId);

        List<String> urls = indexService.getScheduleImages(skdId);

        return JSONResult.ok(urls);
    }

    /**
     * 获取行程中所有行程日记信息 ，加上每个行程日记的图片。
     * @param skdId
     * @return
     */
    @ApiOperation(value = "获取行程中所有行程日记信息", httpMethod = "GET", notes = "获取行程中所有行程日记信息")
    @RequestMapping(value = "/skdDetails", method = RequestMethod.GET)
    public JSONResult getAllSkdDetail(@ApiParam(required = true, value = "行程id", name = "skdId")
                                      @RequestParam(value = "skdId", required = true) String skdId)
    {
        logger.info("获取首页卡片推荐信息接口：skdId============================="+skdId);

        List<skdDetailAndImgVO> result = indexService.getAllSkdDetails(skdId);

        return JSONResult.ok(result);
    }

    /**
     * 获取行程中所有行程商品信息的接口
     * @param skdId
     * @return
     */
    @ApiOperation(value = "获取行程中所有行程商品信息的接口", httpMethod = "GET", notes = "获取行程中所有行程商品信息的接口")
    @RequestMapping(value = "/skdProducts", method = RequestMethod.GET)
    public JSONResult getAllSkdProducts(@ApiParam(required = true, value = "行程id", name = "skdId")
                                      @RequestParam(value = "skdId", required = true) String skdId)
    {
        logger.info("获取行程中所有行程商品信息的接口：skdId============================="+skdId);

        List<productVO> result = indexService.getAllSkdProducts(skdId);

        return JSONResult.ok(result);
    }


    /**
     * 获取行程短评信息VO的接口
     * @param skdId
     * @return
     */
    @ApiOperation(value = "获取行程短评信息VO的接口", httpMethod = "GET", notes = "获取行程短评信息VO的接口")
    @RequestMapping(value = "/skdCommentsVO", method = RequestMethod.GET)
    public JSONResult getAllSkdComments(@ApiParam(required = true, value = "行程id", name = "skdId")
                                        @RequestParam(value = "skdId", required = true) String skdId)
    {
        logger.info("获取行程短评信息VO的接口：skdId============================="+skdId);

        List<commentVO> result = indexService.getAllSkdComments(skdId);

        return JSONResult.ok(result);
    }

    /**
     *
     * 获取行程点赞数的接口
     *
     * 顺便更新行程表中的likes字段todo
     *
     * @param skdId
     * @return
     */
    @ApiOperation(value = "获取行程点赞数的接口", httpMethod = "GET", notes = "获取行程点赞数的接口")
    @RequestMapping(value = "/skdLikes", method = RequestMethod.GET)
    public JSONResult getSkdLikes(@ApiParam(required = true, value = "行程id", name = "skdId")
                                        @RequestParam(value = "skdId", required = true) String skdId)
    {
        logger.info("获取行程点赞数的接口：skdId============================="+skdId);

        Integer nums = indexService.getScheduleLikes(skdId);

        return JSONResult.ok(nums);
    }

    /**
     *
     * 获取行程评分rate的接口
     *
     * 顺便更新行程表中的starslevel字段todo
     *
     * @param skdId
     * @return
     */
    @ApiOperation(value = "获取行程评分rate的接口", httpMethod = "GET", notes = "获取行程评分rate的接口")
    @RequestMapping(value = "/skdStars", method = RequestMethod.GET)
    public JSONResult getSkdStars(@ApiParam(required = true, value = "行程id", name = "skdId")
                                  @RequestParam(value = "skdId", required = true) String skdId)
    {
        logger.info("获取行程评分rate的接口：skdId============================="+skdId);

        BigDecimal average = indexService.getScheduleStars(skdId);

        return JSONResult.ok(average);
    }

    /**
     * 根据搜索记录表获取热搜词接口
     * @return
     */
    @ApiOperation(value = "获取热搜词的接口", httpMethod = "GET", notes = "获取热搜词的接口")
    @RequestMapping(value = "/hotWords", method = RequestMethod.GET)
    public JSONResult getHotWords()
    {
        List<String> hotWordsList = indexService.getHotWords();

        return JSONResult.ok(hotWordsList);
    }


    /**
     * 搜索接口考虑：
     * 1、输入为空，首页初次加载行程卡片。默认搜索规则：（1）按点赞数、短评数降序；（2）分页4-6张卡片。
     * 2、输入不为空。
     * （1）先过滤敏感词DFA；(不做了)
     * （2）对搜索内容存储，再进行jieba分词，获取地名、实体内容（作为热搜词），存入searchRecords表。跟skd的title、desc还有product的title匹配；
     * （3）排序依旧按默认搜索规则；（后续按3:1比例输出付费代购的行程）
     */

    // jieba分析搜索内容、DFA过滤敏感词汇。有问题的搜索记录 或 抽取实体、地点为空字符串的不记录
    // 可以提供给游客、消费者和代购者使用
    @Transactional
    @ApiOperation(value = "主页搜索接口", httpMethod = "GET", notes = "主页搜索接口")
    @RequestMapping(value = "/searchContent", method = RequestMethod.GET)
    public JSONResult searchRecords(@ApiParam(required = true, value = "搜索内容", name = "searchContent")
                                        @RequestParam(value = "searchContent", required = true) String searchContent,
                                    @ApiParam(required = false, value = "是否最新", name = "skdTime")
                                        @RequestParam(value = "skdTime", required = false) String skdTime,
                                    @ApiParam(required = false, value = "是否按点赞、好评", name = "likesOrSales")
                                        @RequestParam(value = "likesOrSales", required = false) String likesOrSales,
                                    @ApiParam(required = false, value = "行程状态", name = "status")
                                        @RequestParam(value = "status", required = false) String status,
                                    @ApiParam(required = false, value = "搜索人id", name = "userId")
                                        @RequestParam(value = "userId", required = false) String userId,
                                    Integer currentPage, Integer pageSize) throws Exception {

        logger.info("主页搜索接口：searchContent============================="+searchContent);
        logger.info("主页搜索接口：skdTime============================="+skdTime);
        logger.info("主页搜索接口：likesOrSales============================="+likesOrSales);
        logger.info("主页搜索接口：status============================="+status);
        logger.info("主页搜索接口：currentPage============================="+currentPage);
        logger.info("主页搜索接口：pageSize============================="+pageSize);
        logger.info("主页搜索接口：userId============================="+userId);

        if(StringUtils.isEmpty(searchContent)){
            return JSONResult.errorMsg("未传入搜索内容！");
        }

        //没有显示显示的页码就显示第一页
        if (currentPage == null) {
            //当前页数
            currentPage = 1;
        }

        if (pageSize == null) {
            // 分页的记录个数
            pageSize = PAGE_SIZE;
        }

        // 1、jieba分词工具抽取地址和实体

        // 1.1 数组抽取地址
        String[] nation = { "台湾", "香港", "澳门", "阿根廷", "澳大利亚", "奥地利", "孟加拉", "比利时", "巴西", "缅甸",
                "白俄罗斯", "柬埔寨", "加拿大", "斯里兰卡", "智利", "哥伦比亚", "哥斯达黎加", "克罗地亚", "古巴", "塞浦路斯",
                "捷克", "丹麦", "厄瓜多尔", "埃塞俄比亚", "芬兰", "法国", "巴勒斯坦", "德国", "加纳", "希腊", "匈牙利", "印度",
                "印尼", "以色列", "意大利", "牙买加", "日本", "哈萨克斯坦", "约旦", "肯尼亚", "朝鲜", "韩国", "吉尔吉斯斯坦",
                "老挝", "黎巴嫩", "利比里亚", "立陶宛", "卢森堡", "马达加斯加", "马来西亚", "马尔代夫", "墨西哥", "蒙古国",
                "摩洛哥", "尼泊尔", "荷兰", "新西兰", "尼日利亚", "挪威", "巴基斯坦", "巴拉圭", "秘鲁", "菲律宾", "波兰",
                 "葡萄牙", "卡塔尔", "罗马尼亚", "俄罗斯", "卢旺达", "沙特阿拉伯", "塞尔维亚", "新加坡", "斯洛伐克", "越南",
               "斯洛文尼亚", "南非", "津巴布韦", "西班牙", "瑞典", "瑞士", "叙利亚", "塔吉克斯坦", "泰国", "突尼斯", "土耳其",
                 "乌克兰", "埃及", "英国", "美国", "乌拉圭", "乌兹别克斯坦", "委内瑞拉"};

        Map<String,Integer> countMap = new HashMap<>();
        for(String loc: nation){
            if(searchContent.contains(loc)){
                countMap.put(loc, countMap.getOrDefault(loc,0)+1);
            }
        }
        String loc = "";
        Integer cnt = 0;
        for(Map.Entry<String, Integer> entry : countMap.entrySet()){
            if(entry.getValue()>cnt){
                loc = entry.getKey();
                cnt = entry.getValue();
            }
        }

        // 1.2 jieba抽取关键词
        List<String> keywords = extractWords.keywords(searchContent);
        String keyword = keywords.size()>0?keywords.get(0):"";

        System.out.println("loc:"+loc);
        System.out.println("keyword:"+keyword);

        // 2、插入search_records
        indexService.saveSearchRecords(searchContent, loc, keyword, userId);

        // 3、根据地址和实体去查询 行程标题、描述、地点
        PagedResult result = scheduleService.getSkdsByCondition(keywords, countMap, currentPage, pageSize,
                skdTime, likesOrSales, status);


        return JSONResult.ok(result);

//        return JSONResult.ok();
    }

}
