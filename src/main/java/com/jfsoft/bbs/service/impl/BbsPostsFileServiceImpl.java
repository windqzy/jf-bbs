package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.dao.BbsPostsFileDao;
import com.jfsoft.bbs.entity.BbsPostsFileEntity;
import com.jfsoft.bbs.service.BbsPostsFileService;
import org.springframework.stereotype.Service;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @Date 2019/5/22  15:13
 */
@Service("bbsPostsFileService")
public class BbsPostsFileServiceImpl extends ServiceImpl<BbsPostsFileDao, BbsPostsFileEntity> implements BbsPostsFileService {
}
