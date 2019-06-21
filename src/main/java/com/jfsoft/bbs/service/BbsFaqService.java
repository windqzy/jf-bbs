package com.jfsoft.bbs.service;


import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.entity.BbsFaqEntity;

import java.util.List;

public interface BbsFaqService extends IService<BbsFaqEntity> {

    List<BbsFaqEntity> getFaqList(Integer typeId);

}
