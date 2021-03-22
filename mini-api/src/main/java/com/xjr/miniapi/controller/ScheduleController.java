package com.xjr.miniapi.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.xjr.pojo.Schedules;
import com.xjr.pojo.SkdImage;
import com.xjr.pojo.UsersAddress;
import com.xjr.pojo.vo.skdVO;
import com.xjr.service.ScheduleService;
import com.xjr.service.SkdImageService;
import com.xjr.utils.JSONResult;
import com.xjr.utils.PagedResult;
import com.xjr.utils.SqlDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "行程业务接口", tags = {"行程管理业务的controller"})
@RequestMapping("/schedule")
public class ScheduleController extends BaseController{

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SkdImageService skdImageService;

    // 日志对象
    private static final Logger logger = Logger.getLogger(ScheduleController.class);


    /**
     * 搜索接口考虑：
     * 1、输入为空，首页初次加载行程卡片。默认搜索规则：（1）按点赞数、短评数降序；（2）分页4-6张卡片。
     * 2、输入不为空。
     * （1）先过滤敏感词DFA；
     * （2）对搜索内容存储，再进行jieba分词，获取地名、实体内容（作为热搜词），存入searchRecords表。跟skd的title、desc还有product的title匹配；
     * （3）排序依旧按默认搜索规则；（后续按3:1比例输出付费代购的行程）
     */


    /**
     * 1、获取用户已发布的行程 status = 0、1
     * 2021/3/19 增加分页查询进该接口
     */
    @ApiOperation(value = "获取用户已发布的行程", httpMethod = "GET", notes = "获取用户已发布的行程")
    @RequestMapping(value = "/published", method = RequestMethod.GET)
    public JSONResult getPublishedSchedule(@ApiParam(required = true, value = "用户id", name = "userId")
                                    @RequestParam(value = "userId", required = true) String userId, Integer currentPage, Integer pageSize)
    {
        logger.info("获取用户已发布的行程接口：userId============================="+userId);
        logger.info("获取用户已发布的行程接口：currentPage============================="+currentPage);
        logger.info("获取用户已发布的行程接口：pageSize============================="+pageSize);
        if(userId == null || userId.equals("")){
            return JSONResult.errorMsg("请检查传入对象！");
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

        PagedResult result = scheduleService.getPublishedSkds(userId, currentPage, pageSize);

//        // 根据userId和skd_status查询所有行程
//        List<Schedules> list_doing = scheduleService.selectSkdByUIdAndStatus(userId, (byte) 0);
//
//        List<Schedules> list_done = scheduleService.selectSkdByUIdAndStatus(userId, (byte) 1);
//
//        // 未完成的行程在前面
//        list_doing.addAll(list_done);

        return JSONResult.ok(result);
    }

    /**
     * 2、获取用户草稿的行程 status = 2
     */
    @ApiOperation(value = "获取用户草稿的行程", httpMethod = "GET", notes = "获取用户草稿的行程")
    @RequestMapping(value = "/draft", method = RequestMethod.GET)
    public JSONResult getDraftSchedule(@ApiParam(required = true, value = "用户id", name = "userId")
                                    @RequestParam(value = "userId", required = true) String userId, Integer currentPage, Integer pageSize)
    {
        logger.info("获取用户草稿行程接口：userId============================="+userId);
        logger.info("获取用户草稿行程接口：currentPage============================="+currentPage);
        logger.info("获取用户草稿行程接口：pageSize============================="+pageSize);

        if(userId == null || userId.equals("")){
            return JSONResult.errorMsg("请检查传入对象！");
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

        PagedResult result = scheduleService.getDraftSkds(userId, currentPage, pageSize);

        return JSONResult.ok(result);
    }


    /**
     * 3、提交用户行程接口（多个图片上传使用另外的接口、删除接口另外编写、行程完成接口另外编写）
     * 第一张图片上传成功后，发送request请求更新行程主图片
     */
    @ApiOperation(value = "提交用户行程", httpMethod = "POST", notes = "提交用户行程")
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public JSONResult submitSchedule(@ApiParam(required = true, value = "用户行程view对象", name = "skdVO")
                                    @RequestBody skdVO skdVO) throws ParseException {
        logger.info("提交用户行程接口：skdVO============================="+skdVO.toString());

        if(skdVO == null){
            return JSONResult.errorMsg("请检查传入对象！");
        }

        // 设置行程完成时，需要携带skd_id
//        if(skdVO.getStatus() == 1 && skdVO.getId()==null){
//            return JSONResult.errorMsg("行程完成时，没有行程id号！");
//        }
//        if(skdVO.getStatus() == 3 && skdVO.getId()==null){
//            return JSONResult.errorMsg("删除时，没有行程id号！");
//        }

        // 后端二次校验，防止已完成的行程没被前端校验拦截，而发送给后端进行错误修改。
        // 事实上：已完成行程发过来，只会对status进行修改。


        String urls[] = skdVO.getImgList();
        String start_time = skdVO.getStarttime();
        String end_time = skdVO.getEndtime();
        Date sDate = SqlDate.convertDateFromString(start_time);
        Date eDate = SqlDate.convertDateFromString(end_time);

        Schedules skd = new Schedules();
        // 以是否存在skdId区分 新增skd or 更新skd
        if(skdVO.getId() != null){
            skd.setSkdId(skdVO.getId());
        }
        skd.setSkdTitle(skdVO.getTitle());
        skd.setSkdDesc(skdVO.getDesc());
        skd.setSkdDestination(skdVO.getDestination());
        skd.setSkdStarttime(sDate);
        skd.setSkdEndtime(eDate);

        // 第一张图片在服务器的地址
//        String urlAddr = "/" + skdVO.getUid() + "/skdImg/" + urls[0];
//        skd.setSkdImgUrl(urlAddr);

        // status不会出现超出-128/127的数
        // 每次提交status都会被更新
        skd.setSkdStatus((byte) skdVO.getStatus());
        skd.setUid(skdVO.getUid());

        // 将封装好的schedules、urls数组传递给service
        String skdId = scheduleService.submitSkd(skd);

        // 将生成的skdId返回给前端
        return JSONResult.ok(skdId);
//        return JSONResult.ok();
    }


    /**
     * 4、行程删除接口，删除按钮触发
     */
    @ApiOperation(value = "删除用户行程", httpMethod = "POST", notes = "删除用户行程")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JSONResult delSchedule(@ApiParam(required = true, value = "用户行程id", name = "skdId")
                                     @RequestParam String skdId) {

        if(skdId == null || skdId.equals("")){
            return JSONResult.errorMsg("请检查传入对象！");
        }

        // 修改status状态为3即可,同时修改skd_image表中所有图片状态为1

        logger.info("删除行程接口：删除的行程skdId============================="+skdId);

        // 1、删除行程和行程图片
        scheduleService.deleteSkd(skdId);

        //2、删除虚拟目录中的图片，直接调用删除方法
        String uploadFilePath = FILE_SPACE + "/" + skdId;
//        File file = new File(uploadFilePath);
//        deleteVirtualDirFile(file);

        return JSONResult.ok();
    }

    /**
     * 5、完成行程接口
     */
    @ApiOperation(value = "完成行程接口", httpMethod = "POST", notes = "完成行程接口")
    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    public JSONResult finSchedule(@ApiParam(required = true, value = "用户行程id", name = "skdId")
                                     @RequestParam String skdId) {

        if(skdId == null || skdId.equals("")){
            return JSONResult.errorMsg("请检查传入对象！");
        }

        logger.info("完成行程接口：完成进度的行程skdId============================="+skdId);

        scheduleService.finishSkd(skdId);


        return JSONResult.ok();
    }

    /**
     * 6、上传行程图片接口 skd_image
     * @param skdId
     * @param files
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "用户上传多张行程图片", httpMethod = "POST", notes = "用户上传多张行程图片")
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public JSONResult uploadImg(String skdId, @RequestParam("file") MultipartFile[] files) throws Exception {

        logger.info("上传行程图片接口：行程skdId============================="+skdId);

        if (StringUtils.isBlank(skdId)) {
            return JSONResult.errorMsg("行程id不能为空！");
        }

        // 修改时fileName是相同的
        String fileName = files[0].getOriginalFilename();
        logger.info("上传行程图片接口：行程图片url名称============================="+fileName);

        //  保存到数据库中的相对路径
        String uploadPathDB = "/" + skdId + "/img";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;

        try {
            if (StringUtils.isNoneBlank(fileName)) {
                //文件上传的最终保存路径

                // 每个文件都使用对应类别的文件夹schedules
                String finalImgPath = FILE_SPACE +"/schedules"+ uploadPathDB + "/" + fileName;
                //设置数据库保存的路径
                uploadPathDB += ("/" + fileName);

                File outFile = new File(finalImgPath);
                if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                    //创建父文件夹
                    outFile.getParentFile().mkdirs();
                }

                fileOutputStream = new FileOutputStream(outFile);
                inputStream = files[0].getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);

            }
        } catch (IOException e) {
            e.printStackTrace();
            return JSONResult.errorMsg("上传出错！");
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }

        // 2021/3/17 行程图片的删除再说todo ---> deleteVirtualDirFile(String)

        // 将图片按行程id插入到skd_image表中，并添加状态
        SkdImage skdImage = new SkdImage();
        skdImage.setSkdId(skdId);
        skdImage.setUrl(uploadPathDB);
        skdImage.setIsDel(0);

        // 已存在就不需要插入图片url
        skdImageService.insertSkdImg(skdImage);

        // 返回图片存储地址给前端
        return JSONResult.ok(uploadPathDB);
    }

    // 删除已有的图片逻辑：前端进入修改界面时，根据图片url显示图片，也根据这个唯一url对skdimage进行修改状态

    // 删除虚拟目录下文件的方法
    public void deleteVirtualDirFile(File imgFile){

        try{
            if (imgFile.exists()) {//判断文件是否存在
                if (imgFile.isFile()) {//判断是否是文件
                    imgFile.delete();//删除文件
                } else if (imgFile.isDirectory()) {//否则如果它是一个目录
                    File[] files = imgFile.listFiles();//声明目录下所有的文件 files[];
                    for (int i = 0;i < files.length;i ++) {//遍历目录下所有的文件
                        this.deleteVirtualDirFile(files[i]);//把每个文件用这个方法进行迭代
                    }
                    imgFile.delete();//删除文件夹
                }
            } else {
                System.out.println("所删除的文件不存在");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 7、更新行程主图片接口（提交图片时第一张图片插入schedules表）
     */
    @ApiOperation(value = "更新行程主图片接口", httpMethod = "POST", notes = "更新行程主图片接口")
    @RequestMapping(value = "/submitMainUrl", method = RequestMethod.POST)
    public JSONResult submitMainUrl(@ApiParam(required = true, value = "用户行程id", name = "skdId")
                                    @RequestParam String skdId,
                                    @ApiParam(required = true, value = "主图片url", name = "uploadFilePath")
                                    @RequestParam String uploadFilePath) {

        logger.info("更新行程主图片接口：行程skdId============================="+skdId);

        logger.info("更新行程主图片接口：主图片url============================="+uploadFilePath);

        // 根据skdId更新主图片url
        scheduleService.submitMainUrl(skdId, uploadFilePath);

        return JSONResult.ok();
    }


    /**
     * 8、判断时间段是否合法的接口
     */
    @ApiOperation(value = "判断时间段是否合法的接口", httpMethod = "POST", notes = "判断时间段是否合法的接口")
    @RequestMapping(value = "/validTimeInterval", method = RequestMethod.POST)
    public JSONResult validTimeInterval(@ApiParam(required = true, value = "用户id", name = "userId")
                                    @RequestParam(value = "userId", required = true) String userId,
                                    @ApiParam(required = true, value = "行程起始时间", name = "startTime")
                                    @RequestParam String startTime,
                                    @ApiParam(required = true, value = "行程结束时间", name = "endTime")
                                    @RequestParam String endTime) {

        logger.info("判断时间段是否合法接口：userId============================="+userId);

        logger.info("判断时间段是否合法接口：行程起始时间============================="+startTime);

        logger.info("判断时间段是否合法接口：行程结束时间============================="+endTime);

        Boolean isValid = scheduleService.isValidTimeInterval(userId, startTime, endTime);

        return JSONResult.ok(isValid);
    }

    /**
     * 9、按skdId查询行程信息的接口
     */
    @ApiOperation(value = "按skdId查询行程信息的接口", httpMethod = "GET", notes = "按skdId查询行程信息的接口")
    @RequestMapping(value = "/selectSkd", method = RequestMethod.GET)
    public JSONResult selectSkd(@ApiParam(required = true, value = "用户行程id", name = "skdId")
                                    @RequestParam String skdId){

        logger.info("查询行程信息接口：行程skdId============================="+skdId);

        Schedules schedules = scheduleService.selectSkdByPrimaryKey(skdId);

        return JSONResult.ok(schedules);
    }

    /**
     * 10、按skdId查询所有行程图片的接口
     */
    @ApiOperation(value = "按skdId查询所有行程图片的接口", httpMethod = "GET", notes = "按skdId查询所有行程图片的接口")
    @RequestMapping(value = "/selectAllSkdImg", method = RequestMethod.GET)
    public JSONResult selectAllSkdImg(@ApiParam(required = true, value = "用户行程id", name = "skdId")
                                @RequestParam String skdId){

        logger.info("按skdId查询所有行程图片的接口：行程skdId============================="+skdId);

        List<String> urls = scheduleService.selectAllImgBySkdId(skdId);

        return JSONResult.ok(urls);
    }

    // 修改行程时需要删除一些图片。skd.deleteUpdateImg(skdId, url) 删除虚拟目录中的图片url根据schedules/skdId/img/url
    /**
     * 11、修改行程和发布草稿行程，需要改动上传的图片。根据skdId、url删除skd_image中图片、schedules主图片、虚拟目录中图片。
     * 每次只删除一张图片！
     */
    @ApiOperation(value = "删除修改的行程图片接口", httpMethod = "POST", notes = "删除修改的行程图片接口")
    @RequestMapping(value = "/deleteUpdateImg", method = RequestMethod.POST)
    public JSONResult deleteUpdateImg(@ApiParam(required = true, value = "用户行程id", name = "skdId")
                                      @RequestParam String skdId,
                                      @ApiParam(required = true, value = "行程图片url", name = "url")
                                      @RequestParam String url){

        logger.info("删除修改的行程图片接口：行程skdId============================="+skdId);
        logger.info("删除修改的行程图片接口：行程图片url============================="+url);

        // 1、删除skd_image中图片
        skdImageService.deleteImgByUrl(url);

        // 2、删除schedules中主图片（如果命中url的话）
        scheduleService.deleteUrlIfExist(url);

        // 3、删除虚拟目录中的图片
        String uploadFilePath = FILE_SPACE + url;
//        File file = new File(uploadFilePath);
//        deleteVirtualDirFile(file);

        return JSONResult.ok();
    }


}
