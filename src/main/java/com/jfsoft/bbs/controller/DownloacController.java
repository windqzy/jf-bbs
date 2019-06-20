package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.entity.BbsPostsFileEntity;
import com.jfsoft.bbs.service.BbsPostsFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @ClassName DownloacController
 * @Description TODO
 * @Author Lxd
 * @Date 2019/6/20 14:12
 * @Version 1.0
 **/
@RestController
@RequestMapping("/download")
public class DownloacController {

    @Autowired
    private BbsPostsFileService bbsPostsFileService;

    @Value("${app.filePath}")
    private String filePath;

    @Value("${app.staticUrl}")
    private String staticUrl;

    /**
     * 文件下载
     *
     * @Author Lxd
     * @Date 14:45 2019/6/20
     * @Param [response, postsId]
     * @Return
     **/
    @GetMapping("/{fileId}")
    public void downLoad(HttpServletResponse response, @PathVariable Integer fileId) {

        BbsPostsFileEntity fileEntity = bbsPostsFileService.selectById(fileId);

        String urlPath = fileEntity.getUrl();
        String downloadName = fileEntity.getName();
//        进行转码后的文件名，用来下载之后的文件名
        try {
            download(response, urlPath, downloadName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void download(HttpServletResponse resp, String urlPath, String downloadName) throws Exception {

        String path = urlPath.replace(staticUrl, filePath);
        File file = new File(path);
        resp.reset();
        resp.setContentType("application/octet-stream");
        resp.setCharacterEncoding("utf-8");
        resp.setContentLength((int) file.length());
        resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(downloadName, "UTF-8"));
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
