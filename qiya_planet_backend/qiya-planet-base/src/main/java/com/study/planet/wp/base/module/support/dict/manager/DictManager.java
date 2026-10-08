package com.study.planet.wp.base.module.support.dict.manager;

import com.study.planet.wp.base.common.util.SmartBeanUtil;
import com.study.planet.wp.base.constant.CacheKeyConst;
import com.study.planet.wp.base.module.support.dict.dao.DictDao;
import com.study.planet.wp.base.module.support.dict.dao.DictDataDao;
import com.study.planet.wp.base.module.support.dict.domain.entity.DictDataEntity;
import com.study.planet.wp.base.module.support.dict.domain.entity.DictEntity;
import com.study.planet.wp.base.module.support.dict.domain.vo.DictDataVO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 数据字典 缓存
 *
 * @Author 1024创新实验室-主任-卓大
 * @Date 2025-03-25 22:25:04
 * @Copyright <a href="https://1024lab.net">1024创新实验室</a>
 */

@Service
public class DictManager {

    @Resource
    private DictDao dictDao;

    @Resource
    private DictDataDao dictDataDao;


    /**
     * 获取字典
     */
    @Cacheable(value = CacheKeyConst.Dict.DICT_DATA, key = "#dictCode + '_' + #dataValue")
    public DictDataVO getDictData(String dictCode, String dataValue) {
        DictEntity dictEntity = dictDao.selectByCode(dictCode);
        if (dictEntity == null) {
            return null;
        }

        DictDataEntity dictDataEntity = dictDataDao.selectByDictIdAndValue(dictEntity.getDictId(), dataValue);
        return SmartBeanUtil.copy(dictDataEntity, DictDataVO.class);
    }

}
