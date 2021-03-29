package com.xjr.miniapi.controller;

import com.xjr.pojo.ProductImage;
import com.xjr.pojo.ScheduleDetails;
import com.xjr.pojo.vo.productVO;
import com.xjr.service.ScheduleProductService;
import com.xjr.utils.JSONResult;
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
import java.util.List;

@RestController
@Api(value = "行程商品业务接口", tags = {"行程商品管理业务的controller"})
@RequestMapping("/scheduleProduct")
public class SkdProductController extends BaseController{

    @Autowired
    private ScheduleProductService scheduleProductService;

    // 日志对象
    private static final Logger logger = Logger.getLogger(SkdProductController.class);

    /**
     * 1、获取用户行程中（草稿也可以有发布的商品，草稿行程不能上下架商品），所有的行程商品的接口。
     *
     * @param skdId
     * @return
     */
    @ApiOperation(value = "获取用户行程中，所有的行程商品的接口", httpMethod = "GET", notes = "获取用户行程中，所有的行程商品的接口")
    @RequestMapping(value = "/allSkdProductsBySkdId", method = RequestMethod.GET)
    public JSONResult getScheduleProducts(@ApiParam(required = true, value = "行程id", name = "skdId")
                                                 @RequestParam(value = "skdId", required = true) String skdId){

        logger.info("获取用户行程中，所有的行程商品的接口：skdId============================="+skdId);
        if(skdId == null || skdId.equals("")){
            return JSONResult.errorMsg("请检查传入skdId！");
        }

        List<productVO> list = scheduleProductService.getAllProductVOBySkdId(skdId);

        return JSONResult.ok(list);
    }


    /**
     * 2、提交行程商品的接口。包括新增、修改行程商品信息（不包括对图片的新增、修改、删除）
     * ps：包括一件商品的多个商品规格、stock设置
     *
     * @param productVO
     * @return
     */
    @ApiOperation(value = "提交行程商品的接口", httpMethod = "POST", notes = "提交行程商品的接口")
    @RequestMapping(value = "/submitSkdProducts", method = RequestMethod.POST)
    public JSONResult submitScheduleProducts(@ApiParam(required = true, value = "行程商品视图模型", name = "productVO")
                                                 @RequestBody productVO productVO){

        logger.info("提交行程商品的接口：productVO============================="+productVO.toString());
        if(productVO == null){
            return JSONResult.errorMsg("请检查传入productVO！");
        }

        // 插入或修改行程商品信息
        String pid = scheduleProductService.submitProducts(productVO);

        return JSONResult.ok(pid);
    }

    /**
     * 3、设置行程商品的上下架状态
     * @param pidList
     * @param status
     * @return
     */
    @ApiOperation(value = "设置行程商品上下架状态的接口", httpMethod = "POST", notes = "设置行程商品上下架状态的接口")
    @RequestMapping(value = "/setSkdProductStatus", method = RequestMethod.POST)
    public JSONResult setSkdProductStatus(@ApiParam(required = true, value = "行程商品id的列表", name = "pidList")
                                              @RequestParam List<String> pidList,
                                          @ApiParam(required = true, value = "行程商品上下架状态", name = "status")
                                             @RequestParam Integer status) {

        logger.info("设置行程商品上下架状态的接口：pidList============================="+pidList);
        logger.info("设置行程商品上下架状态的接口：status============================="+status);

        if(pidList.size()==0){
            return JSONResult.errorMsg("请检查传入pidList！");
        }

        // 设置行程商品的上下架状态
        scheduleProductService.setProductStatusByPidList(pidList, status);

        return JSONResult.ok();

    }

    /**
     * 4、上传多张商品图片的接口，每次上传一张图片
     * @param pid
     * @param files
     * @return
     */
    @ApiOperation(value = "上传多张商品图片的接口", httpMethod = "POST", notes = "上传多张商品图片的接口")
    @RequestMapping(value = "/uploadProductImg", method = RequestMethod.POST)
    public JSONResult uploadImg(@ApiParam(required = true, value = "商品id", name = "pid")
                                    @RequestParam String pid, @RequestParam("file") MultipartFile[] files) throws IOException {

        logger.info("上传多张商品图片的接口：商品id,pid============================="+pid);

        if (StringUtils.isBlank(pid)) {
            return JSONResult.errorMsg("商品id不能为空！");
        }

        // 修改时fileName是相同的
        String fileName = files[0].getOriginalFilename();
        logger.info("上传行程图片接口：行程图片url名称============================="+fileName);

        //  保存到数据库中的相对路径
        String uploadPathDB = "/" + pid + "/img";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;

        try {
            if (StringUtils.isNoneBlank(fileName)) {
                //文件上传的最终保存路径

                // 行程日记文件夹schedules-detail，每个文件都使用对应日记id的文件夹
                String finalImgPath = FILE_SPACE +"/schedules-product"+ uploadPathDB + "/" + fileName;
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

        // 存入product-image表中
        ProductImage productImage = new ProductImage();
        productImage.setUrl(uploadPathDB);
        productImage.setProductId(pid);
        productImage.setIsDel(0);

        // 新增和更新的插入方法
        scheduleProductService.insertSkdProductImg(productImage);

        return JSONResult.ok();
    }

    /**
     * 5、获取商品名称是否重复
     * @param pname
     * @return
     */
    @ApiOperation(value = "获取商品名称是否重复的接口", httpMethod = "GET", notes = "获取商品名称是否重复的接口")
    @RequestMapping(value = "/isProductNameValid", method = RequestMethod.GET)
    public JSONResult isProductNameValid(@ApiParam(required = true, value = "商品名称", name = "pname")
                                @RequestParam String pname){

        Boolean isValid = scheduleProductService.isProductNameValid(pname);

        return JSONResult.ok(isValid);
    }


    // 修改商品方法：修改product表、Product_spec表和stock交给submit接口
    // 后端对图片的修改只是提供上传图片接口和删除图片接口

    /**
     * 6、对应前端修改商品时remove图片的删除图片按钮
     * 根据url和pid删除图片（不存在该url不要紧，存在就删除）
     *
     * @param url
     * @param pid
     * @return
     */
    @ApiOperation(value = "删除商品图片的接口", httpMethod = "POST", notes = "删除商品图片的接口")
    @RequestMapping(value = "/deleteProductImg", method = RequestMethod.POST)
    public JSONResult deleteProductImg(@ApiParam(required = true, value = "商品图片", name = "url")
                                           @RequestParam String url,
                                       @ApiParam(required = true, value = "商品id号", name = "pid")
                                           @RequestParam String pid){

        logger.info("删除商品图片的接口：商品图片url============================="+url);

        logger.info("删除商品图片的接口：商品id号pid============================="+pid);

        if(url == null || url.equals("")){
            return JSONResult.errorMsg("请检查商品图片url");
        }
        if(pid == null || pid.equals("")){
            return JSONResult.errorMsg("请检查商品id号");
        }


        // 1、删除product_image中图片
        scheduleProductService.deleteProductImageByUrlAndPid(url, pid);

        // 2、删除虚拟目录中的图片
        String uploadFilePath = FILE_SPACE + url;
//        File file = new File(uploadFilePath);
//        deleteVirtualDirFile(file);

        return JSONResult.ok();
    }


    // 删除商品接口，根据pid设置product表is_del-1，根据pid设置商品图片表is_del-1，规格表都不需要操作（操作就根据pid删除）

    /**
     * 7、用户在修改界面点击删除按钮响应的接口
     *
     * @param pid
     * @return
     */
    @ApiOperation(value = "删除整个商品的接口", httpMethod = "POST", notes = "删除整个商品的接口")
    @RequestMapping(value = "/deleteSkdProduct", method = RequestMethod.POST)
    public JSONResult deleteSkdProduct(@ApiParam(required = true, value = "商品id号", name = "pid")
                                       @RequestParam String pid){

        logger.info("删除商品图片的接口：商品id号pid============================="+pid);

        if(pid == null || pid.equals("")){
            return JSONResult.errorMsg("请检查商品id号");
        }

        // 1、product表中的is_del置为1
        scheduleProductService.deleteSkdProduct(pid);

        // 2、删除product_image中pid的所有图片
        scheduleProductService.deleteProductImageByPid(pid);

        // 3、删除product_spec中pid的规格
        scheduleProductService.deleteProductSpecByPid(pid);

        // 4、删除stock的pid库存信息
        scheduleProductService.deleteProductStockByPid(pid);

        // 5、删除虚拟目录中的图片 直接删除整个目录
        String uploadFilePath = FILE_SPACE + "/schedules-product/" +pid;
        File file = new File(uploadFilePath);
        deleteVirtualDirFile(file);

        return JSONResult.ok();
    }

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

    //--------------------------------------提供给product.wxml商品详情页面的接口方法---------------------------------------
    // 8、根据product_id查询某个商品的所有信息
    @ApiOperation(value = "查询某个商品的接口", httpMethod = "GET", notes = "查询某个商品的接口")
    @RequestMapping(value = "/productDetail", method = RequestMethod.GET)
    public JSONResult getProductDetail(@ApiParam(required = true, value = "商品id号", name = "pid")
                                       @RequestParam String pid) {

        logger.info("查询某个商品的接口：商品id号pid=============================" + pid);

        productVO productVO = scheduleProductService.getProductVOByPid(pid);

        return JSONResult.ok(productVO);
    }

}
