package com.xjr.miniapi.controller;

import com.xjr.pojo.ScheduleDetails;
import com.xjr.pojo.SkdDetailImage;
import com.xjr.pojo.SkdImage;
import com.xjr.pojo.vo.skdDetailVO;
import com.xjr.service.SkdDetailImageService;
import com.xjr.service.SkdDetailService;
import com.xjr.utils.JSONResult;
import com.xjr.utils.SqlDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "行程日记业务接口", tags = {"行程日记管理业务的controller"})
@RequestMapping("/scheduleDetail")
public class SkdDetailController extends BaseController{

    @Autowired
    private SkdDetailService skdDetailService;

    @Autowired
    private SkdDetailImageService skdDetailImageService;

    // 日志对象
    private static final Logger logger = Logger.getLogger(SkdDetailController.class);

    /**
     * 1、获取用户已发布行程中，所有的行程日记的接口。
     * ps：草稿行程不能发布行程日记；已完成行程不可以修改行程日记
     *
     * @param skdId
     * @return
     */
    @ApiOperation(value = "获取用户已发布行程中，所有的行程日记的接口", httpMethod = "GET", notes = "获取用户已发布行程中，所有的行程日记的接口")
    @RequestMapping(value = "/allSkdDetailsBySkdId", method = RequestMethod.GET)
    public JSONResult getPublishedScheduleDetail(@ApiParam(required = true, value = "行程id", name = "skdId")
                                           @RequestParam(value = "skdId", required = true) String skdId){

        logger.info("获取用户已发布行程中，所有的行程日记的接口：skdId============================="+skdId);
        if(skdId == null || skdId.equals("")){
            return JSONResult.errorMsg("请检查传入skdId！");
        }

        List<ScheduleDetails> list = skdDetailService.getAllSkdDetatilFromSkd(skdId);

        return JSONResult.ok(list);
    }


    /**
     * 2、只负责图片上传以外的日记上传。 新增和修改都是该接口，区别为skdDetailVO对象的skdDetailId是否为空
     * @param skdDetailVO
     * @return
     */
    @ApiOperation(value = "提交行程日记的接口", httpMethod = "POST", notes = "提交行程日记的接口")
    @RequestMapping(value = "/submitSkdDetails", method = RequestMethod.POST)
    public JSONResult submitScheduleDetail(@ApiParam(required = true, value = "日记的view对象", name = "skdDetailVO")
                                               @RequestBody skdDetailVO skdDetailVO) throws ParseException {

        logger.info("提交行程日记的接口：skdDetailVO============================="+skdDetailVO.toString());

        ScheduleDetails scheduleDetails = new ScheduleDetails();
        scheduleDetails.setSkdDetailArticle(skdDetailVO.getArticle());
        scheduleDetails.setSkdDetailPlace(skdDetailVO.getLoc());

        // String 转 Date
        Date time = SqlDate.convertDateFromString(skdDetailVO.getTimepoint());
        scheduleDetails.setSkdDetailTimepoint(time);

        // 插入或修改时进度active是根据传入的status
        Integer status = skdDetailVO.getStatus();
        if(status == null) {
            status = 0;
        }
        scheduleDetails.setSkdDetailActive(status);
        scheduleDetails.setSkdId(skdDetailVO.getSkdId());

        // 将生成的skdDetailId传给前端
        String skdDetailId = skdDetailService.submitSkdDetail(scheduleDetails);

        return JSONResult.ok(skdDetailId);
    }

    /**
     * 3、上传行程日记图片接口 skd_detail_image表
     * @param skdDetailId
     * @param files
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "用户上传多张日记图片", httpMethod = "POST", notes = "用户上传多张日记图片")
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public JSONResult uploadImg(String skdDetailId, @RequestParam("file") MultipartFile[] files) throws Exception {

        logger.info("上传多张日记图片接口：行程日记id,skdDetailId============================="+skdDetailId);

        if (StringUtils.isBlank(skdDetailId)) {
            return JSONResult.errorMsg("日记id不能为空！");
        }

        // 修改时fileName是相同的
        String fileName = files[0].getOriginalFilename();
        logger.info("上传行程图片接口：行程图片url名称============================="+fileName);

        //  保存到数据库中的相对路径
        String uploadPathDB = "/" + skdDetailId + "/img";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;

        try {
            if (StringUtils.isNoneBlank(fileName)) {
                //文件上传的最终保存路径

                // 行程日记文件夹schedules-detail，每个文件都使用对应日记id的文件夹
                String finalImgPath = FILE_SPACE +"/schedules-detail"+ uploadPathDB + "/" + fileName;
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

        // 之前上传返回图片存储地址给前端，是因为需要使用返回的第一张图片作为行程主图片。
        // 这里的行程日记并不需要，所以每次一张的存储到数据库即可。

        SkdDetailImage skdDetailImage = new SkdDetailImage();
        skdDetailImage.setIsDel(0);
        skdDetailImage.setUrl(uploadPathDB);
        skdDetailImage.setSkdDetailId(skdDetailId);

        // 2021/3/21 新增的时候不需要管url重复，修改的时候可能上传重复的url

        skdDetailImageService.insertSkdDetailImg(skdDetailImage);

        return JSONResult.ok();
    }

    /**
     * 4、判断时间点选择是否合法的接口
     */
    @ApiOperation(value = "判断时间点选择是否合法的接口", httpMethod = "POST", notes = "判断时间点选择是否合法的接口")
    @RequestMapping(value = "/validTimePoint", method = RequestMethod.POST)
    public JSONResult validTimePoint(@ApiParam(required = true, value = "行程id", name = "skdId")
                                        @RequestParam(value = "skdId", required = true) String skdId,
                                        @ApiParam(required = true, value = "行程日记时间点", name = "timepoint")
                                        @RequestParam String timepoint) {

        logger.info("判断时间点选择是否合法接口：skdId============================="+skdId);

        logger.info("判断时间点选择是否合法接口：行程日记时间点============================="+timepoint);

        Boolean flag = skdDetailService.isTimePointValid(skdId, timepoint);

        return JSONResult.ok(flag);
    }


    /**
     * 5、获取行程日记所有图片url的接口
     */
    @ApiOperation(value = "获取行程日记所有图片url的接口", httpMethod = "GET", notes = "获取行程日记所有图片url的接口")
    @RequestMapping(value = "/getSkdDetailUrls", method = RequestMethod.GET)
    public JSONResult getSkdDetailUrls(@ApiParam(required = true, value = "行程日记id", name = "skdDetailId")
                                     @RequestParam(value = "skdDetailId", required = true) String skdDetailId) {

        logger.info("获取行程日记所有图片url的接口：skdDetailId============================="+skdDetailId);

        List<String> urls = skdDetailImageService.selectAllUrlsBySkdDetailId(skdDetailId);

        return JSONResult.ok(urls);
    }


    /**
     * 6、设置行程日记的进度状态的接口
     */
    @ApiOperation(value = "设置行程日记的进度状态的接口", httpMethod = "POST", notes = "设置行程日记的进度状态的接口")
    @RequestMapping(value = "/setSkdDetailStatus", method = RequestMethod.POST)
    public JSONResult setSkdDetailStatus(@ApiParam(required = true, value = "行程日记id", name = "skdDetailId")
                                         @RequestParam(value = "skdDetailId", required = true) String skdDetailId,
                                         @ApiParam(required = true, value = "行程日记进度状态", name = "status")
                                         @RequestParam(value = "status", required = true) Integer status) {

        logger.info("设置行程日记的进度状态的接口：skdDetailId============================="+skdDetailId);

        logger.info("设置行程日记的进度状态的接口：status============================="+status);

        skdDetailService.setSkdDetailStatus(skdDetailId, status);


        return JSONResult.ok();
    }


}
