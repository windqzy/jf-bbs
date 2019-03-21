package com.jfsoft.bbs.controller;

import com.jfsoft.bbs.common.utils.DateUtil;
import com.jfsoft.bbs.common.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @Date 2019/2/26  16:04
 */
@RestController
@RequestMapping("/upload")
public class UploadController {


    /**
     * 存储路径
     */
    @Value("${app.filePath}")
    private String filePath;

    /**
     * 映射路径
     */
    @Value("${app.staticUrl}")
    private String staticUrl;

    @PostMapping(value = "/file")
    public R uFile(MultipartFile file) {

        Map<String, Object> result = new HashMap<>();

        //文件名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
        /*上传文件按照时间保存*/
        Date date = new Date();
        String dateStr = DateUtil.format(date, "yyyyMMdd");

        String absPath = "";
        absPath = filePath + dateStr.substring(0, 4) + "/" + dateStr;

        String url = "";
        url = staticUrl + dateStr.substring(0, 4) + "/" + dateStr + "/" + fileName;
        if (!StringUtils.isBlank(suffix)) {
            Map<String, Object> map = new HashMap<>();
            if (!file.isEmpty()) {
                File imgFile = new File(absPath);
                if (!imgFile.exists()) imgFile.mkdirs();
                try {
                    file.transferTo(new File(absPath + "/" + fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                result.put("src", url);
                result.put("title", "jfyt");
                return R.ok("上传成功").put("data", result);
            } else {
                return R.ok("文件上传失败");
            }
        } else {
            return R.ok("上传文件格式错误");
        }
    }


    @PostMapping(value = "/file2")
    public R uFile2(MultipartFile file) {

        Map<String, Object> result = new HashMap<>();

        //文件名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
        /*上传文件按照时间保存*/
        Date date = new Date();
        String dateStr = DateUtil.format(date, "yyyyMMdd");

        String absPath = "";
        absPath = filePath + dateStr.substring(0, 4) + "/" + dateStr;

        String url = "";
        List<String> urlList = new LinkedList<>();
        url = staticUrl + dateStr.substring(0, 4) + "/" + dateStr + "/" + fileName;
        if (!StringUtils.isBlank(suffix)) {
            Map<String, Object> map = new HashMap<>();
            if (!file.isEmpty()) {
                File imgFile = new File(absPath);
                if (!imgFile.exists()) imgFile.mkdirs();
                try {
                    file.transferTo(new File(absPath + "/" + fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                urlList.add(url);
                return R.ok("上传成功").put("errno", 0).put("data", urlList);
            } else {
                return R.error("文件上传失败").put("errno", 1);
            }
        } else {
            return R.error("上传文件格式错误").put("errno", 1);
        }
    }

    @RequestMapping("/del")
    public R delFile(@RequestBody String imagePath) {
        String relPath = filePath + imagePath.replace(imagePath, staticUrl);
        return R.ok();
    }
}
