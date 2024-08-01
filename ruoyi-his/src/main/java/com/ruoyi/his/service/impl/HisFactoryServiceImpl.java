package com.ruoyi.his.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.domain.HisFactory;
import com.ruoyi.his.mapper.HisFactoryMapper;
import com.ruoyi.his.service.IHisFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
生产厂家service
 */
@Service
public class HisFactoryServiceImpl implements IHisFactoryService {


    @Autowired
    private HisFactoryMapper hisFactoryMapper;

    @Override
    public List<HisFactory> selectHisFactoryList(HisFactory hisFactory) {
        return hisFactoryMapper.selectHisFactoryList(hisFactory);
    }

    @Override
    public HisFactory selectHisFactoryById(Long factoryId) {
        return hisFactoryMapper.selectHisFactoryById(factoryId);
    }

    @Override
    public int insertHisFactory(HisFactory hisFactory) {
        return hisFactoryMapper.insertHisFactory(hisFactory);
    }

    @Override
    public int updateHisFactory(HisFactory hisFactory) {

        return hisFactoryMapper.updateHisFactory(hisFactory);
    }

    @Override
    public int deleteHisFactoryByFactoryIds(Long[] factoryIds) {
        return hisFactoryMapper.deleteHisFactoryByFactoryIds(factoryIds);
    }

    @Override
    public int deleteHisFactoryByFactoryId(Long factoryId) {
        return hisFactoryMapper.deleteHisFactoryByFactoryId(factoryId);
    }

    @Override
    public String checkFactoryNameUnique(HisFactory hisFactory) {
        Long factoryId= StringUtils.isNull(hisFactory.getFactoryId())?-1L:hisFactory.getFactoryId();
        HisFactory info=hisFactoryMapper.checkFactoryNameUnique(hisFactory.getFactoryName());

        if(StringUtils.isNotNull(info)&&info.getFactoryId().longValue()!=factoryId.longValue()){
            return UserConstants.NOT_UNIQUE;//不是独立的厂家名字
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkFactoryNoUnique(HisFactory hisFactory) {
        Long factoryId= StringUtils.isNull(hisFactory.getFactoryId())?-1L:hisFactory.getFactoryId();
        HisFactory info=hisFactoryMapper.checkFactoryNoUnique(hisFactory.getFactoryNo());

        if(StringUtils.isNotNull(info)&&info.getFactoryId().longValue()!=factoryId.longValue()){
            return UserConstants.NOT_UNIQUE;//不是独立的厂家编号
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkKeywordUnique(HisFactory hisFactory) {
        Long factoryId= StringUtils.isNull(hisFactory.getFactoryId())?-1L:hisFactory.getFactoryId();
        HisFactory info=hisFactoryMapper.checkKeywordUnique(hisFactory.getKeyword());

        if(StringUtils.isNotNull(info)&&info.getFactoryId().longValue()!=factoryId.longValue()){
            return UserConstants.NOT_UNIQUE;//不是独立的查询
        }
        return UserConstants.UNIQUE;
    }
}
