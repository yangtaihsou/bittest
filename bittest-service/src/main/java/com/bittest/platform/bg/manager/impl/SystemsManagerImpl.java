package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.SystemsMapper;
import com.bittest.platform.bg.domain.po.Systems;
import com.bittest.platform.bg.manager.SystemsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-25 11:16:35
 */
@Service("systemsService")
public class SystemsManagerImpl implements SystemsManager {
    @Autowired
    private SystemsMapper systemsMapper;

    @Override
    public Systems queryObject(Systems systems) {
        return systemsMapper.queryObject(systems);
    }

    @Override
    public List<Systems> queryList(Systems systems) {
        return systemsMapper.queryList(systems);
    }

    @Override
    public int queryTotal(Systems systems) {
        return systemsMapper.queryTotal(systems);
    }

    @Override
    public int save(Systems systems) {
        return systemsMapper.save(systems);
    }

    @Override
    public int update(Systems systems) {
        return systemsMapper.update(systems);
    }

    @Override
    public int delete(Systems systems) {
        return systemsMapper.delete(systems);
    }

    @Override
    public List<Systems> querySystemList(Systems systems) {
        return systemsMapper.querySystemList(systems);
    }

}
