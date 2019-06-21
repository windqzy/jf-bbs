package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.entity.BbsPostsFileEntity;
import com.jfsoft.bbs.service.BbsPostsFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Paths;

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

    private final ResourceLoader resourceLoader;

    @Autowired
    public DownloacController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 文件下载
     *
     * @Author Lxd
     * @Date 14:45 2019/6/20
     * @Param [response, postsId]
     * @Return
     **/
    @GetMapping("/{fileId}")
    public void downloadFile(@PathVariable Integer fileId, HttpServletResponse response) {
        BbsPostsFileEntity fileEntity = bbsPostsFileService.selectById(fileId);
        String urlPath = fileEntity.getUrl();
        String relPath = urlPath.replace(staticUrl, filePath);
        String fileName = fileEntity.getName();
        if (fileName != null) {
            //设置文件路径
            File file = new File(relPath);
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            //File file = new File(realPath , fileName);
            try {
                if (file.exists()) {
                    response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
                    response.setContentType("application/octet-stream");// 设置强制下载不打开
                    response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));// 设置文件名
                    response.setCharacterEncoding("UTF-8");
                    response.setContentLength((int) file.length());
                    response.setHeader("Pragma", "No-cache");
                    response.setHeader("Cache-Control", "No-cache");
                    response.setDateHeader("Expires", 0);
                    byte[] buffer = new byte[1024];
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
//    @GetMapping(value = "/{fileId}")
//    public ResponseEntity<?> getFile(@PathVariable Integer fileId) {
//        try {
//            BbsPostsFileEntity fileEntity = bbsPostsFileService.selectById(fileId);
//            String urlPath = fileEntity.getUrl();
//            String relPath = urlPath.replace(staticUrl, filePath);
//            String fileName = fileEntity.getName();
//            String path = Paths.get(relPath, fileName).toString();
//            Resource resource = resourceLoader.getResource("file:" + path);
//            return ResponseEntity.ok(resource);
//        } catch (Exception e) {
//            throw e;
//        }
//    }

}

