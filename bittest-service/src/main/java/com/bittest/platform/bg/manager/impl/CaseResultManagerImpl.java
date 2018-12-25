package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.CaseResultMapper;
import com.bittest.platform.bg.domain.po.CaseResult;
import com.bittest.platform.bg.manager.CaseResultManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2018-08-27.
 */
@Service("caseResultManager")
public class CaseResultManagerImpl implements CaseResultManager {

    @Autowired
    private CaseResultMapper caseResultMapper;


    @Override
    public int save(CaseResult result) {
        return caseResultMapper.save(result);
    }

    @Override
    public int delete(CaseResult result) {
        return caseResultMapper.delete(result);
    }

    @Override
    public int deleteByTask(CaseResult result) {
        return caseResultMapper.deleteByTask(result);
    }

    @Override
    public CaseResult queryObject(CaseResult result) {
        return caseResultMapper.queryObject(result);
    }

    @Override
    public List<CaseResult> queryList(CaseResult result) {
        return caseResultMapper.queryList(result);
    }

    @Override
    public int queryTotal(CaseResult result) {
        return caseResultMapper.queryTotal(result);
    }
}
